package com.tilgungsplan.demo.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TilgungsDTO {
    private Long id;
    private Date datum;
    private double restschuld;
    private double zinsen;
    private double tilgung;
    private double rate;

    public TilgungsDTO(Long id, Date datum, double restschuld, double zinsen, double tilgung, double rate) {
        this.id = id;
        this.datum = datum;
        this.restschuld = restschuld;
        this.zinsen = zinsen;
        this.tilgung = tilgung;
        this.rate = rate;
    }

    public static TilgungsDTOBuilder newBuilder()
    {
        return new TilgungsDTOBuilder();
    }


    @JsonProperty
    public Long getId() {
        return id;
    }

    public Date getDatum() {
        return datum;
    }


    public double getRestschuld() {
        return restschuld;
    }


    public double getZinsen() {
        return zinsen;
    }


    public double getTilgung() {
        return tilgung;
    }



    public double getRate() {
        return rate;
    }




    public static class TilgungsDTOBuilder{
        private Long id;
        private Date datum;
        private double restschuld;
        private double zinsen;
        private double tilgung;
        private double rate;

        public TilgungsDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public TilgungsDTOBuilder setDatum(Date datum) {
            this.datum = datum;
            return this;
        }

        public TilgungsDTOBuilder setRestschuld(double restschuld) {
            this.restschuld = restschuld;
            return this;
        }

        public TilgungsDTOBuilder setZinsen(double zinsen) {
            this.zinsen = zinsen;
            return this;
        }

        public TilgungsDTOBuilder setTilgung(double tilgung) {
            this.tilgung = tilgung;
            return this;
        }

        public TilgungsDTOBuilder setRate(double rate) {
            this.rate = rate;
            return this;
        }

        public TilgungsDTO createTilgungsDTO(){
            return new TilgungsDTO(id, datum, restschuld, zinsen, tilgung, rate);
        }
    }
}
