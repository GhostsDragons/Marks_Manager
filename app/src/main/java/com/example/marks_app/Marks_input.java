package com.example.marks_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Marks_input extends AppCompatActivity {
    Button save, newstud;
    Spinner Test;
    TextView name;
    EditText marks;
    String selectedOption, namestring, path, subject;
    int marksint;
    TableLayout table;
    TableRow stud;
    ConstraintLayout bg;
    int cnt = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks_input);
        subject = getIntent().getStringExtra("Subject");
        bg = (ConstraintLayout) findViewById(R.id.Background);
        newstud = findViewById(R.id.newstud);

        switch (subject) {
            case "Maths":
                bg.setBackground(ContextCompat.getDrawable(this, R.drawable.math3));
                break;
            case "Biology":
                bg.setBackground(ContextCompat.getDrawable(this, R.drawable.biology));
                break;
            case "Physics":
                bg.setBackground(ContextCompat.getDrawable(this, R.drawable.physics));
                break;
            case "Chemistry":
                bg.setBackground(ContextCompat.getDrawable(this, R.drawable.chemistry));
                break;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference SubRef = FirebaseDatabase.getInstance().getReference().child("Students");
        SubRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (cnt == 0) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        String Name = snapshot1.getKey();
                        tblrw(Name);
                    }
                    cnt++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        save = findViewById(R.id.Save1);
        Test = findViewById(R.id.examselect);
        table = findViewById(R.id.Marks_Table);

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

        newstud.setOnClickListener(v-> {
            Intent Subj = new Intent(Marks_input.this, NewStudent.class);
            Subj.putExtra("Subject", "Maths");
            startActivity(Subj);
        });
        save.setOnClickListener(v-> {
            for (int i = 1; i < table.getChildCount(); i++) {
                stud = (TableRow) table.getChildAt(i);
                name = (TextView) stud.getChildAt(0);
                marks = (EditText) stud.getChildAt(1);
                namestring = name.getText().toString();
                try {
                    marksint = Integer.parseInt(marks.getText().toString());
                }
                catch(Exception E){
                    continue;
                }
                path = "Students/" + namestring + "/" + subject + "/" + selectedOption;
                DatabaseReference myRef = database.getReference(path);
                myRef.setValue(marksint);
                marks.setText("");
            }
            Toast.makeText(Marks_input.this, "Marks Updated", Toast.LENGTH_LONG).show();
        });
    }

    public void tblrw(String Name){
        stud = new TableRow(this);
        TextView Student = new TextView(this);
        Student.setText(Name);
        Student.setGravity(Gravity.CENTER);
        Student.setTextSize(35);
        Student.setTypeface( null, Typeface.BOLD);
        Student.setTextColor(getResources().getColor(R.color.black));
        Student.setPadding(25,4,20,4);
        stud.addView(Student);
        EditText Marks = new EditText(this);
        Marks.setTextSize(20);
        stud.addView(Marks);
        table.addView(stud);
    }
}