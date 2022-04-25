package model;

import java.io.Serializable;

public class Referencia implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4979890555819404922L;
	
	private String referencia = "";

	public Referencia(String referencia) {
		this.referencia = referencia;
	}

	public Referencia() {
	}
	
	

	@Override
	public String toString() {
		return "Codigo [referencia=" + referencia + "]";
	}

	/**
	 * Método para comprobar cantidad de minúsculas, mayúsculas, números y especial
	 * caracteres.
	 * 
	 * @param codigoIntroducidoPorUser
	 */
	public boolean comprobarSiReferenciaCorrectaForEach(String codigoIntroducidoPorUser) {
	
		if (codigoIntroducidoPorUser.chars().allMatch(Character::isDigit)
				&& codigoIntroducidoPorUser.length() == 8) {
			return true;
		}
		return false;

	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}



//	public boolean comprobarSiCodigoCorrectoFor(String codigoIntroducidoPorUser) {
//		long uppercase = 0;
//		long lowerCase = 0;
//		long specialCharacter = 0;
//		long number = 0;
//
//		for (int i = 0; i < codigoIntroducidoPorUser.length(); i++) {
//
//			if (Character.isUpperCase(codigoIntroducidoPorUser.charAt(i))) {
//				uppercase++;
//			} else if (Character.isLowerCase(codigoIntroducidoPorUser.charAt(i))) {
//				lowerCase++;
//			} else if (Character.isDigit(codigoIntroducidoPorUser.charAt(i))) {
//				number++;
//			} else {
//				specialCharacter++;
//			}
//		}
//		System.out.println("El código tiene: ");
//		System.out.println(uppercase + " mayúsculas.");
//		System.out.println(lowerCase + " minúsculas.");
//		System.out.println(number + " números.");
//		System.out.println(specialCharacter + " especiales caracteres.");
//
//		if ((uppercase > 0 || number > 0) && lowerCase == 0 && specialCharacter == 0
//				&& codigoIntroducidoPorUser.length() == 8) {
//			System.out.println("Código correcto");
//			return true;
//		}
//		return false;
//
//	}

//	public static long countUpperCaseDelCodigoConLambda(final String codigoIntroducidoPorUser) {
//		return codigoIntroducidoPorUser.chars() // get all chars from the argument
//				.filter(c -> Character.isUpperCase(c)) // filter only the uppercase
//				.count(); // count the uppercase
//	}
//	public boolean codigoEsCorrecto(String codigoIntroducidoPorUser) {
//		// Chequear si tiene algún caracter especial
//		Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
//		Matcher matcher = pattern.matcher(codigoIntroducidoPorUser);
//		// Si containsSpecialChar= true, es que sí tiene algún caracter especial.
//		boolean containsSpecialChar = matcher.find();
//		if (!containsSpecialChar && !codigoIntroducidoPorUser.chars().anyMatch(Character::isLowerCase)
//				&& codigoIntroducidoPorUser.length() == 8) {
//			return true;
//		}
//		return false;
//	}

}
