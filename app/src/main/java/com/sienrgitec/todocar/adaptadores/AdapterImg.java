package com.sienrgitec.todocar.adaptadores;

import android.app.Activity;
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
    private Context contextc;

    public AdapterImg(Context context, OnViewHolderClick<ctInformacionArt> listener) {
        super(context, listener);
    }

    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.galeria_arts, viewGroup, false);
        contextc = context;
        return view;
    }

    @Override
    protected void bindView(final ctInformacionArt item, RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {

            ImageView ibtnPago = (ImageView)viewHolder.getView(R.id.imgMosaic);



            new DownloadImageTask(ibtnPago).execute("http://192.168.1.13:80/" + item.getcRuta() + item.getcNombre());


            ibtnPago.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String vcRuta = "http://192.168.1.13:80/" + item.getcRuta() + item.getcNombre();
                    Update(vcRuta);
                }
            });


        }
    }


    public void Update(String vcImagen ){
        ImageView ImgView    = (ImageView) ((Activity)contextc).findViewById(R.id.imvArt);
        new DownloadImageTask(ImgView).execute(vcImagen);
    }
}
