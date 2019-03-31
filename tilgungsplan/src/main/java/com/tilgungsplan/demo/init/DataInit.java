package com.tilgungsplan.demo.init;

import com.tilgungsplan.demo.entity.RepaymentDO;
import com.tilgungsplan.demo.output.TableGeneration;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.tilgungsplan.demo.dataAccessObject.RepaymentDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

@Component
public class DataInit implements ApplicationRunner {


    private RepaymentDAO repaymentDAO;
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private Calendar calendar;
    public void DataInit(){}


    public DataInit(RepaymentDAO repaymentDAO) {
        this.repaymentDAO = repaymentDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        double debitInterest = 0.0212;  // 2.12%
        double initialRepayment = 0.02;  //2%
        int monthInYear = 12;
        Date date = dateFormat.parse("30.11.2015");
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        double loanValue = getLoanValue()*-1;
        RepaymentDO firstRepaymentDO = new RepaymentDO();
        // for first Month
        firstRepaymentDO.setDate(date);
        firstRepaymentDO.setRemainingDebt(loanValue);
        firstRepaymentDO.setInterest(0.00);
        firstRepaymentDO.setRepayment(loanValue);
        firstRepaymentDO.setRate(loanValue);
        repaymentDAO.save(firstRepaymentDO);

        double rate = Math.round(loanValue*-1*(initialRepayment + debitInterest)/monthInYear * 100.0) / 100.0;

        double interest = Math.round(repaymentDAO.findById(firstRepaymentDO.getId()).get().getRemainingDebt()*-1* debitInterest/monthInYear
                * 100.0) / 100.0;

        double remainingDebt = Math.round((repaymentDAO.findById(firstRepaymentDO.getId()).get().getRemainingDebt() + rate - interest
        ) * 100.0) / 100.0;

        date = nextMonth(firstRepaymentDO);
        double til = Math.round((rate - interest) * 100.0) / 100.0;

        // 10 Years = 120 Month
        for (int month = 1; month <= 120; month++){

            RepaymentDO repaymentDO = new RepaymentDO();
            repaymentDO.setDate(date);
            repaymentDO.setRate(rate);
            repaymentDO.setRemainingDebt(remainingDebt);
            repaymentDO.setRepayment(til);
            repaymentDO.setInterest(interest);
            repaymentDAO.save(repaymentDO);

            interest = Math.round(repaymentDAO.findById(repaymentDO.getId()).get().getRemainingDebt()*-1*debitInterest/12
                    * 100.0) / 100.0;
            til = Math.round((rate - interest) * 100.0) / 100.0;;
            remainingDebt = Math.round((repaymentDAO.findById(repaymentDO.getId()).get().getRemainingDebt() + til) * 100.0) / 100.0;
            date = nextMonth(repaymentDO);
        }

        TableGeneration test = new TableGeneration(repaymentDAO);
        test.createTable();

    }

    public double getLoanValue() {
        Scanner in = new Scanner(System.in);
        String s;
        do {
            System.out.print("\n\nDer Darlehensbetrag: ");
            s = in.nextLine();
        }
        while (!s.matches("\\d*"));

        return Double.parseDouble(s);
    }

    public Date nextMonth (RepaymentDO repaymentDO){
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        repaymentDAO.save(repaymentDO);
        return calendar.getTime();
    }
}
