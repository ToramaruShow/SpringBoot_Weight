package springboot.boostrap.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springboot.boostrap.model.WeightInfo;

@Repository
public interface WeightInfoRepository extends JpaRepository<WeightInfo, Integer> {
	@Query(value = "select * from weight where regist_year=:year and regist_month=:month and regist_day=:day ORDER BY regist_year, regist_month, regist_day", nativeQuery = true)
	public List<WeightInfo> findYearMonth(@Param("year") Integer year, @Param("month") Integer month, @Param("day") Integer day);
	@Query(value = "select * from weight where regist_year=:year and regist_month=:month ORDER BY regist_year, regist_month, regist_day", nativeQuery = true)
    public List<WeightInfo> findYearMonthNoDay(@Param("year") Integer year, @Param("month") Integer month);
}
