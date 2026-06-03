package it.uniroma3.diadia.comandi;

import java.util.Set;
import java.util.TreeSet;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {

	private String parametro;
	private static Set<String> nomiComandi = new TreeSet<>();

	public AbstractComando() {
		String nomeClasse = this.getClass().getSimpleName();
		if(nomeClasse.startsWith("Comando")) {
			String nomeComando = nomeClasse.substring(7).toLowerCase();
			nomiComandi.add(nomeComando);
		}
	}

	public static Set<String> getNomiComandi(){
		return nomiComandi;
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	@Override
	public String getParametro() {
		return this.parametro;
	}
	@Override
	public abstract void esegui(Partita partita, IO io);

	@Override
	public abstract String getNome();


}
