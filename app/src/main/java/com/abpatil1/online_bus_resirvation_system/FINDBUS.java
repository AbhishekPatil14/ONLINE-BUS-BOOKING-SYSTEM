package com.abpatil1.online_bus_resirvation_system;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import java.util.HashMap;
import java.util.Map;

public class FINDBUS extends AppCompatActivity {

    private ImageView backButton;
    private ImageView filterButton;
    private TextView titleTextView;
    private CardView cardView1, cardView2, cardView3, cardView4, cardView5;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findbus); // Ensure the layout name matches your XML

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize views
        backButton = findViewById(R.id.toolbar).findViewById(R.id.fndbusback);
        filterButton = findViewById(R.id.toolbar).findViewById(R.id.fndbusfilter);
        titleTextView = findViewById(R.id.fndbustxt1);

        cardView1 = findViewById(R.id.cardView);
        cardView2 = findViewById(R.id.cardView2);
        cardView3 = findViewById(R.id.cardView3);
        cardView4 = findViewById(R.id.cardView4);
        cardView5 = findViewById(R.id.cardView5);

        // Set up click listeners
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Booking.class);
                startActivity(intent);
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your filter logic here
            }
        });

        setupCardViewClickListeners();
    }

    private void setupCardViewClickListeners() {

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeBusDetails("Bus 1", "10:00 AM", "2:00 PM", 40);
                navigateToSelectSeat();
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeBusDetails("Bus 2", "11:00 AM", "3:00 PM", 35);
                navigateToSelectSeat();
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeBusDetails("Bus 3", "12:00 PM", "4:00 PM", 30);
                navigateToSelectSeat();
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeBusDetails("Bus 4", "1:00 PM", "5:00 PM", 25);
                navigateToSelectSeat();
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeBusDetails("Bus 5", "2:00 PM", "6:00 PM", 20);
                navigateToSelectSeat();
            }
        });
    }

    private void storeBusDetails(String busName, String departureTime, String arrivalTime, int seatAvailability) {
        Map<String, Object> busDetails = new HashMap<>();
        busDetails.put("busName", busName);
        busDetails.put("departureTime", departureTime);
        busDetails.put("arrivalTime", arrivalTime);
        busDetails.put("seatAvailability", seatAvailability);

        db.collection("buses").add(busDetails)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Firestore", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firestore", "Error adding document", e);
                    }
                });
    }

    private void navigateToSelectSeat() {
        Intent intent = new Intent(getApplicationContext(), SELECTSEAT.class);
        startActivity(intent);
    }
}
