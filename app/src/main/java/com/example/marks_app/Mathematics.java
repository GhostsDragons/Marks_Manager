package com.example.marks_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
    Button save;
    Spinner Test;
    EditText name, marks;
    String selectedOption, namestring, path;
    int marksint;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematics);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        save = findViewById(R.id.Save1);
        name = findViewById(R.id.Name);
        marks = findViewById(R.id.Marks);
        Test = findViewById(R.id.examselect);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Test, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Test.setAdapter(adapter);

        Test.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOption = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        save.setOnClickListener(v-> {
            namestring = name.getText().toString();
            marksint = Integer.parseInt(marks.getText().toString());
            path = "Mathematics/"+selectedOption+"/"+namestring;
            DatabaseReference myRef = database.getReference(path);
            myRef.setValue(marksint);
        });
    }
}