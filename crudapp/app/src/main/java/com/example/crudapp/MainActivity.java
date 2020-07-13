package com.example.crudapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.crudapp.modelo.Producto;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private List<Producto> listProduct = new ArrayList<Producto>();
    ArrayAdapter<Producto> arrayAdapterProducto;

    EditText tproducto, tcategoria, tdetalle;
    ListView listViewProductos;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Producto productoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tproducto = findViewById(R.id.txtproducto);
        tcategoria = findViewById(R.id.txtcategoria);
        tdetalle = findViewById(R.id.txtdetalle);

        listViewProductos = findViewById(R.id.listaProductos);
        inicializarFirebase();
        listarDatos();

        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                productoSeleccionado = (Producto) parent.getItemAtPosition(position);

                tproducto.setText(productoSeleccionado.getNombre());
                tcategoria.setText(productoSeleccionado.getCategoria());
                tdetalle.setText(productoSeleccionado.getDetalle());
            }
        });

    }

    private void listarDatos() {
        databaseReference.child("Producto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listProduct.clear();
                for(DataSnapshot objSnapshot : snapshot.getChildren()){
                    Producto p = objSnapshot.getValue(Producto.class);
                    listProduct.add(p);

                    arrayAdapterProducto = new ArrayAdapter<Producto>(MainActivity.this, android.R.layout.simple_list_item_1, listProduct);
                    listViewProductos.setAdapter(arrayAdapterProducto);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String nproducto = tproducto.getText().toString();
        String pcategoria = tcategoria.getText().toString();
        String pdetalle = tdetalle.getText().toString();

        switch (item.getItemId()){
            case R.id.agregar:{
                if(nproducto.equals("")|| pcategoria.equals("")|| pdetalle.equals("")){
                    validacion();
                }else{
                    Producto p = new Producto();
                    p.setId(UUID.randomUUID().toString());
                    p.setNombre(nproducto);
                    p.setCategoria(pcategoria);
                    p.setDetalle(pdetalle);
                    databaseReference.child("Producto").child(p.getId()).setValue(p);
                    Toast.makeText(this, "Agregar",Toast.LENGTH_LONG).show();
                    limpiar();
                    break;
                }
            }
            case R.id.guardar:{
                Producto p = new Producto();
                p.setId(productoSeleccionado.getId());
                p.setNombre(tproducto.getText().toString().trim());
                p.setCategoria(tcategoria.getText().toString().trim());
                p.setDetalle(tdetalle.getText().toString().trim());
                databaseReference.child("Producto").child(p.getId()).setValue(p);
                Toast.makeText(this, "Guardar",Toast.LENGTH_LONG).show();
                limpiar();
                break;
            }
            case R.id.eliminar:{
                Producto p = new Producto();
                p.setId(productoSeleccionado.getId());
                databaseReference.child("Producto").child(p.getId()).removeValue();
                Toast.makeText(this, "Eliminar", Toast.LENGTH_LONG).show();
                limpiar();
                break;
            }
            default: break;
        }
        return true;
    }

    private void limpiar() {
        tproducto.setText("");
        tcategoria.setText("");
        tdetalle.setText("");
    }

    private void validacion() {
        String pnombre = tproducto.getText().toString();
        String pcategoria = tcategoria.getText().toString();
        String pdetalle = tdetalle.getText().toString();

        if(pnombre.equals("")){
            tproducto.setError("Campo requerido");
        }else if(pcategoria.equals("")){
            tcategoria.setError("Campo Requerido");
        }else if(pdetalle.equals("")){
            tdetalle.setError("Campo Requerido");
        }
    }
}