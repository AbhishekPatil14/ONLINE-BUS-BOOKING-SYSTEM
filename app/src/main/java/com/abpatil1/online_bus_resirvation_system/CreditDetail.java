package com.abpatil1.online_bus_resirvation_system;

public class CreditDetail {
    public String cardNumber;
    public String cardDate;
    public String cvvNumber;
    public String cardName;

    public CreditDetail(String cardNumber, String cardDate, String cvvNumber, String cardName) {
        this.cardNumber = cardNumber;
        this.cardDate = cardDate;
        this.cvvNumber = cvvNumber;
        this.cardName = cardName;
    }

}
