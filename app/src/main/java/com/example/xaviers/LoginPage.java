package com.example.xaviers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity {
    FirebaseAuth Auth ;
    private EditText email , password;
    private Button login ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.unameid);
        password = findViewById(R.id.pass);
        login = findViewById(R.id.submit);

        FirebaseUser user = Auth.getCurrentUser();
        if(user!=null){
            finish();
            startActivity(new Intent(this,TimeTable.class));
        }

    }
    public void addUser(View view)
    {
        Intent intent = new Intent(this,Register.class);
        finish();
        startActivity(intent);
    }


    public void logOn (View v) {
        findViewById(R.id.loading).setVisibility(View.VISIBLE);
        final String eemail = email.getText().toString().trim();
        final String ppasword = password.getText().toString().trim();
        if(eemail.isEmpty())
        {
            email.setError("email is required");
            email.requestFocus();
        }
        else if(ppasword.isEmpty())
        {
            password.setError("password is required");
            password.requestFocus();
        }
        else{
            Auth.signInWithEmailAndPassword(eemail, ppasword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                            //    findViewById(R.id.loginProgress).setVisibility(View.VISIBLE);
                                Intent intent = new Intent(LoginPage.this, TimeTable.class);
                                intent.putExtra("message_key", eemail);
                                finish();
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginPage.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                              //  findViewById(R.id.loginProgress).setVisibility(View.GONE);

                            }


                        }

                    });


        }

    }
}

