package top.wangruns.roommatematching.dao;

import java.util.List;

import top.wangruns.roommatematching.model.Review;
import top.wangruns.roommatematching.model.Support;

public interface ReviewDao {

	/**
	 * 插入评论
	 * @param review
	 * @return
	 */
	int insert(Review review);

	/**
	 * 获取当前用户的所有点赞记录
	 * @param userId
	 * @return
	 */
	List<Support> selectSuportByUserId(int userId);

	/**
	 * 获取目标用户自我分享下面的所有精彩评论，并带上点赞数目
	 * @param targetUserId
	 * @return
	 */
	List<Review> selectHotReviewWithSupportNumber(int targetUserId);

	/**
	 * 根据点赞查询点赞信息
	 * @param support
	 * @return
	 */
	Support selectBySupport(Support support);

	/**
	 * 插入点赞信息
	 * @param support
	 */
	void insertSupportRecord(Support support);

	/**
	 * 根据点赞Id删除点赞信息
	 * @param supportId
	 */
	void deleteSupportRecordById(int supportId);

}
