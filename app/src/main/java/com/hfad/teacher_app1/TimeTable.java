package com.hfad.teacher_app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

public class TimeTable extends AppCompatActivity {
    TextView lec1, lec2, lec3, lec4, lec5, lec6, lec7, lec8, lec9, lec1val, lec2val, lec3val, lec4val, lec5val, lec6val, lec7val, lec8val, lec9val;

    static String user_name;
    String[] lectureTime = new String[9];
    Date date = new Date();
    int d = date.getDay();
    String day = "";


    @Override
    public boolean navigateUpTo(Intent upIntent) {
        return super.navigateUpTo(upIntent);
    }

    static String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        if (d == 1) day = "monday";
        else if (d == 2) day = "tuesday";
        else if (d == 3) day = "wednesday";
        else if (d == 4) day = "thursday";
        else if (d == 5) day = "friday";
        else day = "saturday";


        lec1 = findViewById(R.id._1);
        lec2 = findViewById(R.id._2);
        lec3 = findViewById(R.id._3);
        lec4 = findViewById(R.id._4);
        lec5 = findViewById(R.id._5);
        lec6 = findViewById(R.id._6);
        lec7 = findViewById(R.id._7);
        lec8 = findViewById(R.id._8);
        lec9 = findViewById(R.id._9);

        lec1val = findViewById(R.id.lec1);
        lec2val = findViewById(R.id.lec2);
        lec3val = findViewById(R.id.lec3);
        lec4val = findViewById(R.id.lec4);
        lec5val = findViewById(R.id.lec5);
        lec6val = findViewById(R.id.lec6);
        lec7val = findViewById(R.id.lec7);
        lec8val = findViewById(R.id.lec8);
        lec9val = findViewById(R.id.lec9);
        lectureTime[0] = "0800";
        lectureTime[1] = "0850";
        lectureTime[2] = "0940";
        lectureTime[3] = "1030";
        lectureTime[4] = "1120";
        lectureTime[5] = "1210";
        lectureTime[6] = "1300";
        lectureTime[7] = "1350";
        lectureTime[8] = "1440";

        lec1.setText(lectureTime[0]);
        lec2.setText(lectureTime[1]);
        lec3.setText(lectureTime[2]);
        lec4.setText(lectureTime[3]);
        lec5.setText(lectureTime[4]);
        lec6.setText(lectureTime[5]);
        lec7.setText(lectureTime[6]);
        lec8.setText(lectureTime[7]);
        lec9.setText(lectureTime[8]);

        lec1val.setText("-");

        lec2val.setText("-");

        lec3val.setText("-");

        lec4val.setText("-");

        lec5val.setText("-");

        lec6val.setText("-");

        lec7val.setText("-");

        lec8val.setText("-");

        lec9val.setText("-");


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String s = user.getUid();
        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = db.getReference();
        dbRef.child("Member").child(s).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Map<String, Object> mMap = (Map<String, Object>) dataSnapshot.getValue();

                user_name = (String) mMap.get("fname");


                DatabaseReference dbRef = db.getReference();
                dbRef.child("students").child("fy").child("sub-fac").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String, Object> mMap = (Map<String, Object>) dataSnapshot.getValue();
                        String subject = (String) mMap.get(user_name);
                        String sub1,sub2;
                        if(subject.contains("/")){
                            int index =  subject.indexOf("/");
                            sub1 = subject.substring(0,index);
                            sub2 = subject.substring(index+1,subject.length());
                            database1(this, day,"fy", sub1);
                            database1(this, day,"fy", sub2);
                        }
                        else {
                            database1(this, day, "fy", subject);
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                //sy
                dbRef.child("students").child("sy").child("sub-fac").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Map<String, Object> mMap = (Map<String, Object>) dataSnapshot.getValue();
                        String subject = (String) mMap.get(user_name);
                        String sub1,sub2;

                        if(subject.contains("/")){
                       int index =  subject.indexOf("/");
                            sub1 = subject.substring(0,index);
                            sub2 = subject.substring(index+1,subject.length());
                            database1(this, day,"sy", sub1);
                            database1(this, day,"sy", sub2);
                        }
                        else {
                            database1(this, day, "sy", subject);
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
//ty
                dbRef.child("students").child("ty").child("sub-fac").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String, Object> mMap = (Map<String, Object>) dataSnapshot.getValue();
                        String subject = (String) mMap.get(user_name);
                        String sub1,sub2;
                        if(subject.contains("/")){
                            int index =  subject.indexOf("/");
                            sub1 = subject.substring(0,index);
                            sub2 = subject.substring(index+1,subject.length());
                            database1(this, day,"ty", sub1);
                            database1(this, day,"ty", sub2);
                        }
                        else {
                            database1(this, day, "ty", subject);
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void database1(ValueEventListener view, String day,final String year , final String subject) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        myRef.child("students").child(year).child(day).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Map<String, Object> mMap = (Map<String, Object>) dataSnapshot.getValue();


                // Toast.makeText(getApplicationContext(),((String) mMap.get(lectureTime[5])),Toast.LENGTH_SHORT).show();
                //prac
                if (subject.equals((String) mMap.get(lectureTime[0])))
                {

                    lec1val.setText((String) mMap.get(lectureTime[0])+" "+year);
                }
                else if(((String) mMap.get(lectureTime[0])).contains("/"))
                {

                    String sub1,sub2,sub3,sub4;
                    int temp2 = ((String) mMap.get(lectureTime[0])).length();
                        int index1 = ((String) mMap.get(lectureTime[0])).indexOf("/");
                        sub1 = ((String) mMap.get(lectureTime[0])).substring(0,index1);
                        sub2=   ((String) mMap.get(lectureTime[0])).substring(index1+1,temp2);

                       if(sub1.contains("("))
                        {


                            int index3 = sub1.indexOf("(");
                           // int index4 = sub1.indexOf(")");
                            sub3 = sub1.substring(index3+1,index1-1);

                            if(subject.equals(sub3))
                            {
                                lec1val.setText(sub1+" "+year);

                            }

                        }
                  //  sub1 = ((String) mMap.get(lectureTime[0])).substring(index1,temp2-1);

                    if(sub2.contains("("))
                    {
                        int index3 = sub2.indexOf("(");
                        int index4 = sub2.length();
                        sub3 = sub2.substring(index3+1,index4-1);
                        if(subject.equals(sub3))
                        {
                            lec1val.setText(sub2+" "+year);
                        }

                    }


                    }
////////////////////////////////////////////////////////////////////////////////
                if (subject.equals((String) mMap.get(lectureTime[1])))
                {

                    lec2val.setText((String) mMap.get(lectureTime[1])+" "+year);
                }
                else if(((String) mMap.get(lectureTime[1])).contains("/"))
                {

                    String sub1,sub2,sub3,sub4;
                    int temp2 = ((String) mMap.get(lectureTime[1])).length();
                    int index1 = ((String) mMap.get(lectureTime[1])).indexOf("/");
                    sub1 = ((String) mMap.get(lectureTime[1])).substring(0,index1);
                    sub2=   ((String) mMap.get(lectureTime[1])).substring(index1+1,temp2);

                    if(sub1.contains("("))
                    {


                        int index3 = sub1.indexOf("(");
                        // int index4 = sub1.indexOf(")");
                        sub3 = sub1.substring(index3+1,index1-1);

                        if(subject.equals(sub3))
                        {
                            lec2val.setText(sub1+" "+year);

                        }

                    }
                    //  sub1 = ((String) mMap.get(lectureTime[0])).substring(index1,temp2-1);

                    if(sub2.contains("("))
                    {


                        int index3 = sub2.indexOf("(");
                        int index4 = sub2.length();
                        sub3 = sub2.substring(index3+1,index4-1);

                        if(subject.equals(sub3))
                        {
                            lec2val.setText(sub2+" "+year);
                        }

                    }


                }
                /////////////////////////////////////////////////////////
                if (subject.equals((String) mMap.get(lectureTime[2])))
                {

                    lec3val.setText((String) mMap.get(lectureTime[2])+" "+year);
                }
                else if(((String) mMap.get(lectureTime[2])).contains("/"))
                {

                    String sub1,sub2,sub3,sub4;
                    int temp2 = ((String) mMap.get(lectureTime[2])).length();
                    int index1 = ((String) mMap.get(lectureTime[2])).indexOf("/");
                    sub1 = ((String) mMap.get(lectureTime[2])).substring(0,index1);
                    sub2=   ((String) mMap.get(lectureTime[2])).substring(index1+1,temp2);

                    if(sub1.contains("("))
                    {


                        int index3 = sub1.indexOf("(");
                        // int index4 = sub1.indexOf(")");
                        sub3 = sub1.substring(index3+1,index1-1);

                        if(subject.equals(sub3))
                        {
                            lec3val.setText(sub1+" "+year);

                        }

                    }
                    //  sub1 = ((String) mMap.get(lectureTime[0])).substring(index1,temp2-1);

                    if(sub2.contains("("))
                    {


                        int index3 = sub2.indexOf("(");
                        int index4 = sub2.length();
                        sub3 = sub2.substring(index3+1,index4-1);

                        if(subject.equals(sub3))
                        {
                            lec3val.setText(sub2+" "+year);
                        }

                    }


                }
                /////////////////////////////////////////////////////////////////
                //theory
                if (subject.equals((String) mMap.get(lectureTime[3])))
                {

                    lec4val.setText((String) mMap.get(lectureTime[3])+" "+year);
                }
                if (subject.equals((String) mMap.get(lectureTime[4])))
                {

                    lec5val.setText((String) mMap.get(lectureTime[4])+" "+year);
                }
                if (subject.equals((String) mMap.get(lectureTime[5])))
                {

                    lec6val.setText((String) mMap.get(lectureTime[5])+" "+year);
                }
                if (subject.equals((String) mMap.get(lectureTime[6])))
                {

                    lec7val.setText((String) mMap.get(lectureTime[6])+" "+year);
                }
                if (subject.equals((String) mMap.get(lectureTime[7])))
                {
                    lec8val.setText((String) mMap.get(lectureTime[7])+" "+year);
                }

                else if(((String) mMap.get(lectureTime[7])).contains("/"))
                {

                    String sub1,sub2,sub3,sub4;
                    int temp2 = ((String) mMap.get(lectureTime[7])).length();
                    int index1 = ((String) mMap.get(lectureTime[7])).indexOf("/");
                    sub1 = ((String) mMap.get(lectureTime[7])).substring(0,index1);
                    sub2=   ((String) mMap.get(lectureTime[7])).substring(index1+1,temp2);

                    if(sub1.contains("("))
                    {


                        int index3 = sub1.indexOf("(");
                        // int index4 = sub1.indexOf(")");
                        sub3 = sub1.substring(index3+1,index1-1);

                        if(subject.equals(sub3))
                        {
                            lec7val.setText(sub1+" "+year);

                        }

                    }
                    //  sub1 = ((String) mMap.get(lectureTime[0])).substring(index1,temp2-1);

                    if(sub2.contains("("))
                    {


                        int index3 = sub2.indexOf("(");
                        int index4 = sub2.length();
                        sub3 = sub2.substring(index3+1,index4-1);

                        if(subject.equals(sub3))
                        {
                            lec7val.setText(sub2+" "+year);
                        }

                    }


                }
                if (subject.equals((String) mMap.get(lectureTime[8])))
                {

                    lec9val.setText((String) mMap.get(lectureTime[8])+" "+year);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    }


