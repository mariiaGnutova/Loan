package com.tilgungsplan.demo.dataAccessObject;

import com.tilgungsplan.demo.entity.TilgungDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TilgungsDAO extends CrudRepository<TilgungDO, Long> {

    public List<TilgungDO> findByDatum (Date datum);

}
