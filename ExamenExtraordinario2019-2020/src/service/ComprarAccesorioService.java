package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Articulo;
import model.PrendaDeVestir;
import model.Accesorio;

public class ComprarAccesorioService {
	private List<Articulo> todosLosElectrodomesticosList;
	private Accesorio electrodomesticoPequeQueSeQuiereEliminar;
	private PrendaDeVestir electrodomesticoGrandeQueSeQuiereEliminar;
	private String codigoIntroducidoPorUser;
	private Scanner scanner;
	private boolean esPeque = false;

	public ComprarAccesorioService() {
		todosLosElectrodomesticosList = new ArrayList<>();
		scanner = new Scanner(System.in);
		electrodomesticoPequeQueSeQuiereEliminar = new Accesorio();
		electrodomesticoGrandeQueSeQuiereEliminar = new PrendaDeVestir();
	}

	public void deleteAccesorio(List<Articulo> electrodomesticos) {
		todosLosElectrodomesticosList = electrodomesticos;
		pintarElectrodomesticosPorConsola();
		comprobarCodigo();

	}

	private void pintarElectrodomesticosPorConsola() {
		System.out.println("Estos son los electrodomésticos que hay:");
		int contador = 1;
		for (Articulo electrodomestico : todosLosElectrodomesticosList) {
			System.out.println(contador + ". " + electrodomestico);
			contador++;
		}
	}

	private void comprobarCodigo() {

		do {
			System.out.println("Introduce el código:");
			codigoIntroducidoPorUser = scanner.next();
			if (eseCodigoExiste(codigoIntroducidoPorUser)) {
				System.out.println("Quieres eliminar el siguiente electrodoméstico: ");
				for (Articulo electrodomestico : todosLosElectrodomesticosList) {
					if (electrodomestico.getCodigo().getCodigo().equals(codigoIntroducidoPorUser)) {
						if (electrodomestico instanceof Accesorio) {
							esPeque = true;
							electrodomesticoPequeQueSeQuiereEliminar = (Accesorio) electrodomestico;
							System.out.println(electrodomesticoPequeQueSeQuiereEliminar.toString());
						} else {
							esPeque = false;
							electrodomesticoGrandeQueSeQuiereEliminar = (PrendaDeVestir) electrodomestico;
							System.out.println(electrodomesticoGrandeQueSeQuiereEliminar.toString());
						}
					}
				}
				do {
					System.out.println("¿Es correcto? si/no");
					String opc = scanner.next();
					if (opc.toLowerCase().equals("si")) {
						if (esPeque) {
							todosLosElectrodomesticosList.remove(electrodomesticoPequeQueSeQuiereEliminar);
							System.out.println("Electrodoméstico eliminado.");
						} else {
							todosLosElectrodomesticosList.remove(electrodomesticoGrandeQueSeQuiereEliminar);
							System.out.println("Electrodoméstico eliminado.");
						}
						break;
					} else if (opc.toLowerCase().equals("no")) {
						System.out.println("Has elegido no eliminarlo.");
						break;
					} else {
						System.out.println("Elige sí o no.");
					}
				} while (true);
				break;

			} else {
				System.out.println("Ese código no existe");
			}
		} while (true);
	}

	private boolean eseCodigoExiste(String codigoBusqueda) {
		for (Articulo electrodomestico : todosLosElectrodomesticosList) {
			if (electrodomestico.getCodigo().getCodigo().equals(codigoBusqueda)) {

				return true;
			}
		}
		return false;
	}

}
