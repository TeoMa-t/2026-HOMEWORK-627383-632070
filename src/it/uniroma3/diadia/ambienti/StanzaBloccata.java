package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	// Ora usa Direzione invece di String
	private Direzione direzioneBloccata;
	private String attrezzoSbloccante;

	public StanzaBloccata(String nome, Direzione direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
	}

	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		// Gli enum si possono confrontare in modo sicuro con ==
		if (direzione == this.direzioneBloccata && !this.hasAttrezzo(this.attrezzoSbloccante)) {
			// Restituiamo noi stessi: il giocatore non avanza!
			return this;
		}
		
		// In tutti gli altri casi ci pensa il padre
		return super.getStanzaAdiacente(direzione);
	}

	@Override
	public String getDescrizione() {
		// Aggiungiamo un avviso alla normale descrizione della stanza
		// Usiamo .name() per stampare il nome della direzione
		String avviso = "Attenzione: la direzione " + this.direzioneBloccata.name() + " è bloccata!\n" +
						"Posa un '" + this.attrezzoSbloccante + "' nella stanza per passare.\n";
		
		return avviso + super.getDescrizione();
	}
}
