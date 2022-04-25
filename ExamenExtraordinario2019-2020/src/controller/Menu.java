package controller;

import java.util.List;
import java.util.Scanner;

import model.Articulo;
import service.AddAccesorioService;
import service.BinarioService;
import service.BuyAccesorioService;
import service.ComprarAccesorioService;

public class Menu {
	private AddAccesorioService addAccesorioService = new AddAccesorioService();
	private BuyAccesorioService buyAccesorioService = new BuyAccesorioService();
	private ComprarAccesorioService deleteAccesorioService = new ComprarAccesorioService();
	private Scanner scanner = new Scanner(System.in);

	private int opcion;

	public void menu(List<Articulo> todosLosArticulosList) throws InterruptedException {
		do {
			System.out.println("Elige una opción");
			System.out.println("1. Añadir electrodoméstico.");
			System.out.println("2. Vender electrodoméstico.");
			System.out.println("3. Eliminar electrodoméstico.");
			System.out.println("4. Salir.");
			opcion = scanner.nextInt();
			switch (opcion) {
			case 1: {
				addAccesorioService.addElectrodomestico(todosLosArticulosList);
				break;
			}
			case 2: {
				buyAccesorioService.comprarAccesorio(todosLosArticulosList);
				break;
			}
			case 3: {
				deleteAccesorioService.deleteAccesorio(todosLosArticulosList);
				break;
			}
			case 4: {
				System.out.println("Hasta pronto!");
				BinarioService binarioService = new BinarioService();
				binarioService.setArticulosListEscribiendoBinario(todosLosArticulosList);
				return;
			}
			default:
				System.out.println("opción no válida");
			}

		} while (true);
	}

}
