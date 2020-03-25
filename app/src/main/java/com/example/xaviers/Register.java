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
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private EditText fname, lname , email  , phone, password ;
    private Button submit ;
    FirebaseAuth Auth ;

    Member member ;

    String userID ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fname = findViewById(R.id.fnamev);
        lname = findViewById(R.id.lnamev);
        email = findViewById(R.id.emailid);
        phone = findViewById(R.id.phoneid);
        password = findViewById(R.id.password);
        submit = (Button) findViewById(R.id.resid);
        submit.setOnClickListener(this);
        Auth = FirebaseAuth.getInstance();

    }
    public void letLogin(View view)
    {
        Intent intent = new Intent(this,LoginPage.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        final String ffname = fname.getText().toString();
        final String llname = lname.getText().toString();
        final String eemail = email.getText().toString().trim();
        final  String pphone = phone.getText().toString();
        final String ppasword = password.getText().toString().trim();


        if(eemail.isEmpty())
        {
            email.setText("please write email");

        }
        else if(ppasword.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"please write password",Toast.LENGTH_LONG).show();

        }
        else if(ffname.isEmpty())
        {
            fname.setText("please write first name");

        }
        else if(llname.isEmpty())
        {
            lname.setText("please write last name");

        }
        else if(pphone.isEmpty())
        {
            phone.setText("please write phone number");

        }
        else if(ppasword.contains(ffname))
        {
            Toast.makeText(getApplicationContext(),"enter correct password",Toast.LENGTH_LONG).show();
        }
        else if(!isValidPassword(ppasword))
        {
            Toast.makeText(getApplicationContext(),"uppercase , lowercasee , no and a special character",Toast.LENGTH_LONG).show();
        }
        else if(!isValidPhone(pphone))
        {
            Toast.makeText(getApplicationContext(),"enter valid phone no ",Toast.LENGTH_LONG).show();
        }
        else if(!isValidEmail(eemail))
        {
            email.setText("enter valid email");
        }
        else {
            // register user in firebase
            Auth.createUserWithEmailAndPassword(eemail, ppasword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                      //  findViewById(R.id.regProgress).setVisibility(View.VISIBLE);
                        member = new Member(ffname, llname, eemail, pphone);
                        FirebaseDatabase.getInstance().getReference("StudentUser")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(member).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(Register.this, LoginPage.class));
                            }
                        });

                    } else {
                        //findViewById(R.id.regProgress).setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            });


        }
    }
    public boolean isValidPassword(String password)
    {
        Pattern pattern ;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public boolean isValidPhone(String phone)
    {
        Pattern pattern ;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=\\S+$).{10,13}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    public boolean isValidEmail(String email)
    {
        Pattern pattern ;
        Matcher matcher;

        final String PASSWORD_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
