package com.abpatil1.online_bus_resirvation_system;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.abpatil1.online_bus_resirvation_system.MainActivity;
import com.abpatil1.online_bus_resirvation_system.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView signupTextView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        emailEditText = findViewById(R.id.CusEmail);
        passwordEditText = findViewById(R.id.CusPass);
        loginButton = findViewById(R.id.CusLogin);
        signupTextView = findViewById(R.id.CusSignup);

        // Set click listeners
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }

    private void login() {
        // Retrieve username and password entered by the user
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate username and password
        if (!email.isEmpty() && !password.isEmpty()) {
            // Perform login operation
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Login success
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, Front_Page.class));
                        } else {
                            // If login fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            // Display an error message if username or password is empty
            Toast.makeText(this, "Username and password are required", Toast.LENGTH_SHORT).show();
        }
    }
}
