package sr.unasat.week6.jdbc.dataaccess;

import sr.unasat.week6.jdbc.connection.DBConnection;
import sr.unasat.week6.jdbc.entities.Vak;

import java.sql.*;
import java.util.ArrayList;

public class VakDatabaseAccess {

	DBConnection dbConnection = new DBConnection();


	public ArrayList<Vak> selectAll(){

		ArrayList<Vak> vakken = new ArrayList<>();
		Statement statement = null; 
		ResultSet resultSet = null;

		try {
			//stap 1
			Connection	connect = dbConnection.getConnection();
			// Construeer een statement voor het uitvoeren van een SQL Query
			//Stap 2
			statement = connect.createStatement();
			// Voer de SQL statement uit en verzamel de output in de resultset
			resultSet = statement.executeQuery("select * from vak");

			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				String naam = resultSet.getString("naam");

				// Maak een vak instantie en print deze instantie
				Vak vak = new Vak();
				vak.setId(id);
				vak.setNaam(naam);
				vakken.add(vak);
				System.out.println(vak);
			}
		} catch (SQLException e) {
			System.out.println("Er is een SQL fout ontstaan!");
		}
		return vakken;
	}

	public Vak selectOne(int recordId){

		PreparedStatement preparedStatement  = null;
		ResultSet resultSet = null;
		Vak vak = new Vak();

		try {
			Connection	connect = dbConnection.getConnection();
			// Statements allow to issue SQL queries to the database
			preparedStatement = connect.prepareStatement("select * from vak where id = ? ");
			// Vul de id in de preparedStatement
			preparedStatement.setInt(1, recordId);
			// Voer de statement uit en haal het resultaat op
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				String naam = resultSet.getString("naam");

				// Maak een vak instantie en print deze instantie
				vak.setId(id);
				vak.setNaam(naam);

				System.out.println(vak);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vak;
	}

	public void update(Vak vak){
		PreparedStatement preparedStatement  = null;

		try {
			Connection	connect = dbConnection.getConnection();
			preparedStatement = connect.prepareStatement("update vak set naam = ? where id = ?");
			preparedStatement.setString(1, vak.getNaam());
			preparedStatement.setInt(2, vak.getId());

			// Voer de statement uit en haal het resultaat op
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int delete(int recordId){

		PreparedStatement preparedStatement  = null;
		int result = 0;

		try {
			Connection	connect = dbConnection.getConnection();
			preparedStatement = connect.prepareStatement("delete from vak where id = ?");
			preparedStatement.setInt(1, recordId);

			// Voer de statement uit en haal het resultaat op
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Er is een SQL fout tijdens deleten van een record!");
			e.printStackTrace();
		}

		return result;
	}

	public int insert(Vak vak){

		PreparedStatement preparedStatement  = null;
		int result = 0;

		try {
			Connection	connect = dbConnection.getConnection();
			preparedStatement = connect.prepareStatement("insert into vak values (NULL, ?)");
			preparedStatement.setString(1, vak.getNaam());
			// Voer de statement uit en haal het resultaat op
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Er is een SQL fout tijdens het inserten van een nieuwe record!");
			e.printStackTrace();
		}

		return result;
	}

}
