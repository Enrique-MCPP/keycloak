package model;

import java.io.Serializable;

public abstract class Articulo implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8617058472566824978L;

	private Referencia referencia;

	private String descripcion;
	private int cantidad;
	private double precio;

//	public abstract double calcularPrecio();
	
	

	public Articulo() {
		
	}


	public Articulo(Referencia referencia, String descripcion, int cantidad, double precio) {
	this.referencia = referencia;
	this.descripcion = descripcion;
	this.cantidad = cantidad;
	this.precio = precio;
}







	@Override
	public String toString() {
		return "Articulo [referencia=" + referencia + ", descripcion=" + descripcion + ", cantidad=" + cantidad
				+ ", precioo=" + precio;
	}



	public Referencia getReferencia() {
		return referencia;
	}



	public void setReferencia(Referencia referencia) {
		this.referencia = referencia;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}

	


	

	
}
