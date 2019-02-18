
package jgb.dashboard.service;

import jgb.dashboard.repository.*;
import jgb.dashboard.domain.*;
import jgb.dashboard.chart.*;
import jgb.dashboard.chartList.*;

import java.util.*;

public abstract class AbstractService {
    private ChartListFactory chartListFactory ;
    private ChartFactory chartFactory ;

    public AbstractService() {
        this.chartListFactory = new ChartListFactory() ;
        this.chartFactory = new ChartFactory() ;
    }

    public abstract List<CommonField> getCommonField(String search_type, String device, String date_type, String start_date, String end_date) ;
    public abstract double[][] makeChartDataArray(List<CommonField> commonTableRowList, Map<String, Integer> domainMap, int deduplicatedDomainsSize, 
        Map<String, Integer> dateMap, int deduplicatedDatesSize) ;

    public AbstractChartList makeChartToTable(String search_type, String device, String date_type, String start_date, String end_date){
        List<CommonField> commonTableRowList = null;
        
        commonTableRowList = getCommonField(search_type, device, date_type, start_date, end_date) ;
        AbstractChartList chartList = chartListFactory.makeChartListType(search_type) ;

        List<String> deduplicatedDomains = chartList.getDeduplicatedDomains(commonTableRowList) ;
        Map<String, Integer> domainMap = chartList.makeDomainMap(deduplicatedDomains) ;
        int deduplicatedDomainsSize = deduplicatedDomains.size() ;

        List<String> deduplicatedDates = chartList.getDeduplicatedDates(commonTableRowList) ;
        Map<String, Integer> dateMap = chartList.makeDateMap(deduplicatedDates);
        int deduplicatedDatesSize = deduplicatedDates.size() ;

        double[][] chartDataArray = new double[deduplicatedDomainsSize][deduplicatedDatesSize];

        chartDataArray = makeChartDataArray(commonTableRowList, domainMap, deduplicatedDomainsSize, dateMap, deduplicatedDatesSize) ;

        chartList.setDateList(deduplicatedDates.toArray(new String[deduplicatedDatesSize])) ;
        Chart[] charts = new Chart[deduplicatedDomainsSize];
        for(int i = 0 ; i < charts.length ; i++)
            charts[i] = chartFactory.makeChartType(search_type) ;

        for(int i = 0 ; i < deduplicatedDomainsSize ; i++) {
            Chart chartObject = chartList.makeChartWithOption(i);
            chartObject.setLabel(deduplicatedDomains.get(i));
            chartObject.setData(chartDataArray[i]) ;
            charts[i] = chartObject;
        }

        chartList.setChartData(charts) ;
        return chartList;
    }
}