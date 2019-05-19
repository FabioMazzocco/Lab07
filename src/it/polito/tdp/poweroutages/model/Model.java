package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;
import it.polito.tdp.poweroutages.model.Solution;

public class Model {
	PowerOutageDAO dao;
	List<Nerc> listaNerc;
	HashSet<PowerOutage> list;
	Solution best;
	int best_points;
	int X;
	int Y;
	int nerc_id;
	
	public Model() {
		dao = new PowerOutageDAO();
		listaNerc = new ArrayList<Nerc>();
		list = new HashSet<PowerOutage>();
		best = null;
		best_points = 0;
		X = 0;
		Y = 0;
	}
	
	public String findSolution(String a, String b, String nercName) {
		//CONTROLLO CHE I DUE PARAMETRI PASSATI COME PARAMETRI SIANO NUMERI VALIDI
		if(!correct(a,b))
			return "Errore nell'inserimento dei valori numerici";
		X = Integer.parseInt(a);
		Y = Integer.parseInt(b);
		Nerc nerc = getNerc(nercName);
		if(nerc == null)
			return "Nessun NERC con questo nome trovato";
		nerc_id = nerc.getId();
		list.addAll(dao.getOutages(nerc));
		
		//QUI PARTE L'ALGORITMO RICORSIVO
		Solution partial = new Solution();
		recursive(1, partial, new HashSet<PowerOutage>(list));
		
		//QUI SI ELABORA IL RISULTATO CHE DEV'ESSERE STAMPATO
		if(best == null)
			return "Nessun risultato trovato";
		String result = best.toString();
		return result;
	}
	
	public void recursive(int level, Solution partial, HashSet<PowerOutage> list) {
		System.out.println("Siamo al livello "+level);
		//CONDIZIONI DI TERMINAZIONE
		if(list.size()==0 || partial.isOverY(Y)){
			System.out.println("CONDIZIONI DI TERMINAZIONE");
//			System.out.println("prima condizione, size = "+list.size());
//			System.out.println("Seconda condizione, è "+partial.isOverY(Y));
			controllaSoluzione(partial);
			return;
		}
		
		//RICORSIONE
		for(PowerOutage p : list) {
//			System.out.println(partial.toString());
			if(!partial.isOverX(p, X))
				continue;
			partial.add(p);
			System.out.println("Aggiunto PO: "+p.toString());
			HashSet<PowerOutage> rimanenti = new HashSet<PowerOutage>(list);
			rimanenti.remove(p);
			recursive(level+1, partial, rimanenti);
			partial.remove(p);
			rimanenti.add(p);
		}
	}
	
	private void controllaSoluzione(Solution partial) {
		if(partial.getPoints() > this.best_points) {
			best = partial.clone();
		}
	}

	public boolean correct(String a, String b) {
		try {
			int aa = Integer.parseInt(a);
			int bb = Integer.parseInt(b);
			if(aa <= 0 || bb <= 0)
				return false;
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public List<String> getAllNercAreas(){
		listaNerc.addAll(dao.getNercList());
		List<String> lista = new ArrayList<String>();
		for(Nerc n : listaNerc) {
			lista.add(n.getValue());
		}
		return lista;
	}
	
	public Nerc getNerc(String name) {
		for(Nerc n : listaNerc) {
			if(n.getValue().compareTo(name)==0)
				return n;
		}
		return null;
	}
}
