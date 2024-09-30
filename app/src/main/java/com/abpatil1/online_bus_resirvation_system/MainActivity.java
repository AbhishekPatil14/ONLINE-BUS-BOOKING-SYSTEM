package com.abpatil1.online_bus_resirvation_system;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private CheckBox agreeCheckBox;
    private Button signupButton;
    private TextView loginOptionTextView;
    private FirebaseAuth mAuth;
    private FirebaseFirestore dbroot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        dbroot = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        usernameEditText = findViewById(R.id.Cusedt1);
        emailEditText = findViewById(R.id.CusEmail);
        passwordEditText = findViewById(R.id.CusPass);
        confirmPasswordEditText = findViewById(R.id.Cusedt4);
        agreeCheckBox = findViewById(R.id.Cuscbt1);
        signupButton = findViewById(R.id.Cusbtn1);
        loginOptionTextView = findViewById(R.id.CustextViewLoginOption);

        // Set click listeners
        signupButton.setOnClickListener(v -> signUp());
        loginOptionTextView.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void signUp() {
        // Retrieve user input
        String username = usernameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        boolean agree = agreeCheckBox.isChecked();

        // Validate user input
        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            return;
        }

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!agree) {
            Toast.makeText(this, "Please agree to terms and conditions", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new user with email and password
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign up success
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(MainActivity.this, "Signup successful", Toast.LENGTH_SHORT).show();
                        insertData(username, email);
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish(); // Finish current activity
                    } else {
                        // Handle different Firebase exceptions
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(MainActivity.this, "This email is already in use.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Signup failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void insertData(String username, String email) {
        // Collect data from input fields
        Map<String, String> items = new HashMap<>();
        items.put("Username", username);
        items.put("Email", email);

        // Insert data into Firestore
        dbroot.collection("Registration").add(items)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            // Clear input fields after successful insertion
                            usernameEditText.setText("");
                            emailEditText.setText("");
                            passwordEditText.setText("");
                            confirmPasswordEditText.setText("");
                            Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to insert data", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
