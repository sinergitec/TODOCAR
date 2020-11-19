package com.sienrgitec.todocar.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sienrgitec.todocar.R;
import com.sienrgitec.todocar.actividades.MainActivity;
import com.sienrgitec.todocar.configuracion.Globales;
import com.sienrgitec.todocar.modelos.ctFormasPago;

import java.io.Serializable;

public class AdapterFPago extends RVAdapter<ctFormasPago> {
    public Globales globales;

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
    protected void bindView(final ctFormasPago item, RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {

            ImageView ibtnPago = (ImageView)viewHolder.getView(R.id.ibtnFPago);
            TextView descripcion = (TextView)viewHolder.getView(R.id.tvFPago);




            descripcion.setText(item.getcFormaPago());


            if(item.getCvePago().equals( "PP") ){
               new DownloadImageTask(ibtnPago).execute("https://cdn.pixabay.com/photo/2018/05/08/21/29/paypal-3384015_960_720.png");
            }
            if(item.getCvePago().equals("CP") ){
                new DownloadImageTask(ibtnPago).execute("https://www.mastercard.es/es-es/consumidores/escoge-tu-tarjeta/tarjetas-prepago/_jcr_content/contentpar/herolight/image.adaptive.479.high.jpg/1483985272758.jpg");
            }
            if(item.getCvePago().equals("TC") ){
                new DownloadImageTask(ibtnPago).execute("https://e00-expansion.uecdn.es/assets/multimedia/imagenes/2017/02/22/14877703903696.jpg");
            }

            ibtnPago.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast = Toast.makeText(getContext(), "Seleccionaste la Forma de Pago: " + item.getcFormaPago(), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    globales.g_ctFormasPago = item;
                }
            });

            descripcion.setText(item.getcFormaPago());


        }
    }
}
