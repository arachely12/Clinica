package com.example.araceli.clinica;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    EditText clave, nombre, direccion, telefono, fecha, temperatura, peso, motivo, observaciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clave = (EditText) findViewById(R.id.clave);
        nombre = (EditText) findViewById(R.id.nombre);
        direccion = (EditText) findViewById(R.id.direccion);
        telefono = (EditText) findViewById(R.id.telefono);
        fecha = (EditText) findViewById(R.id.fecha_con);
        temperatura = (EditText)findViewById(R.id.temperatura);
        peso = (EditText) findViewById(R.id.peso);
        motivo =(EditText) findViewById(R.id.motivo);
        observaciones = (EditText) findViewById(R.id.observaciones);
    }

    public void alta (View v) {
        Datos admin = new Datos(this, "clinica", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Clave =  clave.getText().toString();
        String Nombre = nombre.getText().toString();
        String Direccion = direccion.getText().toString();
        String Telefono = telefono.getText().toString();
        String Fecha = fecha.getText().toString();
        String Temperatura = temperatura.getText().toString();
        String Peso = peso.getText().toString();
        String Motivo = motivo.getText().toString();
        String Observaciones = observaciones.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("clave", Clave);
        registro.put("nombre", Nombre);
        registro.put("direccion", Direccion);
        registro.put("telefono", Telefono);
        registro.put("fecha", Fecha);
        registro.put("temperatura", Temperatura);
        registro.put("peso", Peso);
        registro.put("motivo", Motivo);
        registro.put("observaciones", Observaciones);

        bd.insert("clinica", null, registro);
        bd.close();

        clave.setText("");
        nombre.setText("");
        direccion.setText("");
        telefono.setText("");
        fecha.setText("");
        temperatura.setText("");
        peso.setText("");
        motivo.setText("");
        observaciones.setText("");

        Toast.makeText(this, "Se agrego un nuevo paciente al registro", Toast.LENGTH_SHORT).show();
    }

    public void consulta (View v) {

        Datos admin = new Datos(this, "clinica", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Clave = clave.getText().toString();

        Cursor fila = bd.rawQuery("select clave, nombre, direccion, telefono, fecha, temperatura, peso, motivo, observaciones from clinica where clave=" + Clave, null);
        if (fila.moveToFirst()){
            clave.setText(fila.getString(0));
            nombre.setText(fila.getString(1));
            direccion.setText(fila.getString(2));
            telefono.setText(fila.getString(3));
            fecha.setText(fila.getString(4));
            temperatura.setText(fila.getString(5));
            peso.setText(fila.getString(6));
            motivo.setText(fila.getString(7));
            observaciones.setText(fila.getString(8));
        }else {
            Toast.makeText(this, "No existe el registro", Toast.LENGTH_SHORT).show();
        }bd.close();
    }



    public void baja (View v) {

        Datos admin = new Datos(this, "clinica", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Clave = clave.getText().toString();

        int cant = bd.delete("clinica", "clave=" + Clave, null);
        bd.close();

        clave.setText("");
        nombre.setText("");
        direccion.setText("");
        telefono.setText("");
        fecha.setText("");
        temperatura.setText("");
        peso.setText("");
        motivo.setText("");
        observaciones.setText("");

        if (cant==1) {
            Toast.makeText(this, "Se elimino el registro", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No existe el registro", Toast.LENGTH_SHORT).show();
        }
    }


    public void modificacion (View v) {
        Datos admin = new Datos(this, "clinica", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Clave =  clave.getText().toString();
        String Nombre = nombre.getText().toString();
        String Direccion = direccion.getText().toString();
        String Telefono = telefono.getText().toString();
        String Fecha = fecha.getText().toString();
        String Temperatura = temperatura.getText().toString();
        String Peso = peso.getText().toString();
        String Motivo = motivo.getText().toString();
        String Observaciones = observaciones.getText().toString();

        ContentValues registro= new ContentValues();

        registro.put("clave", Clave);
        registro.put("nombre", Nombre);
        registro.put("direccion", Direccion);
        registro.put("telefono", Telefono);
        registro.put("fecha", Fecha);
        registro.put("temperatura", Temperatura);
        registro.put("peso", Peso);
        registro.put("motivo", Motivo);
        registro.put("observaciones", Observaciones);


        int cant = bd.update("clinica", registro, "clave=" +  Clave, null);
        bd.close();

        if (cant==1){
            Toast.makeText(this, "Se modifico el registro del paciente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No existe el registro del paciente", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpia (View v) {
        clave.setText("");
        nombre.setText("");
        direccion.setText("");
        telefono.setText("");
        fecha.setText("");
        temperatura.setText("");
        peso.setText("");
        motivo.setText("");
        observaciones.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent abrir = new Intent(MainActivity.this, informacion.class);
            startActivity(abrir);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
