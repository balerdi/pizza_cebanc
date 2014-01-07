package com.hersoft.pizza;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BebidasActivity extends Activity {

	
	 private PizzasActivity c2= new PizzasActivity();
	 public static Activity clase3;
	 
	private String[] listaBebidas;
	private String[] precioBebidas;

	private String cola_nom, cerveza_nom, naranja_nom, limon_nom, nestea_nom,
			agua_nom;
	boolean cola_flag, cerveza_flag, naranja_flag, limon_flag, nestea_flag,
			agua_flag;
	private int cola_cant, cerveza_cant, naranja_cant, limon_cant, nestea_cant,
			agua_cant;

	GridView gridView;
	ArrayList<Item> gridArray = new ArrayList<Item>();
	CustomGridViewAdapter customGridAdapter;
	ListaPedido adapterPedido;
	ListView listaPedido;

	String nombre, direccion, telefono;
	ArrayList<ItemListaPedido> pizzasEnvio;
	ArrayList<ItemListaPedido> bebidasEnvio;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_bebidas);

		Intent intent = getIntent();

		nombre = intent.getStringExtra("nombre");
		direccion = intent.getStringExtra("direccion");
		telefono = intent.getStringExtra("telefono");
		pizzasEnvio = (ArrayList<ItemListaPedido>) intent
				.getSerializableExtra("pizzasEnvio");

		precioBebidas = getResources().getStringArray(R.array.precioBebidas);
		listaBebidas = getResources().getStringArray(R.array.bebidas);

		Bitmap cerveza = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.btncerveza);
		Bitmap cocacola = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.btncocacola);
		Bitmap insalus = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.insalus);
		Bitmap limon = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.btnkasl);
		Bitmap naranja = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.btnkasn);
		Bitmap nestea = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.nestea);

		gridArray.add(new Item(cerveza, precioBebidas[0]));
		gridArray.add(new Item(cocacola, precioBebidas[1]));
		gridArray.add(new Item(limon, precioBebidas[2]));
		gridArray.add(new Item(insalus, precioBebidas[3]));
		gridArray.add(new Item(naranja, precioBebidas[4]));
		gridArray.add(new Item(nestea, precioBebidas[5]));

		gridView = (GridView) findViewById(R.id.Gbebidas);
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid,
				gridArray);
		gridView.setAdapter(customGridAdapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				boolean flag = false;
				for (int i = 0; i < adapterPedido.getCount(); i++) {
					ItemListaPedido item = adapterPedido.getItem(i);
					if (item.getNombre().equals(listaBebidas[position])) {
						int cantidad = Integer.parseInt(item.getCantidad()) + 1;
						item.setCantidad(String.valueOf(cantidad));
						adapterPedido.notifyDataSetChanged();
						flag = true;
					}
				}

				if (flag == false) {
					adapterPedido.add(new ItemListaPedido(
							listaBebidas[position], "", "",
							precioBebidas[position], "1"));
				}

			}

		});

		adapterPedido = new ListaPedido(this, R.layout.itemlistapedido,
				new ArrayList<ItemListaPedido>());

		listaPedido = (ListView) findViewById(R.id.listaPedido);
		listaPedido.setEmptyView(findViewById(R.layout.listavacia));
		listaPedido.setAdapter(adapterPedido);

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
				Intent in = new Intent(BebidasActivity.this,
						ResumenActivity.class);
				in.putExtra("nombre", nombre);
				in.putExtra("direccion", direccion);
				in.putExtra("telefono", telefono);
				in.putExtra("pizzasEnvio", (Serializable) pizzasEnvio);
				bebidasEnvio = new ArrayList<ItemListaPedido>();
				for (int i = 0; i < adapterPedido.getCount(); i++) {
					bebidasEnvio.add(adapterPedido.getItem(i));
				}
				in.putExtra("bebidasEnvio", (Serializable) bebidasEnvio);
				startActivity(in);
			}
		});

	}

}
