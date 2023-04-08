package com.example.marks_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class  ForgetPassword extends AppCompatActivity {
Button reset;
TextView email;
String emailAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        reset = findViewById(R.id.reset_password_button);
        email = findViewById(R.id.email_input);
        reset.setOnClickListener(v-> {
            emailAddress = email.getText().toString();
            FirebaseAuth auth = FirebaseAuth.getInstance();

            auth.sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgetPassword.this, "Email Sent", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}