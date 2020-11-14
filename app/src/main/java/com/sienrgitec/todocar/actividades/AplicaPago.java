package com.sienrgitec.todocar.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
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
import com.google.gson.reflect.TypeToken;
import com.sienrgitec.todocar.R;
import com.sienrgitec.todocar.adaptadores.AdapterArt;
import com.sienrgitec.todocar.adaptadores.AdapterFPago;
import com.sienrgitec.todocar.configuracion.Globales;
import com.sienrgitec.todocar.modelos.ctArtProveedor;
import com.sienrgitec.todocar.modelos.ctFormasPago;
import com.sienrgitec.todocar.modelos.opPedido;
import com.sienrgitec.todocar.modelos.opPedidoDet;
import com.sienrgitec.todocar.modelos.opPedidoDomicilio;
import com.sienrgitec.todocar.modelos.opPedidoPago;
import com.sienrgitec.todocar.modelos.opPedidoProveedor;

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
    private Button btnFinPed;


    public static ArrayList<ctFormasPago> listafinal  = new ArrayList<>();
    public static List<ctFormasPago>      ctFPagoList = null;


    public static ArrayList<opPedidoPago> opPedPAgoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplica_pago);

        recycler      = (RecyclerView) findViewById(R.id.listFPago);
        recycler.setLayoutManager(new LinearLayoutManager(AplicaPago.this,LinearLayoutManager.VERTICAL,false));

        btnFinPed = (Button) findViewById(R.id.btnCreaPed);

        btnFinPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(globales.g_ctFormasPago == null) {
                    Toast toast = Toast.makeText(AplicaPago.this, "Error: debe indicar una forma de Pago para continuar!!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                CrearPedido();
            }
        });
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

    public void CrearPedido(){

        opPedidoPago objNvoPago = new opPedidoPago();
        objNvoPago.setiPedido(0);
        objNvoPago.setiPartida(1);
        objNvoPago.setiFormaPago(globales.g_ctFormasPago.getiFormaPago());
        objNvoPago.setDeMonto(130.00);
        objNvoPago.setDeProcComision(0.0);
        objNvoPago.setDeComision(0.0);
        objNvoPago.setDePorcPropina(0.0);
        objNvoPago.setDtCreado(null);
        objNvoPago.setDtModificado(null);
        objNvoPago.setcUsuCrea(globales.g_ctUsuario.getcUsuario());
        objNvoPago.setiCliente(globales.g_ctUsuario.getiPersona());
        objNvoPago.setiOrigenFP(1);
        objNvoPago.setcCuenta("100");
        opPedPAgoList.add(objNvoPago);


        JSONObject jsonBody = new JSONObject();
        JSONObject jsonParams = new JSONObject();
        JSONObject jsonDataSet = new JSONObject();




        final Gson gson = new Gson();
        String JS_opPedido = gson.toJson(
                globales.opPedidoList,
                new TypeToken<ArrayList<opPedido>>() {
                }.getType());

        String JS_oPedidoProveedor = gson.toJson(
                globales.opPedidoProvList,
                new TypeToken<ArrayList<opPedidoProveedor>>() {
                }.getType());

        String JS_opPedidoDet = gson.toJson(
                globales.opPedidoDetList,
                new TypeToken<ArrayList<opPedidoDet>>() {
                }.getType());

        String JS_opPedPago = gson.toJson(
                opPedPAgoList,
                new TypeToken<ArrayList<opPedidoPago>>() {
                }.getType());

        String JS_opPedDom = gson.toJson(
                globales.opPedidDomList,
                new TypeToken<ArrayList<opPedidoDomicilio>>() {
                }.getType());

        try {
            JSONArray opPedido   = new JSONArray(JS_opPedido);
            JSONArray opPedidoProveedor = new JSONArray(JS_oPedidoProveedor);
            JSONArray opPedidoDet  = new JSONArray(JS_opPedidoDet);
            JSONArray opPedidoPago  = new JSONArray(JS_opPedPago);
            JSONArray opPedidoDomicilio  = new JSONArray(JS_opPedDom);


            jsonDataSet.put("tt_opPedido",  opPedido);
            jsonDataSet.put("tt_opPedidoProveedor",opPedidoProveedor);
            jsonDataSet.put("tt_opPedidoDet", opPedidoDet);
            jsonDataSet.put("tt_opPedidoPago", opPedidoPago);
            jsonDataSet.put("tt_opPedidoDomicilio", opPedidoDomicilio);



            jsonParams.put("ds_NvoPedido", jsonDataSet);
            jsonBody.put("request", jsonParams);

            Log.i("Response", jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();

        }

        //mRequestQueue = Volley.newRequestQueue(comanda.this);
        getmRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url + "opPedRefacc/", jsonBody, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta resrt->", "mensaje: " + respuesta.toString());

                            Boolean Error = respuesta.getBoolean("oplError");
                            String Mensaje = respuesta.getString("opcerror");
                            if (Error == true) {
                                Toast toast = Toast.makeText(AplicaPago.this, "Error Response: " + Mensaje, Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                return;

                            } else {

                                Toast toast = Toast.makeText(AplicaPago.this, "Pedido Realizado exitosamente: " + Mensaje, Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();


                                Intent carrito = new Intent(AplicaPago.this, MainActivity.class);
                                startActivity(carrito);


                                /*globales.ctPainaniList.clear();
                                globales.ctDomicilioList.clear();
                                globales.ctContactoList.clear();
                                globales.ctUsuarioList.clear();

                                finish();*/
                            }


                        } catch (JSONException e) {
                            Log.i("Error JSONExcepcion", e.getMessage());
                            Toast toast = Toast.makeText(AplicaPago.this, "Error JSONExcepcion: " +  e.getMessage(), Toast.LENGTH_SHORT);
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
                        Toast toast = Toast.makeText(AplicaPago.this, "Error Response: " +  error.toString(), Toast.LENGTH_SHORT);
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
}