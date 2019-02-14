package jgb.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jgb.dashboard.service.TotalTrafficService;
import jgb.dashboard.chartList.*;


@CrossOrigin
@RestController
@RequestMapping("api/totaltraffic")
public class TotalTrafficRestController {
    @Autowired
    TotalTrafficService totalTrafficService;

    @RequestMapping(value="/chartdata", method=RequestMethod.GET)
    public ChartList getChartData(String device, String date_type, String start_date, String end_date) {
        return totalTrafficService.makeTotalTrafficChartByTotalTrafficTable(device, date_type, start_date, end_date) ;
    }
}
