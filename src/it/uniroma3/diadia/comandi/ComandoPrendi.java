package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import java.util.Scanner;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

/** Classe ComandoPrendi che si occupa di far
 *  raccogliere al giocatore un oggetto da una
 *  stanza
 */
public class ComandoPrendi implements Comando{
	private String oggetto;
	private Scanner scanner;
	
	
	public void Comandoprendi(String oggetto) {
		this.oggetto = oggetto;
	}

	@Override
	public void esegui(Partita partita) {
		if(oggetto == null) {
			System.out.println("Che oggetto vuoi prendere?");

			Attrezzo[] attrezziDisponibili = partita.getStanzaCorrente().getAttrezzi();

			boolean stanzaVuota = true;

			for(Attrezzo a : attrezziDisponibili) {
				if(a != null) {
					System.out.println(a.toString() + " | ");
					stanzaVuota = false;
				}
			}
			if(stanzaVuota)
				System.out.println("La stanza non contiene oggetti!");

			System.out.println();
			return;
		}

		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Giocatore giocatore = partita.getGiocatore();
		if(stanzaCorrente.hasAttrezzo(oggetto)) {

			Attrezzo attrezzoPreso = stanzaCorrente.getAttrezzo(oggetto);
			stanzaCorrente.removeAttrezzo(attrezzoPreso);
			if(giocatore.mettiAttrezzonellaBorsa(attrezzoPreso))
				System.out.println("Attrezzo messo nella borsa!");
			else {
				System.out.println("Borsa piena!");
				stanzaCorrente.addAttrezzo(attrezzoPreso);
			}
		}
		else
			System.out.println("Attrezzo non presente nella stanza.");
	}
	
	@Override
	public void setParametro(String parametro) {
		this.oggetto = parametro;
	}
}
