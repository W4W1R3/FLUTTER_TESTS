package com.example.ctfchaser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class SignupActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText editemail;
    EditText  editpassword;
    EditText editname;
    EditText editphone;
    FirebaseDatabase rootNode2;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        FirebaseApp.initializeApp(this); // Initialize Firebase
        rootNode2 = FirebaseDatabase.getInstance();
        reference = rootNode2.getReference("User data");
        editemail = findViewById(R.id.editTextEmail);
        editpassword = findViewById(R.id.editTextTextPassword2);
        editname = findViewById(R.id.editTextName);
        editphone = findViewById(R.id.editTextPhoneNo);
        Button login = findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        @SuppressLint("CutPasteId") Button button=findViewById(R.id.signup);

        String email =editemail.getText().toString();
        String phone = editphone.getText().toString();
        String name = editname.getText().toString();
        String password = editpassword.getText().toString();

        login.setOnClickListener(view -> {
            login.setClickable(false);
            Intent Login = new Intent(SignupActivity.this,MainActivity.class);
            startActivity(Login);
            login.setClickable(true);
            finish();
        });


        button.setOnClickListener(view -> {
            button.setClickable(false);
            createUser();
        });
    }
    private void createUser(){

        Button button=findViewById(R.id.button);
        String email =editemail.getText().toString();
        String name = editname.getText().toString();
        String phone = editphone.getText().toString();
        String password = editpassword.getText().toString();


        if (TextUtils.isEmpty(name)){
            editname.setError("Name cannot be empty");
            editname.requestFocus();
            button.setClickable(true);
            Toast.makeText(getApplicationContext(), "Please Enter name.", Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(email)) {
            editemail.setError("Email cannot be empty");
            editemail.requestFocus();
            button.setClickable(true);
            Toast.makeText(getApplicationContext(), "Please Enter your email.", Toast.LENGTH_LONG).show();
        }else if (phone.length()==13){
            Toast.makeText(getApplicationContext(), "Please Enter full phone number +254....", Toast.LENGTH_LONG).show();
        }else if (password.length() == 0) {
            editpassword.setError("Password cannot be empty");
            editpassword.requestFocus();
            button.setClickable(true);
            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_LONG).show();
        }else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Toast.makeText(SignupActivity.this, "User registered Successfully.", Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(() -> {
                        Registerclass registerclass = new Registerclass(email,name,phone);
                        reference.child(name).setValue(registerclass);

                        Intent Login = new Intent(SignupActivity.this,MainActivity.class);
                        startActivity(Login);
                        finish();
                    },2000);


                }else {

                    Toast.makeText(SignupActivity.this, "Registration Error,Account not created." + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                    button.setClickable(true);
                }

            });
        }








    }
}