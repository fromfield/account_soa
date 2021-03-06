package com.tianque.plugin.account.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.constants.ThreeRecordsIssueTag;
import com.tianque.plugin.account.constants.ThreeRecordsIssueViewType;
import com.tianque.plugin.account.dao.LedgerSteadyWorkDao;
import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.LedgerPoorPeople;
import com.tianque.plugin.account.domain.LedgerSteadyWork;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.state.ThreeRecordsIssueSourceState;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.plugin.account.util.DealYearOrMonthUtil;
import com.tianque.plugin.account.vo.LedgerSteadyWorkVo;

@Repository("ledgerSteadyWorkDao")
public class LedgerSteadyWorkDaoImpl extends AbstractBaseDao implements
		LedgerSteadyWorkDao {

	private PageInfo<LedgerSteadyWorkVo> createIssueVoPageInfoInstance(
			int totalRecord, int pageSize, int pageIndex) {
		PageInfo<LedgerSteadyWorkVo> result = new PageInfo<LedgerSteadyWorkVo>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}
	
	@Override
	public List<LedgerSteadyWork> needTurnLedgerSteadyWork(Map<String, Object> map){
		return getSqlMapClientTemplate().queryForList("ledgerSteadyWork.needTurnSteadyWork", map);
	}
	
	@Override
	public LedgerSteadyWork addTurnLedgerSteadyWork(LedgerSteadyWork ledgerSteadyWork){
		Long id = (Long) getSqlMapClientTemplate().insert(
				"ledgerSteadyWork.addLedgerSteadyWork", ledgerSteadyWork);
		return getLedgerSteadyWorkById(id);
	}

	@Override
	public LedgerSteadyWork getLedgerSteadyWorkById(Long id) {
		return (LedgerSteadyWork) getSqlMapClientTemplate().queryForObject(
				"ledgerSteadyWork.getLedgerSteadyWorkById", id);
	}

	@Override
	public Long addLedgerSteadyWork(LedgerSteadyWork ledgerSteadyWork) {
		return (Long) getSqlMapClientTemplate().insert(
				"ledgerSteadyWork.addLedgerSteadyWork", ledgerSteadyWork);
	}

	@Override
	public void updateLedgerSteadyWork(LedgerSteadyWork ledgerSteadyWork) {
		getSqlMapClientTemplate().update(
				"ledgerSteadyWork.updateLedgerSteadyWork", ledgerSteadyWork);
	}

	@Override
	public void deleteLedgerSteadyWorkById(Long id) {
		getSqlMapClientTemplate().delete(
				"ledgerSteadyWork.deleteLedgerSteadyWorkById", id);
	}

	@Override
	public LedgerSteadyWork findByIdCardNo(String idCardNo) {
		return (LedgerSteadyWork) getSqlMapClientTemplate().queryForObject(
				"ledgerSteadyWork.findByIdCardNo", idCardNo);
	}

	@Override
	public void deletePoorPeopleByIds(String[] ids) {
		getSqlMapClientTemplate().delete(
				"ledgerSteadyWork.deleteLedgerSteadyWorkByIds", ids);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsNeedDo(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			Integer isSupported,Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (leaderView != null && !"".equals(leaderView)) {
			map.put("leaderView", leaderView);
		}
		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("functionalOrgType", functionalOrgType);
		map.put("seachValue", seachValue);
		map.put("orgId", org.getId());
		map.put("isSupported", isSupported);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		map.put("tag", ThreeRecordsIssueTag.NEEDDO_ISSUE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsNeedDoCount(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					"ledgerSteadyWork.findJurisdictionsNeedDo", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"ledgerSteadyWork.findJurisdictionsNeedDo", map,
					(page - 1) * rows, rows));
		}

		return result;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsDone(Organization org,
			List<Long> childOrg, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String seachValue,
			Long functionalOrgType, Integer viewProcess, Long sourceType,Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (StringUtil.isStringAvaliable(seachValue)) {
			map.put("seachValue", seachValue);
		}
		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("functionalOrgType", functionalOrgType);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		map.put("substanceCode", ThreeRecordsIssueState.SUBSTANCE_CODE);
		map.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
		map.put("complete", ThreeRecordsIssueOperate.COMPLETE_CODE);
		map.put("completeStatus", ThreeRecordsIssueState.COMPLETE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsDone(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					"ledgerSteadyWork.findJurisdictionsDone", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"ledgerSteadyWork.findJurisdictionsDone", map,
					(page - 1) * rows, rows));
		}
		return result;
	}
	@Override
	public int getJurisdictionsDone(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"ledgerSteadyWork.countJurisdictionsDone", map);
	}
	@Override
	public int getJurisdictionsCreateAndDone(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"ledgerSteadyWork.countJurisdictionsCreateAndDone", map);
	}

	/**
	 * 查询待办的数量
	 * 
	 * @param map
	 * @return
	 */
	public int getJurisdictionsNeedDoCount(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"ledgerSteadyWork.countJurisdictionsNeedDo", map);
	}

	@Override
	public BaseWorking getSimpleBaseWorkByStepId(Long stepId) {
		return (BaseWorking) getSqlMapClientTemplate().queryForObject(
				"ledgerSteadyWork.getSimpleBaseWorkByStepId", stepId);
	}

	@Override
	public void updateLedgerCurrentStepAndOrg(LedgerSteadyWork ledgerSteadyWork) {
		getSqlMapClientTemplate().update(
				"ledgerSteadyWork.updateIssueCurrentStepAndOrg",
				ledgerSteadyWork);

	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsFeedBack(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (leaderView != null && !"".equals(leaderView)) {
			map.put("leaderView", leaderView);
		}
		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		if (org != null && org.getOrgInternalCode() != null) {
			map.put("orgCode", org.getOrgInternalCode());
		}
		if (org != null && org.getId() != null) {
			map.put("orgId", org.getId());
		}
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("seachValue", seachValue);
		map.put("functionalOrgType", functionalOrgType);
		map.put("verification", ThreeRecordsIssueState.VERIFICATION);// 事件表中已验证状态，值为300
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);// 事件流程表中办理中的状态，值为500
		map.put("periodCode", ThreeRecordsIssueState.PERIOD_CODE);// 事件流程表中阶段办结的状态，值为600
		map.put("issueCompleteCode", ThreeRecordsIssueState.SUBSTANCE_CODE);// 事件流程表中已实质办结的状态，值为700
		map.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsFeedBack(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					"ledgerSteadyWork.findJurisdictionsFeedBack", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"ledgerSteadyWork.findJurisdictionsFeedBack", map,
					(page - 1) * rows, rows));
		}
		return result;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSubStanceDone(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (leaderView != null && !"".equals(leaderView)) {
			map.put("leaderView", leaderView);
		}
		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		if (seachValue != null) {
			map.put("seachValue", seachValue);
		}
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("functionalOrgType", functionalOrgType);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("completeCode", ThreeRecordsIssueState.SUBSTANCE_CODE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsSubstanceDone(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					"ledgerSteadyWork.findJurisdictionsSubstanceDone", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"ledgerSteadyWork.findJurisdictionsSubstanceDone", map,
					(page - 1) * rows, rows));
		}
		return result;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsPeriodDone(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (leaderView != null && !"".equals(leaderView)) {
			map.put("leaderView", leaderView);
		}
		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		if (seachValue != null) {
			map.put("seachValue", seachValue);
		}
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("functionalOrgType", functionalOrgType);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("completeCode", ThreeRecordsIssueState.PERIOD_CODE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsPeriodDone(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					"ledgerSteadyWork.findJurisdictionsPeriodDone", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"ledgerSteadyWork.findJurisdictionsPeriodDone", map,
					(page - 1) * rows, rows));
		}
		return result;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsAssgin(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (leaderView != null && !"".equals(leaderView)) {
			map.put("leaderView", leaderView);
		}
		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("functionalOrgType", functionalOrgType);
		map.put("seachValue", seachValue);
		map.put("orgId", org.getId());
		map.put("orgCode", org.getOrgInternalCode());
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		map.put("assgin", ThreeRecordsIssueSourceState.assign);
		map.put("issueTag", ThreeRecordsIssueTag.ASSIGN_ISSUE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsAssginCount(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					"ledgerSteadyWork.findJurisdictionsAssgin", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"ledgerSteadyWork.findJurisdictionsAssgin", map,
					(page - 1) * rows, rows));
		}
		return result;
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsSubmit(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (leaderView != null && !"".equals(leaderView)) {
			map.put("leaderView", leaderView);
		}
		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		if (seachValue != null) {
			map.put("seachValue", seachValue);
		}
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("functionalOrgType", functionalOrgType);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("completeCode", ThreeRecordsIssueState.STEPCOMPLETE_CODE);
		map.put("submit", ThreeRecordsIssueSourceState.submit);
		map.put("issueTag", ThreeRecordsIssueTag.SUBMIT_ISSUE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsSubmit(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					"ledgerSteadyWork.findJurisdictionsSubmit", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"ledgerSteadyWork.findJurisdictionsSubmit", map,
					(page - 1) * rows, rows));
		}
		return result;
	}
	@Override
	public int getJurisdictionsSubmit(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"ledgerSteadyWork.countJurisdictionsSubmit", map);
	}
	@Override
	public int getJurisdictionsFeedBack(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"ledgerSteadyWork.countJurisdictionsFeedBack", map);
	}
	@Override
	public int getJurisdictionsPeriodDone(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"ledgerSteadyWork.countJurisdictionsPeriodDone", map);
	}
	@Override
	public int getJurisdictionsSubstanceDone(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"ledgerSteadyWork.countJurisdictionsSubstanceDone", map);
	}
	@Override
	public int getJurisdictionsAssginCount(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"ledgerSteadyWork.countJurisdictionsAssgin", map);
	}

	@Override
	public List<LedgerSteadyWork> getLedgerSteadyWorkByHistoryId(String id) {
		return getSqlMapClientTemplate().queryForList(
				"ledgerSteadyWork.getLedgerSteadyWorkByHistoryId", id);
	}

	@Override
	public void updateFeedBackStatus(Long ledgerId, int isFeedBack) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ledgerId", ledgerId);
		map.put("isFeedBack", isFeedBack);
		getSqlMapClientTemplate().update(
				"ledgerSteadyWork.updateFeedBackStatus", map);
	}

	@Override
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsCreateAndDone(
			String seachValue, Organization org, List<Long> childOrg,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,Integer year, Integer month) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (issueType != null) {
			map.put("issueType", issueType);
		}
		if (orgLevel != null) {
			map.put("orgLevel", orgLevel);
		}
		if (StringUtil.isStringAvaliable(seachValue)) {
			map.put("seachValue", seachValue);
		}
		if (sourceType != null) {
			map.put("sourceType", sourceType);
		}
		if (childOrg != null && childOrg.size() > 0) {
			map.put("targetOrgs", childOrg);
		}
		map.put("functionalOrgType", functionalOrgType);
		map.put("orgCode", org.getOrgInternalCode());
		map.put("orgId", org.getId());
		map.put("issueTag", ThreeRecordsIssueTag.DONE_ISSUE);
//		map.put("year", DealYearOrMonthUtil.dealYear(year));
//		map.put("month", DealYearOrMonthUtil.dealMonth(month));
		map.put("yearMonth", DealYearOrMonthUtil.dealYearMonth(year, month));
		PageInfo<LedgerSteadyWorkVo> result = createIssueVoPageInfoInstance(
				getJurisdictionsCreateAndDone(map), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (ThreeRecordsIssueViewType.VIEWPROCESS.equals(viewProcess)) {// 用于查询大屏滚动数据
			result.setResult(getSqlMapClientTemplate().queryForList(
					"ledgerSteadyWork.findJurisdictionsCreateAndDone", map));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"ledgerSteadyWork.findJurisdictionsCreateAndDone", map,
					(page - 1) * rows, rows));
		}
		return result;

	}

	@Override
	public int countLedgerByOldHistoryId(String oldHistoryId, String orgCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("oldHistoryId", oldHistoryId);
		map.put("orgCode", orgCode);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"ledgerSteadyWork.countLedgerByOldHistoryId", map);
	}

}
