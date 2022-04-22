package com.example.examen_interciclo_m4b_velezmarlon.base_de_datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.view.View;
import androidx.annotation.Nullable;
import com.example.examen_interciclo_m4b_velezmarlon.modelo.Proveedor;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper{

    private static String NOMBRE_BD= "base_exam_final";
    private static int VERSION_BD= 1;
    private static String TABLA_PROVEEDOR= "create table proveedor (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ruc text UNIQUE, nombreComercial text, " +
            "representanteLegal text, direccion text, telefono text, productos text, credito double)";

    public SQLiteOpenHelper(@Nullable Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);

    }

    @Override
    public void onCreate(SQLiteDatabase base) {
        base.execSQL(TABLA_PROVEEDOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(TABLA_PROVEEDOR);
    }

    /*public boolean agregarCliente(int id, String cedula, String nombre, String apellido, String telefono, String email){
        SQLiteDatabase bd= getWritableDatabase();
        if (bd!=null){
            try{
                bd.execSQL("INSERT INTO cliente VALUES('"+id+"','"+cedula+"','"+nombre+"','"+apellido+"','"+telefono+"','"+email+"')");
                bd.close();
                return true;
            }catch (SQLiteConstraintException e){
                return false;
            }

        }else{
            return false;
        }
    }*/

    public boolean agregarProveedor(String ruc, String nombreComercial, String representanteLegal, String direccion, String telefono, String productos, String credito){
        SQLiteDatabase bd= getWritableDatabase();
        if (bd!=null){
            try{
                bd.execSQL("INSERT INTO proveedor VALUES("+null+",'"+ruc+"','"+nombreComercial+"','"+representanteLegal+"','"+direccion+"','"+telefono+"','"+productos+"',"+credito+")");
                bd.close();
                return true;
            }catch (SQLiteConstraintException e){
                System.out.println("ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! "+e);
                return false;
            }catch (Exception e){
                System.out.println("ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! ERROR!!! "+e);
                return false;
            }

        }else{
            return false;
        }
    }

    public void editarProveedor(String ruc, String nombre, String representante, String direccion, String telefono, String prodcuto, String credito){
        SQLiteDatabase bd= getWritableDatabase();
        bd.execSQL("UPDATE proveedor " +
                "SET ruc='"+ruc+"', " +
                "nombreComercial='"+nombre+"', " +
                "representanteLegal='"+representante+"', " +
                "direccion='"+direccion+"', " +
                "telefono='"+telefono+"'," +
                "productos='"+prodcuto+"', " +
                "credito="+credito+" WHERE ruc='"+ruc+"'");
        bd.close();
    }
}
