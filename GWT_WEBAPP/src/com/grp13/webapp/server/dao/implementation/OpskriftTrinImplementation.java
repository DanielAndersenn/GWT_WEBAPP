package com.grp13.webapp.server.dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.grp13.webapp.server.dao.interfaces.OpskriftTrinInterface;
import com.grp13.webapp.shared.DALException;
import com.grp13.webapp.shared.opskriftTrinDTO;

public class OpskriftTrinImplementation implements OpskriftTrinInterface{
	private Connection conn;
	private Statement stmt;
	private List<opskriftTrinDTO> otList;

	int returnMsg = 0;
	String SQLMsg = "";
	int SQLErr = 0;

	public OpskriftTrinImplementation(Connection conn) {
		this.conn = conn;
	}
	@Override
	public List<opskriftTrinDTO> getRecipeStepList() throws DALException {
		System.out.println("getRecipeStepList START");
		otList = new ArrayList<opskriftTrinDTO>();
		String sql = "SELECT * FROM opskrift_trin";
		try{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				otList.add(new opskriftTrinDTO(rs.getInt("opskrift_id"), rs.getDouble("maengde"), rs.getInt("tid"), rs.getInt("trin"), 
						rs.getString("ingrediens"), rs.getString("enhed"), rs.getString("handling"), rs.getBoolean("vaegt")));
			}
		}catch(SQLException e){e.printStackTrace();}
		return otList;
	}

	@Override
	public void createRecipeStep(opskriftTrinDTO opskriftTrin) throws DALException {
		System.out.println("createRecipeStep START");
		CallableStatement stmt = null;
		String cmd = "CALL InsertOpskriftTrin(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			stmt = conn.prepareCall(cmd);

			stmt.setInt(1, opskriftTrin.getOpskriftID());
			stmt.setDouble(2, opskriftTrin.getMaengde());
			stmt.setInt(3, opskriftTrin.getTid());
			stmt.setInt(4, opskriftTrin.getTrin());
			stmt.setString(5, opskriftTrin.getIngrediens());
			stmt.setString(6, opskriftTrin.getEnhed());
			stmt.setString(7, opskriftTrin.getHandling());
			stmt.setBoolean(8, opskriftTrin.isVaegt());
			stmt.registerOutParameter(9, java.sql.Types.INTEGER);
			stmt.registerOutParameter(10, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(11, java.sql.Types.SMALLINT);

			stmt.executeUpdate();

			returnMsg = stmt.getInt(9);
			SQLMsg = stmt.getString(10);
			SQLErr = stmt.getInt(11);

			System.out.println("var returnMsg: " + returnMsg + " var SQLMsg: " + SQLMsg + " var SQLErr: " + SQLErr);
			System.out.println("createRecipeStep END");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("createRecipeStep FAIL");
		}

	}

	@Override
	public void updateRecipeStep(opskriftTrinDTO opskriftTrin) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRecipeStep(int a) throws DALException {
		System.out.println("deleteRecipeStep START");
		CallableStatement stmt = null;
		String cmd = "CALL DeleteOpskriftTrin(?, ?, ?, ?)";

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
			System.out.println("deleteOpskriftTrin END");
		} catch (SQLException e) {
			System.out.println("deleteOpskriftTrin FAIL");
			throw new DALException("deleteOpskriftTrin fejlede !!!");
		}

		if(returnMsg != 1) throw new DALException(SQLMsg);

	}
}
