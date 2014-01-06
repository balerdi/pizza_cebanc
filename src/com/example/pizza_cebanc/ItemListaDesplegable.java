package com.example.pizza_cebanc;
 
public class ItemListaDesplegable{
	
    private String nombre;
    private String precio;
	
	public ItemListaDesplegable(String nombre, String precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    
	public String getPrecio() {
		return precio;
	}
	
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
}