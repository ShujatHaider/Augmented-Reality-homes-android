package com.example.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class seller extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.seller,container,false);

    }

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText name,phone,address;
    Button button;
    record seler;
    Button btnLocation;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("data", 0);
        name=getActivity().findViewById(R.id.name1);
        phone=getActivity().findViewById(R.id.phone1);
        address=getActivity().findViewById(R.id.address1);
        button=getActivity().findViewById(R.id.submit1);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        btnLocation=getView().findViewById(R.id.btnlocation);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              fragmentManager=getFragmentManager();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame,new sellerlocation());
                fragmentTransaction.commit();

            }
        });
        final DatabaseReference myRef = database.getReference().child("records");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cust=0;
                seler=new record(name.getText().toString(),phone.getText().toString(),address.getText().toString());
                cust=sharedPreferences.getInt("cust",0)+1;
                editor=sharedPreferences.edit();
                editor.putInt("cust",cust);
                editor.putInt("check",1);
                editor.apply();
                myRef.push().setValue(seler);
                Toast.makeText(getActivity(), "Data entered", Toast.LENGTH_LONG).show();

// Write a message to the database

            }
        });
    }

}
