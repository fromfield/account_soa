package com.tianque.account.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.ThreeRecordsJobDubboService;
import com.tianque.plugin.account.report.service.AccountReportService;
import com.tianque.plugin.account.turn.service.ThreeRecordsLastYearService;

@Component
public class ThreeRecordsJobDubboServiceImpl implements ThreeRecordsJobDubboService {
	
	@Autowired
	private ThreeRecordsLastYearService lastYearService;
	@Autowired
	private AccountReportService accountReportService;
	
	@Override
	public void lastYearTurn() {
		lastYearService.lastYearTurn();
	}

	@Override
	public void initMonthReportData(int year, int month) {
		accountReportService.initMonthRepot(year, month);
	}

}
