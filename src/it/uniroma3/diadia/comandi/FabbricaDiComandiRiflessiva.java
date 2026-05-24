package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

    public Comando costruisciComando(String istruzione) throws Exception {
        Scanner scannerDiParole = new Scanner(istruzione); // es. 'vai sud'
        String nomeComando = null; // es. 'vai'
        String parametro = null;   // es. 'sud'
        Comando comando = null;

        if (scannerDiParole.hasNext())
            nomeComando = scannerDiParole.next(); // prima parola: nome del comando
        if (scannerDiParole.hasNext())
            parametro = scannerDiParole.next();   // seconda parola: eventuale parametro
            
        // Se non è stato inserito nulla, potresti gestire l'errore o restituire un comando vuoto/non valido
        if (nomeComando == null) return new ComandoNonValido(); // Aggiunto per sicurezza

        StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
        // Mette la prima lettera in maiuscolo (es. 'v' -> 'V')
        nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
        // Aggiunge il resto della parola (es. "ai" -> "Vai")
        nomeClasse.append(nomeComando.substring(1));	
        
        // es. nomeClasse ora è: 'it.uniroma3.diadia.comandi.ComandoVai'

        // IL CUORE DELLA RIFLESSIONE
        comando = (Comando) Class.forName(nomeClasse.toString()).newInstance();
        
        comando.setParametro(parametro);
        return comando;
    }
}