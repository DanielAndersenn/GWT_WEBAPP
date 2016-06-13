package com.grp13.webapp.server.dao.implementation;

import java.sql.CallableStatement;
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
		System.out.println("createRecipe START");
		CallableStatement stmt = null;
		String cmd = "CALL InsertOpskrift(?, ?, ?, ?, ?, ?, ?)";
		try {
			stmt = conn.prepareCall(cmd);

			stmt.setString(1, opskrift.getNavn());
			stmt.setString(2, opskrift.getIngredienser());
			stmt.setString(3, opskrift.getBeskrivelse());
			stmt.setLong(4, opskrift.getMedarbejderID());
			stmt.registerOutParameter(5, java.sql.Types.INTEGER);
			stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(7, java.sql.Types.SMALLINT);

			stmt.executeUpdate();

			returnMsg = stmt.getInt(5);
			SQLMsg = stmt.getString(6);
			SQLErr = stmt.getInt(7);

			System.out.println("var returnMsg: " + returnMsg + " var SQLMsg: " + SQLMsg + " var SQLErr: " + SQLErr);
			System.out.println("createRecipe END");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("createRecipe FAIL");
		}

	}

	@Override
	public void updateRecipe(opskriftDTO opskrift) throws DALException {
		System.out.println("updateOpskrift START");
		CallableStatement stmt = null;
		String cmd = "CALL UpdateUser(?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			stmt = conn.prepareCall(cmd);

			stmt.setInt(1, opskrift.getOpskriftID());
			stmt.setString(2, opskrift.getNavn());
			stmt.setString(3, opskrift.getIngredienser());
			stmt.setString(4, opskrift.getBeskrivelse());
			stmt.setLong(5, opskrift.getMedarbejderID());
			stmt.registerOutParameter(6, java.sql.Types.INTEGER);
			stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(8, java.sql.Types.SMALLINT);

			stmt.executeUpdate();

			returnMsg = stmt.getInt(6);
			SQLMsg = stmt.getString(7);
			SQLErr = stmt.getInt(8);

			System.out.println("var returnMsg: " + returnMsg + " var SQLMsg: " + SQLMsg + " var SQLErr: " + SQLErr);
			System.out.println("updateOpskrift END");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("updateOpskrift FAIL");
		}
	}

	@Override
	public void deleteRecipe(int a) throws DALException {
		System.out.println("deleteOpskrift START");
		CallableStatement stmt = null;
		String cmd = "CALL DeleteOpskrift(?, ?, ?, ?)";

		try {
			stmt = conn.prepareCall(cmd);

			stmt.setInt(1, a);
			stmt.registerOutParameter(2, java.sql.Types.INTEGER);
			stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(4, java.sql.Types.SMALLINT);

			stmt.executeUpdate();

			returnMsg = stmt.getInt(2);
			SQLMsg = stmt.getString(3);
			SQLErr = stmt.getInt(4);

			System.out.println("var returnMsg: " + returnMsg + " var SQLMsg: " + SQLMsg + " var SQLErr: " + SQLErr);
			System.out.println("deleteOpskrift END");
		} catch (SQLException e) {
			System.out.println("deleteOpskrift FAIL");
			throw new DALException("deleteOpskrift fejlede !!!");
		}


		if(returnMsg != 1) throw new DALException(SQLMsg);

	}
}
