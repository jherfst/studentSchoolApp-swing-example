package sr.unasat.week6.jdbc.dataaccess;

import sr.unasat.week6.jdbc.connection.DBConnection;
import sr.unasat.week6.jdbc.entities.StudentVak;
import sr.unasat.week6.jdbc.entities.Vak;

import java.sql.*;
import java.util.ArrayList;

public class StudentVakDatabaseAccess {

	DBConnection dbConnection = new DBConnection();


	public ArrayList<StudentVak> selectAll(){

		ArrayList<StudentVak> studentVakken = new ArrayList<>();
		Statement statement = null; 
		ResultSet resultSet = null;

		try {
			//stap 1
			Connection	connect = dbConnection.getConnection();
			// Construeer een statement voor het uitvoeren van een SQL Query
			//Stap 2
			statement = connect.createStatement();
			// Voer de SQL statement uit en verzamel de output in de resultset
			resultSet = statement.executeQuery("select * from student_vak");

			while (resultSet.next()) {

				StudentVak studentVak = new StudentVak();
				int id = resultSet.getInt("id");
				int student = resultSet.getInt("student");
				int vak = resultSet.getInt("vak");

				// Maak een StudentVak instantie en print deze instantie
				studentVak.setId(id);
				studentVak.setStudent(student);
				studentVak.setVak(vak);
				System.out.println(studentVak);
			}
		} catch (SQLException e) {
			System.out.println("Er is een SQL fout ontstaan!");
		}
		return studentVakken;
	}

	public StudentVak selectOne(int recordId){

		PreparedStatement preparedStatement  = null;
		ResultSet resultSet = null;
		StudentVak studentVak = new StudentVak();

		try {
			Connection	connect = dbConnection.getConnection();
			// Statements allow to issue SQL queries to the database
			preparedStatement = connect.prepareStatement("select * from student_vak where id = ? ");
			// Vul de id in de preparedStatement
			preparedStatement.setInt(1, recordId);
			// Voer de statement uit en haal het resultaat op
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				int student = resultSet.getInt("student");
				int vak = resultSet.getInt("vak");

				// Maak een StudentVak instantie en print deze instantie
				studentVak.setId(id);
				studentVak.setStudent(student);
				studentVak.setVak(vak);

				System.out.println(studentVak);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return studentVak;
	}

	public void update(StudentVak StudentVak){
		PreparedStatement preparedStatement  = null;

		try {
			Connection	connect = dbConnection.getConnection();
			preparedStatement = connect.prepareStatement("update student_vak set vak = ? where id = ?");
			preparedStatement.setInt(1, StudentVak.getVak());
			preparedStatement.setInt(2, StudentVak.getId());

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
			preparedStatement = connect.prepareStatement("delete from student_vak where id = ?");
			preparedStatement.setInt(1, recordId);

			// Voer de statement uit en haal het resultaat op
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Er is een SQL fout tijdens deleten van een record!");
			e.printStackTrace();
		}

		return result;
	}

	public int insert(StudentVak studentVak){

		PreparedStatement preparedStatement  = null;
		int result = 0;

		try {
			Connection	connect = dbConnection.getConnection();
			preparedStatement = connect.prepareStatement("insert into student_vak values (NULL, ?,?)");
			preparedStatement.setInt(1, studentVak.getStudent());
			preparedStatement.setInt(2, studentVak.getVak());
			// Voer de statement uit en haal het resultaat op
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Er is een SQL fout tijdens het inserten van een nieuwe record!");
			e.printStackTrace();
		}

		return result;
	}

}
