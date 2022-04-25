package service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Articulo;
import model.PrendaDeVestir;
import model.Accesorio;

public class BuyAccesorioService {
	private List<Articulo> todoLosAccesoriosList;
	private Accesorio electrodomesticoPequeQueSeQuiere;
	private PrendaDeVestir electrodomesticoGrandeQueSeQuiere;
	private boolean hasElegidoPeque = false;
	private int opcion;
	private Scanner scanner;
	private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
	
	

	public BuyAccesorioService() {
		todoLosAccesoriosList = new ArrayList<>();
		electrodomesticoPequeQueSeQuiere = new Accesorio();
		electrodomesticoGrandeQueSeQuiere = new PrendaDeVestir();
		scanner = new Scanner(System.in);
	}

	public void comprarAccesorio(List<Articulo> todoLosAccesoriosList) {
		this.todoLosAccesoriosList = todoLosAccesoriosList;
		if (todoLosAccesoriosList.size() == 0) {
			System.out.println("No hay electrodom�sticos en stock");
			return;
		}
		System.out.println("�Qu� tipo de electrodom�stico desea?");
		System.out.println("1. Electrodom�stico peque�o.");
		System.out.println("2. Electrodom�stico grande.");
		opcion = scanner.nextInt();
		switch (opcion) {
		case 1: {
			casoElegirPeque();
			break;
		}
		case 2: {
			casoElegirGrande();
			break;
		}

		default:
			System.out.println("opci�n no v�lida");
		}
	}
	
	private void casoElegirPeque() {
		List<Accesorio> electrodomesticosPequesList = new ArrayList<>();
		hasElegidoPeque = true;
		int cantidadElectrodomesticosQueSeQuieren;
		System.out.println("Has elegido electrodom�stico peque�o.");
		
		for (Articulo accesorio : todoLosAccesoriosList) {
			if (accesorio instanceof Accesorio) {
				electrodomesticosPequesList.add((Accesorio) accesorio);
			}
		}
		if (electrodomesticosPequesList.size() == 0) {
			System.out.println("No hay electrodom�sticos en stock");
			return;
		}
		System.out.println("Estos son los electrodom�sticos peque�os que hay:");
		pintarElectrodomesticosPequesPorConsola(electrodomesticosPequesList);

		do {
			System.out.println("Elije uno, escribe su c�digo:");
			String codigoBusqueda = scanner.next();
			if (eseCodigoExiste(codigoBusqueda)) {
				break;
			}
			System.out.println("Ese c�digo no existe, estos son los que tienes:");
			pintarElectrodomesticosPequesPorConsola(electrodomesticosPequesList);

		} while (true);
		do {
			System.out.println("�Cu�ntos quieres?");
			cantidadElectrodomesticosQueSeQuieren = scanner.nextInt();
			if (cantidadElectrodomesticosQueSeQuieren > electrodomesticoPequeQueSeQuiere.getStock()) {
				System.out.println("Solo quedan " + electrodomesticoPequeQueSeQuiere.getStock()
						+ " electrodom�sticos de ese tipo.");
			} else {
				electrodomesticoPequeQueSeQuiere
						.setStock(electrodomesticoPequeQueSeQuiere.getStock() - cantidadElectrodomesticosQueSeQuieren);
				break;
			}
		} while (true);
		System.out.println("Quedan " + electrodomesticoPequeQueSeQuiere.getStock() + " del modelo "
				+ electrodomesticoPequeQueSeQuiere.getModelo());
		System.out.println("Precio total: " + cantidadElectrodomesticosQueSeQuieren + "*"
				+ electrodomesticoPequeQueSeQuiere.getPrecioUnitario() + " = "
				+ decimalFormat.format(
						electrodomesticoPequeQueSeQuiere.calcularPrecio() * cantidadElectrodomesticosQueSeQuieren)
				+ "�");
	}
	
	private void casoElegirGrande() {
		hasElegidoPeque = false;
		List<PrendaDeVestir> electrodomesticosGrandesList = new ArrayList<>();
		int cantidadElectrodomesticosQueSeQuieren;
		System.out.println("Has elegido electrodom�stico grande.");
		for (Articulo accesorio : todoLosAccesoriosList) {
			if (accesorio instanceof PrendaDeVestir) {
				electrodomesticosGrandesList.add((PrendaDeVestir) accesorio);
			}
		}
		if (electrodomesticosGrandesList.size() == 0) {
			System.out.println("No hay electrodom�sticos en stock");
			return;
		}
		System.out.println("Estos son los electrodom�sticos grandes que hay:");
		pintarElectrodomesticosGrandesPorConsola(electrodomesticosGrandesList);

		do {
			System.out.println("Elije uno, escribe su c�digo:");
			String codigoBusqueda = scanner.next();
			if (eseCodigoExiste(codigoBusqueda)) {
				break;
			}
			System.out.println("Ese c�digo no existe, estos son los que tienes:");
			pintarElectrodomesticosGrandesPorConsola(electrodomesticosGrandesList);

		} while (true);
		do {
			System.out.println("�Cu�ntos quieres?");
			cantidadElectrodomesticosQueSeQuieren = scanner.nextInt();
			if (cantidadElectrodomesticosQueSeQuieren > electrodomesticoGrandeQueSeQuiere.getStock()) {
				System.out.println("Solo quedan " + electrodomesticoGrandeQueSeQuiere.getStock()
						+ " electrodom�sticos de ese tipo.");
			} else {
				electrodomesticoGrandeQueSeQuiere
						.setStock(electrodomesticoGrandeQueSeQuiere.getStock() - cantidadElectrodomesticosQueSeQuieren);
				break;
			}
		} while (true);
		System.out.println("Quedan " + electrodomesticoGrandeQueSeQuiere.getStock() + " del modelo "
				+ electrodomesticoGrandeQueSeQuiere.getModelo());
		System.out.println("Precio total: " + cantidadElectrodomesticosQueSeQuieren + "*"
				+ electrodomesticoGrandeQueSeQuiere.calcularPrecio() + " = "
				+ decimalFormat.format(
						electrodomesticoGrandeQueSeQuiere.calcularPrecio() * cantidadElectrodomesticosQueSeQuieren)
				+ "� (Gastos de env�o inclu�dos)");
	}

	private boolean eseCodigoExiste(String codigoBusqueda) {
		for (Articulo accesorio : todoLosAccesoriosList) {
			if (accesorio.getCodigo().getCodigo().equals(codigoBusqueda)) {
				if(hasElegidoPeque) {
					electrodomesticoPequeQueSeQuiere = (Accesorio) accesorio;
				}else {
					electrodomesticoGrandeQueSeQuiere= (PrendaDeVestir) accesorio;
				}
				
				return true;
			}
		}
		return false;
	}

	public void pintarElectrodomesticosPequesPorConsola(List<Accesorio> electrodomesticosPequesList) {
		int contador = 1;
		for (Accesorio electrodomesticoPeque : electrodomesticosPequesList) {
			System.out.println(contador + ". " + electrodomesticoPeque);
			contador++;
		}
	}
	
	public void pintarElectrodomesticosGrandesPorConsola(List<PrendaDeVestir> electrodomesticosGrandesList) {
		int contador = 1;
		for (PrendaDeVestir electrodomesticoGrande : electrodomesticosGrandesList) {
			System.out.println(contador + ". " + electrodomesticoGrande);
			contador++;
		}
	}

}
