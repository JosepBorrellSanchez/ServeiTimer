package com.iesebre.DAM2.timer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
 
public class TestServicioLanzador extends Activity
{
    ProgressBar barraProgreso=null;
    TextView ejecuciones=null;
 
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
        // Iniciamos y establecemos el layout super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        // Asociamos los controles
        this.barraProgreso=(ProgressBar)findViewById(R.id.ProgressBar01);
        this.ejecuciones=(TextView)findViewById(R.id.TextView01);
 
        // Establecemos la actividad principal para el servicio
        TestServicio.ACTIVIDAD=this;
 
        // Iniciamos el servicio
        try
        {
            Log.i(getClass().getSimpleName(), "Iniciando servicio desde la actividad...");
 
            // Cogemos el intent el servicio
            Intent servicio = new Intent(this, TestServicio.class);
 
            // Lo ejecutamos
            if(startService(servicio)==null)
            {
                this.notificar("No se ha podido iniciar el servicio");
            }
            else
            {
                this.notificar("Servicio iniciado correctamente");
            }
        }
        catch(Exception e)
        {
            this.notificar(e.getMessage());
        }
    }
 
    public void onDestroy()
    {
        super.onDestroy();
 
        try
        {
            // Finalizamos el servicio
            Log.i(getClass().getSimpleName(), "Finalizando el servicio desde la actividad...");
 
            // Cogemos el intent el servicio
            Intent servicio = new Intent(this, TestServicio.class);
 
            // Lo ejecutamos
            if(stopService(servicio))
            {
                this.notificar("Servicio finalizado correctamente");
            }
            else
            {
                this.notificar("No se ha podido finalizar el servicio");
            }
 
            // Salimos
            this.finalize();
        }
        catch (Throwable e)
        {
            this.notificar(e.getMessage());
        }
    }
 
    private void notificar(String cadena)
    {
        // Notificamos con un toast
        Context contexto = getApplicationContext();
        CharSequence texto = cadena;
        int duracion = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(contexto, texto, duracion);
        toast.show();
    }
}