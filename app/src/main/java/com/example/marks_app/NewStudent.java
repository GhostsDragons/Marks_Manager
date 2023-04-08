package com.example.marks_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class NewStudent extends AppCompatActivity {
    Button save;
    Spinner Test;
    EditText name, marks;
    String selectedOption, namestring, path, subject;
    int marksint;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);
        subject = getIntent().getStringExtra("Subject");

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
            path = "Students/"+namestring+"/"+subject+  "/"+selectedOption;
            DatabaseReference myRef = database.getReference(path);
            myRef.setValue(marksint);
            Toast.makeText(NewStudent.this, "Marks Updated", Toast.LENGTH_LONG).show();
            name.setText("");
            marks.setText("");
        });
    }
}
