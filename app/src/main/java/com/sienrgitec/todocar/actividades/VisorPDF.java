package com.sienrgitec.todocar.actividades;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.gson.Gson;
import com.sienrgitec.todocar.R;
import com.sienrgitec.todocar.adaptadores.AdapterArt;
import com.sienrgitec.todocar.adaptadores.AdapterImg;
import com.sienrgitec.todocar.configuracion.Globales;
import com.sienrgitec.todocar.modelos.ctArtProveedor;
import com.sienrgitec.todocar.modelos.ctInformacionArt;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VisorPDF extends AppCompatActivity {
    public Globales globales;
    private String url = globales.URL;
    RequestQueue mRequestQueue;

    public static ArrayList<ctInformacionArt> listafinal  = new ArrayList<>();

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_pdf);


        CargarPDF();




    }
    public void getmRequestQueue() {
        try{
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(getApplicationContext());
                //your code
            }
        }catch(Exception e){
            Log.d("Volley",e.toString());
        }
    }

    public void CargarPDF(){
        listafinal.clear();

        getmRequestQueue();
        String urlParams = String.format(url + "ctInformacionArt?ipiArticulo=%1$s&ipiProveedor=%2$s&ipiDomicilio=%3$s&ipcTipo=%4$s",
                globales.g_ctArtProveedor.getiArticulo(), globales.g_ctArtProveedor.getiProveedor(), globales.g_ctArtProveedor.getiDomicilio(),"PDF");
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
                                Toast toast = Toast.makeText(getApplicationContext(), "La consulta no generó ningun resultado. ", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                startActivity(new Intent(VisorPDF.this, MainActivity.class));

                            } else {
                                JSONObject ds_InfoArt = respuesta.getJSONObject("tt_ctInformacionArt");
                                JSONArray tt_ctInformacionArt = ds_InfoArt.getJSONArray("tt_ctInformacionArt");
                                globales.g_opInfoArtList = Arrays.asList(new Gson().fromJson(tt_ctInformacionArt.toString(), ctInformacionArt[].class));


                                /*for (ctInformacionArt objImg: globales.g_opInfoArtList ){
                                    listafinal.add(objImg);
                                }*/

                                String url ="http://192.168.1.13:80/" + globales.g_opInfoArtList.get(0).getcRuta() + globales.g_opInfoArtList.get(0).getcNombre();

                                pdfView = (PDFView) findViewById(R.id.pdfView);

                                String urlHTTPS = url.replace("http", "http");

                                new RetrivePDFBytes().execute(urlHTTPS);


                            }
                        } catch (JSONException e) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Error en la conversion de Datos.", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast toast = Toast.makeText(getApplicationContext(), "Error de Conexión", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ipiArticulo", globales.g_ctArtProveedor.getiArticulo().toString());
                params.put("ipiProveedor", globales.g_ctArtProveedor.getiProveedor().toString());
                params.put("ipiDomicilio", globales.g_ctArtProveedor.getiDomicilio().toString());
                params.put("ipcTipo", "PDF");
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

    class RetrivePDFBytes extends AsyncTask<String,Void, byte[]> {

        @Override
        protected byte[] doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);

                Log.i("url", url.toString());

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                Log.i("url", String.valueOf(urlConnection.getResponseCode()));

                if (urlConnection.getResponseCode() == 200) {
                    Log.i("url--> 200", String.valueOf(urlConnection.getResponseCode()));
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }


            try {
                return IOUtils.toByteArray(inputStream);
            } catch (IOException e) {

                Log.i("url--> catch", String.valueOf(e));
                e.printStackTrace();
            }


            return null;

        }


        @Override
        protected void onPostExecute(byte[] bytes) {
            pdfView.fromBytes(bytes).load();

        }

    }
}