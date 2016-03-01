package com.tianque.plugin.account.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.vo.LedgerPeopleSubstanceDesc;
import com.tianque.plugin.account.vo.LedgerPoorPeopleDetail;
import com.tianque.plugin.account.vo.LedgerPoorPeopleVo;
import com.tianque.plugin.account.vo.SearchComprehensiveVo;
import com.tianque.plugin.account.vo.ThreeRecordsCatalogVo;
import com.tianque.plugin.account.vo.ThreeRecordsViewObject;

public interface LedgerPoorPeopleComprehensiveDao {

	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsNeedDo(SearchComprehensiveVo searchVo, 
			List<Organization> orgs, List<Long> childOrg, Integer page, Integer rows, Long functionalOrgType, Integer viewProcess, Long sourceType, Integer isSupported);
	
	public Integer getJurisdictionsNeedDoCount(Map<String, Object> map);

	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsDone(SearchComprehensiveVo searchVo, 
			List<Organization> orgs, List<Long> childOrg, Integer page, Integer rows, Long functionalOrgType, Integer viewProcess, Long sourceType);

	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsFeedBack(SearchComprehensiveVo searchVo, 
			List<Organization> orgs, List<Long> childOrg, Integer page, Integer rows, Long functionalOrgType, Integer viewProcess, Long sourceType);

	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsSubStanceDone(SearchComprehensiveVo searchVo, 
			List<Organization> orgs, List<Long> childOrg, Integer page, Integer rows, Long functionalOrgType, Integer viewProcess, Long sourceType);

	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsPeriodDone(SearchComprehensiveVo searchVo, 
			List<Organization> orgs, List<Long> childOrg, Integer page, Integer rows, Long functionalOrgType, Integer viewProcess, Long sourceType);

	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsAssgin(SearchComprehensiveVo searchVo, 
			List<Organization> orgs, List<Long> childOrg, Integer page, Integer rows, Long functionalOrgType, Integer viewProcess, Long sourceType);

	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsSubmit(SearchComprehensiveVo searchVo, 
			List<Organization> orgs, List<Long> childOrg, Integer page, Integer rows, Long functionalOrgType, Integer viewProcess, Long sourceType);

	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsCreateAndDone(SearchComprehensiveVo searchVo, 
			List<Organization> orgs, List<Long> childOrg, Integer page, Integer rows, Long functionalOrgType, Integer viewProcess, Long sourceType);
	
	public List<LedgerPoorPeopleDetail> getCountGroupByPoorType(Map<String, Object> map);
	public List<LedgerPoorPeopleDetail> getCountGroupByRequiredType(Map<String, Object> map);
	public List<LedgerPeopleSubstanceDesc> getCountGroupByDesc(Map<String, Object> map);
	
	public PageInfo<ThreeRecordsViewObject> exportCreateAndDone(
			SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows);
	
	public Integer exportCountCreateAndDone(
			SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows);
	
	public Integer countCataLog(SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Integer page, Integer rows, Map<String, Object> map);
	
	public PageInfo<ThreeRecordsCatalogVo> exportCataLog(SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Integer page, Integer rows, Map<String, Object> map, String query);
}