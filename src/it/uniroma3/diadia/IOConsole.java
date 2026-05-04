package it.uniroma3.diadia;
import java.util.Scanner;

/*
 * Classe IOConsole - è lo scanner che permette
 * di leggere messaggi in input, e di metterene a schermo.
 * 
 * @author 627383 - 632070
 * @version base
 */
public class IOConsole implements IO{

	//Mostra il messaggio in output
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	//legge input
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
}

