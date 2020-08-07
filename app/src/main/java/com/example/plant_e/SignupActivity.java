package com.example.plant_e;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {


    private EditText email,password,confirmpassword;
    private Button signupBtn;
    private TextView signinpage;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        signupBtn = findViewById(R.id.SignupBtn);
        signinpage = findViewById(R.id.SignInPage);
        confirmpassword = findViewById(R.id.confirmpassword);


        signinpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext = email.getText().toString();
                String passwordtext = password.getText().toString();
                String confirmpasswordtext = confirmpassword.getText().toString();
                if(emailtext.isEmpty()){
                    email.setError("Please enter email");
                    email.requestFocus();

                }
                if(passwordtext.isEmpty()){
                    password.setError("Please enter password");
                    password.requestFocus();

                }
                if(confirmpasswordtext.isEmpty()){
                    confirmpassword.setError("Please confirm your password");
                    confirmpassword.requestFocus();

                }
                if(emailtext.isEmpty() && passwordtext.isEmpty() && confirmpasswordtext.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Some fields are empty!", Toast.LENGTH_LONG).show();
                }

                else if(!(emailtext.isEmpty() && passwordtext.isEmpty() && confirmpasswordtext.isEmpty())){
                    if(passwordtext.equals(confirmpasswordtext)){
                        mFirebaseAuth.createUserWithEmailAndPassword(emailtext,passwordtext).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(SignupActivity.this, "Sign up unsuccessful", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(SignupActivity.this, "Account has been successfully created!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignupActivity.this,MainActivity.class));

                                }
                            }
                        });

                    }
                    else{
                        confirmpassword.setError("Passwords do not match!");
                        confirmpassword.requestFocus();
                        password.requestFocus();
                    }
                }

                else{Toast.makeText(SignupActivity.this, "Error occured. Please try again1!", Toast.LENGTH_SHORT).show();}
            }
        });

    }
}
