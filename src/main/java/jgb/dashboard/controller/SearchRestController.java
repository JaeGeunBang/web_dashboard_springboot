package jgb.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jgb.dashboard.service.SearchService;
import jgb.dashboard.chart.*;

@CrossOrigin
@RestController
@RequestMapping("api/search")
public class SearchRestController {
    @Autowired
    SearchService searchService;

    /* getQmChartData()
    조건에 해당하는 차트 데이터를 반환
    input: 
    - search_type: 0(avg), 1(qm)
    - device: pc, mb
    - date_type: daily, weekly, monthly
    - start_date: 시작 날짜
    - end_date: 종료 날짜
    output:
    - ChartList 반환 
    */
    @RequestMapping(value="/qmchartdata", method=RequestMethod.GET)
    public ChartList getQmChartData(String search_type, String device, String date_type, String start_date, String end_date) {
        return searchService.getQmChartData(search_type, device, date_type, start_date, end_date) ;
    }

    /* getAvgChartData()
    조건에 해당하는 차트 데이터를 반환
    input: 
    - search_type: 0(avg), 1(qm)
    - device: pc, mb
    - date_type: daily, weekly, monthly
    - start_date: 시작 날짜
    - end_date: 종료 날짜
    output:
    - ChartList 반환 
    */
    @RequestMapping(value="/avgchartdata", method=RequestMethod.GET)
    public ChartList getAvgChartData(String search_type, String device, String date_type, String start_date, String end_date) {
        return searchService.getAvgChartData(search_type, device, date_type, start_date, end_date) ;
    }
}