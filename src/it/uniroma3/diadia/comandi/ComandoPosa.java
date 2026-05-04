package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

/** Classe ComandoPosa che si occupa di far
 *  posare al giocatore un oggetto in 
 *  una stanza 
 */
public class ComandoPosa implements Comando{
	private String oggetto;
	
	public void setParametro(String parametro) {
		this.oggetto = parametro;
	}

	@Override
	public void esegui(Partita partita, IO io) {
		Giocatore giocatore = partita.getGiocatore();

		if(oggetto == null) {
			io.mostraMessaggio("Quale oggetto vuoi posare?");
			io.mostraMessaggio(giocatore.getContenutoBorsa());
			io.mostraMessaggio("\n");
			return;
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Attrezzo attrezzoDaPosare = giocatore.prendiAttrezzoDallaBorsa(oggetto);

		if( attrezzoDaPosare != null) {

			if(stanzaCorrente.addAttrezzo(attrezzoDaPosare)) {
				io.mostraMessaggio("Oggetto posato");
			}
			else {
				io.mostraMessaggio("La stanza è piena");
				giocatore.mettiAttrezzonellaBorsa(attrezzoDaPosare);
			}
		}
		else
			io.mostraMessaggio("Non hai questo oggetto");
	}
	
	public String getNome() {
		return "posa";
	}
	
	public String getParametro() {
		return this.oggetto;
	}
}
