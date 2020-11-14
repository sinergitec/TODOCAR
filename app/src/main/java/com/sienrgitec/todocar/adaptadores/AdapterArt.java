package com.sienrgitec.todocar.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sienrgitec.todocar.R;
import com.sienrgitec.todocar.actividades.MainActivity;
import com.sienrgitec.todocar.configuracion.Globales;
import com.sienrgitec.todocar.modelos.ctArtProveedor;

public class AdapterArt extends RVAdapter<ctArtProveedor> {

    private Context contextc;
    public Globales globales;


    public AdapterArt(Context context, RVAdapter.OnViewHolderClick<ctArtProveedor> listener) {
        super(context, listener);
    }


    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {

        contextc = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view    = inflater.inflate(R.layout.ctartprov_list, viewGroup, false);

        return view;

    }


    @Override
    protected void bindView(final ctArtProveedor item, final RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {
            final TextView tvDesc = (TextView) viewHolder.getView(R.id.tvDesc);
            TextView tvMarca  = (TextView) viewHolder.getView(R.id.tvcMarca);
            TextView tvnParte = (TextView) viewHolder.getView(R.id.tvNParte);



            tvDesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*MainActivity  detalle = new MainActivity();
                    detalle.MuestraDetalle(item.getcAplicaciones(), contextc);*/
                    globales.g_ctArtProveedor = item;
                    Update(item.getcAplicaciones(), item.getDePrecio(), item.getDePeso());
                }
            });

            tvMarca.setText(item.getcMarca());
            tvnParte.setText(item.getcArticulo());
            tvDesc.setText(item.getcAplicaciones());

        }
    }

    public void Update(String vcAplicaiones ,Double vdePrecio, Double vdeRating){
        TextView txtView    = (TextView) ((Activity)contextc).findViewById(R.id.etAplicaciones);
        TextView tvDePrecio = (TextView) ((Activity)contextc).findViewById(R.id.editTextNumberDecimal);
        RatingBar ratingBar = (RatingBar) ((Activity)contextc).findViewById(R.id.ratingBar);;

        txtView.setText(vcAplicaiones);

        if(vdePrecio != 0){
            tvDePrecio.setText(vdePrecio + "0");
        }else{
            tvDePrecio.setText( "0.00");
        }
        ratingBar.setRating(Float.parseFloat(String.valueOf(vdeRating)));

    }
}
