package com.fdgproject.firedge.aad_practica4;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Firedge on 21/11/2014.
 */
public class Adaptador extends CursorAdapter{

    public Adaptador(Context co, Cursor cu) {
        super(co, cu, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater i = LayoutInflater.from(viewGroup.getContext());
        View v = i.inflate(R.layout.list_element, viewGroup, false);
        return v;
    }

    public static class ViewHolder{
        public TextView tv_localidad, tv_direccion, tv_precio;
        public ImageView iv_tipo;
        public LinearLayout ly;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        GestorInmueble gi = new GestorInmueble(context);
        Inmueble i = gi.getRow(cursor);
        ViewHolder vh = null;
        if(view != null) {
            vh =new ViewHolder();
            vh.tv_localidad = (TextView)view.findViewById(R.id.tv_localidad);
            vh.tv_direccion = (TextView)view.findViewById(R.id.tv_direccion);
            vh.tv_precio = (TextView)view.findViewById(R.id.tv_precio);
            vh.iv_tipo = (ImageView)view.findViewById(R.id.iv_tipo);
            vh.ly = (LinearLayout)view.findViewById(R.id.ly_bg);
            view.setTag(vh);
        } else {
            vh = (ViewHolder)view.getTag();
        }
        String [] tipos = view.getResources().getStringArray(R.array.tipos);
        vh.tv_localidad.setText(i.getLocalidad());
        vh.tv_direccion.setText(i.getDireccion());
        vh.tv_precio.setText(Double.toString(i.getPrecio()));
        if(i.getTipo().equals(tipos[0]))
            vh.iv_tipo.setImageResource(R.drawable.casa);
        else if(i.getTipo().equals(tipos[1]))
            vh.iv_tipo.setImageResource(R.drawable.piso);
        else if(i.getTipo().equals(tipos[2]))
            vh.iv_tipo.setImageResource(R.drawable.cochera);
        else if(i.getTipo().equals(tipos[3]))
            vh.iv_tipo.setImageResource(R.drawable.trastero);
        if(i.getSubido()!=0){
            vh.ly.setBackgroundColor(Color.parseColor("#635891b0"));
        } else {
            vh.ly.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
