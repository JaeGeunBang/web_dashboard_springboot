
package jgb.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jgb.dashboard.repository.*;
import jgb.dashboard.domain.*;
import jgb.dashboard.chart.*;
import jgb.dashboard.chartList.*;

import java.util.*;

@Service
@Qualifier("TotalTrafficService")
@Transactional
public class TotalTrafficService extends AbstractService{
    @Autowired
    TotalTrafficRepository totalTrafficRepository ;

    @Override
    public double[][] makeChartDataArray(List<CommonField> commonTableRowList, Map<String, Integer> domainMap, int deduplicatedDomainsSize, 
        Map<String, Integer> dateMap, int deduplicatedDatesSize){
        double[][] chartDataArray = new double[deduplicatedDomainsSize][deduplicatedDatesSize];

        for(CommonField commonDomain : commonTableRowList) {
            chartDataArray[domainMap.get(((TotalTraffic)commonDomain).getDomain() + "_" + ((TotalTraffic)commonDomain).getPvuv())]
                [dateMap.get(commonDomain.getDate())]
                    = ((TotalTraffic)commonDomain).getValue() ;
        }
        return chartDataArray ;
    }

    @Override
    public List<CommonField> getCommonField(String search_type, String device, String date_type, String start_date, String end_date) {
        return changeTypeCommonDomainField(totalTrafficRepository.getTotaltrafficTableRowList(device, date_type, start_date, end_date));
    }

    private List<CommonField> changeTypeCommonDomainField(List<TotalTraffic> totalTrafficList) {
        List<CommonField> commonTableRowList = new ArrayList<CommonField>();
        for(TotalTraffic t_object : totalTrafficList) {
            commonTableRowList.add(t_object) ;
        }
        return commonTableRowList;
    }

}