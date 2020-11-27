package com.sienrgitec.todocar.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
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
import com.sienrgitec.todocar.adaptadores.AdapterFPago;
import com.sienrgitec.todocar.adaptadores.AdapterImg;
import com.sienrgitec.todocar.configuracion.Globales;
import com.sienrgitec.todocar.modelos.ctFormasPago;
import com.sienrgitec.todocar.modelos.ctInformacionArt;
import com.sienrgitec.todocar.modelos.opPedidoPago;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisordeImagenes extends AppCompatActivity {

    public Globales globales;
    private String url = globales.URL;
    RequestQueue mRequestQueue;

    ImageView imageView;
    Matrix matrix = new Matrix();
    Float scale = 1f;
    ScaleGestureDetector SGD;


    public static ArrayList<ctInformacionArt> listafinal  = new ArrayList<>();
    private RecyclerView recycler;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visorde_imagenes);

        /*recycler      = (RecyclerView) findViewById(R.id.rvImagenes);
        recycler.setLayoutManager(new LinearLayoutManager(VisordeImagenes.this,LinearLayoutManager.HORIZONTAL,false));
*/

        imageView = (ImageView) findViewById(R.id.imvArt);
        SGD=new ScaleGestureDetector(this, new ScaleListener());
        CargaMultimedia();
    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{

        @Override
        public boolean onScale(ScaleGestureDetector detector){

            scale= scale*detector.getScaleFactor();
            scale=Math.max(0.1f,Math.min(scale,5f));
            matrix.setScale(scale,scale);
            imageView.setImageMatrix(matrix);
            return true;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        SGD.onTouchEvent(event);
        return true;
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
                globales.g_ctArtProveedor.getiArticulo(), globales.g_ctArtProveedor.getiProveedor(), globales.g_ctArtProveedor.getiDomicilio(),"IMAGENES");
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
                                startActivity(new Intent(VisordeImagenes.this, MainActivity.class));

                            } else {
                                JSONObject ds_InfoArt = respuesta.getJSONObject("tt_ctInformacionArt");
                                JSONArray tt_ctInformacionArt = ds_InfoArt.getJSONArray("tt_ctInformacionArt");
                                globales.g_opInfoArtList = Arrays.asList(new Gson().fromJson(tt_ctInformacionArt.toString(), ctInformacionArt[].class));


                                for (ctInformacionArt objImg: globales.g_opInfoArtList ){
                                    listafinal.add(objImg);
                                }


                                LinearLayoutManager llm = new LinearLayoutManager(VisordeImagenes.this,LinearLayoutManager.HORIZONTAL,false);
                                recycler      = (RecyclerView) findViewById(R.id.rvImagenes);
                                recycler.setLayoutManager(llm);
                                AdapterImg adapterImg = new AdapterImg(VisordeImagenes.this,  null);
                                adapterImg.setList(listafinal);
                                recycler.setAdapter(adapterImg);


                                /*AdapterImg adaptering = new AdapterImg(VisordeImagenes.this,  null);
                                adaptering.setList(listafinal);
                                recycler.setAdapter(adaptering);*/

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
                params.put("ipcTipo", "IMAGENES");
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