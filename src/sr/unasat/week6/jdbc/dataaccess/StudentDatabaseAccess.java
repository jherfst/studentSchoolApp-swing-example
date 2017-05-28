package sr.unasat.week6.jdbc.dataaccess;

import sr.unasat.week6.jdbc.connection.DBConnection;
import sr.unasat.week6.jdbc.entities.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentDatabaseAccess {

	DBConnection dbConnection = new DBConnection();


	public ArrayList<Student> selectAll(){

		ArrayList<Student> studenten = new ArrayList<>();
		Statement statement = null; 
		ResultSet resultSet = null;

		try {
			//stap 1
			Connection	connect = dbConnection.getConnection();
			// Construeer een statement voor het uitvoeren van een SQL Query
			//Stap 2
			statement = connect.createStatement();
			// Voer de SQL statement uit en verzamel de output in de resultset
			resultSet = statement.executeQuery("select * from student");

			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				String naam = resultSet.getString("naam");
				String adres = resultSet.getString("adres");
				String studierichting = resultSet.getString("studierichting");
				int leeftijd = resultSet.getInt("leeftijd");
				double cijfergemiddelde = resultSet.getDouble("cijfergemiddelde");

				// Maak een student instantie en print deze instantie
				Student student = new Student();
				student.setId(id);
				student.setNaam(naam);
				student.setAdres(adres);
				student.setLeeftijd(leeftijd);
				student.setStudierichting(studierichting);
				student.setCijfergemiddelde(cijfergemiddelde);
				studenten.add(student);
				System.out.println(student);
			}
		} catch (SQLException e) {
			System.out.println("Er is een SQL fout ontstaan!");
		}
		return studenten;
	}

	public Student selectOne(int recordId){

		PreparedStatement preparedStatement  = null;
		ResultSet resultSet = null;
		Student student = new Student();

		try {
			Connection	connect = dbConnection.getConnection();
			// Statements allow to issue SQL queries to the database
			preparedStatement = connect.prepareStatement("select * from student where id = ? ");
			// Vul de id in de preparedStatement
			preparedStatement.setInt(1, recordId);
			// Voer de statement uit en haal het resultaat op
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				String naam = resultSet.getString("naam");
				String adres = resultSet.getString("adres");
				String studierichting = resultSet.getString("studierichting");
				int leeftijd = resultSet.getInt("leeftijd");
				double cijfergemiddelde = resultSet.getDouble("cijfergemiddelde");

				// Maak een student instantie en print deze instantie
				student.setId(id);
				student.setNaam(naam);
				student.setAdres(adres);
				student.setLeeftijd(leeftijd);
				student.setStudierichting(studierichting);
				student.setCijfergemiddelde(cijfergemiddelde);

				System.out.println(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return student;
	}

	public void update(Student student){
		PreparedStatement preparedStatement  = null;

		try {
			Connection	connect = dbConnection.getConnection();
			preparedStatement = connect.prepareStatement("update student set cijfergemiddelde = ? where id = ?");
			preparedStatement.setDouble(1, student.getCijfergemiddelde());
			preparedStatement.setInt(2, student.getId());

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
			preparedStatement = connect.prepareStatement("delete from student where id = ?");
			preparedStatement.setInt(1, recordId);

			// Voer de statement uit en haal het resultaat op
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Er is een SQL fout tijdens deleten van een record!");
			e.printStackTrace();
		}

		return result;
	}

	public int insert(Student student){

		PreparedStatement preparedStatement  = null;
		int affectedRows = 0;

		try {
			Connection	connect = dbConnection.getConnection();
			preparedStatement = connect.prepareStatement("insert into student values (NULL, ?,?,?,?,?)"
					,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, student.getNaam());
			preparedStatement.setString(2, student.getAdres());
			preparedStatement.setString(3, student.getStudierichting());
			preparedStatement.setInt(4, student.getLeeftijd());
			preparedStatement.setDouble(5, student.getCijfergemiddelde());

			// Voer de statement uit en haal het resultaat op
			affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating student failed, no rows affected.");
			}

			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getInt(1);
				}
				else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}

		} catch (SQLException e) {
			System.out.println("Er is een SQL fout tijdens het inserten van een nieuwe record!");
			e.printStackTrace();
		}

		return affectedRows;
	}

}
