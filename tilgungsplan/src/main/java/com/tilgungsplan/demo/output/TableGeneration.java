package com.tilgungsplan.demo.output;

import com.tilgungsplan.demo.dataAccessObject.RepaymentDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TableGeneration{

    private RepaymentDAO repaymentDAO;

    public TableGeneration(RepaymentDAO repaymentDAO) {
        this.repaymentDAO = repaymentDAO;

    }

    public void createTable() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date c;
        String date = new String();
        System.out.printf("\n%s %27s %11s %30s %7s %n", "Date", "Restschuld", "Zinsen", "Tilgung(+)/Auszahlung(-)",
                "Rate");
        for (long i = 1; i < 4; i++) {

            c = repaymentDAO.findById(i).get().getDate();
            date = dateFormat.format(c);
            System.out.printf("\n%7s %20s %13s %20s %17s %n", date,
                    repaymentDAO.findById(i).get().getRemainingDebt(), repaymentDAO.findById(i).get().getInterest(),
                    repaymentDAO.findById(i).get().getRepayment(), repaymentDAO.findById(i).get().getRate());
        }
        System.out.printf("%40s %170s", "...", "");

        for (long y = repaymentDAO.count() - 1; y < repaymentDAO.count() + 1; y++) {
            c = repaymentDAO.findById(y).get().getDate();
            date = dateFormat.format(c);
            System.out.printf("\n%7s %20s %13s %20s %17s %n", date,
                    repaymentDAO.findById(y).get().getRemainingDebt(), repaymentDAO.findById(y).get().getInterest(),
                    repaymentDAO.findById(y).get().getRepayment(), repaymentDAO.findById(y).get().getRate());
        }
        System.out.printf("\n%7s %14s %14s %20s %17s %n", "Zinsbindungsende", repaymentDAO.findById(
                repaymentDAO.count()).get().getRemainingDebt(), repaymentDAO.sumInterest(), repaymentDAO.sumRepayment(),
                repaymentDAO.sumRate());
    }

}
