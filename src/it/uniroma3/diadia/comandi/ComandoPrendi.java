package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

/** Classe ComandoPrendi che si occupa di far
 *  raccogliere al giocatore un oggetto da una
 *  stanza
 */
public class ComandoPrendi extends AbstractComando{
	
	public ComandoPrendi() {}
	
	

	@Override
	public void esegui(Partita partita, IO io) {
		if(getParametro() == null) {
			io.mostraMessaggio("Che oggetto vuoi prendere?");

			Attrezzo[] attrezziDisponibili = partita.getStanzaCorrente().getAttrezzi().toArray(new Attrezzo[0]);

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
		if(stanzaCorrente.hasAttrezzo(getParametro())) {

			Attrezzo attrezzoPreso = stanzaCorrente.getAttrezzo(getParametro());
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
	public String getNome() {
		return "prendi";
	}
	
	
}
