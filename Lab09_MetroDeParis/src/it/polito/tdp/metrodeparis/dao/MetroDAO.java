package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.metrodeparis.model.Collegamento;
import it.polito.tdp.metrodeparis.model.Fermata;

public class MetroDAO {

	public Map<Integer, Fermata> getAllFermate() {

		final String sql = "SELECT id_fermata, nome, coordx, coordy FROM fermata ORDER BY nome ASC";
		Map<Integer, Fermata> fermate = new TreeMap<Integer, Fermata>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Fermata f = new Fermata(rs.getInt("id_Fermata"), rs.getString("nome"), new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy")));
				fermate.put(rs.getInt("id_Fermata"), f);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return fermate;
	}
	
	
	public List<Collegamento> getAllCollegamenti() {
		
		final String sql = " SELECT id_connessione, id_linea , id_stazP , id_stazA FROM connessione";
		List<Collegamento> collegamenti = new ArrayList<Collegamento>() ;
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Fermata a = new Fermata(rs.getInt("id_stazP"));
				Fermata b = new Fermata(rs.getInt("id_stazA"));
				Collegamento c = new Collegamento(rs.getInt("id_connessione"), a, b, rs.getInt("id_linea")) ;
				collegamenti.add(c);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}
		
		return collegamenti;
		
	}
	
	public int getSpeedFromLine(int id_linea){
		final String sql = "SELECT velocita FROM linea WHERE id_linea = ? ;";
		int speed = 0;
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id_linea);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				speed = rs.getInt("velocita") ;
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}
		
		return speed ;
		
	}
}
