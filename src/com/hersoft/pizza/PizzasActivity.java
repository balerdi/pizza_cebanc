package com.hersoft.pizza;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class PizzasActivity extends Activity {
	
	 private MainActivity c1= new MainActivity();
	 public static Activity clase2;
	 
	String tipoPizzas [];
	String precioTipo [];
	ArrayList<ItemListaDesplegable> tipos = new ArrayList<ItemListaDesplegable>();
	
	String tamanoPizzas [];
	String precioTamano [];
	ArrayList<ItemListaDesplegable> tamanos = new ArrayList<ItemListaDesplegable>();
	
	String nombrePizzas [];
	String precioNombre [];
	ArrayList<ItemListaDesplegable> pizzas = new ArrayList<ItemListaDesplegable>();
	
	int idPedido[] = new int[3];
	
	Spinner spinnerTipo;
	Spinner spinnerTamano;
	Spinner spinnerPizzas;
	
	ListaDesplegable adapterTipo;
	ListaDesplegable adapterTamano;
	ListaDesplegable adapterPizzas;
	
	ListaPedido adapterPedido;
	ListView listaPedido;
	
	NumberPicker picker;
	
	Button btnAceptar;
	Button btnAnadir;
	
	String nombre;
	String direccion;
	String telefono;
	ArrayList <ItemListaPedido> pizzasEnvio;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		c1.clase1.finish();
		super.onCreate(savedInstanceState);
	
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.pizzas);
		
		Intent intent = getIntent();
		
		nombre = intent.getStringExtra("nombre");
		direccion = intent.getStringExtra("direccion");
		telefono = intent.getStringExtra("telefono");
		
		tipoPizzas = getResources().getStringArray(R.array.tipos);
		tamanoPizzas = getResources().getStringArray(R.array.tamanos);
		nombrePizzas = getResources().getStringArray(R.array.pizzas);
		
		precioTipo = getResources().getStringArray(R.array.precioTipo);
		precioTamano = getResources().getStringArray(R.array.precioTamano);
		precioNombre = getResources().getStringArray(R.array.precioPizzas);
		
		for(int i = 0; i < tipoPizzas.length; i++){
			tipos.add(new ItemListaDesplegable(tipoPizzas[i], precioTipo[i]));
        }
		
		for(int i = 0; i < tamanoPizzas.length; i++){
			tamanos.add(new ItemListaDesplegable(tamanoPizzas[i], precioTamano[i]));
        }
		
		for(int i = 0; i < nombrePizzas.length; i++){
			pizzas.add(new ItemListaDesplegable(nombrePizzas[i], precioNombre[i]));
        }
		
		Button siguiente = (Button) findViewById(R.id.btnSiguiente);  
		Button salir = (Button) findViewById(R.id.btnSalir);
		
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
				if(adapterPedido.getCount() < 1){
					 Toast.makeText(PizzasActivity.this, "Has de seleccionar almenos una pizza", Toast.LENGTH_SHORT)
                    .show();
				}else
				{
					Intent in=new Intent(PizzasActivity.this, BebidasActivity.class);					
					in.putExtra("nombre", nombre);
					in.putExtra("direccion", direccion);
					in.putExtra("telefono", telefono);
					pizzasEnvio = new ArrayList<ItemListaPedido>();
					for(int i = 0; i < adapterPedido.getCount(); i++){
						pizzasEnvio.add(adapterPedido.getItem(i));
					}
					in.putExtra("pizzasEnvio", (Serializable) pizzasEnvio);
					startActivity(in);
				}
				
			}
		});
		
		btnAnadir = (Button) findViewById(R.id.btnAnadir);
		
		btnAnadir.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				final Dialog dialog = new Dialog(PizzasActivity.this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.dialogopizzas);
				
				adapterTipo = new ListaDesplegable(PizzasActivity.this, R.layout.itemlistadesplegable , tipos);
				
				spinnerTipo = (Spinner) dialog.findViewById(R.id.spinnerTipo);
				spinnerTipo.setAdapter(adapterTipo);
				
				spinnerTipo.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						idPedido[0] = arg2;
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						
					}
				});
				
				adapterTamano = new ListaDesplegable(PizzasActivity.this, R.layout.itemlistadesplegable , tamanos);
				
				spinnerTamano = (Spinner) dialog.findViewById(R.id.spinnerTamano);
				spinnerTamano.setAdapter(adapterTamano);

				spinnerTamano.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						idPedido[1] = arg2;
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						
					}
				});
				
				adapterPizzas = new ListaDesplegable(PizzasActivity.this, R.layout.itemlistadesplegable , pizzas);
				
				spinnerPizzas = (Spinner) dialog.findViewById(R.id.spinnerPizzas);
				spinnerPizzas.setAdapter(adapterPizzas);
				
				spinnerPizzas.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						idPedido[2] = arg2;
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						
					}
				});
				
				picker = (NumberPicker) dialog.findViewById(R.id.pickerPizzas);
				picker.setCurrent(1);
				
				btnAceptar = (Button) dialog.findViewById(R.id.btnAceptar);
				
				btnAceptar.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						float precio = Float.valueOf(((ItemListaDesplegable) spinnerTipo.getSelectedItem()).getPrecio().replace("€", "")) + 
								Float.valueOf(((ItemListaDesplegable) spinnerTamano.getSelectedItem()).getPrecio().replace("€", "")) + 
								Float.valueOf(((ItemListaDesplegable) spinnerPizzas.getSelectedItem()).getPrecio().replace("€", ""));
						String precioUnidad = String.valueOf(precio);
						
						boolean flag = false;
						for(int i = 0; i < adapterPedido.getCount(); i++){
							ItemListaPedido item = adapterPedido.getItem(i);
							if( item.getNombre().equals(pizzas.get(idPedido[2]).getNombre()) && item.getTamano().equals(tamanos.get(idPedido[1]).getNombre()) && item.getTipo().equals(tipos.get(idPedido[0]).getNombre())){
								int cantidad = Integer.parseInt(item.getCantidad()) +  picker.mCurrent;
								item.setCantidad(String.valueOf(cantidad));
								adapterPedido.notifyDataSetChanged();
								flag = true;
							}
						}
						
						if( flag == false ){
							adapterPedido.add(new ItemListaPedido(pizzas.get(idPedido[2]).getNombre() ,tamanos.get(idPedido[1]).getNombre() ,tipos.get(idPedido[0]).getNombre() , precioUnidad, String.valueOf(picker.mCurrent)));
						}
						
						dialog.dismiss();
					}
				});
				
				dialog.show();
				
			}
		});

		adapterPedido = new ListaPedido(this, R.layout.itemlistapedido , new ArrayList<ItemListaPedido>());
		
		listaPedido = (ListView) findViewById(R.id.listaPedido);
		listaPedido.setEmptyView( findViewById( R.layout.listavacia ) );
		listaPedido.setAdapter(adapterPedido);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pizzas, menu);
		return true;
	}

}
