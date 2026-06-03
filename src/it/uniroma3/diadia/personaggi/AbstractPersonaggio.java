package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractPersonaggio {

	private String nome;
	private String presentazione;
	private boolean haSalutato;

	public AbstractPersonaggio(String nome,String presentazione) {

		this.nome= nome;
		this.presentazione = presentazione;
		this.haSalutato = false;

	}

	public String getNome() {
		return this.getNome();
	}
	public abstract String agisci(Partita partita);

	public String saluta() {
		
		StringBuilder risposta = new StringBuilder("Ciao io sono ");
		risposta.append(this.getNome() +  ". ");
		
		if(!this.haSalutato)
			risposta.append(this.presentazione);
		else
			risposta.append("già ci conosciamo!");
		
		this.haSalutato = true;
		return risposta.toString();
		
		
	}
	@Override
	public String toString() {
		return this.getNome();
	}
}
