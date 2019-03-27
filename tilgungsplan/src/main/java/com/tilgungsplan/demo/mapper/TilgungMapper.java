package com.tilgungsplan.demo.mapper;


import com.tilgungsplan.demo.datatransferobject.TilgungsDTO;
import com.tilgungsplan.demo.entity.TilgungDO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TilgungMapper {
    public static TilgungDO makeTilgungDO (TilgungsDTO tilgungsDTO){
        return new TilgungDO(tilgungsDTO.getDatum(), tilgungsDTO.getRestschuld(), tilgungsDTO.getZinsen(),
                tilgungsDTO.getTilgung(), tilgungsDTO.getRate());
    }

    public static TilgungsDTO makeTilgungsDTO(TilgungDO tilgungDO){

        TilgungsDTO.TilgungsDTOBuilder tilgungsDTOBuilder = TilgungsDTO.newBuilder()
                .setId(tilgungDO.getId())
                .setDatum(tilgungDO.getDatum())
                .setTilgung(tilgungDO.getTilgung())
                .setRate(tilgungDO.getRate())
                .setRestschuld(tilgungDO.getRestschuld())
                .setZinsen(tilgungDO.getZinsen());
        return tilgungsDTOBuilder.createTilgungsDTO();
    }

    public static List<TilgungsDTO> makeCarDTOList(Collection<TilgungDO> tilgungs)
    {
        return tilgungs.stream()
                .map(TilgungMapper::makeTilgungsDTO)
                .collect(Collectors.toList());
    }

}

