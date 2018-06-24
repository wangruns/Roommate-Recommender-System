package top.wangruns.roommatematching.dao;

import java.util.List;

import top.wangruns.roommatematching.model.Dynamic;
import top.wangruns.roommatematching.model.Liking;
import top.wangruns.roommatematching.model.Major;
import top.wangruns.roommatematching.model.Review;
import top.wangruns.roommatematching.model.School;
import top.wangruns.roommatematching.model.User;

public interface UserDao {

	/**
	 * 根据某个用户的email和password进行查询
	 * @param u
	 * 用户User对象
	 * @return
	 * 若查询成功返回查询到的对象，否则返回null
	 */
	public User selectByUser(User u);

	/**
	 * 根据某个email记录进行查询
	 * @param email
	 * 邮箱帐号
	 * @return
	 * 若查询成功返回查询到的对象，否则返回null
	 */
	public User selectByEmail(String email);

	/**
	 * 向user用户表中插入新的记录
	 * @param u
	 * 用户User对象
	 * @return
	 * 若插入成功返回1,否则返回0,即返回受影响的行数
	 */
	public int insert(User u);

	/**
	 * 查询所有的用户记录
	 * @return
	 * 若没有，则返回null
	 */
	public List<User> selectAll();

	/**
	 * 查询所有的用户Id记录
	 * @return
	 * 若没有，则返回null
	 */
	public List<Integer> selectAllUserId();

	/**
	 * 查询所有的学校信息
	 * @return
	 */
	public List<School> selectAllSchools();

	/**
	 * 查询所有的专业信息
	 * @return
	 */
	public List<Major> selectAllMajors();

	/**
	 * 根据当前用户的Id来更新Info添加的信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);

	/**
	 * 更新当前用户的激活状态：激活
	 * @param user
	 */
	public void updateActiveStatus(User user);

	/**
	 * 获取当前用户喜欢的用户Id
	 * @param userId
	 * @return
	 * 如果没有，返回null
	 */
	public List<Integer> selectLikingUserIds(int userId);

	/**
	 * 查询当前喜欢记录并返回该记录
	 * @param liking
	 * @return
	 * 若不存在，返回null
	 */
	public Liking selectLikingByLiking(Liking liking);

	/**
	 * 插入当前喜欢记录
	 * @param liking
	 */
	public void insertLiking(Liking liking);

	/**
	 * 删除喜欢记录，根据Id
	 * @param likeId
	 */
	public void deleteLikingById(int likeId);

	/**
	 * 获取和当前用户相互喜欢的用户信息（排除自己喜欢自己的情况）
	 * @param userId
	 * @return
	 */
	public List<User> selectMatchingUsers(int userId);

	/**
	 * 根据用户Id查询信息
	 * @param userId
	 * @return
	 */
	public User selectUserById(int userId);

	/**
	 * 更新当前用户的申请信息
	 * @param userId 
	 * @param studentId
	 * @param graduateType
	 */
	public void updateApplying(int userId, String studentId, int graduateType);

	/**
	 * 获取已经申请的用户信息
	 * @return
	 */
	public List<User> selectApplyingUsers();

	/**
	 * 查找当前用户Id自我分享下面的评论动态
	 * @param userId
	 * @return
	 */
	public List<Dynamic> selectReviewDynamicByUserId(int userId);

	/**
	 * 获取不是当前用户产生的评论信息
	 * @param userId
	 * @return
	 */
	public List<Review> selectReviewsExceptSelf(int userId);

	/**
	 * 根据用户Id查询当前用户的角色信息
	 * @param userId
	 * 当前用户的Id
	 * @return
	 * 若没有，则返回null
	 */
//	public Role selectRoleByUserId(int userId);
	

}
