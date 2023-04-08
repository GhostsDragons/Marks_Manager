package com.example.marks_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText loginEmailId, logInpasswd;
    String selectedOption;
    Button btnLogIn;
    Button forgetpassButton;
    String studentname;
    FirebaseAuth firebaseAuth;
    boolean accept;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        loginEmailId = findViewById(R.id.loginEmail);
        logInpasswd = findViewById(R.id.loginpaswd);
        btnLogIn = findViewById(R.id.btnLogIn);
        Spinner spinner = findViewById(R.id.positionselector);
        forgetpassButton = findViewById(R.id.forgetpass);
        accept = false;
        FirebaseAuth.getInstance().signOut();


        authStateListener = FirebaseAuth::getCurrentUser;

        forgetpassButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ForgetPassword.class);
            startActivity(intent);
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.position, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOption = parent.getItemAtPosition(position).toString();
                Log.d("Spinner","Selected option: " + selectedOption);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
            btnLogIn.setOnClickListener(view -> {
                String userEmail = loginEmailId.getText().toString();
                String[] parts = userEmail.split("@");
                if (parts.length == 2){
                    studentname = parts[0];
                }
                String userPaswd = logInpasswd.getText().toString();
                if (userEmail.isEmpty()) {
                    loginEmailId.setError("Provide your Email first!");
                    loginEmailId.requestFocus();
                } else if (userPaswd.isEmpty()) {
                    logInpasswd.setError("Enter Password!");
                    logInpasswd.requestFocus();
                } else if (userEmail.isEmpty() && userPaswd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(userEmail.isEmpty() && userPaswd.isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(userEmail, userPaswd).addOnCompleteListener(MainActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Not sucessfull", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                if (selectedOption.equals("Teacher")) {
                                    DatabaseReference SubRef = FirebaseDatabase.getInstance().getReference().child("Teachers/");
                                    SubRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot snapshot1: snapshot.getChildren()) {
                                                if (snapshot1.getValue().toString().equalsIgnoreCase(studentname)){
                                                    accept = true;
                                                    break;
                                                }
                                            }
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    });
                                    if(accept) {
                                        startActivity(new Intent(MainActivity.this, TeacherActivity.class));
                                    }
                                    else {
                                        Toast.makeText(MainActivity.this, "Invalid Teacher", Toast.LENGTH_SHORT).show();
                                    }
                                    }
                                else {
                                    DatabaseReference SubRef = FirebaseDatabase.getInstance().getReference().child("Students/");
                                        SubRef.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                for (DataSnapshot snapshot1: snapshot.getChildren()) {
                                                    if (snapshot1.getKey().equalsIgnoreCase(studentname)){
                                                        accept = true;
                                                        break;
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });
                                        if(accept) {
                                            Intent student = new Intent(MainActivity.this, StudentActivity.class);
                                            student.putExtra("student_name", studentname);
                                            startActivity(student);
                                        }
                                        else {
                                            Toast.makeText(MainActivity.this, "Invalid Student", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}