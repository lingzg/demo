package model;

import com.alibaba.fastjson.JSON;

public class OaProjectPlan {

	private java.lang.Long planId;
	/** 项目id */
	private java.lang.Long projectId;
	/** WBS */
	private java.lang.String wbs;
	/** 项目阶段 */
	private java.lang.String projectStage;
	/** 负责人 */
	private java.lang.String responsibleUser;
	/** 计划开始时间 */
	private java.util.Date planStartDate;
	/** 计划结束时间 */
	private java.util.Date planEndDate;
	/** 计划工期（天） */
	private java.lang.Integer planPeriod;
	/** 实际开始时间 */
	private java.util.Date realStartDate;
	/** 实际结束时间 */
	private java.util.Date realEndDate;
	/** 实际工期（天） */
	private java.lang.Integer realPeriod;
	/** 工作项 */
	private java.lang.String workItem;
	/** 输出物 */
	private java.lang.String outputObj;
	/** 是否里程碑 */
	private java.lang.Integer isMilestone;
	/** 排序 */
	private java.lang.Integer orderNo;
	/** 状态 */
	private java.lang.String status;
	/** 进度 */
	private java.lang.Double progress;
	
	public java.lang.Long getPlanId() {
		return planId;
	}
	public void setPlanId(java.lang.Long planId) {
		this.planId = planId;
	}
	public java.lang.Long getProjectId() {
		return projectId;
	}
	public void setProjectId(java.lang.Long projectId) {
		this.projectId = projectId;
	}
	public java.lang.String getWbs() {
		return wbs;
	}
	public void setWbs(java.lang.String wbs) {
		this.wbs = wbs;
	}
	public java.lang.String getProjectStage() {
		return projectStage;
	}
	public void setProjectStage(java.lang.String projectStage) {
		this.projectStage = projectStage;
	}
	public java.lang.String getResponsibleUser() {
		return responsibleUser;
	}
	public void setResponsibleUser(java.lang.String responsibleUser) {
		this.responsibleUser = responsibleUser;
	}
	public java.util.Date getPlanStartDate() {
		return planStartDate;
	}
	public void setPlanStartDate(java.util.Date planStartDate) {
		this.planStartDate = planStartDate;
	}
	public java.util.Date getPlanEndDate() {
		return planEndDate;
	}
	public void setPlanEndDate(java.util.Date planEndDate) {
		this.planEndDate = planEndDate;
	}
	public java.lang.Integer getPlanPeriod() {
		return planPeriod;
	}
	public void setPlanPeriod(java.lang.Integer planPeriod) {
		this.planPeriod = planPeriod;
	}
	public java.util.Date getRealStartDate() {
		return realStartDate;
	}
	public void setRealStartDate(java.util.Date realStartDate) {
		this.realStartDate = realStartDate;
	}
	public java.util.Date getRealEndDate() {
		return realEndDate;
	}
	public void setRealEndDate(java.util.Date realEndDate) {
		this.realEndDate = realEndDate;
	}
	public java.lang.Integer getRealPeriod() {
		return realPeriod;
	}
	public void setRealPeriod(java.lang.Integer realPeriod) {
		this.realPeriod = realPeriod;
	}
	public java.lang.String getWorkItem() {
		return workItem;
	}
	public void setWorkItem(java.lang.String workItem) {
		this.workItem = workItem;
	}
	public java.lang.String getOutputObj() {
		return outputObj;
	}
	public void setOutputObj(java.lang.String outputObj) {
		this.outputObj = outputObj;
	}
	public java.lang.Integer getIsMilestone() {
		return isMilestone;
	}
	public void setIsMilestone(java.lang.Integer isMilestone) {
		this.isMilestone = isMilestone;
	}
	public java.lang.Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(java.lang.Integer orderNo) {
		this.orderNo = orderNo;
	}
	public java.lang.String getStatus() {
		return status;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	public java.lang.Double getProgress() {
		return progress;
	}
	public void setProgress(java.lang.Double progress) {
		this.progress = progress;
	}
	
	public String toString(){
		return JSON.toJSONString(this);
	}
}
