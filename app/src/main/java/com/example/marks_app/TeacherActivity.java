package com.example.marks_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import com.google.android.material.tabs.TabLayout;

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

            startActivity(new Intent(TeacherActivity.this, Biology.class));
        });

        Phys.setOnClickListener(v-> {

            startActivity(new Intent(TeacherActivity.this, Physics.class));
        });

        Chem.setOnClickListener(v-> {

            startActivity(new Intent(TeacherActivity.this, Chemistry.class));
        });

        Maths.setOnClickListener(v-> {

            startActivity(new Intent(TeacherActivity.this, Mathematics.class));
        });
    }

}