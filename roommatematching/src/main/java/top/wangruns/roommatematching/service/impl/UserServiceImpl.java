package top.wangruns.roommatematching.service.impl;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import top.wangruns.roommatematching.dao.UserDao;
import top.wangruns.roommatematching.model.Liking;
import top.wangruns.roommatematching.model.Major;
import top.wangruns.roommatematching.model.School;
import top.wangruns.roommatematching.model.User;
import top.wangruns.roommatematching.service.UserService;
import top.wangruns.roommatematching.utils.Request;
import top.wangruns.roommatematching.utils.Static;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public User findLogin(User u) {
		return userDao.selectByUser(u);
	}

	public boolean isEmailExisted(String email) {
		boolean isEmailExisted=false;
		User result  = userDao.selectByEmail(email);
		if(result!=null) {
			isEmailExisted=true;
		}
		return isEmailExisted;
	}

	public boolean insert(User u) {
		boolean isInsertSuccessful=false;
		int affectedRows=userDao.insert(u);
		if(affectedRows>0) {
			isInsertSuccessful=true;
		}
		return isInsertSuccessful;
	}

	public List<User> getAllRecordsWithLikingFlag(HttpServletRequest request) {
		List<User> userList=userDao.selectAll();
		
		User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		if(user!=null && userList!=null) {
			//获取当前用户的喜欢用户Id列表
			List<Integer> likingUserIdList=userDao.selectLikingUserIds(user.getUserId());
			if(likingUserIdList!=null) {
				for(User u:userList) {
					for(Integer likingUserId:likingUserIdList) {
						if(u.getUserId()==likingUserId) {
							u.setWhetherLiking(true);
							break;
						}
					}
				}
			}
		}
		return userList;
	}

	public List<Integer> getAllUserIdRecords() {
		return userDao.selectAllUserId();
	}

	public boolean isHasPrivilege(HttpServletRequest request) {
		User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		if(user==null) {
			return false;
		}else {
			return true;
		}
			
	}


	public List<School> getAllSchools() {
		return userDao.selectAllSchools();
	}

	public List<Major> getAllMajors() {
		return userDao.selectAllMajors();
	}

	
	public boolean addInfo(HttpServletRequest request, User u, MultipartFile photo) {
		boolean isInsertSuccessful=false;
		User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		//为当前用户添加信息
		user.setUserName(u.getUserName());
		user.setGender(u.getGender());
		user.setSchoolId(u.getSchoolId());
		user.setMajorId(u.getMajorId());
		user.setSelfInfo(u.getSelfInfo());
		user.setExpectedInfo(u.getExpectedInfo());
		String originalFilename=photo.getOriginalFilename();
		String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
		String photoAddress="photo/"+user.getUserId()+suffix;
		saveFile(photo,request.getServletContext().getRealPath(photoAddress));
		user.setPhotoAddress(photoAddress);
		int affectedRows=userDao.updateUserInfo(user);
		if(affectedRows>0) {
			isInsertSuccessful=true;
			userDao.updateActiveStatus(user);
		}
		return isInsertSuccessful;
	}

	private void saveFile(MultipartFile multipartFile, String realFilePath) {
		try {
			InputStream inputStream=multipartFile.getInputStream();
			FileOutputStream fileOutputStream = new FileOutputStream(realFilePath);  
			try {
				int b = 0;  
	            while ((b = inputStream.read()) != -1) {  
	            	fileOutputStream.write(b);  
	            }  
			}finally{
				inputStream.close();  
	            fileOutputStream.close(); 
			}
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	public boolean isHasPrivilegeAndActive(HttpServletRequest request) {
		User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		if(user==null || user.getStatus()==0 ) {
			return false;
		}else {
			return true;
		}
			
	}

	/**
	 * 由于没有得到官方的验证途径，这里简单的使用文本验证
	 */
	public boolean checkUsernameLegal(HttpServletRequest request,String userName) {
		//是否开启验证,若没有开启，则默认身份都合法
		if(!Static.IS_Authorized) {
			return true;
		}
		String legalNameFilePath =this.getClass().getClassLoader().getResource("legalName.txt").getPath();
		System.out.println(legalNameFilePath);
		try {
			BufferedReader br = new BufferedReader(new FileReader(legalNameFilePath));
			String s = null;
			try {
				while((s = br.readLine())!=null){
					String name=s.trim();
					if(name.equals(userName)) {
						System.out.println(name);
						return true;
					}
				}
			}finally {
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean likingChange(HttpServletRequest request, int likingUserId) {
		boolean isCurLiked=true;
		User user=userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		Liking liking=userDao.selectLikingByLiking(new Liking(user.getUserId(),likingUserId));
		if(liking==null) {
			//点击的用户没有被喜欢过
			isCurLiked=false;
			//添加喜欢记录
			userDao.insertLiking(new Liking(user.getUserId(),likingUserId));
		}else {
			//点击的用户已经被喜欢过了，这次再次点击，取消喜欢
			userDao.deleteLikingById(liking.getLikeId());
		}
		//返回改变后的喜欢状态
		return !isCurLiked;
	}

	public List<User> getAdmiringRecordsWithLikingFlag(HttpServletRequest request) {
		List<User> userList=userDao.selectAll();
		List<User> admiringUserList=new ArrayList<User>();
		
		User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		if(user!=null && userList!=null) {
			//获取当前用户的喜欢用户Id列表
			List<Integer> likingUserIdList=userDao.selectLikingUserIds(user.getUserId());
			if(likingUserIdList!=null) {
				for(User u:userList) {
					for(Integer likingUserId:likingUserIdList) {
						if(u.getUserId()==likingUserId) {
							u.setWhetherLiking(true);
							admiringUserList.add(u);
							break;
						}
					}
				}
			}
		}
		return admiringUserList;
	}

	public List<User> getMatchingRecordsWithLikingFlag(HttpServletRequest request) {
		User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		//获取和当前用户相互喜欢的用户信息
		List<User> matchingUserList=userDao.selectMatchingUsers(user.getUserId());
		//添加喜欢标记（这里是相互喜欢，所以直接全部添加标记即可）
		matchingUserList.forEach(new Consumer<User>() {
			public void accept(User user) {
				user.setWhetherLiking(true);
			}
		});
		return matchingUserList;
	}

	public User getUserById(HttpServletRequest request, int userId) {
		return userDao.selectUserById(userId);
	}

	public String getCurUserPhotoAddress(HttpServletRequest request) {
		User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		return user.getPhotoAddress();
	}

	public boolean isApplyed(HttpServletRequest request) {
		User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		if(user.getStudentId()!=null) {
			//已经申请过了
			return true;
		}else {
			return false;
		}
		
	}

	public void updateApplying(HttpServletRequest request, String studentId, int graduateType) {
		User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		userDao.updateApplying(user.getUserId(),studentId,graduateType);
	}

	public String getApplyingInfo() {
		final StringBuilder sb=new StringBuilder();
		List<User> applyingUserList=userDao.selectApplyingUsers();
		applyingUserList.forEach(new Consumer<User>() {

			public void accept(User user) {
				sb.append(user.getUserName());sb.append("\t");
				sb.append(user.getStudentId());sb.append("\t");
				int graduateType=user.getGraduateType();
				String type=null;
				if(graduateType==1) {
					type="本校推免";
				}else if(graduateType==2) {
					type="外校推免";
				}else if(graduateType==3) {
					type="本校考研";
				}else {
					type="外校考研";
				}
				sb.append(type);sb.append("\n");
			}
			
		});
		return sb.toString();
	}

}
