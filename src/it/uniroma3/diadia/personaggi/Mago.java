package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio{

	private static final String MESSAGGIO_DONO="Sei un vero simpaticone! Con una mia magica azione, troverai un nuovo oggetto per il tuo borsone!!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho più nulla...";
	private Attrezzo attrezzo;

	public Mago(String nome,String presentazione,Attrezzo attrezzo) {
		super(nome,presentazione);
		this.attrezzo = attrezzo;

	}
	@Override
	public String agisci(Partita partita) {
		String msg;
		
		if(this.attrezzo != null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;			
		}
		else
			msg = MESSAGGIO_SCUSE;

		return msg;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg = "Grazie per " + attrezzo.getNome() + "! Con una magia ne dimezzo il peso e lo lascio a terra.";
		
		int nuovoPeso = Math.max(1, attrezzo.getPeso() / 2);
		Attrezzo attrezzoModificato = new Attrezzo(attrezzo.getNome(), nuovoPeso);
		
		partita.getStanzaCorrente().addAttrezzo(attrezzoModificato);
		return msg;
	}
}
