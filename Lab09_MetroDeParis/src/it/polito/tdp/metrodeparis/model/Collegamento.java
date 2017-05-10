package it.polito.tdp.metrodeparis.model;

import org.jgrapht.graph.DefaultWeightedEdge;

public class Collegamento extends DefaultWeightedEdge {

	private int idConnessione ; 
	private int idLinea ;
	private Fermata stazP ;
	private Fermata stazA ;
	
	public Collegamento(int idConnessione, Fermata a, Fermata b, int idLinea) {
		super();
		this.idConnessione = idConnessione;
		this.idLinea = idLinea;
		this.stazP = a ;
		this.stazA = b ;
	}

	public Collegamento(){
		
	}
	
	public int getIdConnessione() {
		return idConnessione;
	}

	public void setIdConnessione(int idConnessione) {
		this.idConnessione = idConnessione;
	}

	public int getIdLinea() {
		return idLinea;
	}

	public void setIdLinea(int idLinea) {
		this.idLinea = idLinea;
	}


	public Fermata getStazP() {
		return stazP;
	}

	public void setStazP(Fermata stazP) {
		this.stazP = stazP;
	}

	public Fermata getStazA() {
		return stazA;
	}

	public void setStazA(Fermata stazA) {
		this.stazA = stazA;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idConnessione;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collegamento other = (Collegamento) obj;
		if (idConnessione != other.idConnessione)
			return false;
		return true;
	}
	
	private double setWeight(int idstaz1, int idstaz2){
		double time = 0.0;
		
		
		return time ; 
	}
	
}
