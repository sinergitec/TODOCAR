package com.sienrgitec.todocar.adaptadores;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    protected void bindView(final ctArtProveedor item, RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {
            final TextView tvDesc = (TextView) viewHolder.getView(R.id.tvDesc);


            tvDesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity  detalle = new MainActivity();
                    detalle.MuestraDetalle(item.getcAplicaciones(), contextc);
                    globales.vg_iArticulo = item.getiArticulo();

                }
            });


            tvDesc.setText(item.getcAplicaciones());


        }
    }
}
