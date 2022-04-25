package util;

public enum TallaEnum {

	XS("XS"), S("S"), M("M"), L("L"), XL("XL"), TREINTA_Y_CUATRO("34"), TREINTA_Y_SEIS("36"), TREINTA_Y_OCHO("38"),
	CUARENTA("40"), CUARENTA_Y_DOS("42"), CUARENTA_Y_CUATRO("44");

	private String talla;

	TallaEnum(String talla) {
		this.talla = talla;
	}

	public String getTalla() {
		return talla;
	}

}
