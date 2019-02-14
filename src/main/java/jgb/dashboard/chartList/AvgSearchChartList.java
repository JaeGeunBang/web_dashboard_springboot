
package jgb.dashboard.chartList;
import jgb.dashboard.chart.*;

public class AvgSearchChartList extends ChartList{
    
    @Override
    public Chart makeChartWithOption(int domainIndex) {
        Chart chartObject = new AvgSearchChart() ;
        
        if(domainIndex == 1){
            ((AvgSearchChart) chartObject).yAxisID = "A";
            int[] borderDash = {};
            ((AvgSearchChart) chartObject).setBorderDash(borderDash);
        }
        else{ 
            ((AvgSearchChart) chartObject).yAxisID = "B";
            int[] borderDash = {5, 15};
            ((AvgSearchChart) chartObject).setBorderDash(borderDash);
        }
        ((AvgSearchChart) chartObject).setBackgroundColor(makeRandomColor()) ;
        ((AvgSearchChart) chartObject).setBorderColor(((AvgSearchChart) chartObject).getBackgroundColor()) ;
        ((AvgSearchChart) chartObject).setFill("false") ;
        
        return chartObject ;
    }
}
