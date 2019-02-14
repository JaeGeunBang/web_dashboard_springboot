package jgb.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jgb.dashboard.service.SearchService;
import jgb.dashboard.chartList.*;

@CrossOrigin
@RestController
@RequestMapping("api/search")
public class SearchRestController {
    @Autowired
    SearchService searchService;

    @RequestMapping(value="/qmchartdata", method=RequestMethod.GET)
    public ChartList getQmChartData(String search_type, String device, String date_type, String start_date, String end_date) {
        return searchService.makeSearchChartBySearchTable(search_type, device, date_type, start_date, end_date) ;
    }

    @RequestMapping(value="/avgchartdata", method=RequestMethod.GET)
    public ChartList getAvgChartData(String search_type, String device, String date_type, String start_date, String end_date) {
        return searchService.makeSearchChartBySearchTable(search_type, device, date_type, start_date, end_date) ;
    }
}