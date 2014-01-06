package com.example.pizza_cebanc;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

public class ListaPedido extends ArrayAdapter<ItemListaPedido> {
	 
	private ArrayList<ItemListaPedido> items;
	private Activity activity;

	public ListaPedido(Activity a, int textViewResourceId, ArrayList<ItemListaPedido> entries) {
		super(a, textViewResourceId, entries);
		this.items = entries;
		this.activity = a;
	}

	public static class ViewHolder {
		public CustomTextView nombre;
		public CustomTextView precio;
		public CustomTextView cantidad;
		public Button btnRes;
		public Button btnX;
		public ImageView imagen;
	}
	
	@Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getView(position, convertView, parent);
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		ViewHolder holder;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.itemlistapedido, null);
			holder = new ViewHolder();
			holder.nombre = (CustomTextView) v.findViewById(R.id.nombre);
			holder.cantidad = (CustomTextView) v.findViewById(R.id.numCantidad);
			holder.precio = (CustomTextView) v.findViewById(R.id.precio);
			holder.btnRes = (Button) v.findViewById(R.id.btnRes);
			holder.btnX = (Button) v.findViewById(R.id.btnX);
			holder.imagen = (ImageView) v.findViewById(R.id.imagen);
			v.setTag(holder);
		} else
			holder = (ViewHolder) v.getTag();

		final ItemListaPedido itemLista = items.get(position);
		final int pos = position;
		if (itemLista != null) {
			holder.nombre.setText(itemLista.getNombre()+" "+itemLista.getTipo()+" "+itemLista.getTamano());
			holder.cantidad.setText(itemLista.getCantidad());
			holder.precio.setText(itemLista.getPrecio() + " €");
			holder.btnRes.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					reducir(pos, itemLista);
				}
				
			});
			holder.btnX.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					eliminar(pos);
				}
			});
			
			if(itemLista.getNombre().equals("Carbonara")){
				holder.imagen.setImageResource(R.drawable.carbo);
			}
			else if(itemLista.getNombre().equals("Barbacoa")){
				holder.imagen.setImageResource(R.drawable.barbacoa);
			}
			else if(itemLista.getNombre().equals("Cuatro Quesos")){
				holder.imagen.setImageResource(R.drawable.cuatro);			
			}
			else if(itemLista.getNombre().equals("Vegetal")){
				holder.imagen.setImageResource(R.drawable.vegetal);
			}
			else if(itemLista.getNombre().equals("Tropical")){
				holder.imagen.setImageResource(R.drawable.tropical);
			}
		}
		return v;
	}
	
	public void reducir(int position, ItemListaPedido itemLista){
		int cantidad = Integer.parseInt(itemLista.getCantidad());
		cantidad--;
		itemLista.setCantidad(String.valueOf(cantidad));
		if (cantidad == 0 ){
			eliminar(position);
		}
		this.notifyDataSetChanged();
	}
	
	public void eliminar(int position){
		items.remove(position);
		this.notifyDataSetChanged();
	}
	
	@Override
    public ItemListaPedido getItem(int position) {
        return items.get(position);
    }

}
