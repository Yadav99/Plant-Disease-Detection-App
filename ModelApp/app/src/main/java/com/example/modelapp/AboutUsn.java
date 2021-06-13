package com.example.modelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutUsn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_usn);

        TextView textview1= (TextView) findViewById(R.id.textView9);
        TextView textview2= (TextView) findViewById(R.id.textView1);
        TextView textview3= (TextView) findViewById(R.id.textView3);
        TextView textview4= (TextView) findViewById(R.id.textView4);
        TextView textview5= (TextView) findViewById(R.id.textView5);
        TextView textview6= (TextView) findViewById(R.id.textView6);
        TextView textview7= (TextView) findViewById(R.id.textView7);
        TextView textview8= (TextView) findViewById(R.id.textView8);
        TextView textview9= (TextView) findViewById(R.id.textView11);
        TextView textview10= (TextView) findViewById(R.id.textView13);

        Toolbar myToolbar2 = (Toolbar) findViewById(R.id.my_toolbar2);
        setSupportActionBar(myToolbar2);


        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        //actionBar.setHomeAsUpIndicator(R.drawable.buttonback);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                this.finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
    }
}