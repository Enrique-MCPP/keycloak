package service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ActualizarArchivoBinario extends ObjectOutputStream {

	public ActualizarArchivoBinario(OutputStream out) throws IOException {
		super(out);

	}

	public ActualizarArchivoBinario() throws IOException, SecurityException {

	}

	@Override
	protected void writeStreamHeader() throws IOException {
		// Para que al escribir un nuevo objeto, se va a escribir una nueva cabecera siempre. 
		reset();
	}

}
