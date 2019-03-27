package com.tilgungsplan.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TILGUNGSPLAN")
public class TilgungDO {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "Datum", nullable = false)
    private Date datum;

    @Column( name = "Restschuld", nullable = false)
    private double restschuld;

    @Column(name = "Zinsen", nullable = false)
    private double zinsen;

    @Column(name = "Tilgung", nullable = false)
    private double tilgung;

    @Column(name = "Rate", nullable = false)
    private double rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getRestschuld() {
        return restschuld;
    }

    public void setRestschuld(double restschuld) {
        this.restschuld = restschuld;
    }

    public double getZinsen() {
        return zinsen;
    }

    public void setZinsen(double zinsen) {
        this.zinsen = zinsen;
    }

    public double getTilgung() {
        return tilgung;
    }

    public void setTilgung(double tilgung) {
        this.tilgung = tilgung;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

   public TilgungDO(Date datum, double restschuld, double zinsen, double tilgung, double rate) {

        this.datum = datum;
        this.restschuld = restschuld;
        this.zinsen = zinsen;
        this.tilgung = tilgung;
        this.rate = rate;
    }

    public TilgungDO(){}
}


