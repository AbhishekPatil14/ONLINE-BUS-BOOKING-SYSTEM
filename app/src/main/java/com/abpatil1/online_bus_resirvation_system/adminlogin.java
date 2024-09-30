package com.abpatil1.online_bus_resirvation_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class adminlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adminlogin);

        EditText username = findViewById(R.id.AdmUser);
        EditText password = findViewById(R.id.AdmPass);
        Button Login = findViewById(R.id.AdmLogin);
        TextView signup = findViewById(R.id.AdmSignup);
        Button back = findViewById(R.id.AdmBack);
        DBHelper DB = new DBHelper(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AdmUser = username.getText().toString();
                String AdmPass = password.getText().toString();

                if (AdmUser.equals("") || AdmPass.equals(""))
                    Toast.makeText(adminlogin.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkadminuserpass = DB.checkadminusernamepassword(AdmUser, AdmPass);
                    if (checkadminuserpass==true){
                        Toast.makeText(adminlogin.this, "LogIn Successful", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(getApplicationContext(), adminpanel.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(adminlogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LOGINBOTH.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), adminsignup.class);
                startActivity(intent);
            }
        });

    }
}