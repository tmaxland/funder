package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProjectEntry implements Comparable<ProjectEntry> {

	// this will be the project id
	Integer id;

	String title, description;
	String postedBy;

	Date startDate;

	Integer fundingTarget;
	Integer fundingDuration;
	Integer daysToGo;

	List<Reward> rewards;

	Date endDate;

	Integer amountFunded;

	float percentage;

	boolean sponsorLink;

	public ProjectEntry() {
		// TODO empty constructer
	}

	public ProjectEntry(Integer id, String title, String postedBy,
			Date startDate, Integer daysToGo, Integer amountFunded,
			float percentage) {
		this.id = id;
		this.title = title;
		this.postedBy = postedBy;
		this.startDate = startDate;
		this.daysToGo = daysToGo;
		this.amountFunded = amountFunded;
		this.percentage = percentage;
	}

	public ProjectEntry(Integer id, String title, String postedBy,
			String description, Date startDate, Integer fundingTarget,
			Integer fundingDuration, Integer daysToGo, List<Reward> rewards,
			boolean sponsorLink) {
		this.id = id;
		this.title = title;
		this.postedBy = postedBy;
		this.description = description;
		this.startDate = startDate;
		this.fundingTarget = fundingTarget;
		this.fundingDuration = fundingDuration;
		this.daysToGo = daysToGo;
		this.amountFunded = 0;
		this.rewards = rewards;
		this.sponsorLink = sponsorLink;
	}

	public ProjectEntry(Integer id, String title, List<Reward> rewards) {
		this.id = id;
		this.title = title;
		this.rewards = rewards;
	}

	Integer pid;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public ProjectEntry(Integer pid) {
		this.pid = pid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getFundingTarget() {
		return fundingTarget;
	}

	public void setFundingTarget(Integer fundingTarget) {
		this.fundingTarget = fundingTarget;
	}

	public Integer getFundingDuration() {
		return fundingDuration;
	}

	public void setFundingDuration(Integer fundingDuration) {
		this.fundingDuration = fundingDuration;
	}

	public Integer getDaysToGo() {
		return daysToGo;
	}

	public void setDaysToGo(Integer daysToGo) {
		this.daysToGo = daysToGo;
	}

	public List<Reward> getRewards() {
		return rewards;
	}

	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}

	public void add(Reward rewards, Integer rewardLength) {
		if (this.rewards != null) {
			this.rewards.add(rewardLength - 1, rewards);
		} else {

			List<Reward> rewardEntries = new ArrayList<Reward>();
			rewardEntries.add(rewards);
			this.setRewards(rewardEntries);

		}

	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.startDate);
		cal.add(Calendar.DATE, this.getFundingDuration());
		this.endDate = cal.getTime();
	}

	public int compareTo(ProjectEntry arg0) {

		Date compareDate = ((ProjectEntry) arg0).getEndDate();

		// ascending order
		// return this.endDate - compareDate;
		return (int) ((this.endDate.getTime() - compareDate.getTime()) / (1000 * 60 * 60 * 24));

		// return 0;
	}

	public Integer getAmountFunded() {
		return amountFunded;
	}

	public void setAmountFunded(Integer amountFunded) {
		this.amountFunded = amountFunded;
	}

	public void addamountFunded(Integer amount) {

		if (this.amountFunded == 0) {
			this.setAmountFunded(amount);
		} else {
			this.setAmountFunded(this.getAmountFunded() + amount);
		}
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public boolean isSponsorLink() {
		return sponsorLink;
	}

	public void setSponsorLink(boolean sponsorLink) {
		this.sponsorLink = sponsorLink;
	}

}
