package com.fdgproject.firedge.aad_practica4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Firedge on 28/01/2015.
 */
public class GestorInmueble {

    private Context context;

    public GestorInmueble(Context c) {
        this.context = c;
    }

    public Cursor query(){
        Uri uri = Contrato.TablaInmueble.CONTENT_URI;
        String[] proyeccion = null;
        String condicion = null;
        String[] parametros = null;
        String orden = null;
        Cursor c = context.getContentResolver().query(uri,proyeccion,condicion,parametros,orden);
        return c;
    }

    public Cursor querySincronizar(){
        Uri uri = Contrato.TablaInmueble.CONTENT_URI;
        String[] proyeccion = null;
        String condicion = Contrato.TablaInmueble.SUBIDO + " = ?";
        String[] parametros = new String[]{"0"};
        String orden = null;
        Cursor c = context.getContentResolver().query(uri,proyeccion,condicion,parametros,orden);
        return c;
    }

    public Inmueble getRow(Cursor c) {
        Inmueble objeto= new Inmueble();
        objeto.setId(c.getInt(0));
        objeto.setLocalidad(c.getString(1));
        objeto.setDireccion(c.getString(2));
        objeto.setTipo(c.getString(3));
        objeto.setPrecio(c.getDouble(4));
        objeto.setSubido(c.getInt(5));
        return objeto;
    }

    public Inmueble getRow(long id){
        List<Inmueble> ls = new ArrayList<Inmueble>();
        Uri uri = Contrato.TablaInmueble.CONTENT_URI;
        String[] proyeccion = null;
        String condicion = Contrato.TablaInmueble._ID + " = ?";
        String[] parametros = new String[]{id + ""};
        String orden = null;
        Cursor cursor = context.getContentResolver().query(uri,proyeccion,condicion,parametros,orden);
        if(cursor != null) {
            cursor.moveToFirst();
            Inmueble objeto;
            while (!cursor.isAfterLast()) {
                objeto = getRow(cursor);
                ls.add(objeto);
                cursor.moveToNext();
            }
            cursor.close();
        }
        if(!ls.isEmpty())
            return ls.get(0);
        return null;
    }


    public void insert(Inmueble i){
        Uri uri = Contrato.TablaInmueble.CONTENT_URI;
        ContentValues values = new ContentValues();
        values.put(Contrato.TablaInmueble.TIPO, i.getTipo());
        values.put(Contrato.TablaInmueble.LOCALIDAD, i.getLocalidad());
        values.put(Contrato.TablaInmueble.DIRECCION, i.getDireccion());
        values.put(Contrato.TablaInmueble.PRECIO, i.getPrecio());
        Uri u = context.getContentResolver().insert(uri, values);
    }

    public void update(Inmueble i){
        Uri uri = Contrato.TablaInmueble.CONTENT_URI;
        ContentValues values = new ContentValues();
        values.put(Contrato.TablaInmueble.TIPO, i.getTipo());
        values.put(Contrato.TablaInmueble.LOCALIDAD, i.getLocalidad());
        values.put(Contrato.TablaInmueble.DIRECCION, i.getDireccion());
        values.put(Contrato.TablaInmueble.PRECIO, i.getPrecio());
        values.put(Contrato.TablaInmueble.SUBIDO, i.getSubido());
        String condicion = Contrato.TablaInmueble._ID+" = ?";
        String[] argumentos = new String[]{i.getId()+""};
        int index = context.getContentResolver().update(uri, values,condicion, argumentos);
    }

    public void delete(Inmueble i){
        Uri uri = Contrato.TablaInmueble.CONTENT_URI;
        String where = Contrato.TablaInmueble._ID+" = ?";
        String[] args = new String[]{i.getId()+""};
        int index = context.getContentResolver().delete(uri, where, args);
    }
}
