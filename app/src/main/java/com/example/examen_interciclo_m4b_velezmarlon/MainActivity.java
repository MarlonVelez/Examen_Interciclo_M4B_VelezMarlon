package com.example.examen_interciclo_m4b_velezmarlon;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.examen_interciclo_m4b_velezmarlon.base_de_datos.SQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    private EditText txtRuc;
    private EditText txtNombreComercial;
    private EditText txtRepresentanteLegal;
    private EditText txtDireccion;
    private EditText txtTelefono;
    private EditText txtProducto;
    private EditText txtCredito;
    private Button btnGuardar;
    private Button btnLista;
    private Button btnEditar;
    private Button btnBorrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtRuc= findViewById(R.id.txtRuc);
        txtRepresentanteLegal= findViewById(R.id.txtRepresentanteLegal);
        txtNombreComercial= findViewById(R.id.txtNombreComercial);
        txtDireccion= findViewById(R.id.txtDireccion);
        txtTelefono= findViewById(R.id.txtTelefono);
        txtProducto= findViewById(R.id.txtProductos);
        txtCredito= findViewById(R.id.txtCredito);

        btnGuardar= findViewById(R.id.btnGuardarProveedor);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ruc= txtRuc.getText().toString();
                String representante= txtRepresentanteLegal.getText().toString();
                String nombre= txtNombreComercial.getText().toString();
                String direccion= txtDireccion.getText().toString();
                String telefono= txtTelefono.getText().toString();
                String producto= txtProducto.getText().toString();
                String credito= txtCredito.getText().toString();

                SQLiteOpenHelper base= new SQLiteOpenHelper(MainActivity.this);
                boolean bandera= base.agregarProveedor(ruc,nombre,representante,direccion,telefono,producto,credito);

                if (bandera==true){
                    Toast.makeText(MainActivity.this, "Proveedor agredado", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "Error al tratar de agregar", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnLista= findViewById(R.id.btnListarProveedores);
        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lista= new Intent(MainActivity.this, ListaProveedor.class);
                startActivity(lista);
            }
        });

        btnEditar= findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarProveedor();
            }
        });


        Intent i = getIntent();
        String ruc = i.getStringExtra("ruc");
        String nombre = i.getStringExtra("nombre");
        String representante = i.getStringExtra("representante");
        String direccion = i.getStringExtra("direccion");
        String telefono = i.getStringExtra("telefono");
        String producto = i.getStringExtra("producto");
        String credito = i.getStringExtra("credito");

        txtRuc.setText(ruc);
        txtNombreComercial.setText(nombre);
        txtRepresentanteLegal.setText(representante);
        txtDireccion.setText(direccion);
        txtTelefono.setText(telefono);
        txtProducto.setText(producto);
        txtCredito.setText(credito);
    }


    public void editarProveedor(){
        SQLiteOpenHelper base= new SQLiteOpenHelper(this);

        new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("¿Estas seguro de eliminar a esta proveedor?")
                .setIcon(R.drawable.icon_warning)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*public void editarProveedor(String ruc, String nombre, String representante, String direccion, String telefono, String prodcuto, String credito){*/
                        base.editarProveedor(
                                txtRuc.getText().toString().trim(),
                                txtNombreComercial.getText().toString().trim(),
                                txtRepresentanteLegal.getText().toString().trim(),
                                txtDireccion.getText().toString().trim(),
                                txtTelefono.getText().toString().trim(),
                                txtProducto.getText().toString().trim(),
                                txtCredito.getText().toString().trim());

                        txtRuc.setText("");
                        txtNombreComercial.setText("");
                        txtRepresentanteLegal.setText("");
                        txtDireccion.setText("");
                        txtTelefono.setText("");
                        txtProducto.setText("");
                        txtCredito.setText("");

                        Toast.makeText(MainActivity.this, "Los cambios se han guardado exitosamente", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Se cancelo la operacion", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

    }
}