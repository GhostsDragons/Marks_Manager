package com.example.marks_app;

import android.annotation.SuppressLint;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Physics extends AppCompatActivity {
    Button save;
    Spinner Test;
    TextView name;
    EditText marks;
    String selectedOption, namestring, path;
    int marksint;
    TableLayout table;
    TableRow stud;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference SubRef = FirebaseDatabase.getInstance().getReference().child("Students");
        SubRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    String Name = snapshot1.getKey().toString();
                    tblrw(Name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        save = findViewById(R.id.Save1);
        name = findViewById(R.id.Name);
        marks = findViewById(R.id.Marks);
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

        save.setOnClickListener(v-> {
            for (int i = 1; i < table.getChildCount(); i++) {
                stud = (TableRow) table.getChildAt(i);
                name = (TextView) stud.getChildAt(0);
                marks = (EditText) stud.getChildAt(1);
                namestring = name.getText().toString();
                marksint = Integer.parseInt(marks.getText().toString());
                path = "Students/" + namestring + "/" + "Physics/" + selectedOption;
                DatabaseReference myRef = database.getReference(path);
                myRef.setValue(marksint);
            }
            Toast.makeText(Physics.this, "Marks Updated", Toast.LENGTH_LONG).show();
            name.setText("");
            marks.setText("");
        });
    }

    public void tblrw(String Name){
        stud = new TableRow(this);
        TextView Student = new TextView(this);
        Student.setText(Name);
        Student.setAllCaps(true);
        Student.setGravity(Gravity.CENTER);
        Student.setTextSize(25);
        stud.addView(Student);
        EditText Marks = new EditText(this);
        Marks.setTextSize(20);
        Marks.setGravity(Gravity.CENTER);
        stud.addView(Marks);
        table.addView(stud);
    }
}