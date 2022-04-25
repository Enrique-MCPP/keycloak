package service;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import controller.Principal;
import model.Articulo;
import model.PrendaDeVestir;
import model.Accesorio;
import model.Referencia;

public class BinarioService {

	private final File archivo = new File(Principal.ARTICULOS_BIN);

	private List<Articulo> todosLosArticulosList = new ArrayList<>();

	public void setArticulosListEscribiendoBinario(List<Articulo> todosLosArticulosList) {

		try {
			FileOutputStream fileOutputStream;
			ObjectOutputStream escritura;
			if (todosLosArticulosList.isEmpty()) {
				fileOutputStream = new FileOutputStream(archivo);
				escritura = new ObjectOutputStream(fileOutputStream);
				escritura.writeObject(new PrendaDeVestir(new Referencia("54545454"), "descripción", "cantidad", 10.12));
				System.out.println("Objeto añadido con éxito.");
			} else {
				fileOutputStream = new FileOutputStream(archivo);
				escritura = new ObjectOutputStream(fileOutputStream);
				for (Articulo articulo : todosLosArticulosList) {
					escritura.writeObject(articulo);
				}
				System.out.println("Lista actualizada");
			}

			escritura.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public List<Articulo> getArticulosListLeyendoBinario() throws ClassNotFoundException {
		try {
			FileInputStream fileInputStream = new FileInputStream(archivo);
			ObjectInputStream lectura = new ObjectInputStream(fileInputStream);
			while (fileInputStream.available() > 0) {
				Articulo articulo = (Articulo) lectura.readObject();
				todosLosArticulosList.add(articulo);
			}
			fileInputStream.close();
		} catch (EOFException e) {
			System.out.println("Error al leer el archivo");
			e.printStackTrace();
			return new ArrayList<Articulo>();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo");
			e.printStackTrace();
			return new ArrayList<Articulo>();
		}
		return todosLosArticulosList;
	}

	public void addBinario(List<Articulo> todosLosArticulosList) {

		int contador = 0;
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(archivo, true);
			ActualizarArchivoBinario updateObjectOutputStream = null;
			Integer codigoAleatorio;
			String codigoEnString;
			// Al principio del programa la lista está vacía.
			if (todosLosArticulosList.isEmpty()) {
				// añadiendo electrodomésticos grandes y peqñ
				while (contador <= 3) {
					codigoAleatorio = ThreadLocalRandom.current().nextInt(0, 99999999);
					codigoEnString = codigoAleatorio.toString();
					while (codigoEnString.length() < 8) {
						codigoEnString += "0";
					}

					updateObjectOutputStream = new ActualizarArchivoBinario(fileOutputStream);
					updateObjectOutputStream.writeObject(
							new PrendaDeVestir(new Referencia("54545454"), "descripción", "cantidad", 10.12));
					// Para que cree otro código aleatorio y no sea el mismo que el del
					// electrodoméstico grande.
					codigoAleatorio = ThreadLocalRandom.current().nextInt(0, 99999999);
					codigoEnString = codigoAleatorio.toString();
					while (codigoEnString.length() < 8) {
						codigoEnString += "0";
					}
					updateObjectOutputStream
							.writeObject(new Accesorio(new Referencia("54545454"), "descripción", "cantidad", 10.12));
					contador++;
				}

			} else {
				updateObjectOutputStream = new ActualizarArchivoBinario(fileOutputStream);
				for (Articulo articulo : todosLosArticulosList) {
					updateObjectOutputStream.writeObject(articulo);
				}
			}

			if (updateObjectOutputStream != null) {
				updateObjectOutputStream.close();
			}

		} catch (Exception e) {
			System.out.println("Error al escribir en el archivo");
			e.printStackTrace();
		}

	}

}
