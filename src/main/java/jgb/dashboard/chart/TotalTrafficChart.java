
package jgb.dashboard.chart;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TotalTrafficChart extends Chart{
    public String yAxisID;
    private int[] borderDash;
    private String fill;
    private String backgroundColor;
    private String borderColor;
}
