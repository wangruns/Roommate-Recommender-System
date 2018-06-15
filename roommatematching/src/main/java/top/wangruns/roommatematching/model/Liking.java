package top.wangruns.roommatematching.model;

public class Liking {
	private int likeId;
	private int userId;
	private int likingUserId;
	
	public Liking() {}
	
	public Liking(int userId,int likingUserId) {
		this.userId=userId;
		this.likingUserId=likingUserId;
	}

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getLikingUserId() {
		return likingUserId;
	}

	public void setLikingUserId(int likingUserId) {
		this.likingUserId = likingUserId;
	}

}
