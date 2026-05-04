package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	private String attrezzoLuminoso;

    public StanzaBuia(String nome, String attrezzoLuminoso) {
        // Chiamiamo il costruttore della classe padre (Stanza)
        super(nome);
        this.attrezzoLuminoso = attrezzoLuminoso;
    }

    @Override
    public String getDescrizione() {
        // Se la stanza NON ha l'attrezzo luminoso, non vediamo nulla
        if (!this.hasAttrezzo(this.attrezzoLuminoso)) {
            return "qui c'è un buio pesto";
        }
        
        // Altrimenti, ci facciamo dare la descrizione normale dalla classe padre
        return super.getDescrizione();
    }
}
