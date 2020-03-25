package com.hfad.teacher_app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    FirebaseAuth Auth ;
    private EditText email , password;
    private Button login ;
    private CheckBox remember ;
    private TextView t ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.unameid);
        password = findViewById(R.id.pass);
        login = findViewById(R.id.submit);
        // remember = findViewById(R.id.remem);

        login.setOnClickListener(this);
        Auth = FirebaseAuth.getInstance();
        FirebaseUser user = Auth.getCurrentUser();
        if (user != null)
        {
            finish();
            startActivity(new Intent(MainActivity.this,Middle.class));
        }
      /*  SharedPreferences sharedpref = getSharedPreferences("checkBox", Context.MODE_PRIVATE);
        String checkBox = sharedpref.getString("remember","");
        if(checkBox.equals("true"))
        {
            Intent intent = new Intent(MainActivity.this,Middle.class);
            startActivity(intent);
        }
        else if(checkBox.equals("false"))
        {
            Toast.makeText(getApplicationContext(),"please sign in ",Toast.LENGTH_SHORT);
        }

        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    SharedPreferences sharedpref = getSharedPreferences("checkBox", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpref.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "checked", Toast.LENGTH_SHORT).show();

                } else if (!buttonView.isChecked()) {
                    SharedPreferences sharedpref = getSharedPreferences("checkBox", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpref.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }
    public void addUser(View view)
    {
        Intent intent = new Intent(this,Register.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        final String eemail = email.getText().toString().trim();
        final String ppasword = password.getText().toString().trim();
        if (eemail.isEmpty()) {
            email.setText("please write email");
        } else if (ppasword.isEmpty()) {
            Toast.makeText(getApplicationContext(),"please write password",Toast.LENGTH_LONG).show();
        }
        else {

            Auth.signInWithEmailAndPassword(eemail, ppasword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
//                                findViewById(R.id.loginProg).setVisibility(View.VISIBLE);
                                Intent intent = new Intent(MainActivity.this, Middle.class);
                                finish();
                                startActivity(intent);
                            } else {

                                // findViewById(R.id.loginProg).setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }


                        }
                    });


        }

    }

}