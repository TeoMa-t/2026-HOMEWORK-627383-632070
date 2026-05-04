package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
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
	public void esegui(Partita partita, IO io) {
		if(oggetto == null) {
			io.mostraMessaggio("Che oggetto vuoi prendere?");

			Attrezzo[] attrezziDisponibili = partita.getStanzaCorrente().getAttrezzi();

			boolean stanzaVuota = true;

			for(Attrezzo a : attrezziDisponibili) {
				if(a != null) {
					io.mostraMessaggio(a.toString() + " | ");
					stanzaVuota = false;
				}
			}
			if(stanzaVuota)
				io.mostraMessaggio("La stanza non contiene oggetti!");

			io.mostraMessaggio("\n");
			return;
		}

		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Giocatore giocatore = partita.getGiocatore();
		if(stanzaCorrente.hasAttrezzo(oggetto)) {

			Attrezzo attrezzoPreso = stanzaCorrente.getAttrezzo(oggetto);
			stanzaCorrente.removeAttrezzo(attrezzoPreso);
			if(giocatore.mettiAttrezzonellaBorsa(attrezzoPreso))
				io.mostraMessaggio("Attrezzo messo nella borsa!");
			else {
				io.mostraMessaggio("Borsa piena!");
				stanzaCorrente.addAttrezzo(attrezzoPreso);
			}
		}
		else
			io.mostraMessaggio("Attrezzo non presente nella stanza.");
	}
	
	@Override
	public void setParametro(String parametro) {
		this.oggetto = parametro;
	}
	
	public String getNome() {
		return "prendi";
	}
	
	public String getParametro() {
		return this.oggetto;
	}
}
