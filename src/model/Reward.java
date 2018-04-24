package model;

public class Reward {

	Integer rewardID;
	Integer rewardAmount;
	String rewardDescription;

	public Reward() {

	}

	public Reward(Integer rewardID, Integer rewardAmount,
			String rewardDescription) {
		this.rewardID = rewardID;
		this.rewardAmount = rewardAmount;
		this.rewardDescription = rewardDescription;
	}
	
	public Reward(Integer rewardAmount,
			String rewardDescription) {

		this.rewardAmount = rewardAmount;
		this.rewardDescription = rewardDescription;
	}

	
	public Integer getRewardID() {
		return rewardID;
	}

	public void setRewardID(Integer rewardID) {
		this.rewardID = rewardID;
	}

	public Integer getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(Integer rewardAmount) {
		this.rewardAmount = rewardAmount;
	}

	public String getRewardDescription() {
		return rewardDescription;
	}

	public void setRewardDescription(String rewardDescription) {
		this.rewardDescription = rewardDescription;
	}

}
