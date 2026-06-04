package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirinto {
	
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             
	
	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    
	
	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";
	
	/* prefisso della riga contenente le specifiche degli attrezzi da collocare 
	 * nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";
	
	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza 
	 * nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";
	
	/*nuovi marker*/
	private static final String STANZE_BUIE_MARKER = "StanzeBuie:";
	private static final String STANZE_BLOCCATE_MARKER = "StanzeBloccate:";
	private static final String STANZE_MAGICHE_MARKER = "StanzeMagiche:";
	private static final String MAGHI_MARKER = "Maghi:";
	private static final String CANI_MARKER = "Cani:";
	private static final String STREGHE_MARKER = "Streghe:";

	private LineNumberReader reader;
	// Prima era: private LabirintoBuilder builder;
	private Labirinto.LabirintoBuilder builder; // Sostituisce la mappa nella bozza

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		// Prima era: this.builder = new LabirintoBuilder();
		this.builder = Labirinto.newBuilder();
	}

	public CaricatoreLabirinto(Reader reader) {
		this.reader = new LineNumberReader(reader);
		// Prima era: this.builder = new LabirintoBuilder();
		this.builder = Labirinto.newBuilder();
	}
	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeMagiche(); // Nuovi metodi per stanze derivate
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();

			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			
			// Personaggi vanno inseriti dopo che le stanze sono state create
			this.leggiECreaMaghi();
			this.leggiECreaCani();
			this.leggiECreaStreghe();

		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	public Labirinto getLabirinto() {
		return this.builder.getLabirinto();
	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga != null && riga.startsWith(marker), "era attesa una riga che cominciasse per " + marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.builder.addStanza(nomeStanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while (scannerDiParole.hasNext()) {
				result.add(scannerDiParole.next().trim());
			}
		}
		return result;
	}

	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER).trim();
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER).trim();
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		
		this.builder.getLabirinto().setStanzaIniziale(this.builder.getListaStanze().get(nomeStanzaIniziale));
		this.builder.getLabirinto().setStanzaVincente(this.builder.getListaStanze().get(nomeStanzaVincente));
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			if (specificaAttrezzo.isEmpty()) continue; 
			
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.builder.getListaStanze().get(nomeStanza).addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}

	private boolean isStanzaValida(String nomeStanza) {
		return this.builder.getListaStanze().containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificheUscite.replaceAll(",", " "))) {			
			while (scannerDiLinea.hasNext()) {
				String stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				String dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				String stanzaDestinazione = scannerDiLinea.next();
				
				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
		} 
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		
		// Nessuna conversione qui! Passiamo la stringa 'dir' grezza. Ci pensa il Builder!
		this.builder.addAdiacenza(stanzaDa, nomeA, dir);
	}

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}
	
	//NUOVI METODI
	
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String stanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String specifica : separaStringheAlleVirgole(stanzeBuie)) {
			if(specifica.isEmpty()) continue;
			try (Scanner scanner = new Scanner(specifica)) {
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza buia."));
				String nomeStanza = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("l'attrezzo per la luce in " + nomeStanza));
				String attrezzoLuce = scanner.next();

				this.builder.addStanzaBuia(nomeStanza, attrezzoLuce);
			}
		}
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String stanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String specifica : separaStringheAlleVirgole(stanzeBloccate)) {
			if(specifica.isEmpty()) continue;
			try (Scanner scanner = new Scanner(specifica)) {
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza bloccata."));
				String nomeStanza = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("la direzione bloccata in " + nomeStanza));
				String direzioneStr = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("l'attrezzo sbloccante in " + nomeStanza));
				String chiave = scanner.next();

				// Nessuna conversione qui! Passiamo la stringa 'direzioneStr' grezza. Ci pensa il Builder!
				this.builder.addStanzaBloccata(nomeStanza, direzioneStr, chiave);
			}
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String stanzeMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String specifica : separaStringheAlleVirgole(stanzeMagiche)) {
			if(specifica.isEmpty()) continue;
			try (Scanner scanner = new Scanner(specifica)) {
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza magica."));
				String nomeStanza = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("la soglia magica in " + nomeStanza));
				String sogliaStr = scanner.next();
				
				int soglia;
				try {
					soglia = Integer.parseInt(sogliaStr);
				} catch(NumberFormatException e) {
					 check(false, "Soglia non valida per " + nomeStanza);
					 soglia = 0; 
				}

				this.builder.addStanzaMagica(nomeStanza, soglia);
			}
		}
	}
	
	private void leggiECreaMaghi() throws FormatoFileNonValidoException {
		String maghi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);
		for(String specifica : separaStringheAlleVirgole(maghi)) {
			if(specifica.isEmpty()) continue;
			try (Scanner scanner = new Scanner(specifica)) {
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome del mago."));
				String nome = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("la presentazione del mago " + nome));
				String presentazione = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome dell'attrezzo del mago " + nome));
				String nomeAttrezzo = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("il peso dell'attrezzo del mago " + nome));
				String pesoStr = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("la stanza del mago " + nome));
				String stanza = scanner.next();

				int peso;
				try {
					peso = Integer.parseInt(pesoStr);
				} catch(NumberFormatException e) {
					check(false, "Peso attrezzo mago " + nome + " non valido");
					peso = 1;
				}
				this.builder.addMago(nome, presentazione, stanza, new Attrezzo(nomeAttrezzo, peso));
			}
		}
	}

	private void leggiECreaCani() throws FormatoFileNonValidoException {
		String cani = this.leggiRigaCheCominciaPer(CANI_MARKER);
		for(String specifica : separaStringheAlleVirgole(cani)) {
			if(specifica.isEmpty()) continue;
			try (Scanner scanner = new Scanner(specifica)) {
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome del cane."));
				String nome = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("la presentazione del cane " + nome));
				String presentazione = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("la stanza del cane " + nome));
				String stanza = scanner.next();

				this.builder.addCane(nome, presentazione, stanza);
			}
		}
	}

	private void leggiECreaStreghe() throws FormatoFileNonValidoException {
		String streghe = this.leggiRigaCheCominciaPer(STREGHE_MARKER);
		for(String specifica : separaStringheAlleVirgole(streghe)) {
			if(specifica.isEmpty()) continue;
			try (Scanner scanner = new Scanner(specifica)) {
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della strega."));
				String nome = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("la presentazione della strega " + nome));
				String presentazione = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("la stanza della strega " + nome));
				String stanza = scanner.next();

				this.builder.addStrega(nome, presentazione, stanza);
			}
		}
	}
}