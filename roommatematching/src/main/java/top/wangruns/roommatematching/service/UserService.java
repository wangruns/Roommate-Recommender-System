package top.wangruns.roommatematching.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import top.wangruns.roommatematching.model.Major;
import top.wangruns.roommatematching.model.School;
import top.wangruns.roommatematching.model.User;

public interface UserService {
	

	/**
	 * 根据帐号和密码查找用户
	 * @param u
	 * 帐号和密码被实例化的User
	 * @return
	 * 若不存在，返回null
	 */
	public User findLogin(User u);

	/**
	 * 检验指定的邮箱帐号是否存在
	 * @param email
	 * 邮箱帐号
	 * @return
	 * 若存在返回true
	 */
	public boolean isEmailExisted(String email);

	/**
	 * 向user用户表中插入新的记录
	 * @param u
	 * 用户User对象
	 * @return
	 * 若插入成功返回true
	 */
	public boolean insert(User u);

	/**
	 * 获取所有的用户记录,并且加上是否被当前用户喜欢的标记
	 * @param request 
	 * @return
	 * 若没有，则返回null
	 */
	public List<User> getAllRecordsWithLikingFlag(HttpServletRequest request);

	/**
	 * 获取所有的用户Id记录
	 * @return
	 * 若没有，则返回null
	 */
	public List<Integer> getAllUserIdRecords();

	/**
	 * 判定当前用户是否具备权限，这里简单的判定用户是否具有权限
	 * @param request
	 * @return
	 * 若有，返回true
	 */
	public boolean isHasPrivilege(HttpServletRequest request);

	/**
	 * 获取所有的学校信息
	 * @return
	 */
	public List<School> getAllSchools();

	/**
	 * 获取所有专业信息
	 * @return
	 */
	public List<Major> getAllMajors();

	/**
	 * 添加用户输入的信息
	 * @param request
	 * @param u
	 * @param photo
	 * @return
	 * 如果添加成功，返回true
	 */
	public boolean addInfo(HttpServletRequest request, User u, MultipartFile photo);

	/**
	 * 验证用户登录，而且已经激活
	 * @param request
	 * @return
	 */
	public boolean isHasPrivilegeAndActive(HttpServletRequest request);

	/**
	 * 验证用户生份的真实性，这里简单的验证一下姓名（正确做法是对接和学校的数据，确保用户为本校学生）
	 * 使用 resources 目录下的legalName.txt里面的名单作为模拟官方验证
	 * @param request 
	 * @param userName
	 * @return
	 * 用户生份信息真实可靠，返回true
	 */
	public boolean checkUsernameLegal(HttpServletRequest request, String userName);

	/**
	 * 改变当前用户对某个用户的喜欢状态
	 * @param request
	 * HttpServletRequest对象
	 * @param likingUserId
	 * 当前用户喜欢的用户Id
	 * @return
	 * 若改变后状态为喜欢，则返回true
	 */
	public boolean likingChange(HttpServletRequest request, int likingUserId);

	/**
	 * 获取当前用户喜欢的人信息
	 * @param request
	 * @return
	 */
	public List<User> getAdmiringRecordsWithLikingFlag(HttpServletRequest request);

	/**
	 * 获取和当前用户相互喜欢的人信息
	 * @param request
	 * @return
	 */
	public List<User> getMatchingRecordsWithLikingFlag(HttpServletRequest request);

	/**
	 * 根据用户Id获取当前用户的信息
	 * @param request
	 * @param userId
	 * @return
	 */
	public User getUserById(HttpServletRequest request, int userId);

	/**
	 * 获取当前用户的头像地址
	 * @param request
	 * @return
	 */
	public String getCurUserPhotoAddress(HttpServletRequest request);

}
