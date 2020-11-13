package com.sienrgitec.todocar.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.sienrgitec.todocar.R;
import com.sienrgitec.todocar.adaptadores.AdapterArt;
import com.sienrgitec.todocar.adaptadores.AdapterFPago;
import com.sienrgitec.todocar.configuracion.Globales;
import com.sienrgitec.todocar.modelos.ctArtProveedor;
import com.sienrgitec.todocar.modelos.ctFormasPago;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AplicaPago extends AppCompatActivity {
    public Globales globales;
    private String url = globales.URL;

    RequestQueue mRequestQueue;
    public RecyclerView recycler;

    public static ArrayList<ctFormasPago> listafinal       = new ArrayList<>();
    public static List<ctFormasPago> ctFPagoList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplica_pago);

        recycler      = (RecyclerView) findViewById(R.id.listFPago);
        recycler.setLayoutManager(new LinearLayoutManager(AplicaPago.this,LinearLayoutManager.VERTICAL,false));

        CargaFPago();
    }


    public void getmRequestQueue() {
        try{
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(AplicaPago.this);
                //your code
            }
        }catch(Exception e){
            Log.d("Volley",e.toString());
        }
    }

    public void CargaFPago(){

        listafinal.clear();

        getmRequestQueue();
        String urlParams = String.format(url + "ctFormasPago");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlParams, null, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta--->", respuesta.toString());

                            String Mensaje = respuesta.getString("opcError");
                            Boolean Error = respuesta.getBoolean("oplError");


                            if (Error == true) {
                                Toast toast = Toast.makeText(AplicaPago.this, "La consulta no generó ningun resultado. ", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();


                            } else {


                                JSONObject ds_ctFPago   = respuesta.getJSONObject("tt_ctFormasPago");
                                JSONArray tt_ctFormasPago  = ds_ctFPago.getJSONArray("tt_ctFormasPago");
                                ctFPagoList     = Arrays.asList(new Gson().fromJson(tt_ctFormasPago.toString(), ctFormasPago[].class));



                                for(ctFormasPago obj: ctFPagoList){
                                    listafinal.add(obj);
                                    Log.e("AplicaPago-->", "ok");
                                }


                                AdapterFPago adapter = new AdapterFPago(AplicaPago.this,null);
                                adapter.setList((List<ctFormasPago>) listafinal);
                                recycler.setAdapter(adapter);


                            }
                        } catch (JSONException e) {
                            Toast toast = Toast.makeText(AplicaPago.this, "Error en la conversion de Datos.", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast toast = Toast.makeText(AplicaPago.this, "Error de Conexión", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();


                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };


        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        mRequestQueue.add(jsonObjectRequest);

    }
}