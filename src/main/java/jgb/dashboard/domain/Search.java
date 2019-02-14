package jgb.dashboard.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@Table (name="search")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class Search extends CommonDomainField{
    @Column(nullable = false)
    private String valueType; 

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private Integer searchType;
}