package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import model.Articulo;
import service.BinarioService;

public class Principal {
	
	public final static String ARTICULOS_BIN = "articulos.bin";

	public static void main(String[] args) throws InterruptedException, ClassNotFoundException {

		try {
			final File archivo = new File(ARTICULOS_BIN);
			Menu menu = new Menu();
			BinarioService binarioService = new BinarioService();
			// Si el archivo no existe, lo crea
			if (archivo.exists()) {
				binarioService.setArticulosListEscribiendoBinario(new ArrayList<Articulo>());
				binarioService.addBinario(new ArrayList<Articulo>());
				menu.menu(binarioService.getArticulosListLeyendoBinario());
				//Pero si existe:
			} else {
				FileInputStream fileInputStream = new FileInputStream(archivo);
				// Si el archivo no tiene bytes, se escribe
				if (fileInputStream.available() <= 0) {
					binarioService.setArticulosListEscribiendoBinario(new ArrayList<Articulo>());
					binarioService.addBinario(new ArrayList<Articulo>());
					menu.menu(binarioService.getArticulosListLeyendoBinario());
					fileInputStream.close();
					// Si ya tiene algo escrito, algún byte, que lea esta lista inicial y la manda al menú.
				} else {
					menu.menu(binarioService.getArticulosListLeyendoBinario());
				}
			}

		} catch (InputMismatchException inputMismatchException) {
			// para debug, puedes ver la causa con el siguiente código.
			inputMismatchException.printStackTrace();
			Thread.sleep(1000);
			// para que el usuario tenga un mensaje.
			System.out.println("Uh oh! Has escrito algo que no es del tipo correcto!");
			main(args);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			main(args);
		} catch (IOException e) {
			e.printStackTrace();
			main(args);
		}

	}

}
