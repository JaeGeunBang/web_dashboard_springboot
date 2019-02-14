
package jgb.dashboard.chart;
import lombok.*;
import java.util.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AvgSearchChart  extends Chart{
    public String yAxisID;
    private int[] borderDash;
    private String fill;
    private String backgroundColor;
    private String borderColor;
}