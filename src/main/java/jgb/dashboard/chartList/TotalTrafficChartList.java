
package jgb.dashboard.chartList;
import jgb.dashboard.chart.*;

public class TotalTrafficChartList extends ChartList{
    
    @Override
    public Chart makeChartWithOption(int domainIndex) {
        Chart chartObject = new TotalTrafficChart() ;
        
        if(domainIndex % 2 == 0){
            ((TotalTrafficChart) chartObject).yAxisID = "A";
            int[] borderDash = {};
            ((TotalTrafficChart) chartObject).setBorderDash(borderDash);
        }
        else{ 
            ((TotalTrafficChart) chartObject).yAxisID = "B";
            int[] borderDash = {5, 15};
            ((TotalTrafficChart) chartObject).setBorderDash(borderDash);
        }
        
        ((TotalTrafficChart) chartObject).setBorderColor(((TotalTrafficChart) chartObject).getBackgroundColor()) ;
        ((TotalTrafficChart) chartObject).setFill("false") ;

        return chartObject ;
    }
}
