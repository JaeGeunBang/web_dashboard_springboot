package jgb.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jgb.dashboard.service.*;
import jgb.dashboard.chartList.*;


@CrossOrigin
@RestController
@RequestMapping("api/totaltraffic")
public class TotalTrafficRestController {
    @Autowired
    @Qualifier("TotalTrafficService")
    AbstractService service;

    @RequestMapping(value="/chartdata", method=RequestMethod.GET)
    public AbstractChartList getChartData(String search_type, String device, String date_type, String start_date, String end_date) {
        return service.makeChartToTable(search_type, device, date_type, start_date, end_date) ;
    }
}
