package com.lutfi.kapaldibawaterbang;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import server.ConfigUrl;

public class registrasiActivity extends AppCompatActivity {
  private RequestQueue mRequestQueue;
  private Button btnLinkLogin;
  private Button btnKirim;
  private EditText edtNama, edtAlamat, edtTl, edtTglL, edtUsia, edtNoTP, edtKode, edtEmail, edtPassword;
  private ProgressDialog pDialog;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registrasi);

    getSupportActionBar().hide();
    mRequestQueue = Volley.newRequestQueue(this);

    edtNama = (EditText) findViewById(R.id.edtNama);
    edtAlamat = (EditText) findViewById(R.id.edtAlamat);
    edtTl = (EditText) findViewById(R.id.edtTl);
    edtTglL = (EditText) findViewById(R.id.edtTglL);
    edtUsia = (EditText) findViewById(R.id.edtUsia);
    edtNoTP = (EditText) findViewById(R.id.edtNoTP);
    edtKode = (EditText) findViewById(R.id.edtKode);
    edtEmail =(EditText) findViewById(R.id.edtEmail);
    edtPassword = (EditText) findViewById(R.id.edtPassword);

    btnKirim = (Button) findViewById(R.id.btnKirim);

    pDialog = new ProgressDialog(this);
    pDialog.setCancelable(false);

    btnKirim.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        String strNama= edtNama.getText().toString();
        String strAlamat = edtAlamat.getText().toString();
        String strTl = edtTl.getText().toString();
        String strTglL = edtTglL.getText().toString();
        String strUsia = edtUsia.getText().toString();
        String strNoTP = edtNoTP.getText().toString();
        String strKode = edtKode.getText().toString();
        String strEmail = edtEmail.getText().toString();
        String strPass = edtPassword.getText().toString();

        if(strNama.isEmpty()){
          Toast.makeText(getApplicationContext(), "Nama tidak boleh kosong",
            Toast.LENGTH_LONG).show();
        } else if(strAlamat.isEmpty()) {
          Toast.makeText(getApplicationContext(), "Alamat tidak boleh kosong",
            Toast.LENGTH_LONG).show();
        } else  if(strTl.isEmpty()) {
          Toast.makeText(getApplicationContext(), "Tempat Lahir tidak boleh kosong",
            Toast.LENGTH_LONG).show();
        } else  if(strTglL.isEmpty()) {
          Toast.makeText(getApplicationContext(), "Tanggal Lahir tidak boleh kosong",
            Toast.LENGTH_LONG).show();
        } else  if(strUsia.isEmpty()) {
          Toast.makeText(getApplicationContext(), "Usia tidak boleh kosong",
            Toast.LENGTH_LONG).show();
        } else  if(strNoTP.isEmpty()) {
          Toast.makeText(getApplicationContext(), "Nomor Telepon tidak boleh kosong",
            Toast.LENGTH_LONG).show();
        } else  if(strKode.isEmpty()) {
          Toast.makeText(getApplicationContext(), "Kode Pos tidak boleh kosong",
            Toast.LENGTH_LONG).show();
        } else  if(strEmail.isEmpty()) {
          Toast.makeText(getApplicationContext(), "Email tidak boleh kosong",
            Toast.LENGTH_LONG).show();
        } else  if(strPass.isEmpty()) {
          Toast.makeText(getApplicationContext(), "Password tidak boleh kosong",
            Toast.LENGTH_LONG).show();
        } else {
          inputData(strNama, strAlamat, strTl, strTglL, strUsia, strNoTP, strKode, strEmail, strPass);
        }
      }
    });

    getSupportActionBar().hide();

    btnLinkLogin= (Button) findViewById(R.id.btnLinkLogin);

    btnLinkLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(registrasiActivity.this, loginActivity.class);
        startActivity(i);
        finish();
      }
    });
  }
  private void inputData(String Nama, String Alamat, String Tl, String TglL, String Usia, String NoTP, String Kode, String Email, String Pass) {
    //final String URL = "/volley/resource/12";
    // Post params to be sent to the server
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("nama", Nama);
    params.put("alamat", Alamat);
    params.put("tempatLahir", Tl);
    params.put("tanggalLahir", TglL);
    params.put("usia", Usia);
    params.put("noTelepon", NoTP);
    params.put("kodePos", Kode);
    params.put("email", Email);
    params.put("password", Pass);

    pDialog.setMessage("Mohon Tunggu ");
    showDialog();

    JsonObjectRequest req = new JsonObjectRequest(ConfigUrl.inputDataRe, new JSONObject(params),
      new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
          hideDialog();
          try {
            boolean status = response.getBoolean("error");
            String msg;
            if (status == true) {
              msg = response.getString("pesan");
            } else {
              msg = response.getString("pesan");
              Intent i = new Intent(registrasiActivity.this,loginActivity.class);
              startActivity(i);
              finish();
            }
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            VolleyLog.v("Response:%n %s", response.toString(9));
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        hideDialog();
        VolleyLog.e("Error: ", error.getMessage());
      }
    });

// add the request object to the queue to be executed
//ApplicationController.getInstance().addToRequestQueue(req);
    mRequestQueue.add(req);
  }

  private void showDialog() {
    if (!pDialog.isShowing())
      pDialog.show();
  }
  private void hideDialog() {
    if (pDialog.isShowing())
      pDialog.dismiss();
  }
}
