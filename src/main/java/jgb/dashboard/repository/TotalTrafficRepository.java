
package jgb.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jgb.dashboard.domain.TotalTraffic;
import java.util.*;

@Repository
public interface TotalTrafficRepository extends JpaRepository<TotalTraffic, Integer> {
    @Query(value = "SELECT * FROM totaltraffic x WHERE x.device = :device AND x.daily_Weekly_Monthly = :date_type AND x.date >= :start_date AND x.date <= :end_date"
        + " ORDER BY x.date, x.device, x.domain, x.pvuv", nativeQuery = true)
    List<TotalTraffic> getTotaltraffic(
        @Param("device") String device,
        @Param("date_type") String date_type,
        @Param("start_date") String start_date,
        @Param("end_date") String end_date
    ) ;
}