package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;

public class PowerOutageDAO {

	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return nercList;
	}
	
	public Set<PowerOutage> getOutages(Nerc nerc){
		int nerc_id = nerc.getId();
		
		String sql = "SELECT * FROM poweroutages WHERE nerc_id= ?";
		
		Set<PowerOutage> outagesList = new HashSet<PowerOutage>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc_id);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutage temp = new PowerOutage(res.getInt("id"), res.getInt("event_type_id"), res.getInt("tag_id"), res.getInt("area_id"), res.getInt("nerc_id"), res.getInt("responsible_id"), res.getInt("customers_affected"), res.getTimestamp("date_event_began").toLocalDateTime(), res.getTimestamp("date_event_finished").toLocalDateTime(), res.getInt("demand_loss"));
				outagesList.add(temp);
			}
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return outagesList;
	}

}
