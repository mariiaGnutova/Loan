package com.tilgungsplan.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REPAYMENTPLAN")  // TILGUNGSPLAN
public class RepaymentDO {  // TildungDO

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "Date", nullable = false)
    private Date date;  // datum

    @Column( name = "RemainingDebt", nullable = false)
    private double remainingDebt;  // Restschuld

    @Column(name = "Interest", nullable = false)
    private double interest;  // Zinsen

    @Column(name = "Repayment", nullable = false)
    private double repayment;  // Tildung

    @Column(name = "Rate", nullable = false)
    private double rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }  // getDatum

    public void setDate(Date date) {
        this.date = date;
    }  // setDatum

    public double getRemainingDebt() {
        return remainingDebt;
    }

    public void setRemainingDebt(double remainingDebt) {
        this.remainingDebt = remainingDebt;
    }  // setRestschuld

    public double getInterest() {
        return interest;
    }  // getZinsen

    public void setInterest(double interest) {
        this.interest = interest;
    }  // setZinsen

    public double getRepayment() {
        return repayment;
    }  // getTildung

    public void setRepayment(double repayment) {
        this.repayment = repayment;
    }  // setTildung

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

   public RepaymentDO(Date date, double remainingDebt, double interest, double repayment, double rate) {

        this.date = date;
        this.remainingDebt = remainingDebt;
        this.interest = interest;
        this.repayment = repayment;
        this.rate = rate;
    }

    public RepaymentDO(){}
}


