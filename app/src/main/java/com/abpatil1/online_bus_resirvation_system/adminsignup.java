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

public class adminsignup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adminsignup);

        Button btnsignup = findViewById(R.id.btn1);
        TextView btnsignin = findViewById(R.id.textViewLoginOption);
        EditText et1 = findViewById(R.id.edt1);
        EditText et2 = findViewById(R.id.edt2);
        EditText et3 = findViewById(R.id.edt3);
        EditText et4 = findViewById(R.id.edt4);
        EditText et5 = findViewById(R.id.edt5);
        DBHelper DB = new DBHelper(this);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = et1.getText().toString();
                String username = et2.getText().toString();
                String email = et3.getText().toString();
                String password = et4.getText().toString();
                String code = et5.getText().toString();

                if (fullname.equals("") ||email.equals("")||username.equals("")||password.equals("") || code.equals(""))
                    Toast.makeText(adminsignup.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkadminuser = DB.checkadminusername(username);
                    if (checkadminuser==false){
                        if (code.equals("12345678")){
                            Boolean insert = DB.insertadmin(fullname,email,username,password);
                            if (insert==true){
                                Toast.makeText(adminsignup.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), adminlogin.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(adminsignup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(adminsignup.this, "The Admin Code is Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(adminsignup.this, "User Already exists please signin", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), adminlogin.class);
                        startActivity(intent);
                    }
                }

            }
        });
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), adminlogin.class);
                startActivity(intent);
            }
        });
    }
}