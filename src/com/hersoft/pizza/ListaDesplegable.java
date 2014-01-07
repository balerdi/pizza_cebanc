package com.hersoft.pizza;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ListaDesplegable extends ArrayAdapter<ItemListaDesplegable> {
	 
	private ArrayList<ItemListaDesplegable> items;
	private Activity activity;

	public ListaDesplegable(Activity a, int textViewResourceId, ArrayList<ItemListaDesplegable> entries) {
		super(a, textViewResourceId, entries);
		this.items = entries;
		this.activity = a;
	}

	public static class ViewHolder {
		public CustomTextView nombre;
		public CustomTextView precio;
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
			v = vi.inflate(R.layout.itemlistadesplegable, null);
			holder = new ViewHolder();
			holder.nombre = (CustomTextView) v.findViewById(R.id.nombre);
			holder.precio = (CustomTextView) v.findViewById(R.id.precio);
			v.setTag(holder);
		} else
			holder = (ViewHolder) v.getTag();

		final ItemListaDesplegable itemLista = items.get(position);
		if (itemLista != null) {
			holder.nombre.setText(itemLista.getNombre());
			holder.precio.setText(itemLista.getPrecio());
		}
		return v;
	}
	
	@Override
    public ItemListaDesplegable getItem(int position) {
        return items.get(position);
    }

}