package com.tianque.plugin.account.domain;

import java.math.BigDecimal;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.PropertyDict;

public class Science extends BaseDomain {

	private LedgerPeopleAspirations peopleAspiration;
	/*
	 * 受益人口数
	 */
	private Long beneficiaryNumber;
	/*
	 * 建设性质
	 */
	private PropertyDict buildType;

	private String projectName;
	/*
	 * 项目类别
	 */
	private PropertyDict projectCategory;

	private PropertyDict projectSubCategory;

	private PropertyDict projectLevelCategory;

	/*
	 * 建设单位名称
	 */
	private String buildCompanyName;
	/*
	 * 规模
	 */
	private Long scienceScope;

	/*
	 * 计划投资
	 */
	private BigDecimal plannedInvestment;
	/*
	 * 自筹资金
	 */
	private BigDecimal selfFund;
	/*
	 * 缺口资金
	 */
	private BigDecimal gapFund;
	private String fromAddress;
	private String toAddress;
	private String otherContent;

	private PropertyDict contentCategory;
	/*
	 * 宣传次数
	 */
	private Long publicizeNum;
	/*
	 * 主题内容
	 */
	private String content;
	/*
	 * 科技项目名称
	 */
	private String itemName;

	public Science() {
		super();
	}

	public Science(LedgerPeopleAspirations peopleAspiration,
			Long beneficiaryNumber, PropertyDict buildType, String projectName,
			PropertyDict projectCategory, PropertyDict projectSubCategory,
			PropertyDict projectLevelCategory, String buildCompanyName,
			Long scienceScope, BigDecimal plannedInvestment,
			BigDecimal selfFund, BigDecimal gapFund, String fromAddress,
			String toAddress, String otherContent,
			PropertyDict contentCategory, Long publicizeNum, String content,
			String itemName) {
		super();
		this.peopleAspiration = peopleAspiration;
		this.beneficiaryNumber = beneficiaryNumber;
		this.buildType = buildType;
		this.projectName = projectName;
		this.projectCategory = projectCategory;
		this.projectSubCategory = projectSubCategory;
		this.projectLevelCategory = projectLevelCategory;
		this.buildCompanyName = buildCompanyName;
		this.scienceScope = scienceScope;
		this.plannedInvestment = plannedInvestment;
		this.selfFund = selfFund;
		this.gapFund = gapFund;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.otherContent = otherContent;
		this.contentCategory = contentCategory;
		this.publicizeNum = publicizeNum;
		this.content = content;
		this.itemName = itemName;
	}

	public Long getPublicizeNum() {
		return publicizeNum;
	}

	public void setPublicizeNum(Long publicizeNum) {
		this.publicizeNum = publicizeNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public PropertyDict getContentCategory() {
		return contentCategory;
	}

	public void setContentCategory(PropertyDict contentCategory) {
		this.contentCategory = contentCategory;
	}

	public LedgerPeopleAspirations getPeopleAspiration() {
		return peopleAspiration;
	}

	public void setPeopleAspiration(LedgerPeopleAspirations peopleAspiration) {
		this.peopleAspiration = peopleAspiration;
	}

	public Long getBeneficiaryNumber() {
		return beneficiaryNumber;
	}

	public void setBeneficiaryNumber(Long beneficiaryNumber) {
		this.beneficiaryNumber = beneficiaryNumber;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public PropertyDict getProjectCategory() {
		return projectCategory;
	}

	public void setProjectCategory(PropertyDict projectCategory) {
		this.projectCategory = projectCategory;
	}

	public PropertyDict getBuildType() {
		return buildType;
	}

	public void setBuildType(PropertyDict buildType) {
		this.buildType = buildType;
	}

	public String getBuildCompanyName() {
		return buildCompanyName;
	}

	public void setBuildCompanyName(String buildCompanyName) {
		this.buildCompanyName = buildCompanyName;
	}

	public PropertyDict getProjectSubCategory() {
		return projectSubCategory;
	}

	public void setProjectSubCategory(PropertyDict projectSubCategory) {
		this.projectSubCategory = projectSubCategory;
	}

	public PropertyDict getProjectLevelCategory() {
		return projectLevelCategory;
	}

	public void setProjectLevelCategory(PropertyDict projectLevelCategory) {
		this.projectLevelCategory = projectLevelCategory;
	}

	public Long getScienceScope() {
		return scienceScope;
	}

	public void setScienceScope(Long scienceScope) {
		this.scienceScope = scienceScope;
	}

	public BigDecimal getPlannedInvestment() {
		return plannedInvestment;
	}

	public void setPlannedInvestment(BigDecimal plannedInvestment) {
		this.plannedInvestment = plannedInvestment;
	}

	public BigDecimal getSelfFund() {
		return selfFund;
	}

	public void setSelfFund(BigDecimal selfFund) {
		this.selfFund = selfFund;
	}

	public BigDecimal getGapFund() {
		return gapFund;
	}

	public void setGapFund(BigDecimal gapFund) {
		this.gapFund = gapFund;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getOtherContent() {
		return otherContent;
	}

	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}

}
