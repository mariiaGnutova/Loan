package com.tilgungsplan.demo.dataAccessObject;

import com.tilgungsplan.demo.entity.RepaymentDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RepaymentDAO extends CrudRepository<RepaymentDO, Long> {

    List<RepaymentDO> findByDate (Date date);  // findByDatum

    @Query(value = "SELECT ROUND(SUM(RATE),2) FROM REPAYMENTPLAN WHERE ID>1", nativeQuery = true)
    double sumRate();

    @Query(value = "SELECT ROUND(SUM(Interest),2) FROM REPAYMENTPLAN", nativeQuery = true)
    double sumInterest();

    @Query(value = "SELECT ROUND(SUM (Repayment) ,2) FROM REPAYMENTPLAN WHERE ID>1", nativeQuery = true)
    double sumRepayment();

}
