
package jgb.dashboard.domain;

import javax.persistence.*;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CommonDomainField {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String dailyWeeklyMonthly;

    @Column(nullable = false)
    private String device ; 

    @Column(nullable = false)
    private String date ; 
}