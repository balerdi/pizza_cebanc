package com.example.pizza_cebanc;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.pizza_cebanc.ListaPedido.ViewHolder;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListaPedidoCompleto extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<String> headers;
	private HashMap<String, ArrayList<ItemListaPedido>> items;
	
	public static class ViewHolder {
		public CustomTextView nombre;
		public CustomTextView precio;
		public CustomTextView cantidad;
		public CustomTextView total;
		public ImageView imagen;
		public LinearLayout layoutPrecio;
		
	}

	public ListaPedidoCompleto(Context context, ArrayList<String> headers,
			HashMap<String, ArrayList<ItemListaPedido>> items) {
		this.context = context;
		this.headers = headers;
		this.items = items;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this.items.get(this.headers.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		int ptotal=0;
		final ItemListaPedido item = (ItemListaPedido) getChild(groupPosition, childPosition);
		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.itemlistapedidocompleto, null);
			holder = new ViewHolder();
			holder.nombre = (CustomTextView) convertView.findViewById(R.id.nombre);
			holder.cantidad = (CustomTextView) convertView.findViewById(R.id.numCantidad);
			holder.precio = (CustomTextView) convertView.findViewById(R.id.precio);
			holder.total = (CustomTextView) convertView.findViewById(R.id.precioTotal);
			holder.imagen = (ImageView) convertView.findViewById(R.id.imagen);
			holder.layoutPrecio = (LinearLayout) convertView.findViewById(R.id.layoutPrecio);
			
		
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		if(groupPosition != 0){
			holder.layoutPrecio.setVisibility(View.VISIBLE);
			holder.nombre.setText(item.getNombre()+" "+item.getTipo()+" "+item.getTamano());
			holder.cantidad.setText(item.getCantidad());
			holder.precio.setText(item.getPrecio() + " €");
			holder.total.setText((float) Float.parseFloat(item.getCantidad()) * Float.parseFloat(item.getPrecio()) + " €");
			
			if(item.getNombre().equals("Carbonara")){
				
				holder.imagen.setImageResource(R.drawable.carbo);
	
			}
			else if(item.getNombre().equals("Barbacoa")){
				holder.imagen.setImageResource(R.drawable.barbacoa);
			}
			else if(item.getNombre().equals("Cuatro Quesos")){
				holder.imagen.setImageResource(R.drawable.cuatro);			
			}
			else if(item.getNombre().equals("Vegetal")){
				holder.imagen.setImageResource(R.drawable.vegetal);
			}
			else if(item.getNombre().equals("Tropical")){
				holder.imagen.setImageResource(R.drawable.tropical);
			}
			else if(item.getNombre().equals("Kas Naranja")){
				holder.imagen.setImageResource(R.drawable.btnkasn);
			}
			else if(item.getNombre().equals("Kas Limon")){
				holder.imagen.setImageResource(R.drawable.btnkasl);			
			}
			else if(item.getNombre().equals("Nestea")){
				holder.imagen.setImageResource(R.drawable.nestea);
			}
			else if(item.getNombre().equals("Cerveza")){
				holder.imagen.setImageResource(R.drawable.btncerveza);
			}
			else if(item.getNombre().equals("Insalus")){
				holder.imagen.setImageResource(R.drawable.insalus);			
			}
			else if(item.getNombre().equals("Coca Cola")){
				holder.imagen.setImageResource(R.drawable.btncocacola);
			}
		}
		else{
			if(childPosition == 0){
				holder.nombre.setText("Nombre: "+item.getNombre());
				holder.layoutPrecio.setVisibility(View.GONE);
			}
			else if(childPosition == 1){
				holder.nombre.setText("Dirección: "+item.getNombre());
				holder.layoutPrecio.setVisibility(View.GONE);
			}
			else if(childPosition == 2){
				holder.nombre.setText("Teléfono: "+item.getNombre());
				holder.layoutPrecio.setVisibility(View.GONE);
			}
		}

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.items.get(this.headers.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.headers.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.headers.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater
					.inflate(R.layout.headerexpandable, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.lblListHeader);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}