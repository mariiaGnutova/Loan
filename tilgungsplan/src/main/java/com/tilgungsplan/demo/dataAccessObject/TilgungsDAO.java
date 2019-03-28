package com.tilgungsplan.demo.dataAccessObject;

import com.tilgungsplan.demo.entity.TilgungDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TilgungsDAO extends CrudRepository<TilgungDO, Long> {

    List<TilgungDO> findByDatum (Date datum);

    @Query(value = "SELECT ROUND(SUM(" + "RATE" +"),2) FROM TILGUNGSPLAN WHERE ID>1", nativeQuery = true)
    double sumRate();

    @Query(value = "SELECT ROUND(SUM( ZINSEN ),2) FROM TILGUNGSPLAN", nativeQuery = true)
    double sumZinsen();

    @Query(value = "SELECT ROUND(SUM (TILGUNG) ,2) FROM TILGUNGSPLAN WHERE ID>1", nativeQuery = true)
    double sumTilgung();

}
