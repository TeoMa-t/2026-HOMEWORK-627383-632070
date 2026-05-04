package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza{
	final static private int SOGLIA_MAGICA_DEFAULT = 3;
    private int contatoreAttrezziPosati;
    private int sogliaMagica;
    
    public StanzaMagica(String nome) {
        this(nome, SOGLIA_MAGICA_DEFAULT);
    }
    
    public StanzaMagica(String nome, int soglia) {
        super(nome);
        this.contatoreAttrezziPosati = 0;
        this.sogliaMagica = soglia;  
    }
    
    @Override
    public boolean addAttrezzo(Attrezzo attrezzo) {
        this.contatoreAttrezziPosati++;
        
        // Se abbiamo superato la soglia magica, modifichiamo l'attrezzo
        if (this.contatoreAttrezziPosati > this.sogliaMagica) {
            attrezzo = this.modificaAttrezzo(attrezzo);
        }
        
        // Alla fine, usiamo il metodo originale della classe padre per inserire l'oggetto
        return super.addAttrezzo(attrezzo);
    }

    private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
        // Magia 1: Invertiamo il nome (usiamo StringBuilder per comodità)
        StringBuilder nomeInvertito = new StringBuilder(attrezzo.getNome());
        nomeInvertito = nomeInvertito.reverse();
        
        // Magia 2: Raddoppiamo il peso
        int pesoRaddoppiato = attrezzo.getPeso() * 2;
        
        // Creiamo e restituiamo il nuovo attrezzo magico
        return new Attrezzo(nomeInvertito.toString(), pesoRaddoppiato);
    }
}
