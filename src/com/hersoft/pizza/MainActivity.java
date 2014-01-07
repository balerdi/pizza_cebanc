package com.hersoft.pizza;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
private EditText nombre,direccion,telefono;
public static Activity clase1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 clase1=this;
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
		Typeface font = Typeface.createFromAsset(this.getAssets(), "font/chopin.otf");  
		Button siguiente = (Button) findViewById(R.id.btnSiguiente);  
		nombre =(EditText)findViewById(R.id.inNombre); 
		direccion =(EditText)findViewById(R.id.inDireccion);  
		telefono =(EditText)findViewById(R.id.inTelefono);  
		Button salir = (Button) findViewById(R.id.btnSalir);
		siguiente.setTypeface(font); 
		
		salir.setTypeface(font); 
		salir.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				
			}
		});
		siguiente.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(nombre.length()==0 || direccion.length()==0 || telefono.length()==0){
					 Toast.makeText(MainActivity.this, "Faltan campos por rellenar", Toast.LENGTH_SHORT)
                     .show();

				}else
				{
					if(telefono.length()>=6){
						String nom,dir,tlf;
						nom=nombre.getText().toString();
						dir=direccion.getText().toString();
						tlf=telefono.getText().toString();
						Intent in=new Intent(MainActivity.this, PizzasActivity.class);					
						in.putExtra("nombre", nom);
						in.putExtra("direccion", dir);
						in.putExtra("telefono", tlf);
						startActivity(in);
					}else{
						Toast.makeText(MainActivity.this, "El telefono no es valido 6 numeros minimo", Toast.LENGTH_SHORT)
	                     .show();
					}
					
					
				}
				
			}
		});
		}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
