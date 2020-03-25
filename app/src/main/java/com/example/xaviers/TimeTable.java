package com.example.xaviers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Map;

public class TimeTable extends AppCompatActivity {

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        name=findViewById(R.id.name);
        FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        String u= firebaseAuth.getCurrentUser().getUid();

       db.getReference().child("StudentUser").child(u).addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               Map<String,Object> map= (Map<String, Object>) dataSnapshot.getValue();
               String na = (String) map.get("fname");

               name.setText("Welcome "+na);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

    }


    public void goNextFy(View view) {
        Intent i = new Intent ( this , Getapp.class );
        i.putExtra ( "year","fy" );
        startActivity (i);

    }

    public void goNextSy(View view) {
        Intent i = new Intent ( this , Getapp.class );
        i.putExtra ( "year","sy" );
        startActivity (i);
    }

    public void goNextTy(View view) {
        Intent i = new Intent ( this , Getapp.class );
        i.putExtra ( "year","ty" );
        startActivity (i);
    }

    public void logOut(View view) {
     FirebaseAuth.getInstance().signOut();
     finish();
     startActivity(new Intent(this,LoginPage.class));
    }
}
