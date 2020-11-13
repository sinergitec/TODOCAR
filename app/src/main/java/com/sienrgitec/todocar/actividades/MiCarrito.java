package com.sienrgitec.todocar.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sienrgitec.todocar.R;
import com.sienrgitec.todocar.adaptadores.AdapterArt;
import com.sienrgitec.todocar.adaptadores.CarritoAdapter;
import com.sienrgitec.todocar.configuracion.Globales;
import com.sienrgitec.todocar.modelos.ctArtProveedor;
import com.sienrgitec.todocar.modelos.opPedidoDet;

import java.util.ArrayList;
import java.util.List;

public class MiCarrito extends AppCompatActivity {

    public RecyclerView recycler;
    private Globales globales;
    private Button btnPagar;

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
                Intent pagos = new Intent(MiCarrito.this, AplicaPago.class);
                startActivity(pagos);
            }
        });

        CreaDetalle();
    }

    public void CreaDetalle(){
        listafinal.clear();
        for(opPedidoDet objLista: globales.opPedidoDetList){
            listafinal.add(objLista);
        }

        CarritoAdapter adapter = new CarritoAdapter(MiCarrito.this,null);
        adapter.setList((List<opPedidoDet>) listafinal);
        recycler.setAdapter(adapter);

    }
}