
package jgb.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jgb.dashboard.repository.*;
import jgb.dashboard.domain.*;
import jgb.dashboard.chart.*;
import jgb.dashboard.chartList.*;

import java.util.*;

@Service
@Transactional
public class SearchService{
    @Autowired
    SearchRepository searchRepository ;

    ChartListFactory chartListFactory ;
    ChartFactory chartFactory ;

    public SearchService() {
        this.chartListFactory = new ChartListFactory() ;
        this.chartFactory = new ChartFactory() ;
    }

    private List<CommonDomainField> changeTypeCommonDomainField(List<Search> totalTrafficList) {
        List<CommonDomainField> commonTableRowList = new ArrayList<CommonDomainField>();
        for(Search t_object : totalTrafficList) {
            commonTableRowList.add(t_object) ;
        }
        return commonTableRowList;
    }

    public ChartList makeSearchChartBySearchTable(String search_type, String device, String date_type, String start_date, String end_date){
        List<CommonDomainField> commonTableRowList = null;
        commonTableRowList = changeTypeCommonDomainField(searchRepository.getSearchTableRowList(search_type, device, date_type, start_date, end_date)) ;
        
        ChartList chartList = null;

        if(search_type.equals("0"))
            chartList = chartListFactory.makeChartListType("QmSearch") ;
        else 
            chartList = chartListFactory.makeChartListType("AvgSearch") ;

        List<String> deduplicatedDomains = chartList.getDeduplicatedDomains(commonTableRowList) ;
        Map<String, Integer> domainMap = chartList.makeDomainMap(deduplicatedDomains) ;
        int deduplicatedDomainsSize = deduplicatedDomains.size() ;

        List<String> deduplicatedDates = chartList.getDeduplicatedDates(commonTableRowList) ;
        Map<String, Integer> dateMap = chartList.makeDateMap(deduplicatedDates);
        int deduplicatedDatesSize = deduplicatedDates.size() ;

        double[][] chartDataArray = new double[deduplicatedDomainsSize][deduplicatedDatesSize];

        for(CommonDomainField commonDomain : commonTableRowList) {
            chartDataArray[domainMap.get(((TotalTraffic)commonDomain).getDomain() + "_" + ((TotalTraffic)commonDomain).getPvuv())]
                [dateMap.get(commonDomain.getDate())]
                    = ((TotalTraffic)commonDomain).getValue() ;
        }
        
        chartList.setDateList(deduplicatedDates.toArray(new String[deduplicatedDatesSize])) ;
        Chart[] charts = new Chart[deduplicatedDomainsSize];
        for(int i = 0 ; i < charts.length ; i++) {
            if(search_type.equals("0"))
                charts[i] = chartFactory.makeChartType("QmSearch") ;
            else
                charts[i] = chartFactory.makeChartType("AvgSearch") ;
        }

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