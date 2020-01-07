package com.lutfi.kapaldibawaterbang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class HomeActivity extends AppCompatActivity {

  private  static  int Splash_Time_Out = 4000;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent gogetstarted = new Intent(HomeActivity.this,Home2Activity.class);
        startActivity(gogetstarted);
        finish();
      }
    }, Splash_Time_Out);
  }
}
