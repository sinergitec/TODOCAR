package com.sienrgitec.todocar.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
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
import com.sienrgitec.todocar.modelos.ctDomicilio;
import com.sienrgitec.todocar.modelos.ctTelefono;
import com.sienrgitec.todocar.modelos.ctUsuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreaDomicilio extends AppCompatActivity {
    private EditText etCalle, etNE, etNI, etCol, etDel, etCP;
    private Button btnBuscaCP, btnGuarda;

    public static ArrayList<ctDomicilio> ctDomicilioNvo = new ArrayList<>();
    private Globales globales;
    private String   url = globales.URL;
    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_domicilio);


        etCalle = (EditText) findViewById(R.id.etCalle);
        etNE    = (EditText) findViewById(R.id.etNumero);
        etNI    = (EditText) findViewById(R.id.etNumI);
        etCol   = (EditText) findViewById(R.id.etColonia);
        etDel   = (EditText) findViewById(R.id.etDel);
        etCP    = (EditText) findViewById(R.id.etCP);

        btnBuscaCP = (Button) findViewById(R.id.button2);
        btnGuarda  = (Button) findViewById(R.id.btnGuarda);

        btnBuscaCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etCalle.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(CreaDomicilio.this, "No se capturó la calle", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                if (etNE.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(CreaDomicilio.this, "No se capturó el número", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }

                if (etCol.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(CreaDomicilio.this, "No se capturó el nombre de la Colonia", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                if (etDel.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(CreaDomicilio.this, "No se capturó  Del/Mpio.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                if (etCP.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(CreaDomicilio.this, "No se capturó el Código Postal", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }

                String vcDomicilio = etCalle.getText().toString() + " "+ etNE.getText().toString() + " " + etCol.getText().toString()
                        + " " + etDel.getText().toString() + etCP.getText().toString();

                GuardaDom(vcDomicilio);
            }
        });
    }



    public void GuardaDom(String ipcDomicilio){

        Geocoder geocoder=new Geocoder(CreaDomicilio.this);
        List<Address> addressList;

        double vdeLatitud = 0.0, vdeLongitud = 0.0;

        try {
            addressList = geocoder.getFromLocationName(ipcDomicilio, 1);
            if(addressList!=null){
                Address singleaddress=addressList.get(0);
                vdeLatitud  = singleaddress.getLatitude();
                vdeLongitud = singleaddress.getLongitude();
                /*latLng =new LatLng(singleaddress.getLatitude(),singleaddress.getLongitude());*/
                Log.e("Home-->", " Abriendo mapas" + vdeLatitud + " " + vdeLongitud);

            }

        }
        catch (Exception e){
            e.printStackTrace();
            return ;
        }



        ctDomicilio objNvoDom = new ctDomicilio();
        objNvoDom.setiDomicilio(globales.g_ctCliente.getiCliente());
        objNvoDom.setiTipoDomicilio(0);
        objNvoDom.setiTipoPersona(0);
        objNvoDom.setiPersona(0);
        objNvoDom.setcCalle(etCalle.getText().toString());
        objNvoDom.setcNumExt(etNE.getText().toString());
        objNvoDom.setcNumInt(etNI.getText().toString());
        objNvoDom.setcColonia(etCol.getText().toString());
        objNvoDom.setcMpioDeleg(etDel.getText().toString());
        objNvoDom.setcCP(etCP.getText().toString());
        objNvoDom.setDeLatitud(vdeLatitud);
        objNvoDom.setDeLongitud(vdeLongitud);
        objNvoDom.setcUsuCrea(globales.g_ctCliente.getcEmail());
        objNvoDom.setcUsuModifica(globales.g_ctCliente.getcEmail());
        ctDomicilioNvo.add(objNvoDom);


        JSONObject jsonBody = new JSONObject();
        JSONObject jsonParams = new JSONObject();
        JSONObject jsonDataSet = new JSONObject();


        final Gson gson = new Gson();
        String JS_ctDomicilio = gson.toJson(
                ctDomicilioNvo,
                new TypeToken<ArrayList<ctDomicilio>>() {
                }.getType());
        try {
            JSONArray ctDomicilio   = new JSONArray(JS_ctDomicilio);

            jsonDataSet.put("tt_ctDomicilio",  ctDomicilio);


            jsonParams.put("ds_ctDomicilio", jsonDataSet);
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
                                Toast toast = Toast.makeText(CreaDomicilio.this, "Error Response: " + Mensaje, Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                return;

                            } else {

                                Toast toast = Toast.makeText(CreaDomicilio.this, "Pedido Realizado exitosamente: " + Mensaje, Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();


                                Intent carrito = new Intent(CreaDomicilio.this, MainActivity.class);
                                startActivity(carrito);


                                /*globales.opPedidoList.clear();
                                globales.opPedidoProvList.clear();
                                globales.opPedidoDetList.clear();*/


                            }


                        } catch (JSONException e) {
                            Log.i("Error JSONExcepcion", e.getMessage());
                            Toast toast = Toast.makeText(CreaDomicilio.this, "Error JSONExcepcion: " +  e.getMessage(), Toast.LENGTH_SHORT);
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
                        Toast toast = Toast.makeText(CreaDomicilio.this, "Error Response: " +  error.toString(), Toast.LENGTH_SHORT);
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
                mRequestQueue = Volley.newRequestQueue(CreaDomicilio.this);
                //your code
            }
        }catch(Exception e){
            Log.d("Volley",e.toString());
        }
    }
}