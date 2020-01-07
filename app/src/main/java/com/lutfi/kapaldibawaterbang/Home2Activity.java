package com.lutfi.kapaldibawaterbang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Home2Activity extends AppCompatActivity {
  private  static  int Splash_Time_Out = 1000;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home2);

    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent gogetstarted = new Intent(Home2Activity.this,loginActivity.class);
        startActivity(gogetstarted);
        finish();
      }
    }, Splash_Time_Out);
  }
}
