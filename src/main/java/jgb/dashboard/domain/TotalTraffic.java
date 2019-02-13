package jgb.dashboard.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@Table (name="totaltraffic")
@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class TotalTraffic{
    @Id
    @GeneratedValue
    public Integer id;

    @Column(nullable = false)
    public String dailyWeeklyMonthly;

    @Column(nullable = false)
    public String device ;

    @Column(nullable = false)
    public String date ;

    @Column(nullable = false)
    public String domain;

    @Column(nullable = false)
    public String pvuv;

    @Column(nullable = false)
    public Integer value;
}