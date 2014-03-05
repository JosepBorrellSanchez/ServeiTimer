package com.iesebre.DAM2.timer;

import java.util.Timer;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Service {

	 public static Activity ACTIVIDAD;
	    private Timer timer = null;
	 
	    public static void establecerActividadPrincipal(Activity actividad)
	    {
	        MainActivity.ACTIVIDAD=actividad;
	    }
	    
	    
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate();
        this.iniciarServicio();
        
        Log.i(getClass().getSimpleName(), "Servicio iniciado");
        // Iniciamos el servicio
        this.iniciarServicio();
    }
 
    public void onDestroy()
    {
        super.onDestroy();
        this.finalizarServicio();
        
        Log.i(getClass().getSimpleName(), "Servicio detenido");
        // Detenemos el servicio
        this.finalizarServicio();
    }
 
    public IBinder onBind(Intent intent)
    {
        // No usado de momento
        return null;
    }

    public void iniciarServicio()
    {
        try
        {
            Log.i(getClass().getSimpleName(), "Iniciando servicio...");
 
            // Creamos el timer
            this.timer=new Timer();
            long period = 1000;
            long wheen = 0;
 
            // Configuramos lo que tiene que hacer
            this.timer.scheduleAtFixedRate ( new MainActivity() {
            	public void run() {
            		ejecutarTarea(); }
            }
            	, 0, 1000);// Cada segundo );
            
            this.timer.scheduleAtFixedRate(new MainActivity(){
            	public void run(){
            		ejecutarTarea();}
            	}
            , wheen, period);
 
            Log.i(getClass().getSimpleName(), "Temporizador iniciado");
        }
        catch(Exception e)
        {
            Log.i(getClass().getSimpleName(), e.getMessage());
        }
    }
 
    public void finalizarServicio()
    {
        try
        {
            Log.i(getClass().getSimpleName(), "Finalizando servicio...");
 
            // Detenemos el timer
            this.timer.cancel();
 
            Log.i(getClass().getSimpleName(), "Temporizador detenido");
        }
        catch(Exception e)
        {
            Log.i(getClass().getSimpleName(), e.getMessage());
        }
    }
 
    private void ejecutarTarea()
    {
        Log.i(getClass().getSimpleName(), "Ejecutando tarea...");
 
        // Reflejamos la tarea en la actividad principal
        MainActivity.ACTIVIDAD.runOnUiThread ( new Runnable()
        {
            public void run()
            {
                TextView ejecuciones=(TextView)MainActivity.ACTIVIDAD.findViewById(R.id.TextView01);
                ejecuciones.append(".");
            }
        } );
    }
}
