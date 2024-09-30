package com.abpatil1.online_bus_resirvation_system;
public class PaymentDetail {
    public String total_cost;
    public String total_seats;

    public PaymentDetail(String total_cost, String total_seats) {
        this.total_cost = total_cost;
        this.total_seats = total_seats;
    }

    public PaymentDetail() {
    }
}
