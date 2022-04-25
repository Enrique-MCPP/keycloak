package model;

import java.io.Serializable;

import util.IDevolucion;

public class Accesorio extends Articulo implements Serializable, IDevolucion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8607175772410584915L;


	public Accesorio() {
	
	}


	public Accesorio(Referencia referencia, String descripcion, int cantidad, double precio) {
		super(referencia, descripcion, cantidad, precio);
	}


	@Override
	public String toString() {
		return "Accesorio: " + super.toString() + "]";
	}
	
	@Override
	public double devolver(int numeroDias) {

		return (4 + numeroDias) / 10;
	}



}
