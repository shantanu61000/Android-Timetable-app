package com.hfad.teacher_app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Middle extends AppCompatActivity {
FirebaseAuth auth ;
TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle);
        auth = FirebaseAuth.getInstance();

        name=findViewById(R.id.name);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        String u= auth.getCurrentUser().getUid();

        db.getReference().child("Member").child(u).addListenerForSingleValueEvent(new ValueEventListener() {
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
    public void addUp(View view)
    {
        Intent intent = new Intent(Middle.this,Login.class);
        startActivity(intent);
    }
    public void addNew1(View view)
    {
        Intent intent = new Intent(Middle.this,TimeTable.class);
        startActivity(intent);
    }
    public void addLog(View view)
    {
        auth.signOut();
        finish();
        startActivity(new Intent(this,MainActivity.class));

    }


}
