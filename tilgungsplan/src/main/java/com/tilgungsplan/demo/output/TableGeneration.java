package com.tilgungsplan.demo.output;

import com.tilgungsplan.demo.dataAccessObject.TilgungsDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TableGeneration{

    private TilgungsDAO tilgungsDAO;

    public TableGeneration(TilgungsDAO tilgungsDAO) {
        this.tilgungsDAO = tilgungsDAO;

    }

    public void createTable() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date c;
        String date = new String();
        System.out.printf("\n%s %27s %11s %30s %7s %n", "Date", "Restschuld", "Zinsen", "Tilgung(+)/Auszahlung(-)",
                "Rate");
        for (long i = 1; i < 4; i++) {

            c = this.tilgungsDAO.findById(i).get().getDatum();
            date = dateFormat.format(c);
            System.out.printf("\n%7s %20s %13s %20s %17s %n", date,
                    this.tilgungsDAO.findById(i).get().getRestschuld(), this.tilgungsDAO.findById(i).get().getZinsen(),
                    this.tilgungsDAO.findById(i).get().getTilgung(), this.tilgungsDAO.findById(i).get().getRate());
        }
        System.out.printf("%40s %140s", "...", "");

        for (long y = tilgungsDAO.count() - 1; y < tilgungsDAO.count() + 1; y++) {
            c = this.tilgungsDAO.findById(y).get().getDatum();
            date = dateFormat.format(c);
            System.out.printf("\n%7s %20s %13s %20s %17s %n", date,
                    this.tilgungsDAO.findById(y).get().getRestschuld(), this.tilgungsDAO.findById(y).get().getZinsen(),
                    this.tilgungsDAO.findById(y).get().getTilgung(), this.tilgungsDAO.findById(y).get().getRate());
        }
        System.out.printf("\n%7s %14s %14s %20s %17s %n", "Zinsbindungsende", this.tilgungsDAO.findById(
                tilgungsDAO.count()).get().getRestschuld(), this.tilgungsDAO.sumZinsen(), this.tilgungsDAO.sumTilgung(),
                this.tilgungsDAO.sumRate());
    }

}
