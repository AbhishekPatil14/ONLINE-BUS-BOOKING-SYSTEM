package com.abpatil1.online_bus_resirvation_system;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;

public class PaybleActivity extends AppCompatActivity {
    private Button buttonPay;
    private TextView totalCost;
    private TextView totalSeat;
    private TextView a, b, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payble);

        // Initialize Views
        a = findViewById(R.id.textView11);
        b = findViewById(R.id.textView21);
        c = findViewById(R.id.textView31);
        totalCost = findViewById(R.id.totalCostFinal);
        totalSeat = findViewById(R.id.totalSeatsFinal);
        buttonPay = findViewById(R.id.btnPay);

        // Set up ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("You Can Pay");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Retrieve and set data from Intent
        final String total = getIntent().getStringExtra("TOTALCOST");
        final String seats = getIntent().getStringExtra("TOTALSEAT");
        final String nameBus = getIntent().getStringExtra("NAME_BUS");
        final String dateBus = getIntent().getStringExtra("DATE_BUS");
        final String conditionBus = getIntent().getStringExtra("CONDITION_BUS");

        a.setText(nameBus != null ? nameBus : "Unknown Bus");
        b.setText(dateBus != null ? dateBus : "Unknown Date");
        c.setText(conditionBus != null ? conditionBus : "Unknown Condition");

        totalCost.setText(formatValue(total, "Payable : Rs."));
        totalSeat.setText(formatValue(seats, "Number Of Seats :"));

        buttonPay.setOnClickListener(view -> {
            String total_cost = totalCost.getText().toString().replace("Payable : Rs.", "").trim();
            String total_seats = totalSeat.getText().toString().replace("Number Of Seats :", "").trim();

            Intent intent = new Intent(PaybleActivity.this, PayActivity.class);
            intent.putExtra("TOTALCOSTI", total_cost);
            intent.putExtra("NAME_BUS", nameBus);
            intent.putExtra("DATE_BUS", dateBus);
            intent.putExtra("CONDITION_BUS", conditionBus);
            intent.putExtra("TOTALSEAT", total_seats);
            startActivity(intent);
        });
    }

    private String formatValue(String value, String prefix) {
        if (value != null) {
            try {
                double doubleValue = Double.parseDouble(value);
                return prefix + " " + (doubleValue < 0 ? 0 : doubleValue);
            } catch (NumberFormatException e) {
                return prefix + " 0";
            }
        }
        return prefix + " 0";
    }
}
