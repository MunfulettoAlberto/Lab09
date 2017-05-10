package it.polito.tdp.metrodeparis.model;

import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.graph.WeightedMultigraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metrodeparis.dao.MetroDAO;

public class Model {
	
	
	private WeightedMultigraph<Fermata, Collegamento> graph ;
	public Model() {
		super();
		graph = new WeightedMultigraph<Fermata, Collegamento>(Collegamento.class);
	}
	
	private MetroDAO dao = new MetroDAO();
	private Map<Integer, Fermata> fermate = new TreeMap<Integer, Fermata>();
	private List<Collegamento> collegamenti = new ArrayList< Collegamento> () ;
	
	public Map<Integer, Fermata> createGraph (){
		
		Graphs.addAllVertices(graph, dao.getAllFermate().values()) ;
		fermate = dao.getAllFermate();
		
		for(Collegamento c : dao.getAllCollegamenti()){
	
			if(graph.containsVertex(c.getStazP() )&& (graph.containsVertex(c.getStazA()))){
				
				Fermata f1 = null ;
				Fermata f2 = null ;
				
				for(Fermata f : graph.vertexSet()){
					if(f.equals(c.getStazP())){
						f1 = f ;
					}
					if(f.equals(c.getStazA())){
						f2 = f ;
					}
				}
				System.out.println(f1);
				System.out.println(f2);
				Collegamento c1 = graph.addEdge(f1, f2) ;
				System.out.println(c1);
				
				graph.setEdgeWeight(c1, this.setWeight(f1.getCoords(), f2.getCoords(), c.getIdLinea())) ;
				System.out.println(this.setWeight(f1.getCoords(), f2.getCoords(), c.getIdLinea()));
			}
		}
		collegamenti.addAll(dao.getAllCollegamenti()) ;
		
		
		
		return fermate ;
	}
	
	private double setWeight(LatLng a, LatLng b, int id_linea){
		double time = 0.0;
		double distance = 0.0;
		distance = LatLngTool.distance(a, b, LengthUnit.KILOMETER);
		
		time= distance*dao.getSpeedFromLine(id_linea);
	
		return time ;
	}


}
