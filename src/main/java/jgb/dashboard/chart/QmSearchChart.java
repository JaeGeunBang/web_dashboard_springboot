
package jgb.dashboard.chart;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class QmSearchChart  extends Chart{
    public String yAxisID;
    private int[] borderDash;
    private String backgroundColor;
    private String borderColor;
    private String fill;
    private String type;
}