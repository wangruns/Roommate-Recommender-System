package top.wangruns.roommatematching.model;

public class Support {
	private int supportId;
	private int userId;
	private int reviewId;

	public Support() {}

	public Support(int userId,int reviewId) {
		this.userId=userId;
		this.reviewId=reviewId;
	}

	public int getSupportId() {
		return supportId;
	}

	public void setSupportId(int supportId) {
		this.supportId = supportId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

}
