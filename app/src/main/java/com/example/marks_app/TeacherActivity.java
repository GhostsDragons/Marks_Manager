package com.example.marks_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherActivity extends AppCompatActivity {
    Button Maths, Phys, Chem, Bio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        Maths = findViewById(R.id.maths);
        Phys = findViewById(R.id.Phys);
        Chem = findViewById(R.id.Chem);
        Bio = findViewById(R.id.Bio);

        Bio.setOnClickListener(v-> {

            Intent Subj = new Intent(TeacherActivity.this, Marks_input.class);
            Subj.putExtra("Subject", "Biology");
            startActivity(Subj);
        });

        Phys.setOnClickListener(v-> {

            Intent Subj = new Intent(TeacherActivity.this, Marks_input.class);
            Subj.putExtra("Subject", "Physics");
            startActivity(Subj);
        });

        Chem.setOnClickListener(v-> {

            Intent Subj = new Intent(TeacherActivity.this, Marks_input.class);
            Subj.putExtra("Subject", "Chemistry");
            startActivity(Subj);
        });

        Maths.setOnClickListener(v-> {

            Intent Subj = new Intent(TeacherActivity.this, Marks_input.class);
            Subj.putExtra("Subject", "Maths");
            startActivity(Subj);
        });
    }

}