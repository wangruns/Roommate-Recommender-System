package top.wangruns.roommatematching.model;

public class Dynamic {
	private int userId;
	private String userName;
	private String reviewTime;
	private int targetUserId;//在那个用户下面的评论那里@了当前用户

	public Dynamic() {}
	
	public Dynamic(String userName,String reviewTime,int targetUserId) {
		this.userName=userName;
		this.reviewTime=reviewTime;
		this.targetUserId=targetUserId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}
	public int getTargetUserId() {
		return targetUserId;
	}
	public void setTargetUserId(int targetUserId) {
		this.targetUserId = targetUserId;
	}
	
	
	

}
