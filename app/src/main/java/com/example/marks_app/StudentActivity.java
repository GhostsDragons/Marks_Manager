package com.example.marks_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.widget.*;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class StudentActivity extends AppCompatActivity {

    TableLayout table;
    TableRow stud;
    int marks[];
    String subject[], Name = "Hari";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        subject = getResources().getStringArray(R.array.Subject);

        for (String i : subject){
            DatabaseReference SubRef = FirebaseDatabase.getInstance().getReference().child("Students/"+Name+"/"+i);
            SubRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    marks = new int[3];
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        if (snapshot1.getKey().toString().equals("UT1")){
                            marks[0] = Integer.parseInt(snapshot1.getValue().toString());
                        }
                        else if (snapshot1.getKey().toString().equals("UT2")){
                            marks[1] = Integer.parseInt(snapshot1.getValue().toString());
                        }
                        else {
                            marks[2] = Integer.parseInt(snapshot1.getValue().toString());
                        }
                    }
                    tblrw(i, marks);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
                });
            }
        }

        public void tblrw(String Subject, int[] marks){
            table = findViewById(R.id.Marks_Table);
            stud = new TableRow(this);
            TextView subject = new TextView(this);
            subject.setText(Subject);
            subject.setAllCaps(true);
            subject.setGravity(Gravity.CENTER);
            subject.setTextSize(25);
            stud.addView(subject);
            for (int i = 0; i < 3; i++) {
                TextView Mark = new TextView(this);
                Mark.setText(""+marks[i]);
                Mark.setTextSize(20);
                Mark.setGravity(Gravity.CENTER);
                stud.addView(Mark);
            }
            table.addView(stud);
        }

}