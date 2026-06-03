package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	private static final String MESSAGGIO_CANE = "ARGHHH,GRRR... ti ho morso!!";
	private static final String CIBO_PREFERITO = "osso";

	public Cane(String nome,String presentazione) {
		super(nome,presentazione);

	}

	@Override
	public String agisci(Partita partita) {

		int cfuAttuali = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfuAttuali - 1);

		return MESSAGGIO_CANE;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo.getNome().equals(CIBO_PREFERITO)) {
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("collare", 2));
			return "Il cane scodinzola felice per l'" + attrezzo.getNome() + " e lascia cadere un collare!";
		} 
		else {
			int cfuAttuali = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(cfuAttuali - 1);
			return "Grrr! Al cane non piace " + attrezzo.getNome() + " e ti morde! (-1 CFU)";
		}
	}
}
