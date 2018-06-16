package top.wangruns.roommatematching.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.wangruns.roommatematching.dao.ReviewDao;
import top.wangruns.roommatematching.dao.UserDao;
import top.wangruns.roommatematching.model.Review;
import top.wangruns.roommatematching.model.Support;
import top.wangruns.roommatematching.model.User;
import top.wangruns.roommatematching.service.ReviewService;
import top.wangruns.roommatematching.utils.Request;

@Service("reviewServiceImpl")
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private ReviewDao reviewDao;

	public boolean addReview(HttpServletRequest request, int targetUserId, String content) {
		boolean isInsertSuccessful = false;
		User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		if (user == null) {
			return isInsertSuccessful;
		}
		Review review = new Review(user.getUserId(), targetUserId, content);
		int affectedRows = reviewDao.insert(review);
		if (affectedRows > 0) {
			isInsertSuccessful = true;
		}
		return isInsertSuccessful;
	}

	public List<Review> getHotReviewByTargetUserIdWithLikeFlag(HttpServletRequest request, int targetUserId) {
		User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		//获取用户的点赞列表
		List<Support> suportList=null;
		if(user!=null) {
			suportList= reviewDao.selectSuportByUserId(user.getUserId());
		}
		//获取目标用户自我分享下面的精彩评论列表
		List<Review> hotReviewList= reviewDao.selectHotReviewWithSupportNumber(targetUserId);
		
		//在结果列表中给已经被该用户点赞的评论加上标记
		if(hotReviewList!=null && suportList!=null) {
			for(Support support:suportList) {
				for(Review review:hotReviewList) {
					if(support.getReviewId()==review.getReviewId()) {
						review.setWhetherLiked(true);
					}
				}
			}
		}
		return hotReviewList;
	}

	public boolean reviewLikeChange(HttpServletRequest request, int reviewId) {
		boolean isLiked=true;
		User user=userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		//获取当前评论的点赞状态
		Support support=reviewDao.selectBySupport(new Support(user.getUserId(),reviewId));
		if(support==null) {
			//该评论还没有被该用户点赞
			isLiked=false;
			//进行点赞
			reviewDao.insertSupportRecord(new Support(user.getUserId(),reviewId));
		}else {
			//已经点赞了，则取消点赞
			reviewDao.deleteSupportRecordById(support.getSupportId());
		}
		//返回该评论改变后的点赞状态
		return !isLiked;
	}

	public boolean tooQuickly(HttpServletRequest request) {
		if(request.getSession().getAttribute("lastTime")==null) {
			System.out.println("null");
			request.getSession().setAttribute("lastTime", new SimpleDateFormat("mm").format(new Date()));
			return false;
		}
		String lastMinute=(String) request.getSession().getAttribute("lastTime");
		String curMinute=new SimpleDateFormat("mm").format(new Date());
		
		if( Math.abs(Integer.valueOf(curMinute)- Integer.valueOf(lastMinute)) <=2) {
			return true;
		}
		request.getSession().setAttribute("lastTime",curMinute);
		return false;
	}
	
	
}
