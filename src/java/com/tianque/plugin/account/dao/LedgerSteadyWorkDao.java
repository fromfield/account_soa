package com.tianque.plugin.account.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.LedgerSteadyWork;
import com.tianque.plugin.account.vo.LedgerSteadyWorkVo;

public interface LedgerSteadyWorkDao {

	public LedgerSteadyWork getLedgerSteadyWorkById(Long id);
	
	public List<LedgerSteadyWork> needTurnLedgerSteadyWork(Map<String, Object> map);
	
	public LedgerSteadyWork addTurnLedgerSteadyWork(LedgerSteadyWork ledgerSteadyWork);

	public Long addLedgerSteadyWork(LedgerSteadyWork ledgerSteadyWork);

	public void updateLedgerSteadyWork(LedgerSteadyWork ledgerSteadyWork);

	public void updateLedgerCurrentStepAndOrg(LedgerSteadyWork ledgerSteadyWork);

	public void deleteLedgerSteadyWorkById(Long id);

	public void deletePoorPeopleByIds(String[] ids);

	/** 查询返回列表 */
	// public PageInfo<LedgerSteadyWork> queryLedgerSteadyWorkForPageResult(
	// Map<String, Object> map, int pageNum, int pageSize);

	public LedgerSteadyWork findByIdCardNo(String idCardNo);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsNeedDo(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			Integer isSupported, Integer year, Integer month);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsDone(Organization org,
			List<Long> childOrg, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			Integer year, Integer month);

	public int getJurisdictionsNeedDoCount(Map<String, Object> map);

	public BaseWorking getSimpleBaseWorkByStepId(Long stepId);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSubmit(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			Integer year, Integer month);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSubStanceDone(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			Integer year, Integer month);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsPeriodDone(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			Integer year, Integer month);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsFeedBack(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			Integer year, Integer month);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsAssgin(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			Integer year, Integer month);

	public List<LedgerSteadyWork> getLedgerSteadyWorkByHistoryId(String id);

	/**
	 * 更新稳定反馈信息状态
	 * 
	 * @param ledgerId
	 * @param isFeedBack
	 */
	public void updateFeedBackStatus(Long ledgerId, int isFeedBack);

	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsCreateAndDone(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			Integer year, Integer month);

	/**
	 * 统计台账数，数据导入用，判断是否已经导入过
	 * @param oldHistoryId
	 * @return
	 */
	public int countLedgerByOldHistoryId(String oldHistoryId, String orgCode);

	public int getJurisdictionsDone(Map<String, Object> map);

	public int getJurisdictionsPeriodDone(Map<String, Object> map);

	public int getJurisdictionsSubstanceDone(Map<String, Object> map);

	public int getJurisdictionsCreateAndDone(Map<String, Object> map);

	public int getJurisdictionsFeedBack(Map<String, Object> map);

	public int getJurisdictionsAssginCount(Map<String, Object> map);

	public int getJurisdictionsSubmit(Map<String, Object> map);

}
