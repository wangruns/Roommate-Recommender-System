package top.wangruns.roommatematching.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import top.wangruns.roommatematching.model.Review;

public interface ReviewService {

	/**
	 * 添加评论并返回是否成功
	 * @param request
	 * HttpServletRequest
	 * @param targetUserId
	 * 被评论的用户Id
	 * @param review
	 * 评论信息
	 * @return
	 * 如果添加成功，返回true
	 */
	boolean addReview(HttpServletRequest request, int targetUserId, String review);

	/**
	 * 获取当前目标用户自我分享下面的精彩评论，并加上是否被当前用户点赞的标记
	 * @param request
	 * HttpServletRequest
	 * @param targetUserId
	 * 目标用户Id
	 * @return
	 * 评论信息列表
	 */
	List<Review> getHotReviewByTargetUserIdWithLikeFlag(HttpServletRequest request, int targetUserId);

	/**
	 * 改变当前用户对某个评论的点赞状态
	 * @param request
	 * HttpServletRequest对象
	 * @param reviewId
	 * 需要改变点赞状态的评论Id
	 * @return
	 * 若改变后状态为已经点赞，则返回true
	 */
	boolean reviewLikeChange(HttpServletRequest request, int reviewId);

	/**
	 * 简单限制两分钟内只能评价一次
	 * @param request
	 * @return
	 */
	boolean tooQuickly(HttpServletRequest request);

}
