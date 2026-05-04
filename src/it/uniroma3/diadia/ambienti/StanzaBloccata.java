package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	private String direzioneBloccata;
    private String attrezzoSbloccante;

    public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
        super(nome);
        this.direzioneBloccata = direzioneBloccata;
        this.attrezzoSbloccante = attrezzoSbloccante;
    }

    @Override
    public Stanza getStanzaAdiacente(String direzione) {
        // Se si va verso la direzione bloccata E l'attrezzo sbloccante NON c'è
        if (direzione.equals(this.direzioneBloccata) && !this.hasAttrezzo(this.attrezzoSbloccante)) {
            // Restituiamo noi stessi: il giocatore non avanza!
            return this;
        }
        
        // In tutti gli altri casi (direzione libera o attrezzo presente), ci pensa il padre
        return super.getStanzaAdiacente(direzione);
    }

    @Override
    public String getDescrizione() {
        // Aggiungiamo un avviso alla normale descrizione della stanza
        String avviso = "Attenzione: la direzione " + this.direzioneBloccata + " è bloccata!\n" +
                        "Posa un '" + this.attrezzoSbloccante + "' nella stanza per passare.\n";
        
        return avviso + super.getDescrizione();
    }
}
