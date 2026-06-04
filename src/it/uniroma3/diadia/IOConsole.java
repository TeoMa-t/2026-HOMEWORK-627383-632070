package it.uniroma3.diadia;
import java.util.Scanner;

/*
 * Classe IOConsole - gestisce l'input/output
 * @version base
 */
public class IOConsole implements IO {

	private Scanner scannerDiLinee;

	// Costruttore 1: Per i Test (così non si rompe nulla di quello che abbiamo fatto)
	public IOConsole() {
		this.scannerDiLinee = new Scanner(System.in);
	}

	// Costruttore 2 (ESERCIZIO 20): Riceve lo scanner dall'esterno (dal main)
	public IOConsole(Scanner scanner) {
		this.scannerDiLinee = scanner;
	}

	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	public String leggiRiga() {
		// Ora usiamo lo scanner salvato come variabile di istanza, senza ricrearlo ogni volta
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
}