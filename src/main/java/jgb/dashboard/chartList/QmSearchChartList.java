
package jgb.dashboard.chartList;
import jgb.dashboard.chart.*;

public class QmSearchChartList extends ChartList{
    
    @Override
    public Chart makeChartWithOption(int domainIndex) {
        Chart chartObject = new QmSearchChart() ;

        ((QmSearchChart) chartObject).setFill("origin") ;
        
        if(domainIndex == 0 || domainIndex == 1) {
            ((QmSearchChart) chartObject).yAxisID = "A";
            String color = makeRandomRgba(1);
            ((QmSearchChart) chartObject).setBorderColor(color) ;
            ((QmSearchChart) chartObject).setBackgroundColor(color) ;
            ((QmSearchChart) chartObject).setType("line") ;
            ((QmSearchChart) chartObject).setFill("false") ;
            int[] borderDash = {};
            ((QmSearchChart) chartObject).setBorderDash(borderDash);
        }
        else {
            String color = makeRandomRgba(0.4);
            ((QmSearchChart) chartObject).setBorderColor(color) ;
            ((QmSearchChart) chartObject).setBackgroundColor(color) ;
            ((QmSearchChart) chartObject).yAxisID = "B";
            int[] borderDash = {5, 15};
            ((QmSearchChart) chartObject).setBorderDash(borderDash);
        }
        return chartObject ;
    }
}
