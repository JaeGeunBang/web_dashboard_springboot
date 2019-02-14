package jgb.dashboard.chartList ;

public class ChartListFactory {
    public ChartList makeChartListType(String type) {
        if(type.equals("TotalTraffic")) 
            return new TotalTrafficChartList() ;
        else if(type.equals("AvgSearch")) 
            return new AvgSearchChartList();
        else if(type.equals("QmSearch")) 
            return new QmSearchChartList() ;
        return null;
    }
    
}