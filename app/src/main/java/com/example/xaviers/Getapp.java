package com.example.xaviers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Map;

public class Getapp extends AppCompatActivity {

    static String rachana=".",roy=".",lydia=".", subhash=".";

    TextView lec1, lec2, lec3, lec4, lec5, lec6, lec7, lec8, lec9, lec1val, lec2val, lec3val, lec4val, lec5val, lec6val, lec7val, lec8val, lec9val, header, dayText;
    String TAG = "abc";
    Boolean Roy = true, Lydia = true, Rachana = true, Subhash = true, Melisa = true, Floyd = true, SpcT = true, Ashok = true, CfcT = true;
    public static String cancelledsub=" ",teachers="";

    String[] lectureTime = new String[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Intent i = getIntent();

        String year = i.getStringExtra("year");

        header = findViewById(R.id.header);
        dayText = findViewById(R.id.day);
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


        lec1val.setText("loading");

        lec2val.setText("loading");

        lec3val.setText("loading");

        lec4val.setText("loading");

        lec5val.setText("loading");

        lec6val.setText("loading");

        lec7val.setText("loading");

        lec8val.setText("loading");

        lec9val.setText("loading");


        header.setText("FYBScIT");
        Log.d(TAG, "onCreate: " + year);

        if (year.equals("fy")) header.setText("FYBScIT SEM II Timetable");
        else if (year.equals("sy")) header.setText("SYBScIT SEM IV Timetable");
        else if (year.equals("ty")) header.setText("TYBScIT SEM VI Timetable");


        Date date = new Date();
        int d = date.getDay();
        String day = "";

        if (d == 1) day = "monday";
        else if (d == 2) day = "tuesday";
        else if (d == 3) day = "wednesday";
        else if (d == 4) day = "thursday";
        else if (d == 5) day = "friday";
        else day = "saturday";


        dayText.setText(day.toUpperCase());

        database(this, day, year);


    }


    public void database(Getapp view, final String day, final String year) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        myRef.child("teacherNextDayUpdate").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Map<Object, String> teacherStatus = (Map<Object, String>) dataSnapshot.getValue();

                if (teacherStatus.get("Roy").equals("no")) {
                    Roy = false;
                }

                if (teacherStatus.get("Rachana").equals("no")) {
                    Rachana = false;
                }

                if (teacherStatus.get("Lydia").equals("no")) {
                    Lydia = false;
                }

                if (teacherStatus.get("Melisa").equals("no")) {
                    Melisa = false;
                }

                if (teacherStatus.get("Floyd").equals("no")) {
                    Floyd = false;
                }

                if (teacherStatus.get("SpcT").equals("no")) {
                    SpcT = false;
                }

                if (teacherStatus.get("Ashok").equals("no")) {
                    Ashok = false;
                }

                if (teacherStatus.get("Subhash").equals("no")) {
                    Subhash = false;
                }

                if (teacherStatus.get("CfcT").equals("no")) {
                    CfcT = false;
                }


                Log.d(TAG, "database: roy" + Roy);

                Log.d(TAG, "database: Lydia" + Lydia);

                Log.d(TAG, "database: Rachana" + Rachana);

                Log.d(TAG, "database: Subhash" + Subhash);

                Log.d(TAG, "database: spcfy" + SpcT);

                Log.d(TAG, "database: melisa" + Melisa);

                Log.d(TAG, "database: floyd" + Floyd);

                Log.d(TAG, "database: ashok" + Ashok);

                Log.d(TAG, "database: cfc" + CfcT);


                if (Roy.equals(false)) {
                    Log.d(TAG, "onDataChange: inside roy");
                    myRef.child("students").child(year).child("sub-fac").addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Log.d(TAG, "onDataChange: inside datasnapshot");
                            Map<String, Object> mMap = (Map<String, Object>) dataSnapshot.getValue();
                            cancelledsub = cancelledsub + mMap.get("roy").toString();
                            teachers = teachers + "Roy";

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (Lydia.equals(false)) {
                    Log.d(TAG, "onDataChange: inside lydia");

                    myRef.child("students").child(year).child("sub-fac").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Map<String, Object> mMap = (Map<String, Object>) dataSnapshot.getValue();
                            cancelledsub = cancelledsub + mMap.get("lydia").toString();
                            teachers = teachers + "Lydia";

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (Rachana.equals(false)) {
                    myRef.child("students").child(year).child("sub-fac").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Map<String, Object> mMap = (Map<String, Object>) dataSnapshot.getValue();
                            cancelledsub = cancelledsub + mMap.get("rachana").toString();
                            teachers = teachers + "Rachana";

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (Subhash.equals(false)) {
                    myRef.child("students").child(year).child("sub-fac").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Map<String, Object> mMap = (Map<String, Object>) dataSnapshot.getValue();
                            cancelledsub = cancelledsub + mMap.get("subhash").toString();
                            teachers = teachers + "Subhash";
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

                if (Ashok.equals(false)) {
                    myRef.child("students").child(year).child("sub-fac").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Map<String, Object> mMap = (Map<String, Object>) dataSnapshot.getValue();
                            cancelledsub = cancelledsub + mMap.get("ashok").toString();
                            teachers = teachers + "Ashok";
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

                if (Melisa.equals(false)) {
                    myRef.child("students").child(year).child("sub-fac").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Map<String, Object> mMap = (Map<String, Object>) dataSnapshot.getValue();
                            cancelledsub = cancelledsub + mMap.get("melisa").toString();
                           teachers = teachers + "Melisa";
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (SpcT.equals(false)) {
                    myRef.child("students").child(year).child("sub-fac").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Map<String, Object> mMap = (Map<String, Object>) dataSnapshot.getValue();
                            cancelledsub = cancelledsub + mMap.get("spct").toString();
                            teachers = teachers + "SpcT";

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

                if (CfcT.equals(false)) {
                    myRef.child("students").child(year).child("sub-fac").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Map<String, Object> mMap = (Map<String, Object>) dataSnapshot.getValue();
                            cancelledsub = cancelledsub + mMap.get("cfct").toString();
                           teachers = teachers + "CftT";
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


                myRef.child("students").child(year).child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Map<String, Object> mMap = (Map<String, Object>) dataSnapshot.getValue();
                        String lec1 = (String) mMap.get(lectureTime[0]);
                        String lec2 = (String) mMap.get(lectureTime[1]);
                        String lec3 = (String) mMap.get(lectureTime[2]);
                        String lec8 = (String) mMap.get(lectureTime[7]);
                        String lec9 = (String) mMap.get(lectureTime[8]);

                        Log.d(TAG, "onDataChange: lec1= " + lec1);
                        Log.d(TAG, "onDataChange: lec2= " + lec2);
                        Log.d(TAG, "onDataChange: lec3= " + lec3);
                        Log.d(TAG, "onDataChange: lec8= " + lec8);
                        Log.d(TAG, "onDataChange: lec9= " + lec9);



                        /*--------------------------------------------------------------------------------------------------------------------*/
                        if (lec1.contains("/")) {

                            String batch1 = lec1.substring(0, lec1.indexOf("/"));
                            String batch2 = lec1.substring(lec1.indexOf("/") + 1, lec1.length());
                            Log.d(TAG, "onDataChange: lec1 batch1= " + batch1);
                            Log.d(TAG, "onDataChange: lec1 batch2= " + batch2);

                            if (!batch1.equals("")) {
                                batch1 = batch1.substring(batch1.indexOf("(") + 1, batch1.indexOf(")"));
                                Log.d(TAG, "onDataChange: batch1 trimmed= " + batch1);
                            }
                            if (!batch2.equals("")) {
                                batch2 = batch2.substring(batch2.indexOf("(") + 1, batch2.indexOf(")"));

                                Log.d(TAG, "onDataChange: batch2 trimmed= " + batch2);
                            }


                            if (cancelledsub.contains(batch1)) {
                                Log.d(TAG, "onDataChange: in lec 1 1st if");
                                lec1val.setTextColor(getResources().getColor(R.color.red));
                                //adjust(lectureTime[0], year, day, teachers);
                                lec1val.setText("cancelled /prac(" + batch2 + ")");
                            }
                            if (cancelledsub.contains(batch2)) {
                                Log.d(TAG, "onDataChange: in lec 1 2nd if");
                                lec1val.setTextColor(getResources().getColor(R.color.red));
                                lec1val.setText("prac(" + batch1 + ")/cancelled");
                            }
                            if (cancelledsub.contains(batch1) && cancelledsub.contains(batch2)) {
                                Log.d(TAG, "onDataChange: in lec 1 3rd if");
                                lec1val.setTextColor(getResources().getColor(R.color.red));
                                lec1val.setText("cancelled/cancelled");

                            }

                            if (!cancelledsub.contains(batch1) && !cancelledsub.contains(batch2)) {
                                lec1val.setText((String) mMap.get(lectureTime[0]));
                            }

                        }
                        else {
                            String abc = (String)mMap.get(lectureTime[0]);

                            if (cancelledsub.contains(abc)) {
                                Log.d(TAG, "onDataChange: in lec 1 5th if");
                                lec1val.setTextColor(getResources().getColor(R.color.red));
                              //  adjust(lectureTime[0], year, day, teachers);
                                lec1val.setText((String) mMap.get(lectureTime[0]) + " -cancelled");


                            } else {

                                Log.d(TAG, "onDataChange: in lec 1 5th if");
                                lec1val.setText((String) mMap.get(lectureTime[0]));
                            }
                        }

                        /*-----------------------------------------------------------------------------------------------------------------*/
                        /*--------------------------------------------------------------------------------------------------------------------*/
                        if (lec2.contains("/")) {
                            String batch1 = lec2.substring(0, lec2.indexOf("/"));
                            String batch2 = lec2.substring(lec2.indexOf("/") + 1, lec2.length());
                            Log.d(TAG, "onDataChange: batch1= " + batch1);
                            Log.d(TAG, "onDataChange: batch2= " + batch2);

                            if (!batch1.equals("")) {
                                batch1 = batch1.substring(batch1.indexOf("(") + 1, batch1.indexOf(")"));
                                Log.d(TAG, "onDataChange: lec2 batch1 trimmed= " + batch1);
                            }

                            if (!batch2.equals("")) {

                                batch2 = batch2.substring(batch2.indexOf("(") + 1, batch2.indexOf(")"));
                                Log.d(TAG, "onDataChange: lec2 batch2 trimmed= " + batch2);

                            }


                            if (cancelledsub.contains(batch1)) {

                                Log.d(TAG, "onDataChange: in 1st / if");
                                lec2val.setTextColor(getResources().getColor(R.color.red));
                                lec2val.setText("cancelled /prac(" + batch2 + ")");
                            }
                            if (cancelledsub.contains(batch2)) {

                                Log.d(TAG, "onDataChange: in 2nd / if");
                                lec2val.setTextColor(getResources().getColor(R.color.red));
                                lec2val.setText("prac(" + batch1 + ")/cancelled");
                            }
                            if (cancelledsub.contains(batch1) && cancelledsub.contains(batch2)) {

                                Log.d(TAG, "onDataChange: in 3rd / if");
                                lec2val.setTextColor(getResources().getColor(R.color.red));
                                lec2val.setText("cancelled/cancelled");

                            }

                            if (!cancelledsub.contains(batch1) && !cancelledsub.contains(batch2)) {
                                lec2val.setText((String) mMap.get(lectureTime[1]));
                            }
                        } else {
                            if (cancelledsub.contains((String) mMap.get(lectureTime[1]))) {
                                Log.d(TAG, "onDataChange: in else if");
                                lec2val.setTextColor(getResources().getColor(R.color.red));
                                lec2val.setText((String) mMap.get(lectureTime[1]) + " -cancelled");

                            } else {
                                Log.d(TAG, "onDataChange: in else");
                                lec2val.setText((String) mMap.get(lectureTime[1]));
                            }
                        }




                        /*-----------------------------------------------------------------------------------------------------------------*/


                        /*--------------------------------------------------------------------------------------------------------------------*/
                        if (lec3.contains("/")) {
                            String batch1 = lec3.substring(0, lec3.indexOf("/"));
                            String batch2 = lec3.substring(lec3.indexOf("/") + 1, lec3.length());
                            //  Log.d(TAG, "onDataChange: batch1= "+batch1);
                            // Log.d(TAG, "onDataChange: batch2= "+batch2);

                            if (!batch1.equals("")) {
                                batch1 = batch1.substring(batch1.indexOf("(") + 1, batch1.indexOf(")"));
                                Log.d(TAG, "onDataChange: batch1 trimmed= " + batch1);
                            }


                            if (!batch2.equals("")) {
                                batch2 = batch2.substring(batch2.indexOf("(") + 1, batch2.indexOf(")"));

                                Log.d(TAG, "onDataChange: batch2 trimmed= " + batch2);
                            }


                            if (cancelledsub.contains(batch1)) {

                                lec3val.setTextColor(getResources().getColor(R.color.red));
                                lec3val.setText("cancelled /prac(" + batch2 + ")");
                            }
                            if (cancelledsub.contains(batch2)) {
                                lec3val.setTextColor(getResources().getColor(R.color.red));
                                lec3val.setText("prac(" + batch1 + ")/cancelled");
                            }
                            if (cancelledsub.contains(batch1) && cancelledsub.contains(batch2)) {
                                lec3val.setTextColor(getResources().getColor(R.color.red));
                                lec3val.setText("cancelled/cancelled");

                            }


                            if (!cancelledsub.contains(batch1) && !cancelledsub.contains(batch2)) {
                                lec3val.setText((String) mMap.get(lectureTime[2]));
                            }
                        } else {
                            if (cancelledsub.contains((String) mMap.get(lectureTime[2]))) {

                                lec3val.setTextColor(getResources().getColor(R.color.red));
                                lec3val.setText((String) mMap.get(lectureTime[2]) + " -cancelled");

                            } else {
                                lec3val.setText((String) mMap.get(lectureTime[2]));

                            }
                        }






                        /*-----------------------------------------------------------------------------------------------------------------*/


                        if (cancelledsub.contains((String) mMap.get(lectureTime[3]))) {
                            lec4val.setTextColor(getResources().getColor(R.color.red));
                            lec4val.setText((String) mMap.get(lectureTime[3]) + " -cancelled");

                        } else lec4val.setText((String) mMap.get(lectureTime[3]));

                        if (cancelledsub.contains((String) mMap.get(lectureTime[4]))) {
                            lec5val.setTextColor(getResources().getColor(R.color.red));
                            lec5val.setText((String) mMap.get(lectureTime[4]) + " -cancelled");

                        } else lec5val.setText((String) mMap.get(lectureTime[4]));

                        if (cancelledsub.contains((String) mMap.get(lectureTime[5]))) {
                            lec6val.setTextColor(getResources().getColor(R.color.red));
                            lec6val.setText((String) mMap.get(lectureTime[5]) + " -cancelled");

                        } else lec6val.setText((String) mMap.get(lectureTime[5]));

                        if (cancelledsub.contains((String) mMap.get(lectureTime[6]))) {
                            lec7val.setTextColor(getResources().getColor(R.color.red));
                            //adjust(lectureTime[5], year, day, teachers);
                            lec7val.setText((String) mMap.get(lectureTime[6]) + " -cancelled");

                        } else lec7val.setText((String) mMap.get(lectureTime[6]));

                        /*--------------------------------------------------------------------------------------------------------------------*/
                        if (lec8.contains("/")) {
                            String batch1 = lec8.substring(0, lec8.indexOf("/"));
                            String batch2 = lec8.substring(lec8.indexOf("/") + 1, lec8.length());
                            //  Log.d(TAG, "onDataChange: batch1= "+batch1);
                            // Log.d(TAG, "onDataChange: batch2= "+batch2);

                            if (!batch1.equals("")) {
                                batch1 = batch1.substring(batch1.indexOf("(") + 1, batch1.indexOf(")"));
                                Log.d(TAG, "onDataChange: batch1 trimmed= " + batch1);
                            }


                            if (!batch2.equals("")) {
                                batch2 = batch2.substring(batch2.indexOf("(") + 1, batch2.indexOf(")"));

                                Log.d(TAG, "onDataChange: batch2 trimmed= " + batch2);
                            }


                            if (cancelledsub.contains(batch1)) {

                                lec8val.setText("cancelled /prac(" + batch2 + ")");
                            }
                            if (cancelledsub.contains(batch2)) {
                                lec8val.setText("prac(" + batch1 + ")/cancelled");
                            }
                            if (cancelledsub.contains(batch1) && cancelledsub.contains(batch2)) {
                                lec8val.setTextColor(getResources().getColor(R.color.red));
                                lec8val.setText("cancelled/cancelled");

                            }
                            if (!cancelledsub.contains(batch1) && !cancelledsub.contains(batch2)) {
                                lec8val.setText((String) mMap.get(lectureTime[7]));
                            }

                        } else {
                            if (cancelledsub.contains((String) mMap.get(lectureTime[7]))) {

                                lec8val.setText((String) mMap.get(lectureTime[7]) + " -cancelled");
                            } else {

                                lec8val.setText((String) mMap.get(lectureTime[7]));

                            }
                        }







                        /*-----------------------------------------------------------------------------------------------------------------*/


                        /*--------------------------------------------------------------------------------------------------------------------*/
                        if (lec9.contains("/")) {
                            String batch1 = lec9.substring(0, lec9.indexOf("/"));
                            String batch2 = lec9.substring(lec9.indexOf("/") + 1, lec9.length());
                            //  Log.d(TAG, "onDataChange: batch1= "+batch1);
                            // Log.d(TAG, "onDataChange: batch2= "+batch2);

                            if (!batch1.equals("")) {
                                batch1 = batch1.substring(batch1.indexOf("(") + 1, batch1.indexOf(")"));
                                Log.d(TAG, "onDataChange: batch1 trimmed= " + batch1);
                            }


                            if (!batch2.equals("")) {
                                batch2 = batch2.substring(batch2.indexOf("(") + 1, batch2.indexOf(")"));

                                Log.d(TAG, "onDataChange: batch2 trimmed= " + batch2);
                            }


                            if (cancelledsub.contains(batch1)) {
                                Log.d(TAG, "onDataChange: 1");
                                lec9val.setTextColor(getResources().getColor(R.color.red));
                                lec9val.setText("cancelled /prac(" + batch2 + ")");
                            }
                            if (cancelledsub.contains(batch2)) {
                                Log.d(TAG, "onDataChange: 2");
                                lec9val.setTextColor(getResources().getColor(R.color.red));
                                lec9val.setText("prac(" + batch1 + ")/cancelled");
                            }
                            if (cancelledsub.contains(batch1) && cancelledsub.contains(batch2)) {
                                Log.d(TAG, "onDataChange: 3");
                                lec9val.setTextColor(getResources().getColor(R.color.red));
                                lec9val.setText("cancelled/cancelled");

                            }

                            if (!cancelledsub.contains(batch1) && !cancelledsub.contains(batch2)) {
                                lec9val.setText((String) mMap.get(lectureTime[8]));
                            }

                        } else {
                            if (cancelledsub.contains((String) mMap.get(lectureTime[8]))) {
                                Log.d(TAG, "onDataChange: 4");
                                lec9val.setTextColor(getResources().getColor(R.color.red));
                                lec9val.setText((String) mMap.get(lectureTime[8]) + " -cancelled");

                            } else {
                                Log.d(TAG, "onDataChange: 5");
                                lec9val.setText((String) mMap.get(lectureTime[8]));

                            }

                        }






                        /*-----------------------------------------------------------------------------------------------------------------*/

                        Log.d(TAG, "onDataChange: " + cancelledsub);
                        cancelledsub = "";
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
    //////////////////////////////////////////////////////////////
/*
    private void adjust(final String lectime, final String year, final String day, final String teachers) {
        String abteach = "";


        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = db.getReference();
        if (teachers.contains("Roy")) {
            abteach = "Roy";
        } else if (teachers.contains("Rachana")) {
            abteach = "Rachana";
        } else if (teachers.contains("Lydia")) {
            abteach = "Lydia";
        } else if (teachers.contains("Subhash")) {
            abteach = "Subhash";
        } else if (teachers.contains("Melisa")) {
            abteach = "Melisa";
        } else if (teachers.contains("Ashok")) {
            abteach = "Ashok";
        } else if (teachers.contains("SpcT")) {
            abteach = "SpcT";
        }

        if (year.equals("fy")) {
            if (abteach.equals("Roy")) {


                dbRef.child("FacultyTimeTable").child("rachana").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                        rachana = (String) map.get(lectime);
                        if (rachana.equals("-")) {
                            //   sendNoti();
                            Toast.makeText(Getapp.this, "Send Notification To Rachana ", Toast.LENGTH_SHORT).show();
                        } else {
                            //////////////////////////////////

                            dbRef.child("FacultyTimeTable").child("lydia").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                    lydia = (String) map.get(lectime);
                                    if (lydia.equals("-")) {
                                        //   sendNoti();
                                        Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                    }////////////////////////////
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }
                });
            }
            //////////////////////////////

            else if (abteach.equals("Rachana")) {


                dbRef.child("FacultyTimeTable").child("roy").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                       roy = (String) map.get(lectime);
                        if (roy.equals("-")) {
                            //   sendNoti();
                            Toast.makeText(Getapp.this, "Send Notification To Roy ", Toast.LENGTH_SHORT).show();
                        } else {
                            //////////////////////////////////

                            dbRef.child("FacultyTimeTable").child("lydia").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                     lydia = (String) map.get(lectime);
                                    if (lydia.equals("-")) {
                                        //   sendNoti();
                                        Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                    }////////////////////////////
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }
                });
            }
            //////////////////////////////////
            else if (abteach.equals("Lydia")) {


                dbRef.child("FacultyTimeTable").child("roy").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();

                        roy = (String) map.get(lectime);

                        if (roy.equals("-")) {
                            //   sendNoti();

                        } else {
                            //////////////////////////////////

                            dbRef.child("FacultyTimeTable").child("rachana").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                     rachana = (String) map.get(lectime);
                                    if (rachana.equals("-")) {
                                        //   sendNoti();
                                        Toast.makeText(Getapp.this, "Send Notification To Rachana", Toast.LENGTH_SHORT).show();
                                    }////////////////////////////
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }
                });
            }

        }
        /////////////////////////////////////////////////////////////////\\///\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        else if (year.equals("sy")) {
            if (abteach.equals("Roy")) {


                dbRef.child("FacultyTimeTable").child("rachana").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                       rachana = (String) map.get(lectime);
                        if (rachana.equals("-")) {
                            //   sendNoti();
                            Toast.makeText(Getapp.this, "Send Notification To Rachana ", Toast.LENGTH_SHORT).show();
                        } else {
                            //////////////////////////////////

                            dbRef.child("FacultyTimeTable").child("lydia").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                    lydia = (String) map.get(lectime);
                                    if (lydia.equals("-")) {
                                        //   sendNoti();
                                        Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                    } else {
                                        ///////////////////////////////////////////////////////////
                                        dbRef.child("FacultyTimeTable").child("subhash").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                               subhash = (String) map.get(lectime);
                                                if (subhash.equals("-")) {
                                                    //   sendNoti();
                                                    Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                                }

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    } ///////
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }

                });

            }
            ///////////////////////////////////////////////////////////

            else if (abteach.equals("Rachana")) {


                dbRef.child("FacultyTimeTable").child("roy").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                        roy = (String) map.get(lectime);
                        if (roy.equals("-")) {
                            //   sendNoti();
                            Toast.makeText(Getapp.this, "Send Notification To Rachana ", Toast.LENGTH_SHORT).show();
                        } else {
                            //////////////////////////////////

                            dbRef.child("FacultyTimeTable").child("lydia").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                    lydia = (String) map.get(lectime);
                                    if (lydia.equals("-")) {
                                        //   sendNoti();
                                        Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                    } else {
                                        ///////////////////////////////////////////////////////////
                                        dbRef.child("FacultyTimeTable").child("subhash").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                                subhash = (String) map.get(lectime);
                                                if (subhash.equals("-")) {
                                                    //   sendNoti();
                                                    Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                                }

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    } ///////
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }

                });

            }
            //////////////////////////////////
            else if (abteach.equals("Lydia")) {


                dbRef.child("FacultyTimeTable").child("rachana").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                        Log.d(TAG, "onDataChange: ---------------------------------------------------------------------------------------------------------"+(String) map.get(lectime));
                        rachana = (String) map.get(lectime);
                        if (rachana.equals("-")) {
                            //   sendNoti();
                            Toast.makeText(Getapp.this, "Send Notification To Rachana ", Toast.LENGTH_SHORT).show();
                        } else {
                            //////////////////////////////////

                            dbRef.child("FacultyTimeTable").child("roy").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                    roy = (String) map.get(lectime);
                                    if (roy.equals("-")) {
                                        //   sendNoti();
                                        Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                    } else {
                                        ///////////////////////////////////////////////////////////
                                        dbRef.child("FacultyTimeTable").child("subhash").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                                subhash = (String) map.get(lectime);
                                                if (subhash.equals("-")) {
                                                    //   sendNoti();
                                                    Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                                }

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    } ///////
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }

                });

            }
            //////////////////
            else if (abteach.equals("Subhash")) {


                dbRef.child("FacultyTimeTable").child("rachana").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                        rachana = (String) map.get(lectime);
                        if (rachana.equals("-")) {
                            //   sendNoti();
                            Toast.makeText(Getapp.this, "Send Notification To Rachana ", Toast.LENGTH_SHORT).show();
                        } else {
                            //////////////////////////////////

                            dbRef.child("FacultyTimeTable").child("lydia").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                   lydia = (String) map.get(lectime);
                                    if (lydia.equals("-")) {
                                        //   sendNoti();
                                        Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                    } else {
                                        ///////////////////////////////////////////////////////////
                                        dbRef.child("FacultyTimeTable").child("roy").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                               roy = (String) map.get(lectime);
                                                if (roy.equals("-")) {
                                                    //   sendNoti();
                                                    Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                                }

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    } ///////
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }

                });

            }

        }
            /////////////////////////////////////////////////////
            else if (year.equals("ty")) {
                if (abteach.equals("Roy")) {


                    dbRef.child("FacultyTimeTable").child("rachana").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                         rachana = (String) map.get(lectime);
                            if (rachana.equals("-")) {
                                //   sendNoti();
                                Toast.makeText(Getapp.this, "Send Notification To Rachana ", Toast.LENGTH_SHORT).show();
                            } else {
                                //////////////////////////////////

                                dbRef.child("FacultyTimeTable").child("lydia").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                      lydia = (String) map.get(lectime);
                                        if (lydia.equals("-")) {
                                            //   sendNoti();
                                            Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                        } else {
                                            ///////////////////////////////////////////////////////////
                                            dbRef.child("FacultyTimeTable").child("subhash").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                                    subhash = (String) map.get(lectime);
                                                    if (subhash.equals("-")) {
                                                        //   sendNoti();
                                                        Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                                    }

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        } ///////
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {


                        }

                    });

                }
                ///////////////////////////////////////////////////////////

                else if (abteach.equals("Rachana")) {


                    dbRef.child("FacultyTimeTable").child("roy").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                            roy = (String) map.get(lectime);
                            if (roy.equals("-")) {
                                //   sendNoti();
                                Toast.makeText(Getapp.this, "Send Notification To Rachana ", Toast.LENGTH_SHORT).show();
                            } else {
                                //////////////////////////////////

                                dbRef.child("FacultyTimeTable").child("lydia").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                      lydia = (String) map.get(lectime);
                                        if (lydia.equals("-")) {
                                            //   sendNoti();
                                            Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                        } else {
                                            ///////////////////////////////////////////////////////////
                                            dbRef.child("FacultyTimeTable").child("subhash").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                                    subhash = (String) map.get(lectime);
                                                    if (subhash.equals("-")) {
                                                        //   sendNoti();
                                                        Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                                    }

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        } ///////
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {


                        }

                    });

                }
                //////////////////////////////////
                else if (abteach.equals("Lydia")) {


                    dbRef.child("FacultyTimeTable").child("rachana").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                          rachana = (String) map.get(lectime);
                            if (rachana.equals("-")) {
                                //   sendNoti();
                                Toast.makeText(Getapp.this, "Send Notification To Rachana ", Toast.LENGTH_SHORT).show();
                            } else {
                                //////////////////////////////////

                                dbRef.child("FacultyTimeTable").child("roy").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                      roy = (String) map.get(lectime);
                                        if (roy.equals("-")) {
                                            //   sendNoti();
                                            Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                        } else {
                                            ///////////////////////////////////////////////////////////
                                            dbRef.child("FacultyTimeTable").child("subhash").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                                   subhash = (String) map.get(lectime);
                                                    if (subhash.equals("-")) {
                                                        //   sendNoti();
                                                        Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                                    }

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        } ///////
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {


                        }

                    });

                }
                //////////////////
                else if (abteach.equals("Subhash")) {


                    dbRef.child("FacultyTimeTable").child("rachana").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                            rachana = (String) map.get(lectime);
                            if (rachana.equals("-")) {
                                //   sendNoti();
                                Toast.makeText(Getapp.this, "Send Notification To Rachana ", Toast.LENGTH_SHORT).show();
                            } else {
                                //////////////////////////////////

                                dbRef.child("FacultyTimeTable").child("lydia").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                        lydia = (String) map.get(lectime);
                                        if (lydia.equals("-")) {
                                            //   sendNoti();
                                            Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                        } else {
                                            ///////////////////////////////////////////////////////////
                                            dbRef.child("FacultyTimeTable").child("roy").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                                  roy = (String) map.get(lectime);
                                                    if (roy.equals("-")) {
                                                        //   sendNoti();
                                                        Toast.makeText(Getapp.this, "Send Notification To Lydia", Toast.LENGTH_SHORT).show();
                                                    }

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        } ///////
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {


                        }

                    });

                }


            }
        }*/

    }
