package com.abpatil1.online_bus_resirvation_system;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class PayActivity extends AppCompatActivity {
    private Button buttonOffer;
    private Button buttonCredit;
    private Button buttonDebit;
    private Button buttonNetBanking;
    private Button buttonWallets;
    private TextView textViewTotal;
    private TextView a, b, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Pay Information");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        buttonOffer = findViewById(R.id.buttonOffer);
        buttonCredit = findViewById(R.id.buttonCredit);
        buttonDebit = findViewById(R.id.buttonDebit);
        buttonNetBanking = findViewById(R.id.buttonNetBanking);
        buttonWallets = findViewById(R.id.buttonWallets);

        textViewTotal = findViewById(R.id.textViewTotal);
        a = findViewById(R.id.textView1);
        b = findViewById(R.id.textView2);
        c = findViewById(R.id.textView3);

        final String nameBus = getIntent().getStringExtra("NAME_BUS");
        final String dateBus = getIntent().getStringExtra("DATE_BUS");
        final String conditionBus = getIntent().getStringExtra("CONDITION_BUS");

        a.setText(nameBus);
        b.setText(dateBus);
        c.setText(conditionBus);

        String total = getIntent().getStringExtra("TOTALCOSTI");
        textViewTotal.setText(total);

        // Set up a common click listener for all buttons
        View.OnClickListener paymentClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayActivity.this, CreditActivity.class);
                intent.putExtra("NAME_BUS", nameBus);
                intent.putExtra("DATE_BUS", dateBus);
                intent.putExtra("CONDITION_BUS", conditionBus);
                startActivity(intent);
            }
        };

        // Attach the click listener to each button
        buttonOffer.setOnClickListener(paymentClickListener);
        buttonCredit.setOnClickListener(paymentClickListener);
        buttonDebit.setOnClickListener(paymentClickListener);
        buttonNetBanking.setOnClickListener(paymentClickListener);
        buttonWallets.setOnClickListener(paymentClickListener);
    }
}
