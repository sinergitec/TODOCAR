package com.sienrgitec.todocar.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

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
import com.sienrgitec.todocar.adaptadores.AdapterImg;
import com.sienrgitec.todocar.configuracion.Globales;
import com.sienrgitec.todocar.modelos.ctInformacionArt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ViewVideo extends AppCompatActivity {
    public Globales globales;
    private String url = globales.URL;
    RequestQueue mRequestQueue;
    public static ArrayList<ctInformacionArt> listafinal  = new ArrayList<>();

    VideoView videoview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video);

        videoview = (VideoView) findViewById(R.id.videoView);

        CargaMultimedia();
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
    public void CargaMultimedia() {
        listafinal.clear();

        getmRequestQueue();
        String urlParams = String.format(url + "ctInfoArt?ipiArticulo=%1$s&ipiProveedor=%2$s&ipiDomicilio=%3$s&ipcTipo=%4$s",
                globales.g_ctArtProveedor.getiArticulo(), globales.g_ctArtProveedor.getiProveedor(), globales.g_ctArtProveedor.getiDomicilio(),"VIDEO");
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

                            Error = false;
                            if (Error == true) {
                                Toast toast = Toast.makeText(getApplicationContext(), "La consulta no generó ningun resultado. ", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                startActivity(new Intent(ViewVideo.this, MainActivity.class));

                            } else {
                                JSONObject ds_InfoArt = respuesta.getJSONObject("tt_ctInformacionArt");
                                JSONArray tt_ctInformacionArt = ds_InfoArt.getJSONArray("tt_ctInformacionArt");
                                globales.g_opInfoArtList = Arrays.asList(new Gson().fromJson(tt_ctInformacionArt.toString(), ctInformacionArt[].class));

                                Uri uri = Uri.parse("http://192.168.1.13:80/" + globales.g_opInfoArtList.get(0).getcRuta() + globales.g_opInfoArtList.get(0).getcNombre());
                                videoview.setMediaController((new MediaController(ViewVideo.this)));
                                videoview.setVideoURI(uri);
                                videoview.requestFocus();
                                videoview.start();

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
                params.put("ipcTipo", "VIDEO");
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