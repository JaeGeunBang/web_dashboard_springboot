
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
public class SearchService{
    @Autowired
    SearchRepository searchRepository ;

    /* getAvgChartList()
    조건에 해당하는 차트 데이터를 반환
    input: 
    - device: PC, Mobile
    - date_type: daily, weekly, monthly
    - start_date: 시작 날짜
    - end_date: 종료 날짜
    output:
    - ChartList 반환 
    */
    public ChartList getAvgChartData(String search_type, String device, String date_type, String start_date, String end_date){
        // Search 리스트 반환 후 valueArray 생성
        List<Search> searchList = null;
        searchList = searchRepository.getSearch(search_type, device, date_type, start_date, end_date) ;
        
        // 도메인 리스트, Map 생성
        List<String> domainList = getDomains(searchList) ;
        Map<String, Integer> domainMap = new HashMap<String, Integer>() ;
        for(int i = 0 ; i < domainList.size() ; i++)
            domainMap.put(domainList.get(i), i) ;

        // 날짜 리스트, Map 생성
        List<String> dateList = getDates(searchList) ;
        Map<String, Integer> dateMap = new HashMap<String, Integer>() ;
        for(int i = 0 ; i < dateList.size() ; i++)
            dateMap.put(dateList.get(i), i) ;

        double[][] valueArray = new double[domainList.size()][dateList.size()];
        
        for(Search search : searchList) {
            valueArray[domainMap.get(search.getValueType())]
                [dateMap.get(search.getDate())]
                    = search.getValue() ;
        }
        ChartList chartList = new ChartList() ;
        chartList.setDateList(dateList.toArray(new String[dateList.size()])) ;
        Chart[] avgSearchChart = new AvgSearchChart[domainList.size()];

        for(int i = 0 ; i < domainList.size() ; i++)
        {
            Chart c_object = new AvgSearchChart() ;
            if(!domainList.get(i).equals("평균 서치스")){
                //c_object.setYAxisID("A");
                ((AvgSearchChart) c_object).yAxisID = "A";
                int[] borderDash = {};
                ((AvgSearchChart) c_object).setBorderDash(borderDash);
            }
            else{ 
                //c_object.setYAxisID("B");
                ((AvgSearchChart) c_object).yAxisID = "B";
                int[] borderDash = {5, 15};
                ((AvgSearchChart) c_object).setBorderDash(borderDash);
            }
            c_object.setLabel(domainList.get(i));
            c_object.setData(valueArray[i]) ;
            ((AvgSearchChart) c_object).setBackgroundColor(chartList.makeRandomColor()) ;
            ((AvgSearchChart) c_object).setBorderColor(((AvgSearchChart) c_object).getBackgroundColor()) ;
            ((AvgSearchChart) c_object).setFill("false") ;
            avgSearchChart[i] = c_object;
        }

        chartList.setChartData(avgSearchChart) ;
        return chartList;
    }

    /* getQmChartData()
    조건에 해당하는 차트 데이터를 반환
    input: 
    - device: PC, Mobile
    - date_type: daily, weekly, monthly
    - start_date: 시작 날짜
    - end_date: 종료 날짜
    output:
    - ChartList 반환 
    */
    public ChartList getQmChartData(String search_type, String device, String date_type, String start_date, String end_date){
        // Search 리스트 반환 후 valueArray 생성
        List<Search> searchList = null;
        searchList = searchRepository.getSearch(search_type, device, date_type, start_date, end_date) ;

        // 도메인 리스트, Map 생성
        List<String> domainList = getDomains(searchList) ;
        Map<String, Integer> domainMap = new HashMap<String, Integer>() ;
        for(int i = 0 ; i < domainList.size() ; i++)
            domainMap.put(domainList.get(i), i) ;

        // 날짜 리스트, Map 생성
        List<String> dateList = getDates(searchList) ;
        Map<String, Integer> dateMap = new HashMap<String, Integer>() ;
        for(int i = 0 ; i < dateList.size() ; i++)
            dateMap.put(dateList.get(i), i) ;

        double[][] valueArray = new double[domainList.size()][dateList.size()];
        
        for(Search search : searchList) {
            valueArray[domainMap.get(search.getValueType())]
                [dateMap.get(search.getDate())]
                    = search.getValue() ;
        }
        ChartList chartList = new ChartList() ;
        chartList.setDateList(dateList.toArray(new String[dateList.size()])) ;
        Chart[] qmSearchChartArray = new QmSearchChart[domainList.size()];

        for(int i = 0 ; i < domainList.size() ; i++)
        {
            Chart c_object = new QmSearchChart() ;
            c_object.setLabel(domainList.get(i));
            c_object.setData(valueArray[i]) ;
            
            ((QmSearchChart) c_object).setFill("origin") ;
            
            if(domainList.get(i).equals("전체 서치스") || domainList.get(i).equals("포커스 서치스")) {
                ((QmSearchChart) c_object).yAxisID = "A";
                String color = chartList.makeRandomRgba(1);
                ((QmSearchChart) c_object).setBorderColor(color) ;
                ((QmSearchChart) c_object).setBackgroundColor(color) ;
                ((QmSearchChart) c_object).setType("line") ;
                ((QmSearchChart) c_object).setFill("false") ;
                int[] borderDash = {};
                ((QmSearchChart) c_object).setBorderDash(borderDash);
            }
            else {
                String color = chartList.makeRandomRgba(0.4);
                ((QmSearchChart) c_object).setBorderColor(color) ;
                ((QmSearchChart) c_object).setBackgroundColor(color) ;
                ((QmSearchChart) c_object).yAxisID = "B";
                int[] borderDash = {5, 15};
                ((QmSearchChart) c_object).setBorderDash(borderDash);
            }
            qmSearchChartArray[i] = c_object;
        }

        chartList.setChartData(qmSearchChartArray) ;
        return chartList;
    }

    private List<String> getDomains(List<Search> searchList)
    {
        List<String> list = new ArrayList<String>();
        Set<String> set = new HashSet<String>() ;
        for(Search search : searchList){
            set.add(search.getValueType()) ;
        }
        list.addAll(set) ;
        Collections.sort(list);
        return list ;
    }

    private List<String> getDates(List<Search> searchList) 
    {
        List<String> list = new ArrayList<String>();
        Set<String> set = new HashSet<String>() ;
        for(Search search : searchList){
            set.add(search.getDate()) ;
        }
        list.addAll(set) ;
        Collections.sort(list);
        return list ;
    }
}