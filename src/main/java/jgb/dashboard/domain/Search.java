package jgb.dashboard.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@Table (name="search")
@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Search{
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String dailyWeeklyMonthly;

    @Column(nullable = false)
    private String device ; 

    @Column(nullable = false)
    private String date ; 

    @Column(nullable = false)
    private String valueType; 

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private Integer searchType;
}