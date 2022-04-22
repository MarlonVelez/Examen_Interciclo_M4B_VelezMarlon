package com.example.examen_interciclo_m4b_velezmarlon;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.examen_interciclo_m4b_velezmarlon.adapter.AdapterProveedor;
import com.example.examen_interciclo_m4b_velezmarlon.base_de_datos.SQLiteOpenHelper;
import com.example.examen_interciclo_m4b_velezmarlon.modelo.Proveedor;

import java.util.ArrayList;

public class ListaProveedor extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterProveedor adapterProveedor;
    private ArrayList<Proveedor> miProveedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_proveedor);

        recyclerView= findViewById(R.id.listaProveedores);
        
        miProveedor=listarProveedores(null, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapterProveedor = new AdapterProveedor(this,miProveedor);
        recyclerView.setAdapter(adapterProveedor);

    }

    public ArrayList<Proveedor> listarProveedores(String ruc, Context context) {
        ArrayList<Proveedor> Arrayproveedor= new ArrayList<>();
        SQLiteOpenHelper base = new SQLiteOpenHelper(context);
        SQLiteDatabase open = base.getReadableDatabase();
        Cursor fila = null;
        if (ruc == null) {
            fila = open.rawQuery("select * from proveedor", null);
        } else {
            fila = open.rawQuery("select * from proveedor where ruc=" + ruc, null);
        }

        if (fila.moveToFirst()) {

            do {
                Proveedor proveedor = new Proveedor();
                proveedor.setRUC(fila.getString(1));
                proveedor.setNombreComercial(fila.getString(2));
                proveedor.setRepresentanteLegal(fila.getString(3));
                proveedor.setDireccion(fila.getString(4));
                proveedor.setTelefono(fila.getString(5));
                proveedor.setProductos(fila.getString(6));
                proveedor.setCredito(fila.getDouble(7));
                Arrayproveedor.add(proveedor);

                for (int i = 0; i < Arrayproveedor.size(); i++) {
                    Toast.makeText(context, "NOMBRE= "+Arrayproveedor.get(i).getRepresentanteLegal(), Toast.LENGTH_SHORT).show();
                }

                base.close();
                open.close();
                return Arrayproveedor;

            } while (fila.moveToNext());
            
        } else {
            base.close();
            open.close();
            return Arrayproveedor;
        }
    }

}