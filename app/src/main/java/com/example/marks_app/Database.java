package com.example.marks_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Database extends AppCompatActivity {

    TextView hi;
    // creating a variable for
    // our Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our
    // Database Reference for Firebase.
    DatabaseReference databaseReference;

    // variable for Text view.
    private TextView retrieveTV;

    @SuppressLint({"MissingInflatedId", "RestrictedApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Biology");

        myRef.setValue("NMIMS");
        String path = myRef.getPath().toString();
        Toast.makeText(Database.this, "Storage Successful", Toast.LENGTH_LONG).show();



//        // below line is used to get the instance
//        // of our Firebase database.
//        firebaseDatabase = FirebaseDatabase.getInstance();
//
//        // below line is used to get
//        // reference for our database.
//        databaseReference = firebaseDatabase.getReference("Child/Hari/Age");
//
//        // initializing our object class variable.
//        retrieveTV = findViewById(R.id.textView2);
//
//        // calling method
//        // for getting data.
//        getdata();
    }
//    private void getKeyValues(){
//
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("promotion").child("discount");
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String key = dataSnapshot.getKey();
//
//                for (DataSnapshot child: dataSnapshot.getChildren()){
//                    //Object object = child.getKey();
//                    labelGetKey.setText(child.getKey());
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.e("Read Fail", "Error");
//            }
//        });
//    }
//    private void getdata() {
//
//        // calling add value event listener method
//        // for getting the values from database.
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                // this method is call to get the realtime
//                // updates in the data.
//                // this method is called when the data is
//                // changed in our Firebase console.
//                // below line is for getting the data from
//                // snapshot of our database.
//                String value = snapshot.getValue(String.class);
//
//                // after getting the value we are setting
//                // our value to our text view in below line.
//                retrieveTV.setText(value);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // calling on cancelled method when we receive
//                // any error or we are not able to get the data.
////                Toast.makeText(Database.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
//                retrieveTV.setText("Error");
//            }
//        });
//    }
}