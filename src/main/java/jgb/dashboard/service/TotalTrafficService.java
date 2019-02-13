
package jgb.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jgb.dashboard.repository.*;
import jgb.dashboard.domain.*;
import jgb.dashboard.chart.*;

import java.util.*;

@Service
@Transactional
public class TotalTrafficService{
    @Autowired
    TotalTrafficRepository totalTrafficRepository ;

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
    public ChartList getChartData(String device, String date_type, String start_date, String end_date){
        // totalTraffic 리스트 반환 후 valueArray 생성
        List<TotalTraffic> totalTrafficList = null;
        totalTrafficList = totalTrafficRepository.getTotaltraffic(device, date_type, start_date, end_date) ;
        
        // 도메인 리스트, Map 생성
        List<String> domainList = getDomains(totalTrafficList) ;
        Map<String, Integer> domainMap = new HashMap<String, Integer>() ;
        for(int i = 0 ; i < domainList.size() ; i++)
            domainMap.put(domainList.get(i), i) ;

        // 날짜 리스트, Map 생성
        List<String> dateList = getDates(totalTrafficList) ;
        Map<String, Integer> dateMap = new HashMap<String, Integer>() ;
        for(int i = 0 ; i < dateList.size() ; i++)
            dateMap.put(dateList.get(i), i) ;

        // 7(일주일치 날짜) * 50 (도메인 수)
        double[][] valueArray = new double[domainList.size()][dateList.size()];

        for(TotalTraffic totalTraffic : totalTrafficList) {
            valueArray[domainMap.get(totalTraffic.getDomain() + "_" + totalTraffic.getPvuv())]
                [dateMap.get(totalTraffic.getDate())]
                    = totalTraffic.getValue() ;
        }
        
        ChartList chartList = new ChartList() ;
        chartList.setDateList(dateList.toArray(new String[dateList.size()])) ;
        Chart[] totalTrafficCharts = new TotalTrafficChart[domainList.size()];

        for(int i = 0 ; i < domainList.size() ; i++)
        {
            Chart c_object = new TotalTrafficChart() ;
            c_object.setLabel(domainList.get(i));
            if(i % 2 == 0){
                //c_object.setYAxisID("A");
                ((TotalTrafficChart) c_object).yAxisID = "A";
                int[] borderDash = {};
                ((TotalTrafficChart) c_object).setBorderDash(borderDash);
            }
            else{ 
                //c_object.setYAxisID("B");
                ((TotalTrafficChart) c_object).yAxisID = "B";
                int[] borderDash = {5, 15};
                ((TotalTrafficChart) c_object).setBorderDash(borderDash);
            }
            ((TotalTrafficChart) c_object).setBackgroundColor(chartList.makeRandomColor()) ;
            ((TotalTrafficChart) c_object).setBorderColor(((TotalTrafficChart) c_object).getBackgroundColor()) ;
            ((TotalTrafficChart) c_object).setFill("false") ;
            c_object.setData(valueArray[i]) ;
            totalTrafficCharts[i] = c_object;
        }

        chartList.setChartData(totalTrafficCharts) ;
        return chartList;
    }

    private List<String> getDomains(List<TotalTraffic> totalTrafficList)
    {
        List<String> list = new ArrayList<String>();
        Set<String> set = new HashSet<String>() ;
        for(TotalTraffic totalTraffic : totalTrafficList){
            set.add(totalTraffic.getDomain() + "_" + totalTraffic.getPvuv()) ;
        }
        list.addAll(set) ;
        Collections.sort(list);
        return list ;
    }
    
    private List<String> getDates(List<TotalTraffic> totalTrafficList) 
    {
        List<String> list = new ArrayList<String>();
        Set<String> set = new HashSet<String>() ;
        for(TotalTraffic totalTraffic : totalTrafficList){
            set.add(totalTraffic.getDate()) ;
        }
        list.addAll(set) ;
        Collections.sort(list);
        return list ;
    }
}