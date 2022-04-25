package model;

import java.io.Serializable;

import util.IDevolucion;

public class PrendaDeVestir extends Articulo implements Serializable, IDevolucion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7220641039786725033L;

//	public final List<String> tallasDisponibles = Arrays.asList("XS", "S", "M", "L", "XL", "34", "36", "38", "40", "42",
//			"44");

	private String talla;

	boolean admiteDevolucion = true;

	public PrendaDeVestir() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PrendaDeVestir(Referencia referencia, String descripcion, int cantidad, double precio) {
		super(referencia, descripcion, cantidad, precio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PrendaDeVestir:" + super.toString() + ", talla=" + talla + ", admiteDevolucion=" + admiteDevolucion
				+ "]";
	}

	@Override
	public double devolver(int numeroDias) {

		return (4 + numeroDias) / 10;
	}

	public boolean isAdmiteDevolucion() {
		return admiteDevolucion;
	}

	public void setAdmiteDevolucion(boolean admiteDevolucion) {
		this.admiteDevolucion = admiteDevolucion;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

}
