package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurazione {

	private static final String NOME_FILE = "diadia.properties";
	private static Properties prop = null;

	/**
	 * Metodo privato che carica il file properties solo la prima volta
	 * che viene richiesto un parametro (Lazy Initialization)
	 */
	private static void carica() {
		prop = new Properties();
		// Usiamo il ClassLoader per leggere il file ovunque si trovi, anche dentro un .jar
		try (InputStream input = Configurazione.class.getClassLoader().getResourceAsStream(NOME_FILE)) {
			if (input != null) {
				prop.load(input);
			} else {
				System.err.println("File " + NOME_FILE + " non trovato. Verranno usati i valori di default.");
			}
		} catch (IOException e) {
			System.err.println("Errore nel caricamento del file di configurazione.");
			e.printStackTrace();
		}
	}

	public static int getCFU() {
		if (prop == null) {
			carica();
		}
		// Legge la stringa dal file, se non la trova usa "20" come default, poi converte in int
		return Integer.parseInt(prop.getProperty("cfu_iniziali", "20"));
	}

	public static int getPesoMax() {
		if (prop == null) {
			carica();
		}
		// Legge la stringa dal file, se non la trova usa "10" come default, poi converte in int
		return Integer.parseInt(prop.getProperty("peso_max_borsa", "10"));
	}
}
