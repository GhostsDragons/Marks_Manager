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

    int Marks;
    String Subject;

    public Database() {
    }

    public Database(int marks, String subject) {
        Marks = marks;
        Subject = subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public void setMarks(int marks) {
        Marks = marks;
    }

    public String getSubject() {
        return Subject;
    }

    public int getMarks() {
        return Marks;
    }
}