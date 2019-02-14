package jgb.dashboard.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@Table (name="totaltraffic")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class TotalTraffic  extends CommonDomainField{
    @Column(nullable = false)
    public String domain;

    @Column(nullable = false)
    public String pvuv;

    @Column(nullable = false)
    public Integer value;
}