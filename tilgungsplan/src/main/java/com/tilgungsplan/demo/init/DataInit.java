package com.tilgungsplan.demo.init;

import com.tilgungsplan.demo.entity.TilgungDO;
import com.tilgungsplan.demo.output.TableGeneration;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.tilgungsplan.demo.dataAccessObject.TilgungsDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

@Component
public class DataInit implements ApplicationRunner {


    private TilgungsDAO tilgungsDAO;
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private Calendar calendar;
    public void DataInit(){}


    public DataInit(TilgungsDAO tilgungsDAO) {
        this.tilgungsDAO = tilgungsDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Date date = dateFormat.parse("30.11.2015");
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        double darlehen = getDarlehen()*-1;
        TilgungDO firstTilgungDO = new TilgungDO();
        firstTilgungDO.setDatum(date);
        firstTilgungDO.setRestschuld(darlehen);
        firstTilgungDO.setZinsen(0.00);
        firstTilgungDO.setTilgung(darlehen);
        firstTilgungDO.setRate(darlehen);
        tilgungsDAO.save(firstTilgungDO);

        double rate = Math.round(darlehen*-0.0412/12 * 100.0) / 100.0;

        double zinsen = Math.round(tilgungsDAO.findById(firstTilgungDO.getId()).get().getRestschuld()*-0.0212/12
                * 100.0) / 100.0;

        double restschuld = Math.round((tilgungsDAO.findById(firstTilgungDO.getId()).get().getRestschuld() + rate - zinsen
        ) * 100.0) / 100.0;

        date = nextMonth(firstTilgungDO);
        double til = Math.round((rate - zinsen) * 100.0) / 100.0;

        for (int i = 1; i <= 120; i++){

            TilgungDO tilgungDO = new TilgungDO();
            tilgungDO.setDatum(date);
            tilgungDO.setRate(rate);
            tilgungDO.setRestschuld(restschuld);
            tilgungDO.setTilgung(til);
            tilgungDO.setZinsen(zinsen);
            tilgungsDAO.save(tilgungDO);

            zinsen = Math.round(tilgungsDAO.findById(tilgungDO.getId()).get().getRestschuld()*-0.0212/12
                    * 100.0) / 100.0;
            til = Math.round((rate - zinsen) * 100.0) / 100.0;;
            restschuld = Math.round((tilgungsDAO.findById(tilgungDO.getId()).get().getRestschuld() + til) * 100.0) / 100.0;
            date = nextMonth(tilgungDO);
        }

        TableGeneration test = new TableGeneration(tilgungsDAO);
        test.createTable();
    }

    public double getDarlehen () {
        Scanner in = new Scanner(System.in);
        System.out.print("\n\nDer Darlehensbetrag: ");
        double d = in.nextDouble();
        String s = String.valueOf((int)d);
        while (!s.matches("\\d*")){
            System.out.print("\n\nDer Darlehensbetrag: ");
            d = in.nextDouble();
            s = String.valueOf((int)d);
        }

        return d;
    }

    public Date nextMonth (TilgungDO tilgungDO){
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        tilgungsDAO.save(tilgungDO);
        return calendar.getTime();
    }
}
