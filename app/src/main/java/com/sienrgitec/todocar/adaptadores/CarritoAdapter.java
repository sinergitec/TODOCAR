package com.sienrgitec.todocar.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sienrgitec.todocar.R;
import com.sienrgitec.todocar.actividades.MainActivity;
import com.sienrgitec.todocar.actividades.MiCarrito;
import com.sienrgitec.todocar.configuracion.Globales;
import com.sienrgitec.todocar.modelos.opPedidoDet;

import java.text.DecimalFormat;

public class CarritoAdapter extends RVAdapter<opPedidoDet> implements View.OnClickListener {

    private Context contextc;
    public Globales globales;

    private View.OnClickListener listener;


    public CarritoAdapter(Context context, RVAdapter.OnViewHolderClick<opPedidoDet> listener) {
        super(context, listener);
    }


    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {

        contextc = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view    = inflater.inflate(R.layout.micarritolist, viewGroup, false);
        view.setOnClickListener(this);
        return view;

    }


    @Override
    protected void bindView(final opPedidoDet item, final RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {


            TextView tvDescPda = (TextView) viewHolder.getView(R.id.tvDescPda);
            TextView tvCanPda  = (TextView) viewHolder.getView(R.id.tvCanPda);
            TextView tvImporte = (TextView) viewHolder.getView(R.id.tvImporte);
            ImageView ibtnEditar = (ImageView) viewHolder.getView(R.id.imageView8);

            tvDescPda.setText("Articulo: " + item.getcDescripcion());

            String cantString = new DecimalFormat("0").format(item.getDeCantidad());
            tvCanPda.setText("Cant.: " + cantString);

            String importeString = new DecimalFormat("0.00").format(item.getDeImporte());
            tvImporte.setText("Importe: " + importeString);



            ibtnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*MainActivity  detalle = new MainActivity();
                    detalle.MuestraDetalle(item.getcAplicaciones(), contextc);*/

                    Update(item.getcDescripcion(), item.getiPartida());
                }
            });

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

    public void Update(String vcAplicaiones, int viPartida){
        TextView txtView    = (TextView) ((Activity)contextc).findViewById(R.id.tvArtSelecc);
        txtView.setText(vcAplicaiones);

        globales.vg_iPartida = viPartida;


    }
}
