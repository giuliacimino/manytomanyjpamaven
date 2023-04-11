package it.manytomanyjpamaven.test;

import java.time.LocalDate;
import java.util.List;

import it.manytomanyjpamaven.dao.EntityManagerUtil;
import it.manytomanyjpamaven.model.Ruolo;
import it.manytomanyjpamaven.model.StatoUtente;
import it.manytomanyjpamaven.model.Utente;
import it.manytomanyjpamaven.service.MyServiceFactory;
import it.manytomanyjpamaven.service.RuoloService;
import it.manytomanyjpamaven.service.UtenteService;

public class ManyToManyTest {

	public static void main(String[] args) {
		UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();
		RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();

		// ora passo alle operazioni CRUD
		try {

			// inizializzo i ruoli sul db
			initRuoli(ruoloServiceInstance);

//			System.out.println("In tabella Utente ci sono " + utenteServiceInstance.listAll().size() + " elementi.");
//
//			testInserisciNuovoUtente(utenteServiceInstance);
//			System.out.println("In tabella Utente ci sono " + utenteServiceInstance.listAll().size() + " elementi.");
			
//			testAggiornaUtente(utenteServiceInstance);
//			System.out.println("In tabella Utente ci sono " + utenteServiceInstance.listAll().size() + " elementi.");
			
//			testRimuoviUtente(utenteServiceInstance);
//			System.out.println("In tabella Utente ci sono " + utenteServiceInstance.listAll().size() + " elementi.");

//			
//			System.out.println("in tabella Ruolo ci sono " +ruoloServiceInstance.listAll().size() + " elementi.");
			
			
//			testCollegaUtenteARuoloEsistente(ruoloServiceInstance, utenteServiceInstance);
//			System.out.println("In tabella Utente ci sono " + utenteServiceInstance.listAll().size() + " elementi.");
//
//			testModificaStatoUtente(utenteServiceInstance);
//			System.out.println("In tabella Utente ci sono " + utenteServiceInstance.listAll().size() + " elementi.");
//
//			testRimuoviRuoloDaUtente(ruoloServiceInstance, utenteServiceInstance);
//			System.out.println("In tabella Utente ci sono " + utenteServiceInstance.listAll().size() + " elementi.");
//			
			testDeleteRuolo(ruoloServiceInstance);
			System.out.println("In tabella Ruolo ci sono " + ruoloServiceInstance.listAll().size() + " elementi.");
			
//			testTrovaUtentiCreatiInMeseEAnno(utenteServiceInstance);
//			System.out.println("In tabella Utente ci sono " + utenteServiceInstance.listAll().size() + " elementi.");
			
//			testContaUtentiConRuoloAdmin(utenteServiceInstance);
//			System.out.println("In tabella Utente ci sono " + utenteServiceInstance.listAll().size() + " elementi.");
			
//			testCercaDistinctDescrizioniRuoloConUtente(ruoloServiceInstance);
//			System.out.println("In tabella Ruolo ci sono " + ruoloServiceInstance.listAll().size() + " elementi.");
			
//			testAggiornaRuolo(ruoloServiceInstance);
//			System.out.println("In tabella Ruolo ci sono " + ruoloServiceInstance.listAll().size() + " elementi.");
			
//			testInserisciRuolo(ruoloServiceInstance);
//			System.out.println("In tabella Ruolo ci sono " + ruoloServiceInstance.listAll().size() + " elementi.");
			


			

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}

	private static void initRuoli(RuoloService ruoloServiceInstance) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Classic User", "ROLE_CLASSIC_USER"));
		}
	}

	private static void testInserisciNuovoUtente(UtenteService utenteServiceInstance) throws Exception {
		System.out.println(".......testInserisciNuovoUtente inizio.............");

		Utente utenteNuovo = new Utente("pippo.rossi", "xxx", "pippo", "rossi", LocalDate.now());
		utenteServiceInstance.inserisciNuovo(utenteNuovo);
		if (utenteNuovo.getId() == null)
			throw new RuntimeException("testInserisciNuovoUtente fallito ");

		System.out.println(".......testInserisciNuovoUtente fine: PASSED.............");
	}
	
	//
	private static void testAggiornaUtente (UtenteService utenteServiceInstance) throws Exception{
		System.out.println(".......testAggiornaUtente inizio.............");
		List<Utente> listaUtenti = utenteServiceInstance.listAll();
		if (listaUtenti.size() < 1)
			throw new RuntimeException("errore: non sono presenti utenti sul db.");
		Utente utenteDaAggiornare = listaUtenti.get(0);
		String nuovoNome = "Pluto";
		utenteDaAggiornare.setNome(nuovoNome);
		utenteServiceInstance.aggiorna(utenteDaAggiornare);
		System.out.println(utenteDaAggiornare);
		System.out.println(".......testAggiornaUtente fine.......");
	}
	
	//
	private static void testRimuoviUtente (UtenteService utenteServiceInstance) throws Exception{
		System.out.println(".......testRimuoviUtente inizio.............");
		List<Utente> listaUtenti = utenteServiceInstance.listAll();
		if (listaUtenti.size() < 1) {
			throw new RuntimeException("non sono presenti utenti sul db");
		}
		utenteServiceInstance.rimuovi(listaUtenti.get(0).getId());
		List<Utente> listaUtentiDopoRimozione = utenteServiceInstance.listAll();
		if (listaUtenti.size() == listaUtentiDopoRimozione.size()) {
			throw new RuntimeException("test fallito: utente non rimosso");

		}
		System.out.println(".......testRimuoviUtente fine.............");
	}
	
	//
	private static void testAggiornaRuolo (RuoloService ruoloServiceInstance) throws Exception{
		System.out.println(".......testAggiornaRuolo inizio.............");
		List<Ruolo> listaRuoli = ruoloServiceInstance.listAll();
		if (listaRuoli.size() < 1)
			throw new RuntimeException("errore: non sono presenti ruolo sul db.");
		Ruolo ruoloDaAggiornare = listaRuoli.get(0);
		String nuovaDescrizione = "Data manager";
		ruoloDaAggiornare.setDescrizione(nuovaDescrizione);
		ruoloServiceInstance.aggiorna(ruoloDaAggiornare);
		System.out.println(ruoloDaAggiornare);
		System.out.println(".......testAggiornaRuolo fine.......");

	}
	
	//
	private static void testInserisciRuolo (RuoloService ruoloServiceInstance) throws Exception{
			System.out.println(".......testInserisciRuolo inizio.............");

			Ruolo ruoloNuovo = new Ruolo("GGG", "ggg");
			ruoloServiceInstance.inserisciNuovo(ruoloNuovo);
			if (ruoloNuovo.getId() == null)
				throw new RuntimeException("testInserisciNuovoUtente fallito ");

			System.out.println(".......testInserisciRuolo fine: PASSED.............");
	}

	//
	private static void testDeleteRuolo (RuoloService ruoloServiceInstance) throws Exception{
		System.out.println(".........testDeleteRuolo inizio........");
		List<Ruolo> listaRuoli = ruoloServiceInstance.listAll();
		if (listaRuoli.size() < 1)
			throw new RuntimeException("errore: non sono presenti ruoli  sul db.");

		// inserisco un atleta per poi eliminarlo
		Ruolo nuovoRuolo = new Ruolo("ppp", "PPP");
		ruoloServiceInstance.inserisciNuovo(nuovoRuolo);
		if (nuovoRuolo.getId() == null) {
			throw new RuntimeException("errore: ruolo non inserito.");
		}
		ruoloServiceInstance.rimuovi(nuovoRuolo.getId());
		List<Ruolo> listaRuoliDopoRimozione = ruoloServiceInstance.listAll();
		if (listaRuoli.size() != listaRuoliDopoRimozione.size())
			throw new RuntimeException("errore: ruolo non rimosso.");
		System.out.println(".........testDeleteRuolo fine........");
	}

	
	//
	private static void testCollegaUtenteARuoloEsistente(RuoloService ruoloServiceInstance,
			UtenteService utenteServiceInstance) throws Exception {
		System.out.println(".......testCollegaUtenteARuoloEsistente inizio.............");

		Ruolo ruoloEsistenteSuDb = ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN");
		if (ruoloEsistenteSuDb == null)
			throw new RuntimeException("testCollegaUtenteARuoloEsistente fallito: ruolo inesistente ");

		// mi creo un utente inserendolo direttamente su db
		Utente utenteNuovo = new Utente("mario.bianchi", "JJJ", "mario", "bianchi", LocalDate.now());
		utenteServiceInstance.inserisciNuovo(utenteNuovo);
		if (utenteNuovo.getId() == null)
			throw new RuntimeException("testInserisciNuovoUtente fallito: utente non inserito ");

		utenteServiceInstance.aggiungiRuolo(utenteNuovo, ruoloEsistenteSuDb);
		// per fare il test ricarico interamente l'oggetto e la relazione
		Utente utenteReloaded = utenteServiceInstance.caricaUtenteSingoloConRuoli(utenteNuovo.getId());
		if (utenteReloaded.getRuoli().size() != 1)
			throw new RuntimeException("testInserisciNuovoUtente fallito: ruoli non aggiunti ");

		System.out.println(".......testCollegaUtenteARuoloEsistente fine: PASSED.............");
	}
	
	
	//
	private static void testModificaStatoUtente(UtenteService utenteServiceInstance) throws Exception {
		System.out.println(".......testModificaStatoUtente inizio.............");

		// mi creo un utente inserendolo direttamente su db
		Utente utenteNuovo = new Utente("mario1.bianchi1", "JJJ", "mario1", "bianchi1", LocalDate.now());
		utenteServiceInstance.inserisciNuovo(utenteNuovo);
		if (utenteNuovo.getId() == null)
			throw new RuntimeException("testModificaStatoUtente fallito: utente non inserito ");

		// proviamo a passarlo nello stato ATTIVO ma salviamoci il vecchio stato
		StatoUtente vecchioStato = utenteNuovo.getStato();
		utenteNuovo.setStato(StatoUtente.ATTIVO);
		utenteServiceInstance.aggiorna(utenteNuovo);

		if (utenteNuovo.getStato().equals(vecchioStato))
			throw new RuntimeException("testModificaStatoUtente fallito: modifica non avvenuta correttamente ");

		System.out.println(".......testModificaStatoUtente fine: PASSED.............");
	}
	
	
	//
	private static void testRimuoviRuoloDaUtente(RuoloService ruoloServiceInstance, UtenteService utenteServiceInstance)
			throws Exception {
		System.out.println(".......testRimuoviRuoloDaUtente inizio.............");

		// carico un ruolo e lo associo ad un nuovo utente
		Ruolo ruoloEsistenteSuDb = ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN");
		if (ruoloEsistenteSuDb == null)
			throw new RuntimeException("testRimuoviRuoloDaUtente fallito: ruolo inesistente ");

		// mi creo un utente inserendolo direttamente su db
		Utente utenteNuovo = new Utente("aldo.manuzzi", "pwd@2", "aldo", "manuzzi", LocalDate.now());
		utenteServiceInstance.inserisciNuovo(utenteNuovo);
		if (utenteNuovo.getId() == null)
			throw new RuntimeException("testRimuoviRuoloDaUtente fallito: utente non inserito ");
		utenteServiceInstance.aggiungiRuolo(utenteNuovo, ruoloEsistenteSuDb);

		// ora ricarico il record e provo a disassociare il ruolo
		Utente utenteReloaded = utenteServiceInstance.caricaUtenteSingoloConRuoli(utenteNuovo.getId());
		boolean confermoRuoloPresente = false;
		for (Ruolo ruoloItem : utenteReloaded.getRuoli()) {
			if (ruoloItem.getCodice().equals(ruoloEsistenteSuDb.getCodice())) {
				confermoRuoloPresente = true;
				break;
			}
		}

		if (!confermoRuoloPresente)
			throw new RuntimeException("testRimuoviRuoloDaUtente fallito: utente e ruolo non associati ");

		// ora provo la rimozione vera e propria ma poi forzo il caricamento per fare un
		// confronto 'pulito'
		utenteServiceInstance.rimuoviRuoloDaUtente(utenteReloaded.getId(), ruoloEsistenteSuDb.getId());
		utenteReloaded = utenteServiceInstance.caricaUtenteSingoloConRuoli(utenteNuovo.getId());
		if (!utenteReloaded.getRuoli().isEmpty())
			throw new RuntimeException("testRimuoviRuoloDaUtente fallito: ruolo ancora associato ");

		System.out.println(".......testRimuoviRuoloDaUtente fine: PASSED.............");
	}
	

	
	//
	private static void testTrovaUtentiCreatiInMeseEAnno (UtenteService utenteServiceInstance) throws Exception{
		System.out.println(".......testTrovaUtentiCreatiInMeseEAnno inizio.............");
		List<Utente> listaUtenti= utenteServiceInstance.listAll();
		if(listaUtenti.size()<1) {
			throw new RuntimeException("errore: non sono presenti utenti sul db");
		}
		List<Utente> utentiCreatiInMeseEAnno= utenteServiceInstance.trovaUtentiCreatiInMeseEAnno(01, 2021);
		if (utentiCreatiInMeseEAnno.size()<1) {
			throw new RuntimeException("errore: non sono presenti utenti creati in mese e anno.");			
		}
		System.out.println(utentiCreatiInMeseEAnno);
		System.out.println(".......testTrovaUtentiCreatiInMeseEAnno fine.............");
	}
	
	//
	private static void testContaUtentiConRuoloAdmin (UtenteService utenteServiceInstance) throws Exception {
		System.out.println(".......testContaUtentiConRuoloAdmin inizio.............");
		List<Utente> listaUtenti= utenteServiceInstance.listAll();
		if(listaUtenti.size()<1) {
			throw new RuntimeException("errore: non sono presenti utenti sul db");
		}
		Long numeroUtentiAdmin= utenteServiceInstance.contaUtentiConRuoloAdmin();
		System.out.println(numeroUtentiAdmin);
		System.out.println(".......testContaUtentiConRuoloAdmin fine.............");

	}
	
	//
	private static void testCercaDistinctDescrizioniRuoloConUtente (RuoloService ruoloServiceInstance) throws Exception{
				System.out.println(".......testCercaDistinctDescrizioniRuoloConUtente inizio.............");
				List<Ruolo> listaRuoli = ruoloServiceInstance.listAll();
				if (listaRuoli.size() < 1) {
					throw new RuntimeException("errore: non ci sono ruoli presenti in lista");
				}
				List<String> listaDescrizioni = ruoloServiceInstance.cercaDistinctDescrizioniRuoloConUtente();
				System.out.println(listaDescrizioni);
				System.out.println(".......testCercaDistinctDescrizioniRuoloConUtente fine.............");
	}

}
