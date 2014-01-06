package com.example.pizza_cebanc;

import java.io.Serializable;
 
public class ItemListaPedido implements Serializable{
	
    private String nombre;
    private String tamano;
    private String tipo;
    private String precio;
    private String cantidad;

	public ItemListaPedido(String nombre, String tamano, String tipo,
			String precio, String cantidad) {
		super();
		this.nombre = nombre;
		this.tamano = tamano;
		this.tipo = tipo;
		this.precio = precio;
		this.cantidad = cantidad;
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
	
	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getTamano() {
		return tamano;
	}

	public void setTamano(String tamano) {
		this.tamano = tamano;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
}
