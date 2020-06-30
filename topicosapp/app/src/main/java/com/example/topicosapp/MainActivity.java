package com.example.topicosapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText tproducto, tcategoria, tdetalle, tprecio;
    ListView listViewProductos;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tproducto = findViewById(R.id.txtproducto);
        tcategoria = findViewById(R.id.txtcategoria);
        tdetalle = findViewById(R.id.txtdetalle);
        tprecio = findViewById(R.id.txtprecio);

        listViewProductos = findViewById(R.id.listaProductos);
        inicializarFirebase();
        
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = 
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
        Double pprecio = Double.valueOf(tprecio.getText().toString());

        switch (item.getItemId()){
            case R.id.agregar:{
                if(nproducto.equals("")|| pcategoria.equals("")|| pdetalle.equals("")||pprecio.equals("")){
                    validacion();
                }else
                Toast.makeText(this, "Agregar",Toast.LENGTH_LONG).show();
                limpiar();
                break;
            }
            case R.id.guardar:{
                Toast.makeText(this, "Guardar",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.eliminar:{
                    Toast.makeText(this, "Eliminar",Toast.LENGTH_LONG).show();
                break;
            }
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void limpiar() {
        tproducto.setText("");
        tcategoria.setText("");
        tdetalle.setText("");
        tprecio.setText("");
    }

    private void validacion() {
        String pnombre = tproducto.getText().toString();
        String pcategoria = tcategoria.getText().toString();
        String pdetalle = tdetalle.getText().toString();
        Double pprecio = Double.valueOf(tprecio.getText().toString());

        if(pnombre.equals("")){
            tproducto.setError("Campo requerido");
        }else if(pcategoria.equals("")){
            tcategoria.setError("Campo Requerido");
        }else if(pdetalle.equals("")){
            tdetalle.setError("Campo Requerido");
        }else if(pprecio.equals("")){
            tprecio.setError("Campo Requerido");
        }
    }
}