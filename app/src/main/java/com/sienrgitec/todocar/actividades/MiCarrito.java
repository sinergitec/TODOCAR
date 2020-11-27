package com.sienrgitec.todocar.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sienrgitec.todocar.R;
import com.sienrgitec.todocar.adaptadores.AdapterArt;
import com.sienrgitec.todocar.adaptadores.CarritoAdapter;
import com.sienrgitec.todocar.configuracion.Globales;
import com.sienrgitec.todocar.modelos.ctArtProveedor;
import com.sienrgitec.todocar.modelos.opPedido;
import com.sienrgitec.todocar.modelos.opPedidoDet;
import com.sienrgitec.todocar.modelos.opPedidoDomicilio;
import com.sienrgitec.todocar.modelos.opPedidoProveedor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MiCarrito extends AppCompatActivity {


    public RecyclerView recycler;
    private Globales globales;
    private Button btnPagar;
    private TextView tvTArt, tvTImporte, tvArtSelecc;
    private ImageView ibtnBorrar, ibtnActualizar;

    private opPedidoDet renglon;

    public static ArrayList<opPedidoDet> listafinal       = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_carriro);

        recycler      = (RecyclerView) findViewById(R.id.listCarrito);
        recycler.setLayoutManager(new LinearLayoutManager(MiCarrito.this,LinearLayoutManager.VERTICAL,false));

        btnPagar = (Button) findViewById(R.id.btnPagar);


        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeleccPago();
            }
        });

        tvTArt     =  (TextView) findViewById(R.id.tvTotArtD);
        tvTImporte =  (TextView) findViewById(R.id.tvImporteD);
        tvArtSelecc = (TextView) findViewById(R.id.tvArtSelecc);

        ibtnBorrar = (ImageView) findViewById(R.id.imageView);
        ibtnActualizar = (ImageView) findViewById(R.id.imageView2);

        ibtnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EliminaPda();
            }
        });

        CreaDetalle();
    }

    public void CreaDetalle(){
        listafinal.clear();
        CarritoAdapter adapter = new CarritoAdapter(MiCarrito.this,null);
        adapter.setList((List<opPedidoDet>) listafinal);

       /* adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvArtSelecc.setText(listafinal.get(recycler.getChildAdapterPosition(view)).getcDescripcion());
                globales.vg_cDescripcion = (listafinal.get(recycler.getChildAdapterPosition(view)).getcDescripcion());;



                globales.vg_iPartida = (listafinal.get(recycler.getChildAdapterPosition(view)).getiPartida());
                renglon              = (listafinal.get(recycler.getChildAdapterPosition(view)));

                Log.e("micarrito --> ", "pda " +globales.vg_iPartida );

            }
        }) ;*/

        recycler.setAdapter(adapter);
        CalculaTotales();
    }

    public void CalculaTotales(){
        double vdeTArtC = 0 , vdeTImpC = 0;

        for(opPedidoDet objLista: globales.opPedidoDetList){
            listafinal.add(objLista);
            vdeTArtC = vdeTArtC + objLista.getDeCantidad();
            vdeTImpC = vdeTImpC + objLista.getDeImporte();
        }

        String cantidadString = new DecimalFormat("0").format(vdeTArtC);
        tvTArt.setText("Tot. Art.: " + cantidadString);

        String totalString = new DecimalFormat("0.00").format(vdeTImpC);
        tvTImporte.setText("Tot. a pagar: " + totalString);


    }
    public void SeleccPago(){
        double vdeTotArt = 0, vdeImporte = 0;

        for(opPedidoDet objDet: globales.opPedidoDetList){
            vdeTotArt = vdeTotArt + objDet.getDeCantidad();
            vdeImporte = vdeImporte + objDet.getDeImporte();
        }

        /*Crea Encabezado*/
        opPedido objNvoPed = new opPedido();
        objNvoPed.setiPedido(0);
        objNvoPed.setiUnidad(4);
        objNvoPed.setDtFecha(null);
        objNvoPed.setiEstadoPedido(1);
        objNvoPed.setiNegocios(1);
        objNvoPed.setDeTotalPiezas(vdeTotArt);
        objNvoPed.setDeImporte(vdeImporte);
        objNvoPed.setiCliente(globales.g_ctUsuario.getiPersona());
        objNvoPed.setcUsuCrea(globales.g_ctUsuario.getcUsuario());
        objNvoPed.setcUsuModifica(globales.g_ctUsuario.getcUsuario());
        globales.opPedidoList.add(objNvoPed);

        /*Crea enc x Proveedor*/
        opPedidoProveedor objNvoPProv = new opPedidoProveedor();
        objNvoPProv.setiPedido(0);
        objNvoPProv.setiPedidoProv(1);
        objNvoPProv.setiProveedor(25);
        objNvoPProv.setiDomicilio(1);
        objNvoPProv.setDeTotalPzas(vdeTotArt);
        objNvoPProv.setDeImporte(vdeImporte);
        objNvoPProv.setcUsuCrea(globales.g_ctUsuario.getcUsuario());
        objNvoPProv.setcUsuModifica(globales.g_ctUsuario.getcUsuario());
        globales.opPedidoProvList.add(objNvoPProv);


        /*Crea Domicilio Ped*/
        opPedidoDomicilio objNvoDom = new opPedidoDomicilio();
        objNvoDom.setiPedido(0);
        objNvoDom.setiDomicilio(globales.g_ctDomicilio.getiDomicilio());
        objNvoDom.setiCliente(globales.g_ctCliente.getiCliente());
        objNvoDom.setlHabitual(true);
        objNvoDom.setcUsuCrea(globales.g_ctUsuario.getcUsuario());
        objNvoDom.setcUsuModifica(globales.g_ctUsuario.getcUsuario());
        objNvoDom.setDtCreado(null);
        objNvoDom.setDtModificado(null);
        globales.opPedidDomList.add(objNvoDom);


        Intent pagos = new Intent(MiCarrito.this, AplicaPago.class);
        startActivity(pagos);

    }
    public void EliminaPda(){
        AlertDialog.Builder alert = new AlertDialog.Builder(MiCarrito.this);
        alert.setTitle("Eliminar Articulo ");
        alert.setMessage("¿Desea Borrar el Articulo?" + " " + globales.vg_iPartida + " " + globales.vg_cDescripcion);
        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // continue with delete
                for (Iterator<opPedidoDet> itr = globales.opPedidoDetList.iterator(); itr.hasNext(); ) {
                    opPedidoDet Obj = itr.next();

                    if (Obj.getiPartida().equals(globales.vg_iPartida)) {

                        itr.remove(); // right call
                    }
                }

                globales.opPedidoDetList.remove(globales.vg_iPartida);
                renglon = null;

                Log.e("mis articulos--> " , "antes de aceptar");

                for (opPedidoDet objnvos: globales.opPedidoDetList){
                    Log.e("mis articulos--> " , objnvos.getcDescripcion());
                }
                Log.e("MiCarrito--> " , "despues de for construyendo adapter");

                listafinal.clear();
               /* for(opPedidoDet objLista: globales.opPedidoDetList){
                    listafinal.add(objLista);
                }*/


                CarritoAdapter adapter = new CarritoAdapter(MiCarrito.this,null);
                adapter.setList((List<opPedidoDet>) listafinal);
                recycler.setAdapter(adapter);
                CalculaTotales();
            }
        });
        alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // close dialog
                /*reglon = null;
                lvLista.clearChoices();
                adapter.notifyDataSetChanged();
                lvLista.setAdapter(adapter);*/
                dialog.cancel();
            }
        });
        alert.show();
    }
}