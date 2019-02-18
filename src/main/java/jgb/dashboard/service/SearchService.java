
package jgb.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jgb.dashboard.repository.*;
import jgb.dashboard.domain.*;

import java.util.*;

@Service
@Qualifier("SearchService")
@Transactional
public class SearchService extends AbstractService{
    @Autowired
    SearchRepository searchRepository ;

    @Override
    public double[][] makeChartDataArray(List<CommonField> commonTableRowList, Map<String, Integer> domainMap, int deduplicatedDomainsSize, 
        Map<String, Integer> dateMap, int deduplicatedDatesSize){
        double[][] chartDataArray = new double[deduplicatedDomainsSize][deduplicatedDatesSize];

        for(CommonField commonDomain : commonTableRowList) {
            chartDataArray[domainMap.get(((Search)commonDomain).getValueType())]
                [dateMap.get(commonDomain.getDate())]
                    = ((Search)commonDomain).getValue() ;
        }
        return chartDataArray ;
    }

    @Override
    public List<CommonField> getCommonField(String search_type, String device, String date_type, String start_date, String end_date) {
        // 임시로
        if(search_type.equals("QmSearch"))
            return changeTypeCommonDomainField(searchRepository.getSearchTableRowList("0", device, date_type, start_date, end_date));
        else
            return changeTypeCommonDomainField(searchRepository.getSearchTableRowList("1", device, date_type, start_date, end_date));
    }

    private List<CommonField> changeTypeCommonDomainField(List<Search> totalTrafficList) {
        List<CommonField> commonTableRowList = new ArrayList<CommonField>();
        for(Search t_object : totalTrafficList) {
            commonTableRowList.add(t_object) ;
        }
        return commonTableRowList;
    }

    
}