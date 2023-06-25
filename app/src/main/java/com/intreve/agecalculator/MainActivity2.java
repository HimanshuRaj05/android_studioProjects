package com.intreve.agecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;


public class MainActivity2 extends AppCompatActivity {
    //ed=Edit text, p= present, b=birth, d,m, and y are day, month, and year respectively
    EditText etpd, etpm, etpy, etbd, etbm, etby;

    //Id's are the same as variable name
    //tv is TextView
    TextView tvy, tvm, tvd;

    Button btnCalculate, btnclear;

    int pDay, pMonth, pYear, bDay, bMonth, bYear, dSubt, mSubt, ySubt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().hide();




        etpd=findViewById(R.id.etpd);
        etpm=findViewById(R.id.etpm);
        etpy=findViewById(R.id.etpy);
        etbd=findViewById(R.id.etbd);
        etbm=findViewById(R.id.etbm);
        etby=findViewById(R.id.etby);

        tvy=findViewById(R.id.tvy);
        tvm=findViewById(R.id.tvm);
        tvd=findViewById(R.id.tvd);

        btnCalculate=findViewById(R.id.btnCalculate);
        btnclear=findViewById(R.id.clear);

        pDay=0;
        pMonth=0;
        pYear=0;
        pYear=0;
        bMonth=0;
        bMonth=0;
        dSubt=0;
        mSubt=0;
        ySubt=0;

//        etby.requestFocus();

        Calendar calendar=Calendar.getInstance();

        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        if(day<10){
            etpd.setText("0"+String.valueOf(day));
        }

        else{
            etpd.setText(String.valueOf(day));
        }


        if(month<10){
            etpm.setText("0"+String.valueOf(month));
        }

        else{
            etpm.setText(String.valueOf(month));
        }

        etpy.setText(String.valueOf(year));

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearInput();
            }
        });



       etbd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Count the number of digits
                int digitCount = s.toString().replaceAll("\\D", "").length();

                // Show a Toast with the number of digits
               // Toast.makeText(MainActivity2.this, "Number of digits: " + digitCount, Toast.LENGTH_SHORT).show();

                if(digitCount>=2){

                    etbm.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed for this implementation
            }
        });

        etbm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Count the number of digits
                int digitCount = s.toString().replaceAll("\\D", "").length();

                // Show a Toast with the number of digits
                // Toast.makeText(MainActivity2.this, "Number of digits: " + digitCount, Toast.LENGTH_SHORT).show();

                if(digitCount>=2){

                    etby.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed for this implementation
            }
        });


//        etbd.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // Not needed for this implementation
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() == 2 && count == 2) {
//                    int len=s.length();
//                    String date_length=String.valueOf(len);
//                    Log.d("datelen",date_length);
//                    tvy.setText(date_length);
//                    // Move focus to the month EditText
//                    etbm.requestFocus();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                // Not needed for this implementation
//            }
//        });
//
//        etbm.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // Not needed for this implementation
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() == 2 && count == 2) {
//                    // Move focus to the year EditText
//                    etby.requestFocus();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                // Not needed for this implementation
//            }
//        });





//        pDay=Integer.parseInt(etpd.getText().toString());
//        pMonth=Integer.parseInt(etpm.getText().toString());
//        pYear=Integer.parseInt(etpy.getText().toString());
//
//        bDay=Integer.parseInt(etbd.getText().toString());
//        bMonth=Integer.parseInt(etpm.getText().toString());
//        bYear=Integer.parseInt(etpy.getText().toString());



        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvd.setText("");
                tvm.setText("");
                tvy.setText("");

                hideKeyboard();


                boolean proceed=true;
                try {

                    pDay=Integer.parseInt(etpd.getText().toString());
                    pMonth=Integer.parseInt(etpm.getText().toString());
                    pYear=Integer.parseInt(etpy.getText().toString());

                    bDay=Integer.parseInt(etbd.getText().toString());
                    bMonth=Integer.parseInt(etbm.getText().toString());
                    bYear=Integer.parseInt(etby.getText().toString());

                }

                catch (Exception e){
                    Toast.makeText(MainActivity2.this, "Enter all the values", Toast.LENGTH_SHORT).show();
                    proceed=false;
                }

                if(isValid(pDay, pMonth, pYear, bDay, bMonth, bYear)&& proceed){

                    int presentDay=calculateDay(pDay,bDay);

                    int presentMonth=calculateMonth(pMonth, bMonth);

                    int presentYear=calculateYear(pYear, bYear);

                    if(presentDay<10){
                        tvd.setText("0"+String.valueOf(presentDay));
                    }

                    else{
                        tvd.setText(String.valueOf(presentDay));
                    }

                    if(presentMonth<10){
                        tvm.setText("0"+String.valueOf(presentMonth));

                    }

                    else{
                        tvm.setText(String.valueOf(presentMonth));
                    }



                    tvy.setText(String.valueOf(presentYear));


                }


                else {

                    if(proceed) {
                        Toast.makeText(MainActivity2.this, "Invalid input", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }


    //method to check whether the user input is valid
    boolean isValid(int pDay, int pMonth, int pYear, int bDay, int bMonth, int bYear){



        if(pDay > 31 || bDay>31){
            return false;
        }

        if(pMonth>12 || bMonth>12){
            return false;
        }

        if(pYear-bYear<0){
            return false;
        }

        return true;

    }

    int calculateDay(int pDay, int bDay){

        dSubt=pDay-bDay;

        if(dSubt<0){

            if(pMonth==3){

                if(pYear%4==0){
                    dSubt+=29;
                    pMonth--;
                    return dSubt;
                }

                else{
                    dSubt+=28;
                    pMonth--;
                    return dSubt;
                }


            }

            if(wilLContain31(pMonth)){
                dSubt+=31;
                pMonth--;
                return dSubt;
            }

            dSubt+=30;
            pMonth--;
            return dSubt;

        }

        return dSubt;


    }

    boolean wilLContain31(int pMonth){
        boolean contains=false;

        int[] arr={2,4,6,8,9,11};

        for(int i=0; i<arr.length; i++){
            if(arr[i]==pMonth){
                return true;
            }
        }

        return false;


    }

    int calculateMonth(int pMonth, int bMonth){
        mSubt=pMonth-bMonth;

        if(mSubt<0){
            mSubt+=12;
            pYear--;
            return mSubt;

        }

        return mSubt;

    }

    int calculateYear(int pYear, int bYear){
        ySubt=pYear-bYear;

        return ySubt;
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    void clearInput(){
        etbd.setText("");
        etby.setText("");
        etbm.setText("");

        tvd.setText("");
        tvy.setText("");
        tvm.setText("");

    }


}