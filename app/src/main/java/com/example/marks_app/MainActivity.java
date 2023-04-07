package com.example.marks_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<CharSequence> adapter;
    EditText loginEmailId, logInpasswd;
    String selectedOption;
    Button btnLogIn;
    Button forgetpassButton;
    String studentname;
    FirebaseAuth firebaseAuth;
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
        FirebaseAuth.getInstance().signOut();



        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    Toast.makeText(MainActivity.this, "User logged in ", Toast.LENGTH_SHORT).show();
////                    Intent I = new Intent(MainActivity.this, UserActivity.class);
////                    startActivity(I);
//                } else {
//                    Toast.makeText(MainActivity.this, "Login to continue", Toast.LENGTH_SHORT).show();
//                }
            }
        };
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent I = new Intent(MainActivity.this, MainActivity.class);
//                startActivity(I);
//            }
//        });
        forgetpassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgetPassword.class);
                startActivity(intent);
            }
        });

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
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
            btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                                if (selectedOption.compareTo("Teacher") == 0) {
                                    startActivity(new Intent(MainActivity.this, TeacherActivity.class));
                                }
                                else {
                                    Intent student = new Intent(MainActivity.this, StudentActivity.class);
                                    student.putExtra("student_name", studentname);
                                    startActivity(student);
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}