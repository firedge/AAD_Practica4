package com.fdgproject.firedge.aad_practica4;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Firedge on 14/11/2014.
 */
public class Contrato {

    private Contrato (){
    }

    public static abstract class TablaInmueble implements BaseColumns {
        public static final String TABLA = "inmueble";
        public static final String LOCALIDAD = "localidad";
        public static final String DIRECCION = "direccion";
        public static final String TIPO = "tipo";
        public static final String PRECIO = "precio";
        public static final String SUBIDO = "subido";
        public static final String CONTENT_TYPE_INMUEBLES =
                "vnd.android.cursor.dir/vnd.firedge.inmuebles";
        public static final String CONTENT_TYPE_INMUEBLE =
                "vnd.android.cursor.dir/vnd.firedge.inmueble";
        public static final Uri CONTENT_URI =Uri.parse("content://" +
                Proveedor.AUTORIDAD + "/" + TABLA);
    }

}
