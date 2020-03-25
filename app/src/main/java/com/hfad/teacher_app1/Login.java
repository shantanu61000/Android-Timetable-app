package com.hfad.teacher_app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    FirebaseAuth Auth ;
    static String str ;
    static String res ;
    FirebaseDatabase db ;
    private static DatabaseReference dbref ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    /*    Intent intent = getIntent();
        str = intent.getStringExtra("message_key");*/
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            str = user.getEmail();
        }

        if(str.equals("melisa@xaviers.edu"))
        {
            res = "Melisa";
        }
        else if(str.equals("spc@xaviers.edu"))
        {
            res = "SpcT";
        }
        else if(str.equals("roy@xaviers.edu"))
        {
            res = "Roy";
        }
        else if(str.equals("rachana@xaviers.edu"))
        {
            res = "Rachana";
        }
        else if(str.equals("subhash@xaviers.edu"))
        {
            res = "Subhash";
        }
        else if(str.equals("lydia@xaviers.edu"))
        {
            res = "Lydia";
        }
        else if(str.equals("ashok@xaviers.edu"))
        {
            res="Ashok";
        }

        FirebaseDatabase db =  FirebaseDatabase.getInstance();
        DatabaseReference dbref = db.getReference();



        //dbref.child("teacherNextDayUpdate").child(res).setValue("no");

    }
    public void ohYes(View view)
    {

        FirebaseDatabase.getInstance().getReference("teacherNextDayUpdate").child(res).setValue("yes");
        Toast.makeText(this, "Update Successful", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(Login.this,Middle.class));
    }
    public void ohNo(View view)
    {
        FirebaseDatabase.getInstance().getReference("teacherNextDayUpdate").child(res).setValue("no");
        Toast.makeText(this, "Update Successful", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(Login.this,Middle.class));
    }
    public void goBack(View view)
    {
        finish();
        startActivity(new Intent(Login.this,Middle.class));
    }
}
