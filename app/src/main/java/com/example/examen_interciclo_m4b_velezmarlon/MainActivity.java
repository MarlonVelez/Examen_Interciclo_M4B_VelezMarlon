package com.example.examen_interciclo_m4b_velezmarlon;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.FileProvider;
import com.example.examen_interciclo_m4b_velezmarlon.base_de_datos.SQLiteOpenHelper;

import java.io.File;
import java.io.IOException;

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
    private Button btnImagen;
    private ImageView imageView;
    private static final int COD_SELECCIONA = 10;
    private final int MIS_PERMISOS = 100;
    private static final int COD_FOTO = 20;
    private static final String CARPETA_PRINCIPAL = "misImagenesApp/";//directorio principal
    private static final String CARPETA_IMAGEN = "imagenes";//carpeta donde se guardan las fotos
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;//ruta carpeta de directorios
    private String path;
    File fileImagen;
    Bitmap bitmap;


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
        imageView= findViewById(R.id.imageView);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarProveedor();
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

        btnBorrar= findViewById(R.id.btnBorrar);
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarCliente();
            }
        });

        btnImagen= findViewById(R.id.btnImagen);
        btnImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogOpciones();
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

    public void agregarProveedor(){
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
            limpiarCampos();
        }else {
            Toast.makeText(MainActivity.this, "Error al tratar de agregar", Toast.LENGTH_LONG).show();
        }
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

                        limpiarCampos();

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

    public void eliminarCliente(){
        SQLiteOpenHelper base= new SQLiteOpenHelper(this);
        new AlertDialog.Builder(this)
                .setTitle("Eliminar")
                .setMessage("¿Esta seguro de eliminar a este proveedor?")
                .setIcon(R.drawable.icon_warning)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        base.eliminarProveedor(txtRuc.getText().toString().trim());
                        limpiarCampos();
                        Toast.makeText(MainActivity.this, "Cliente eliminado", Toast.LENGTH_SHORT).show();
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

    private void limpiarCampos(){
        txtRuc.setText("");
        txtNombreComercial.setText("");
        txtRepresentanteLegal.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtProducto.setText("");
        txtCredito.setText("");
    }

    private void mostrarDialogOpciones() {
        final CharSequence[] opciones={"Camara","Galeria","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Elige una Opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Camara")){
                    abrirCamara();
                }else{
                    if (opciones[i].equals("Galeria")){
                        Intent intent=new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"Seleccione"),COD_SELECCIONA);



                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case COD_SELECCIONA:
                Uri miPath=data.getData();
                imageView.setImageURI(miPath);

                try {
                    bitmap=MediaStore.Images.Media.getBitmap(MainActivity.this.getContentResolver(),miPath);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case COD_FOTO:
                if(requestCode==1 && resultCode==RESULT_OK){
                    Bundle bundle =data.getExtras();
                    Bitmap imagen= (Bitmap) bundle.get("data");
                    imageView.setImageBitmap(imagen);
                }
        }
        bitmap=redimensionarImagen(bitmap,600,800);
    }

    private Bitmap redimensionarImagen(Bitmap bitmap, float anchoNuevo, float altoNuevo) {

        int ancho=bitmap.getWidth();
        int alto=bitmap.getHeight();

        if(ancho>anchoNuevo || alto>altoNuevo){
            float escalaAncho=anchoNuevo/ancho;
            float escalaAlto= altoNuevo/alto;

            Matrix matrix=new Matrix();
            matrix.postScale(escalaAncho,escalaAlto);

            return Bitmap.createBitmap(bitmap,0,0,ancho,alto,matrix,false);

        }else{
            return bitmap;
        }


    }

    private void abrirCamara(){
        /*Intent intentCamara= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intentCamara.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intentCamara,COD_FOTO);
        }*/

    }
}