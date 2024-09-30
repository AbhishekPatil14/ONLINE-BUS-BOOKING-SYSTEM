package com.abpatil1.online_bus_resirvation_system;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashSet;
import java.util.Set;

public class SELECTSEAT extends AppCompatActivity {

    GridLayout mainGrid;
    Double seatPrice = 900.00;
    Double totalCost = 0.0;
    int totalSeats = 0;
    TextView totalPrice;
    TextView totalBookedSeats;
    private Button buttonBook;
    private Set<Integer> selectedSeats = new HashSet<>();

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectseat);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Select Your Favorite Seats");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        mainGrid = findViewById(R.id.mainGrid);
        totalBookedSeats = findViewById(R.id.total_seats);
        totalPrice = findViewById(R.id.total_cost);
        buttonBook = findViewById(R.id.btnBook);

        setToggleEvent(mainGrid);

        Bundle extras = getIntent().getExtras();
        final String nameBus = extras != null ? extras.getString("NAME_BUS") : "";
        final String dateBus = extras != null ? extras.getString("DATE_BUS") : "";
        final String conditionBus = extras != null ? extras.getString("CONDITION_BUS") : "";

        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String totalPriceI=totalPrice.getText().toString().trim();
                String totalBookedSeatsI=totalBookedSeats.getText().toString().trim();

                PaymentDetail paymentDetail=new PaymentDetail(totalPriceI,totalBookedSeatsI);

                FirebaseUser user=firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).child("SeatDetails").setValue(paymentDetail);

                Intent intent=new Intent(SELECTSEAT.this,PaybleActivity.class);
                intent.putExtra("TOTALCOST",totalPriceI);
                intent.putExtra("TOTALSEAT",totalBookedSeatsI);

                intent.putExtra("NAME_BUS",nameBus);
                intent.putExtra("DATE_BUS",dateBus);
                intent.putExtra("CONDITION_BUS",conditionBus);

                startActivity(intent);

            }
        });

    }


    private void setToggleEvent(GridLayout mainGrid) {
        int whiteColor = Color.WHITE;
        int greenColor = Color.GREEN;

        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(view -> {
                if (selectedSeats.contains(finalI)) {
                    cardView.setCardBackgroundColor(whiteColor);
                    selectedSeats.remove(finalI);
                    totalCost -= seatPrice;
                    totalSeats--;
                    Toast.makeText(SELECTSEAT.this, "You Unselected Seat Number: " + (finalI + 1), Toast.LENGTH_SHORT).show();
                } else {
                    cardView.setCardBackgroundColor(greenColor);
                    selectedSeats.add(finalI);
                    totalCost += seatPrice;
                    totalSeats++;
                    Toast.makeText(SELECTSEAT.this, "You Selected Seat Number: " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }

                totalPrice.setText(String.format("%.2f", totalCost));
                totalBookedSeats.setText(String.valueOf(totalSeats));

                Log.d("SELECTSEAT", "Total Seats: " + totalSeats + ", Total Cost: " + totalCost);
            });
        }
    }
}
