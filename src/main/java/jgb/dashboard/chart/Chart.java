
package jgb.dashboard.chart;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Chart{
    private String label;
    private double[] data;
}
