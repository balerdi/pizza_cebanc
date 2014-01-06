package com.example.pizza_cebanc;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class ResumenActivity extends Activity {

	CustomTextView total;
	String nombre;
	String direccion;
	String telefono;
	ArrayList<ItemListaPedido> pizzasEnvio;
	ArrayList<ItemListaPedido> bebidasEnvio;

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	private ArrayList<String> headers;
	private HashMap<String, ArrayList<ItemListaPedido>> items;
	float precioTotal=0,cantidad,precio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.resumen);

		Intent intent = getIntent();
		Button siguiente = (Button) findViewById(R.id.btnSiguiente);
		Button salir=(Button)findViewById(R.id.btnSalir);
		nombre = intent.getStringExtra("nombre");
		direccion = intent.getStringExtra("direccion");
		telefono = intent.getStringExtra("telefono");
		pizzasEnvio = (ArrayList<ItemListaPedido>) intent.getSerializableExtra("pizzasEnvio");
		bebidasEnvio = (ArrayList<ItemListaPedido>) intent.getSerializableExtra("bebidasEnvio");
		total = (CustomTextView)findViewById(R.id.total);
		expListView = (ExpandableListView) findViewById(R.id.listaPedido);

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
				if(precioTotal>=20&&precioTotal<=32){

					//AVISO

					String text="Regalo un peluche al azar entre los siguientes:";
					Context context = getApplicationContext();
					Toast t =  Toast.makeText(context, text, Toast.LENGTH_SHORT);  
					t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					t.show();


					//PREMIO

					LayoutInflater li = getLayoutInflater();  
					View layout = li.inflate(R.layout.customtoast2,(ViewGroup) findViewById(R.id.toast_layout_root));  
					Toast toast = Toast.makeText(context, null, 0); 
					toast = new Toast(getApplicationContext());  
					toast.setDuration(Toast.LENGTH_LONG);  
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);  
					toast.setView(layout);//setting the view of custom toast layout  
					toast.show();  

					//DESPEDIDA

					text="Su pedido se ha tramitado con exito.";
					context = getApplicationContext();
					t =  Toast.makeText(context, text, Toast.LENGTH_SHORT);  
					t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					t.show();

					Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);

				}else if(precioTotal>=33){

					//AVISO
					String text="Regalo una en comida en el Restaurante Cebanc:";
					Context context = getApplicationContext();
					Toast t =  Toast.makeText(context, text, Toast.LENGTH_SHORT);  
					t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					t.show();

					//PREMIO

					LayoutInflater li = getLayoutInflater();  
					View layout = li.inflate(R.layout.customtoast,(ViewGroup) findViewById(R.id.toast_layout_root));  
					Toast toast = Toast.makeText(context, null, 0); 
					toast = new Toast(getApplicationContext());  
					toast.setDuration(Toast.LENGTH_LONG);  
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);  
					toast.setView(layout);//setting the view of custom toast layout  
					toast.show(); 

					//DESPEDIDA

					text="Su pedido se ha tramitado con exito.";
					context = getApplicationContext();
					t =  Toast.makeText(context, text, Toast.LENGTH_SHORT);  
					t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					t.show();

					 Handler handler = new Handler(); 
					    handler.postDelayed(new Runnable() { 
					         public void run() { 
					        	 Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
									i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(i);
					         } 
					    }, 8000); 
					
					
				}

			}
		});


		prepareListData();

		listAdapter = new ListaPedidoCompleto(this, headers, items);

		expListView.setAdapter(listAdapter);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resumen, menu);
		return true;
	}

	private void prepareListData() {
		headers = new ArrayList<String>();
		items = new HashMap<String, ArrayList<ItemListaPedido>>();

		headers.add("Datos personales");
		headers.add("Pizzas");
		headers.add("Bebidas");

		ArrayList<ItemListaPedido> datos = new ArrayList<ItemListaPedido>();
		datos.add(new ItemListaPedido(nombre,"","","",""));
		datos.add(new ItemListaPedido(direccion,"","","",""));
		datos.add(new ItemListaPedido(telefono,"","","",""));

		items.put(headers.get(0), datos);
		items.put(headers.get(1), pizzasEnvio);
		items.put(headers.get(2), bebidasEnvio);


		//pizzas

		for(int i = 0; i < pizzasEnvio.size(); i++){
			precio=Float.parseFloat(pizzasEnvio.get(i).getPrecio());
			cantidad=Float.parseFloat(pizzasEnvio.get(i).getCantidad());
			precioTotal = precioTotal + (precio*cantidad);
		}

		//bebidas

		for(int i = 0; i < bebidasEnvio.size(); i++){
			precio=Float.parseFloat(bebidasEnvio.get(i).getPrecio());
			cantidad=Float.parseFloat(bebidasEnvio.get(i).getCantidad());
			precioTotal = precioTotal + (precio*cantidad);
		}


		total.setText("Precio total:  "+Float.toString(precioTotal)+"  € .");

	}

}
