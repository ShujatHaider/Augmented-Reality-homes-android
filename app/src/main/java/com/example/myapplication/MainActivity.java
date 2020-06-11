package com.example.myapplication;

//import android.support.v4.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Button seller,buyer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        fragmentManager = getFragmentManager();

        myRef.setValue("Hello, World!");

        seller=findViewById(R.id.seller);
        buyer=findViewById(R.id.buyer);

        seller.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                seller.setVisibility(View.INVISIBLE);
                buyer.setVisibility(View.INVISIBLE);
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frame, new seller());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        buyer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                seller.setVisibility(View.INVISIBLE);
                buyer.setVisibility(View.INVISIBLE);
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frame,new buyer());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        getFragemntStackClear();
        seller.setVisibility(View.VISIBLE);
        buyer.setVisibility(View.VISIBLE);

    }


    public void getFragemntStackClear(){
        Fragment fragment = fragmentManager.findFragmentById(R.id.frame);
        Fragment fragment1 = fragmentManager.findFragmentById(R.id.frame);

       while (fragment != null)
       {
           fragmentTransaction = fragmentManager.beginTransaction();
           fragmentTransaction.remove(fragment);
           fragmentTransaction.commit();
       }
    }

}
