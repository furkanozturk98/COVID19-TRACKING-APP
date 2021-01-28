package com.example.coronavirustracking.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coronavirustracking.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    EditText email, password, passwordRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.Email);
        password = findViewById(R.id.password);
        passwordRepeat = findViewById(R.id.passwordRepeat);

    }

    public void registerClick(View view){

        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        String passwordRepeatString = passwordRepeat.getText().toString();

        if(emailString.isEmpty() && passwordString.isEmpty()){
            Toast.makeText(RegisterActivity.this, "Please fill all the fields!" ,Toast.LENGTH_LONG).show();
            return;
        }


        if(passwordString.contains(passwordRepeatString)){
            firebaseAuth.createUserWithEmailAndPassword(emailString,passwordString).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(RegisterActivity.this, "User Created" ,Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterActivity.this, e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });

        }
        else {
            Toast.makeText(RegisterActivity.this, "Passwords does not match!",Toast.LENGTH_LONG).show();
        }

    }
}