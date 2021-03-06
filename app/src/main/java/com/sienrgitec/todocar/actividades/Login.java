package com.sienrgitec.todocar.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.sienrgitec.todocar.R;
import com.sienrgitec.todocar.configuracion.Globales;
import com.sienrgitec.todocar.modelos.ctCliente;
import com.sienrgitec.todocar.modelos.ctDomicilio;
import com.sienrgitec.todocar.modelos.ctUsuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login extends AppCompatActivity {
    private Globales globales;
    private String   url = globales.URL;
    private EditText etUsuario, etPassword;
    private Button   btnEntrar;
    private TextView txtNvoUsuario, txtRecupera;

    private static RequestQueue mRequestQueue;

    public static List<ctUsuario>   ctUsuarioList = null;
    public static List<ctDomicilio> ctDomicilioList = null;
    public static List<ctCliente>   ctClienteList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario  = (EditText) findViewById(R.id.etUsuario);
        etPassword = (EditText) findViewById(R.id.etPassw);

        btnEntrar  = (Button)   findViewById(R.id.loginBtn);

        txtNvoUsuario = (TextView) findViewById(R.id.registro);
        txtRecupera   = (TextView) findViewById(R.id.recupera);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Entrar();
            }
        });

        txtNvoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registro.class));
            }
        });
    }
    public void getmRequestQueue(){
        try{
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(getApplicationContext());
                //your code
            }
        }catch(Exception e){
            Log.d("Volley",e.toString());
        }
    }

    public void Entrar(){
        btnEntrar.setEnabled(false);

        final String vcUsuLog = etUsuario.getText().toString();
        final String password = etPassword.getText().toString();

        if (etUsuario.getText().toString().isEmpty()) {
            Toast toast = Toast.makeText(Login.this, "No se capturó el nombre de usuario", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }else{
            String email    = etUsuario.getText().toString().trim();

            // String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

            String emailPattern =  "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

            if (!email.matches(emailPattern)){
                etUsuario.requestFocus();
                etUsuario.setError("el email debe tener un formato valido");

                return;

            }
        }

        if (etPassword.getText().toString().isEmpty()) {
            Toast toast = Toast.makeText(Login.this, "No se capturó la contraseña", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }

        getmRequestQueue();

        String urlParams = String.format(url + "loginCli?ipcUsuario=%1$s&ipcPassword=%2$s",
                vcUsuLog, password);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlParams, null, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta--->", respuesta.toString());

                            String Mensaje  = respuesta.getString("opcError");
                            Boolean Error   = respuesta.getBoolean("oplError");
                            Boolean vlTieneDom = respuesta.getBoolean("oplTieneDom");


                            JSONObject ds_ctUsuario   = respuesta.getJSONObject("tt_ctUsuario");
                            JSONObject ds_ctDomicilio = respuesta.getJSONObject("tt_ctDomicilio");
                            JSONObject ds_ctCliente   = respuesta.getJSONObject("tt_ctCliente");


                            JSONArray tt_ctUsuario = ds_ctUsuario.getJSONArray("tt_ctUsuario");
                            JSONArray tt_ctDomicilio = ds_ctDomicilio.getJSONArray("tt_ctDomicilio");
                            JSONArray tt_ctCliente = ds_ctCliente.getJSONArray("tt_ctCliente");


                            ctUsuarioList = Arrays.asList(new Gson().fromJson(tt_ctUsuario.toString(), ctUsuario[].class));
                            ctDomicilioList = Arrays.asList(new Gson().fromJson(tt_ctDomicilio.toString(), ctDomicilio[].class));
                            ctClienteList   = Arrays.asList(new Gson().fromJson(tt_ctCliente.toString(), ctCliente[].class));

                            if (Error == true) {
                                btnEntrar.setEnabled(true);
                                Toast toast = Toast.makeText(Login.this, "Error: " + Mensaje, Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                            } else {
                                globales.g_ctUsuario   = ctUsuarioList.get(0);
                                globales.g_ctCliente   = ctClienteList.get(0);

                                //startActivity(new Intent(Login.this, MainActivity.class));

                                if(vlTieneDom == false ){
                                    Toast toast = Toast.makeText(Login.this, "¡Bienvenid@! para continuar debes registrar tu domicilio" , Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER, 0, 0);
                                    toast.show();

                                    startActivity(new Intent(Login.this, CreaDomicilio.class));

                                }else {
                                    globales.g_ctDomicilio = ctDomicilioList.get(0);
                                    startActivity(new Intent(Login.this, MainActivity.class));
                                }
                            }
                        } catch (JSONException e) {
                            btnEntrar.setEnabled(true);
                            Toast toast = Toast.makeText(Login.this, "Error: " + e, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        btnEntrar.setEnabled(true);
                        Toast toast = Toast.makeText(Login.this, "No se pudo conectar con el servidor " + error, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ipcUsuario", vcUsuLog);
                params.put("ipcPassword", password);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        // Access the RequestQueue through your singleton class.
        mRequestQueue.add(jsonObjectRequest);
    }

}