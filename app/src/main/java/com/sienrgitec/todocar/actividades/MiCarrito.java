package com.sienrgitec.todocar.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

    public static ArrayList<opPedidoDet> listafinal       = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_carriro);

        recycler      = (RecyclerView) findViewById(R.id.listCarrito);
        recycler.setLayoutManager(new LinearLayoutManager(MiCarrito.this,LinearLayoutManager.VERTICAL,false));

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