package com.example.examen_interciclo_m4b_velezmarlon.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.examen_interciclo_m4b_velezmarlon.MainActivity;
import com.example.examen_interciclo_m4b_velezmarlon.R;
import com.example.examen_interciclo_m4b_velezmarlon.modelo.Proveedor;

import java.util.ArrayList;

public class AdapterProveedor extends RecyclerView.Adapter<AdapterProveedor.ProveedorViewHolder>{

    private Context context;
    private ArrayList<Proveedor> miProveedor= new ArrayList<>();

    public AdapterProveedor(Context context, ArrayList<Proveedor> miProveedor) {
        this.context = context;
        this.miProveedor = miProveedor;
    }

    @NonNull
    @Override
    public ProveedorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_proveedor, parent, false);
        return new ProveedorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProveedorViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.labelRuc.setText(miProveedor.get(position).getRUC());
        holder.labelNombreComercio.setText(miProveedor.get(position).getNombreComercial());
        holder.labelRepresentanteLegal.setText(miProveedor.get(position).getRepresentanteLegal());
        holder.labelDireccion.setText(miProveedor.get(position).getDireccion());
        holder.labelTelefono.setText(miProveedor.get(position).getTelefono());
        holder.labelProductos.setText(miProveedor.get(position).getProductos());
        holder.labelCredito.setText(String.valueOf(miProveedor.get(position).getCredito()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, MainActivity.class);
                i.putExtra("ruc",miProveedor.get(position).getRUC());
                i.putExtra("nombre", miProveedor.get(position).getNombreComercial());
                i.putExtra("representante", miProveedor.get(position).getRepresentanteLegal());
                i.putExtra("direccion", miProveedor.get(position).getDireccion());
                i.putExtra("telefono", miProveedor.get(position).getTelefono());
                i.putExtra("producto", String.valueOf(miProveedor.get(position).getProductos()));
                i.putExtra("credito", String.valueOf(miProveedor.get(position).getCredito()));
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return miProveedor.size();
    }

    public  static class ProveedorViewHolder extends RecyclerView.ViewHolder{

        ImageView categoryImage;
        TextView labelRuc;
        TextView labelNombreComercio;
        TextView labelRepresentanteLegal;
        TextView labelDireccion;
        TextView labelTelefono;
        TextView labelProductos;
        TextView labelCredito;

        public ProveedorViewHolder(@NonNull View itemView) {
            super(itemView);
            labelRuc= itemView.findViewById(R.id.labelRuc);
            labelNombreComercio= itemView.findViewById(R.id.labelNombreComercio);
            labelRepresentanteLegal= itemView.findViewById(R.id.labelRepresentanteLegal);
            labelDireccion= itemView.findViewById(R.id.labelDireccion);
            labelTelefono= itemView.findViewById(R.id.labelTelefono);
            labelProductos= itemView.findViewById(R.id.labelProducto);
            labelCredito= itemView.findViewById(R.id.labelCredito);
        }
    }
}
