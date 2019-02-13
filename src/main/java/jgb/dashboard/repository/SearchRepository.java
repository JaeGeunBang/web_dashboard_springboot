
package jgb.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jgb.dashboard.domain.Search;
import java.util.*;

@Repository
public interface SearchRepository extends JpaRepository<Search, Integer> {
    @Query(value = "SELECT * FROM search x WHERE x.device = :device AND x.daily_Weekly_Monthly = :date_type"
        + " AND x.date >= :start_date AND x.date <= :end_date"
        + " AND x.search_type = :search_type ORDER BY x.date, x.value_type, x.device", nativeQuery = true)
    List<Search> getSearch(
        @Param("search_type") String search_type,
        @Param("device") String device,
        @Param("date_type") String date_type,
        @Param("start_date") String start_date,
        @Param("end_date") String end_date
    ) ;
}