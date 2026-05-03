package it.uniroma3.diadia.comandi;

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
	public void esegui(Partita partita) {
		Giocatore giocatore = partita.getGiocatore();

		if(oggetto == null) {
			System.out.println("Quale oggetto vuoi posare?");
			System.out.println(giocatore.getContenutoBorsa());
			System.out.println("\n");
			return;
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Attrezzo attrezzoDaPosare = giocatore.prendiAttrezzoDallaBorsa(oggetto);

		if( attrezzoDaPosare != null) {

			if(stanzaCorrente.addAttrezzo(attrezzoDaPosare)) {
				System.out.println("Oggetto posato");
			}
			else {
				System.out.println("Stanza piena");
				giocatore.mettiAttrezzonellaBorsa(attrezzoDaPosare);
			}
		}
		else
			System.out.println("Non possiedi l'oggetto");
	}

}
