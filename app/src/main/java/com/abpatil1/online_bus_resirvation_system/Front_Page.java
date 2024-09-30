package com.abpatil1.online_bus_resirvation_system;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

public class Front_Page extends AppCompatActivity {

    private CardView cardView1, cardView2, cardView3, cardView4, cardView5, cardView6, cardView7, cardView8, cardView9,cardView10;


    private Button button1,button2;

    private DrawerLayout drawerLay;
    private ImageButton bdtoggle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        // Initialize CardViews
        cardView1 = findViewById(R.id.Card_view1);
        cardView2 = findViewById(R.id.Card_view2);
        cardView3 = findViewById(R.id.Card_view3);
        cardView4 = findViewById(R.id.Card_view4);
        cardView5 = findViewById(R.id.Card_view5);
        cardView6 = findViewById(R.id.Card_view6);
        cardView7 = findViewById(R.id.Card_view7);
        cardView8 = findViewById(R.id.Card_view8);
        cardView9 = findViewById(R.id.Card_view9);
        cardView10 = findViewById(R.id.Card_view10);

        button1=findViewById(R.id.Cusbtn1);
        button2=findViewById(R.id.btn2);

        drawerLay=findViewById(R.id.drawerLayout);
        bdtoggle=findViewById(R.id.buttonDrawerToggle);

        // Set click listeners for each CardView
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Front_Page.this,Booking.class));

            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Front_Page.this,Cancellation.class));
            }
        });


        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Front_Page.this,Status.class));
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Front_Page.this,Track.class));
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Front_Page.this,RefundStatus.class));
            }
        });

        cardView6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Front_Page.this,Review.class));
            }
        });

        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Front_Page.this,Virtual.class));
            }
        });

        cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Front_Page.this,TimeTable.class));
            }
        });

        cardView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Front_Page.this,CurrentBooking.class));
            }
        });
        cardView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Front_Page.this,BusPass.class));
            }
        });

        bdtoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLay.open();
            }
        });



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(Front_Page.this,MainActivity.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Front_Page.this,LoginActivity.class));
            }
        });






    }
}
