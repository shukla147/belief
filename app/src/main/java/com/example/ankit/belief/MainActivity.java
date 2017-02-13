package com.example.ankit.belief;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.preference.PreferenceManager;

import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public static final int ACCESS_FINE_LOCATION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here

            setContentView(R.layout.welcome);

            // mark first time has runned.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.apply();
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {


                } else {

                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},ACCESS_FINE_LOCATION);

                }
            }
        }
        else {
            setContentView(R.layout.activity_main);
        }

    }



    //for menu
    private int group1Id=1;
    int settingId=Menu.FIRST;
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(group1Id,settingId,settingId,"Settings");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(MainActivity.this, BassBoost.Settings.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getStarted(View view) {
        Intent myIntent = new Intent(view.getContext(), MainActivity.class);
        startActivity(myIntent);

    }
    public void fakeCall(View view){
        Intent intent = new Intent(MainActivity.this,FakeCall.class);
        startActivity(intent);
    }

    public void clickPhoto(View view){
        Intent intent = new Intent(MainActivity.this,takephoto.class);
        startActivity(intent);
    }

    public void addData(View view){
        Intent intent = new Intent(MainActivity.this,contacts.class);
        startActivity(intent);
    }

    public void getLocation(View view){
        Intent intent = new Intent(MainActivity.this,myLocation.class);
        startActivity(intent);
    }

    public void siren(View view){

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sirensound);
        mp.start();

        findViewById(R.id.SirenButton).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(!mp.isPlaying())
                {
                    mp.start();
                }
                else{
                    mp.seekTo(0);
                    mp.pause();

                }
            }
        });
    }


}
