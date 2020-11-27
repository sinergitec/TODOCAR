package com.sienrgitec.todocar.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sienrgitec.todocar.R;
import com.sienrgitec.todocar.actividades.MainActivity;
import com.sienrgitec.todocar.configuracion.Globales;
import com.sienrgitec.todocar.modelos.ctArtProveedor;

public class AdapterArt extends RVAdapter<ctArtProveedor> implements View.OnClickListener {

    private Context contextc;
    public Globales globales;

    private View.OnClickListener listener;

    public AdapterArt(Context context, RVAdapter.OnViewHolderClick<ctArtProveedor> listener) {
        super(context, listener);
    }


    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {

        contextc = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view    = inflater.inflate(R.layout.ctartprov_list, viewGroup, false);

        view.setOnClickListener(this);
        return view;

    }


    @Override
    protected void bindView(final ctArtProveedor item, final RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {
            final TextView tvDescs = (TextView) viewHolder.getView(R.id.tvDescr);
            TextView tvMarca  = (TextView) viewHolder.getView(R.id.tvcMarca);
            TextView tvnParte = (TextView) viewHolder.getView(R.id.tvNParte);

            tvMarca.setText(item.getcMarca());
            tvnParte.setText(item.getcArticulo());
            tvDescs.setText(item.getcDescripcion());




        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener= listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }
}
