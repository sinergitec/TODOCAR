package com.sienrgitec.todocar.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.sienrgitec.todocar.R;
import com.sienrgitec.todocar.adaptadores.AdapterArt;
import com.sienrgitec.todocar.configuracion.Globales;
import com.sienrgitec.todocar.modelos.ctArtProveedor;
import com.sienrgitec.todocar.modelos.opPedido;
import com.sienrgitec.todocar.modelos.opPedidoDet;
import com.sienrgitec.todocar.modelos.opPedidoDomicilio;
import com.sienrgitec.todocar.modelos.opPedidoProveedor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    public Globales globales;
    private String url = globales.URL;

    RequestQueue mRequestQueue;

    private Context context;
    private Button btnBuscaArt, btnAddCarrito, btnMiCarrito, btnComements;
    private ImageButton btnImagenes, btnVideos, btnPDF;
    private EditText etCapturaArt;
    private TextView  tvAplicaciones, tvCliente, tvPrecio;
    private RatingBar ratingBar;

    public RecyclerView recycler;

    public static ArrayList<ctArtProveedor> listafinal       = new ArrayList<>();
    public static List<ctArtProveedor> ctArtProviList = null;

    public  int viCantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        btnBuscaArt   = (Button)   findViewById(R.id.btnBuscaArt);
        btnAddCarrito = (Button)   findViewById(R.id.button6);
        btnMiCarrito  = (Button)   findViewById(R.id.button3);
        btnComements  = (Button)   findViewById(R.id.button);

        btnImagenes  = (ImageButton)   findViewById(R.id.ibtnGaleria);
        btnVideos    = (ImageButton)   findViewById(R.id.imageButton);
        btnPDF       = (ImageButton)   findViewById(R.id.imageButton3);


        etCapturaArt = (EditText) findViewById(R.id.etCapturaArt);

        tvAplicaciones = (TextView) findViewById(R.id.etAplicaciones);
        tvCliente      = (TextView) findViewById(R.id.tvCliente);
        tvPrecio       = (TextView) findViewById(R.id.editTextNumberDecimal);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);;

        tvCliente.setText("TOCAR.MX HOLA! " + globales.g_ctCliente.getcNombre());


        recycler      = (RecyclerView) findViewById(R.id.listaArt);
        recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));


        btnBuscaArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuscarArts();
            }
        });

        btnImagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuscarFotos( view);
            }
        });

        btnVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuscarVideos();
            }
        });

        btnPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuscarPDF();
            }
        });

        btnAddCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarCarrito();
            }
        });

        btnMiCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MiCarrito();
            }
        });

        btnComements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, "En Construccion!!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });



    }

    public void getmRequestQueue() {
        try{
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(MainActivity.this);
                //your code
            }
        }catch(Exception e){
            Log.d("Volley",e.toString());
        }
    }

    public void BuscarArts(){
        if (etCapturaArt.getText().toString().isEmpty()) {
            Toast toast = Toast.makeText(MainActivity.this, "Debes capturar una palabrea clave  para buscar.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }

        tvPrecio.setText("0.00");
        tvAplicaciones.setText("");
        ratingBar.setRating(0);

        Toast toast = Toast.makeText(MainActivity.this, "Generando Consulta. Epsera...", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        listafinal.clear();

        getmRequestQueue();
        String urlParams = String.format(url + "ctArtRefacciones?ipcAplicaciones=%1$s",  etCapturaArt.getText());
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
                                Toast toast = Toast.makeText(MainActivity.this, "La consulta no generó ningun resultado. " +  etCapturaArt.getText(), Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                            } else {

                                Log.e("mainActivity-->", "else: " + etCapturaArt.getText());
                                JSONObject ds_Articulos   = respuesta.getJSONObject("tt_ctArtProveedor");
                                JSONArray tt_ctArtProveedor  = ds_Articulos.getJSONArray("tt_ctArtProveedor");
                                ctArtProviList     = Arrays.asList(new Gson().fromJson(tt_ctArtProveedor.toString(), ctArtProveedor[].class));

                                for(ctArtProveedor obj: ctArtProviList){
                                    listafinal.add(obj);
                                    Log.e("mainActivity-->", "ok");
                                }

                                AdapterArt adapter = new AdapterArt(MainActivity.this,null);
                                adapter.setList((List<ctArtProveedor>) listafinal);
                                adapter.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        globales.g_ctArtProveedor = listafinal.get(recycler.getChildAdapterPosition(view));
                                        Toast toast = Toast.makeText(MainActivity.this, "Seleccionado: " + listafinal.get(recycler.getChildAdapterPosition(view)).getcArticulo(), Toast.LENGTH_SHORT);
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show();

                                        tvAplicaciones.setText(listafinal.get(recycler.getChildAdapterPosition(view)).getcAplicaciones());
                                        tvPrecio.setText(listafinal.get(recycler.getChildAdapterPosition(view)).getDePrecio().toString());
                                        ratingBar.setRating(Float.parseFloat(String.valueOf(listafinal.get(recycler.getChildAdapterPosition(view)).getDePeso())));

                                    }
                                }) ;
                                 recycler.setAdapter(adapter);


                            }
                        } catch (JSONException e) {
                            Toast toast = Toast.makeText(MainActivity.this, "Error en la conversion de Datos.", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast toast = Toast.makeText(MainActivity.this, "Error de Conexión", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ipcAplicaciones", etCapturaArt.getText().toString());

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

    public void BuscarFotos(View v){
        Log.e("buscando-fotos","C");
    }

    public void BuscarVideos(){
        Log.e("buscando-fotos-Art--> ",globales.vg_iArticulo.toString());
    }

    public void BuscarPDF(){
        Log.e("buscando-PDF-Art--> ",globales.vg_iArticulo.toString());
    }

    public void AgregarCarrito(){

        final opPedidoDet[] opPedidoDetUltimo = new opPedidoDet[1];

        if(globales.vg_lPideCant.equals(true)){
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            View promptView = layoutInflater.inflate(R.layout.prompt,null);
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(MainActivity.this);
            alertdialog.setTitle(Html.fromHtml("<font color ='#FF0000'> Capture la cantidad a comprar?  </font>"));
            alertdialog.setView(promptView);

            final EditText e1 = (EditText) promptView.findViewById(R.id.editText);

            alertdialog.setCancelable(false)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if(e1.getText().toString().isEmpty()){
                                Toast toast = Toast.makeText(MainActivity.this, "La cantidad no puede estar vacía", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                return;
                            }
                            if(e1.getText().toString().equals(0)){
                                Toast toast = Toast.makeText(MainActivity.this, "La cantidad no puede ser cero", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                return;
                            }

                            String vcNumero = e1.getText().toString();
                            viCantidad =  Integer.valueOf(vcNumero);

                            int viPartida = 0;
                            if (globales.opPedidoDetList.size() == 0) {
                                viPartida = 1;
                            } else {
                                opPedidoDetUltimo[0] = globales.opPedidoDetList.get(globales.opPedidoDetList.size() - 1);
                                viPartida = opPedidoDetUltimo[0].getiPartida() + 1;
                            }

                            /*Crea Detalle*/
                            opPedidoDet objNvoArt = new opPedidoDet();
                            objNvoArt.setiPedido(0);
                            objNvoArt.setiPedidoProv(25);
                            objNvoArt.setiPartida(viPartida);
                            objNvoArt.setDtFecha(null);
                            objNvoArt.setiArticulo(globales.g_ctArtProveedor.getiArticulo());
                            objNvoArt.setcArticulo(globales.g_ctArtProveedor.getcArticulo());
                            objNvoArt.setcDescripcion(globales.g_ctArtProveedor.getcDescripcion());
                            objNvoArt.setDePrecioVta(globales.g_ctArtProveedor.getDePrecio());
                            objNvoArt.setDePrecioUnit(globales.g_ctArtProveedor.getDePrecio());
                            objNvoArt.setDeCantidad((double) viCantidad);
                            objNvoArt.setDeImporte(globales.g_ctArtProveedor.getDePrecio() * (double) viCantidad);
                            objNvoArt.setcUsuCrea(globales.g_ctUsuario.getcUsuario());
                            objNvoArt.setcUsuModificado(globales.g_ctUsuario.getcUsuario());
                            globales.opPedidoDetList.add(objNvoArt);


                            Toast toast = Toast.makeText(MainActivity.this, "Agregando a Carrito", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();

                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });
            alertdialog.create();
            alertdialog.show();
        }
    }

    public void MiCarrito(){
        Intent carrito = new Intent(MainActivity.this, MiCarrito.class);
        startActivity(carrito);
    }


}
