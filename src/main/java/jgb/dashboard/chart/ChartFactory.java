package jgb.dashboard.chart ;

public class ChartFactory {
    public Chart makeChartType(String type) {
        if(type.equals("TotalTraffic")) 
            return new TotalTrafficChart() ;
        else if(type.equals("AvgSearch")) 
            return new AvgSearchChart();
        else if(type.equals("QmSearch")) 
            return new QmSearchChart() ;
        return null;
    }
    
}