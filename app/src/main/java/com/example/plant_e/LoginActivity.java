package com.example.plant_e;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText email,password,confirmpassword;
    private Button signinBtn;
    private TextView signuppage;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.Email1);
        password = findViewById(R.id.Password1);
        signinBtn = findViewById(R.id.SigninBtn1);
        signuppage = findViewById(R.id.SignUpPage1);




        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailtext = email.getText().toString();
                String passwordtext = password.getText().toString();
                if(emailtext.isEmpty()){
                    email.setError("Please enter email");
                    email.requestFocus();

                }
                if(passwordtext.isEmpty()){
                    password.setError("Please enter password");
                    password.requestFocus();

                }
                if(emailtext.isEmpty() && passwordtext.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Some fields are empty!", Toast.LENGTH_LONG).show();
                }

                else if(!(emailtext.isEmpty() && passwordtext.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(emailtext,passwordtext).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login unsuccessful. Please try again!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                        }
                    });

                }


                else{Toast.makeText(LoginActivity.this, "Error occured. Please try again!", Toast.LENGTH_SHORT).show();}
            }
        });

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null){
                    Toast.makeText(LoginActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                }
                else{

                }
            }
        };

        signuppage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

    }

    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
