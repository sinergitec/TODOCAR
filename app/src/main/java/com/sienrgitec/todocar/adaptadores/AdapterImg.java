package com.sienrgitec.todocar.adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sienrgitec.todocar.R;
import com.sienrgitec.todocar.configuracion.Globales;
import com.sienrgitec.todocar.modelos.ctInformacionArt;

public class AdapterImg extends RVAdapter<ctInformacionArt> {
    public Globales globales;

    public AdapterImg(Context context, OnViewHolderClick<ctInformacionArt> listener) {
        super(context, listener);
    }

    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.img_arts, viewGroup, false);
        return view;
    }

    @Override
    protected void bindView(final ctInformacionArt item, RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {

            ImageView ibtnPago = (ImageView)viewHolder.getView(R.id.ibtnArt);

            Log.e("Mis imagenes---> ",  "valores--> " + item.getcRuta() + item.getcNombre() );

            new DownloadImageTask(ibtnPago).execute("http://192.168.1.13:80/" + item.getcRuta() + item.getcNombre());


        }
    }
}
