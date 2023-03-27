package com.example.marks_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//
//public class practice<FirebaseListAdapter extends SpinnerAdapter> extends AppCompatActivity {
//
//    DatabaseReference reference;
//    Spinner examselect;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mathematics);
//
//        reference = FirebaseDatabase.getInstance().getReference();
//
//        examselect = (Spinner) findViewById(R.id.examselect);
//        DatabaseReference mref = reference.child("areas");
//
//        FirebaseListAdapter firebaseListAdapter = new FirebaseListAdapter(this, String.class, android.R.layout.simple_spinner_item, mref) {
//
//            protected void populateView(View view, String s, int position) {
//                ((TextView) view.findViewById(android.R.id.text1)).setText(s);
//            }
//        };
//        examselect.setAdapter(firebaseListAdapter);
//    }
//}

public class Mathematics extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematics);
    }
}