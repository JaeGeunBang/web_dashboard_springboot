
package jgb.dashboard.chartList;

import lombok.*;
import java.util.Random;
import java.util.*;
import jgb.dashboard.domain.*;
import jgb.dashboard.chart.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public abstract class ChartList{
    private String[] dateList ;
    private Chart[] chartData ;

    public abstract Chart makeChartWithOption(int domainIndex) ;

    public List<String> getDeduplicatedDomains(List<CommonDomainField> tableRowList) {
        List<String> list = new ArrayList<String>();
        Set<String> set = new HashSet<String>() ;
        for(CommonDomainField tableRow : tableRowList){
            if(tableRow instanceof TotalTraffic)
                set.add(((TotalTraffic)tableRow).getDomain() + "_" + ((TotalTraffic)tableRow).getPvuv()) ;
            else if(tableRow instanceof Search)
                set.add(((Search)tableRow).getValueType()) ;
        }
        list.addAll(set) ;
        Collections.sort(list);
        return list ;
    }
    public List<String> getDeduplicatedDates(List<CommonDomainField> tableRowList) {
        List<String> list = new ArrayList<String>();
        Set<String> set = new HashSet<String>() ;
        for(CommonDomainField tableRow : tableRowList){
            set.add(tableRow.getDate()) ;
        }
        list.addAll(set) ;
        Collections.sort(list);
        return list ;
    }
    public Map<String, Integer> makeDomainMap(List<String> allDomains){
        Map<String, Integer> domainMap = new HashMap<String, Integer>() ;
        for(int i = 0 ; i < allDomains.size() ; i++)
            domainMap.put(allDomains.get(i), i) ;
        return domainMap;
    }
    public Map<String, Integer> makeDateMap(List<String> allDates){
        Map<String, Integer> dateMap = new HashMap<String, Integer>() ;
        for(int i = 0 ; i < allDates.size() ; i++)
            dateMap.put(allDates.get(i), i) ;
        return dateMap;
    }
    public String makeRandomColor() {
        String randomColor = "#";
        Random generator = new Random();   
        for(int i = 0 ; i < 6 ; i++){
            int num1= generator.nextInt(15) + 1;
            if(num1 == 10) randomColor += "A";
            else if(num1 == 11) randomColor += "B";
            else if(num1 == 12) randomColor += "C";
            else if(num1 == 13) randomColor += "D";
            else if(num1 == 14) randomColor += "E";
            else if(num1 == 15) randomColor += "F";
            else randomColor += num1;
        }  
        return randomColor;
    }
    public String makeRandomRgba(double alpha){
        Random generator = new Random() ;
        int[] r_color = new int[3] ;
        for(int i = 0 ; i < r_color.length ; i++){
            int num = generator.nextInt(255) + 1;
            r_color[i] = num ;
        }

        return String.format("rgba(%d, %d, %d, %f)", r_color[0], r_color[1], r_color[2], alpha) ;
    }
}