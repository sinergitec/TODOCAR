package com.sienrgitec.todocar.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sienrgitec.todocar.R;
import com.sienrgitec.todocar.configuracion.Globales;
import com.sienrgitec.todocar.modelos.ctCliente;
import com.sienrgitec.todocar.modelos.ctTelefono;
import com.sienrgitec.todocar.modelos.ctUsuario;
import com.sienrgitec.todocar.modelos.opPedido;
import com.sienrgitec.todocar.modelos.opPedidoDet;
import com.sienrgitec.todocar.modelos.opPedidoDomicilio;
import com.sienrgitec.todocar.modelos.opPedidoPago;
import com.sienrgitec.todocar.modelos.opPedidoProveedor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    private Globales globales;
    private String   url = globales.URL;
    RequestQueue mRequestQueue;

    private EditText etNombre, etApPat, etApMat, etCorreo, etPassword, etTelefono;
    private Button btnRegistro;

    public static ArrayList<ctCliente> ctClienteNvo = new ArrayList<>();
    public static ArrayList<ctUsuario> ctUsuarioNvo = new ArrayList<>();
    public static ArrayList<ctTelefono> ctTelefonoNvo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = (EditText) findViewById(R.id.descripcion);
        etApPat  = (EditText) findViewById(R.id.apellidoP);
        etApMat  = (EditText) findViewById(R.id.apellidoM);
        etCorreo = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        etTelefono = (EditText) findViewById(R.id.telefono);

        btnRegistro = (Button) findViewById(R.id.registroBtn);



        btnRegistro.setOnClickListener(new View.OnClickListener(){
            public void onClick(View c){
                CreaNvoCli();
            }
        });
    }

    public void CreaNvoCli (){
        if (etNombre.getText().toString().isEmpty()) {
            Toast toast = Toast.makeText(Registro.this, "No se capturó el nombre de usuario", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if (etApPat.getText().toString().isEmpty()) {
            Toast toast = Toast.makeText(Registro.this, "No se capturó el Apellido Paterno de usuario", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if (etApMat.getText().toString().isEmpty()) {
            Toast toast = Toast.makeText(Registro.this, "No se capturó el Apellido Materno de usuario", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if (etCorreo.getText().toString().isEmpty()) {
            Toast toast = Toast.makeText(Registro.this, "No se capturó el correo electronico de usuario", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if (etPassword.getText().toString().isEmpty()) {
            Toast toast = Toast.makeText(Registro.this, "No se capturó el password de usuario", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if (etTelefono.getText().toString().isEmpty()) {
            Toast toast = Toast.makeText(Registro.this, "No se capturó el telefono de usuario", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }

        ctCliente objNvoCli = new ctCliente();
        objNvoCli.setiCliente(0);
        objNvoCli.setcNombre(etNombre.getText().toString());
        objNvoCli.setcApellidos(etApPat.getText().toString() + " " + etApMat.getText().toString());
        objNvoCli.setcEmail(etCorreo.getText().toString());
        objNvoCli.setcUsuCrea(etCorreo.getText().toString());
        objNvoCli.setcUsuModifica(etCorreo.getText().toString());
        ctClienteNvo.add(objNvoCli);

        ctUsuario objNvoUsu = new ctUsuario();
        objNvoUsu.setiPersona(0);
        objNvoUsu.setiTipoPersona(0);
        objNvoUsu.setcUsuario(etCorreo.getText().toString());
        objNvoUsu.setcUsuCrea(etCorreo.getText().toString());
        objNvoUsu.setcUsuModifica(etCorreo.getText().toString());
        ctUsuarioNvo.add(objNvoUsu);

        ctTelefono objNvoTel = new ctTelefono();
        objNvoTel.setiPersona(0);
        objNvoTel.setiTipoPersona(0);
        objNvoTel.setiTipoTelefono(1);
        objNvoTel.setiTelefono(0);
        objNvoTel.setcTelefono(etTelefono.getText().toString());
        objNvoTel.setlActivo(true);
        objNvoTel.setcUsuCrea(etCorreo.getText().toString());
        objNvoTel.setcUsuModifica(etCorreo.getText().toString());
        ctTelefonoNvo.add(objNvoTel);


        JSONObject jsonBody = new JSONObject();
        JSONObject jsonParams = new JSONObject();
        JSONObject jsonDataSet = new JSONObject();




        final Gson gson = new Gson();
        String JS_ctCliente = gson.toJson(
                ctClienteNvo,
                new TypeToken<ArrayList<ctCliente>>() {
                }.getType());
        String JS_ctTelefono = gson.toJson(
                ctTelefonoNvo,
                new TypeToken<ArrayList<ctTelefono>>() {
                }.getType());
        String JS_ctUsuario = gson.toJson(
                ctUsuarioNvo,
                new TypeToken<ArrayList<ctUsuario>>() {
                }.getType());





        try {
            JSONArray ctCliente   = new JSONArray(JS_ctCliente);
            JSONArray ctTelefono = new JSONArray(JS_ctTelefono);
            JSONArray ctUsuario  = new JSONArray(JS_ctUsuario);


            jsonDataSet.put("tt_ctCliente",  ctCliente);
            jsonDataSet.put("tt_ctTelefono",ctTelefono);
            jsonDataSet.put("tt_ctUsuario", ctUsuario);

            jsonParams.put("ds_ctCliente", jsonDataSet);
            jsonBody.put("request", jsonParams);

            Log.i("Response", jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();

        }

        //mRequestQueue = Volley.newRequestQueue(comanda.this);
        getmRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url + "ctCliente/", jsonBody, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta resrt->", "mensaje: " + respuesta.toString());

                            Boolean Error = respuesta.getBoolean("oplError");
                            String Mensaje = respuesta.getString("opcError");
                            if (Error == true) {
                                Toast toast = Toast.makeText(Registro.this, "Error Response: " + Mensaje, Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                return;

                            } else {

                                Toast toast = Toast.makeText(Registro.this, "Pedido Realizado exitosamente: " + Mensaje, Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();


                                Intent carrito = new Intent(Registro.this, MainActivity.class);
                                startActivity(carrito);


                                /*globales.opPedidoList.clear();
                                globales.opPedidoProvList.clear();
                                globales.opPedidoDetList.clear();*/


                            }


                        } catch (JSONException e) {
                            Log.i("Error JSONExcepcion", e.getMessage());
                            Toast toast = Toast.makeText(Registro.this, "Error JSONExcepcion: " +  e.getMessage(), Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            return;
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i("Error", error.toString());
                        Toast toast = Toast.makeText(Registro.this, "Error Response: " +  error.toString(), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        return;

                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        mRequestQueue.add(jsonObjectRequest);

    }

    public void getmRequestQueue() {
        try{
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(Registro.this);
                //your code
            }
        }catch(Exception e){
            Log.d("Volley",e.toString());
        }
    }
 }

