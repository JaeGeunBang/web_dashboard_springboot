package jgb.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jgb.dashboard.service.TotalTrafficService;
import jgb.dashboard.chart.*;


@CrossOrigin
@RestController
@RequestMapping("api/totaltraffic")
public class TotalTrafficRestController {
    @Autowired
    TotalTrafficService totalTrafficService;

    /* getChartData()
    조건에 해당하는 차트 데이터를 반환
    input: 
    - device: PC, Mobile
    - date_type: daily, weekly, monthly
    - start_date: 시작 날짜
    - end_date: 종료 날짜
    output:
    - ChartList 반환 
    */
    @RequestMapping(value="/chartdata", method=RequestMethod.GET)
    public ChartList getChartData(String device, String date_type, String start_date, String end_date) {
        return totalTrafficService.getChartData(device, date_type, start_date, end_date) ;
    }
}
