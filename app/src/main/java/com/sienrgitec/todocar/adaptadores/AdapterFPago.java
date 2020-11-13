package com.sienrgitec.todocar.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sienrgitec.todocar.R;
import com.sienrgitec.todocar.modelos.ctFormasPago;

import java.io.Serializable;

public class AdapterFPago extends RVAdapter<ctFormasPago> {

    public AdapterFPago(Context context, OnViewHolderClick<ctFormasPago> listener) {
        super(context, listener);
    }

    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.ctformap_list, viewGroup, false);
        return view;
    }

    @Override
    protected void bindView(ctFormasPago item, RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {

            ImageView ibtnPago = (ImageView)viewHolder.getView(R.id.ibtnFPago);
            TextView descripcion = (TextView)viewHolder.getView(R.id.tvFPago);

//https://www.bbva.com/wp-content/uploads/2019/11/dineromexicano-1024x678.jpg


            descripcion.setText(item.getcFormaPago());

            new DownloadImageTask(ibtnPago).execute("https://www.bbva.com/wp-content/uploads/2019/11/dineromexicano-1024x678.jpg");
            ibtnPago.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        }
    }
}
