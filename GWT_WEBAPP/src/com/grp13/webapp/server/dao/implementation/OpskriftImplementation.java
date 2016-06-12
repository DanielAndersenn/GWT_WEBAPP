package com.grp13.webapp.server.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.grp13.webapp.server.dao.interfaces.opskriftInterface;
import com.grp13.webapp.shared.DALException;
import com.grp13.webapp.shared.opskriftDTO;

public class OpskriftImplementation implements opskriftInterface{
	private Connection conn;
	private Statement stmt;
	private List<opskriftDTO> oList;

	int returnMsg = 0;
	String SQLMsg = "";
	int SQLErr = 0;

	public OpskriftImplementation(Connection conn) {
		this.conn = conn;
	}
	@Override
	public List<opskriftDTO> getRecipeList() throws DALException {
		System.out.println("getRecipeList START");
		oList = new ArrayList<opskriftDTO>();
		String sql = "SELECT * FROM opskrift";
		try{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				oList.add(new opskriftDTO(rs.getInt("opskrift_id"), rs.getString("navn"), rs.getString("ingredienser"), rs.getString("beskrivelse"), rs.getInt("user_ID")));
			}
		}catch(SQLException e){e.printStackTrace();}
		return oList;
	}

	@Override
	public void createRecipe(opskriftDTO opskrift) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRecipe(opskriftDTO opskrift) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRecipe(opskriftDTO opskrift) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
