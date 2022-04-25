package service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.Accesorio;
import model.Articulo;
import model.PrendaDeVestir;
import model.Referencia;
import util.TallaEnum;

public class AddAccesorioService {
	private List<Articulo> todosLosArticulosList;
	private List<String> tallasQueHay;
	private Accesorio accesorioAActualizar;
	private PrendaDeVestir prendaDeVestirAActualizar;
	private boolean hasElegidoAccesorio = false;
	private Scanner scanner;
	private int opcion;
	private String descripcion;
	private int cantidad;
	private double precio;
	private String referenciaIntroducidaPorUser;
	private boolean userTypedCorrect = false;
	private int cantidadAAgregar;

	public AddAccesorioService() {
		tallasQueHay = new ArrayList<>();
		todosLosArticulosList = new ArrayList<>();
		accesorioAActualizar = new Accesorio();
		prendaDeVestirAActualizar = new PrendaDeVestir();
		scanner = new Scanner(System.in);
	}

	public void addElectrodomestico(List<Articulo> todosLosArticulosList) throws InterruptedException {
		this.todosLosArticulosList = todosLosArticulosList;
		System.out.println("¿Qué tipo de artículo desea?");
		System.out.println("1. Accesorio.");
		System.out.println("2. Prenda de vestir.");
		opcion = scanner.nextInt();
		switch (opcion) {
		case 1: {
			hasElegidoAccesorio = true;
			System.out.println("Has elegido accesorio.");

			comprobarReferencia();

			
			break;
		}
		case 2: {
			hasElegidoAccesorio = false;
			System.out.println("Has elegido la prenda de vestir.");
			comprobarReferencia();
			comunNewArticulo();

			newPrendaDeVestir(todosLosArticulosList);

			break;
		}

		default:
			System.out.println("opción no válida");
		}
	}

	private void comprobarReferencia() throws InterruptedException {

		Referencia referencia = new Referencia();
		do {
			System.out.println("Introduce el código: (El código tiene que tener 8 cifras y sólo números).");
			referenciaIntroducidaPorUser = scanner.next();
			if (esaReferenciaExiste(referenciaIntroducidaPorUser)) {
				System.out.println("Esa referencia ya existe.");
				if (hasElegidoAccesorio) {
					System.out.println("¿Qué cantidad desea añadir al " + accesorioAActualizar.toString() + "?");
					cantidadAAgregar = scanner.nextInt();
				} else {
					System.out.println("¿Qué cantidad desea añadir al " + prendaDeVestirAActualizar.toString() + "?");
					cantidadAAgregar = scanner.nextInt();
				}
				sumarleLaCantidadAAgregar();
				System.out.println("Cantidad sumada correctamente.");
				return;

			} else {
				System.out.println("Nuevo artículo:");
				boolean codigoCorrecto = referencia.comprobarSiReferenciaCorrectaForEach(referenciaIntroducidaPorUser);

				if (codigoCorrecto) {
					System.out.println();
					System.out.println("Código correcto!!");
					while (userTypedCorrect == false) {
						try {
							comunNewArticulo();
							userTypedCorrect = true;
						} catch (InputMismatchException inputMismatchException) {
							// para debug, puedes ver la causa con el siguiente código.
							inputMismatchException.printStackTrace();
							Thread.sleep(1000);
							// para que el usuario tenga un mensaje.
							System.out.println("Uh oh! Has escrito algo que no es del tipo correcto!");
							System.out.println("Vuelve a introducir los datos");
							scanner.next();
						}
					}
					newAccesorio(todosLosArticulosList);
					return;
				} else {
					System.out.println("Código incorrecto.");
				}
			}
		} while (true);
	}

	private Referencia referenciaParaElAccesorio() {

		return new Referencia(referenciaIntroducidaPorUser);
	}

	private void comunNewArticulo() {
		System.out.println("Introduce descripción:");
		descripcion = scanner.next();
		System.out.println("Introduce cantidad:");
		cantidad = scanner.nextInt();
		System.out.println("Introduce precio:");
		precio = scanner.nextDouble();
	}

	private void newAccesorio(List<Articulo> todosLosArticulosList) {
		Accesorio accesorio = new Accesorio(referenciaParaElAccesorio(), descripcion, cantidad, precio);
		todosLosArticulosList.add((Articulo) accesorio);
	}

	private void newPrendaDeVestir(List<Articulo> todosLosArticulosList) throws InputMismatchException {
		PrendaDeVestir prendaDeVestir = new PrendaDeVestir(referenciaParaElAccesorio(), descripcion, cantidad, precio);
		pintarTallasQueHayDisponibles();

		do {
			String talla = scanner.next();
			if (tallasQueHay.contains(talla)) {
				prendaDeVestir.setTalla(talla);
				break;
			} else {
				System.out.println("Esa talla no la tenemos, escribe otra talla.");
				pintarTallasQueHayDisponibles();
			}

		} while (true);

		do {
			System.out.println("¿Requiere instalación el electrodoméstico? si/no");
			String respuesta = scanner.next();
			if (respuesta.toLowerCase().equals("si")) {
				accesorio.setRequiereInstalacion(true);
				break;
			} else if (respuesta.toLowerCase().equals("no")) {
				accesorio.setRequiereInstalacion(false);
				break;
			} else {
				System.out.println("Elige sí o no por favor.");
			}
		} while (true);
		todosLosArticulosList.add((Articulo) accesorio);
	}

	private boolean esaReferenciaExiste(String codigoBusqueda) {
		for (Articulo articulo : todosLosArticulosList) {
			if (articulo.getReferencia().getReferencia().equals(codigoBusqueda)) {
				if (hasElegidoAccesorio) {
					accesorioAActualizar = (Accesorio) articulo;
				} else {
					prendaDeVestirAActualizar = (PrendaDeVestir) articulo;
				}
				return true;
			}
		}
		return false;
	}

	private void sumarleLaCantidadAAgregar() {
		for (Articulo articulo : todosLosArticulosList) {
			if (hasElegidoAccesorio) {
				if (articulo.equals(accesorioAActualizar) || articulo.equals(prendaDeVestirAActualizar)) {
					articulo.setCantidad(articulo.getCantidad() + cantidadAAgregar);

				}
			}
		}
	}

	private void pintarElectrodomesticosPequesPorConsola() {
		int contador = 1;
		for (Articulo electrodomestico : todosLosArticulosList) {
			System.out.println(contador + ". " + electrodomestico);
			contador++;
		}
	}

	private void pintarTallasQueHayDisponibles() {
		System.out.println("Estas son las tallas que tenemos disponibles, elige una:");

		for (TallaEnum talla : TallaEnum.values()) {
			System.out.println(talla);
			tallasQueHay.add(talla.getTalla());
		}
	}
}
