package com.sienrgitec.todocar.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sienrgitec.todocar.R;
import com.sienrgitec.todocar.configuracion.Globales;
import com.sienrgitec.todocar.modelos.opPedidoDet;

public class CarritoAdapter extends RVAdapter<opPedidoDet> {

    private Context contextc;
    public Globales globales;


    public CarritoAdapter(Context context, RVAdapter.OnViewHolderClick<opPedidoDet> listener) {
        super(context, listener);
    }


    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {

        contextc = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view    = inflater.inflate(R.layout.micarritolist, viewGroup, false);

        return view;

    }


    @Override
    protected void bindView(final opPedidoDet item, final RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {
            TextView tvDescPda = (TextView) viewHolder.getView(R.id.tvDescPda);
            TextView tvCanPda  = (TextView) viewHolder.getView(R.id.tvCanPda);
            TextView tvImporte = (TextView) viewHolder.getView(R.id.tvImporte);

            tvDescPda.setText("Articulo: " + item.getcDescripcion());
            tvCanPda.setText("Cantidad:" + item.getDeCantidad().toString());
            tvImporte.setText("Total:" + item.getDeImporte().toString());
        }
    }


}
