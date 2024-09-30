package com.abpatil1.online_bus_resirvation_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Booking extends AppCompatActivity {

    private TextInputLayout from, to, date;
    private EditText fromEditText, toEditText, dateEditText;
    private Button searchBusesButton;

    FirebaseFirestore dbroot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);  // Assuming your XML file is named activity_booking.xml

        // Initialize Firestore
        dbroot = FirebaseFirestore.getInstance();

        // Initialize views
        from = findViewById(R.id.BkgFrom);
        to = findViewById(R.id.BkgTo);
        date= findViewById(R.id.BkgDate);

        fromEditText = from.getEditText();
        toEditText = to.getEditText();
        dateEditText = date.getEditText();

        searchBusesButton = findViewById(R.id.BkgSearch);

        // Set onClick listener for the button
        searchBusesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                searchBuses();
                insertdata();
                Intent intent = new Intent(getApplicationContext(), FINDBUS.class);
                startActivity(intent);

            }
        });
    }

    public void insertdata() {
        // Collect data from input fields
        Map<String, String> items = new HashMap<>();
        items.put("From", fromEditText.getText().toString().trim());
        items.put("To", toEditText.getText().toString().trim());
        items.put("Date", dateEditText.getText().toString().trim());

        // Insert data into Firestore
        dbroot.collection("Booking").add(items)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            // Clear input fields after successful insertion
                            fromEditText.setText("");
                            toEditText.setText("");
                            dateEditText.setText("");
                            Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to insert data", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void searchBuses() {
        // Get input data
        String fromLocation = fromEditText.getText().toString().trim();
        String toLocation = toEditText.getText().toString().trim();
        String travelDate = dateEditText.getText().toString().trim();

        // Validate input
        if (fromLocation.isEmpty()) {
            from.setError("Please enter a starting location");
            return;
        } else {
            from.setError(null);
        }

        if (toLocation.isEmpty()) {
            to.setError("Please enter a destination");
            return;
        } else {
            to.setError(null);
        }

        if (travelDate.isEmpty()) {
            date.setError("Please select a travel date");
            return;
        } else {
            date.setError(null);
        }

        // Process the search (this is where you would typically connect to Firebase or another backend service)
        Toast.makeText(this, "Searching buses from " + fromLocation + " to " + toLocation + " on " + travelDate, Toast.LENGTH_SHORT).show();

        // Implement your search logic here (e.g., querying a database, making a network request, etc.)
    }
}
