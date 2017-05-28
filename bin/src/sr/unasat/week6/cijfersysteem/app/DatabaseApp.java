package sr.unasat.week6.cijfersysteem.app;

import sr.unasat.week6.jdbc.dataaccess.VakDatabaseAccess;
import sr.unasat.week6.jdbc.entities.Student;
import sr.unasat.week6.jdbc.dataaccess.StudentDatabaseAccess;

import java.util.ArrayList;

public class DatabaseApp {

	public static void main(String[] args) {
		VakDatabaseAccess vakDatabaseAccess = new VakDatabaseAccess();
		vakDatabaseAccess.selectAll();

		// Maak een instantie van de StudentDatabase class
		StudentDatabaseAccess studentDataAccess = new StudentDatabaseAccess();
		
		// Test selectOne methode
		//Student studentMetId1 = studentDataAccess.selectOne(1);

		// Test selectAll methode
		//ArrayList<Student> studentenLijst = studentDataAccess.selectAll();

		// Test update van een cijfergemiddelde van een student
				// stap 1: Selecteer een record
				Student studentMetId2 = studentDataAccess.selectOne(2);
				// stap 2: wijzig de cijfergemiddelde van deze record
				studentMetId2.setCijfergemiddelde(7.9);
				// stap 3: voer de update door naar de database
				studentDataAccess.update(studentMetId2);
				// stap 4: Controleer door hetzelfde record weer uit de
				//         database te lezen.
				studentDataAccess.selectOne(2);
		
		// Test Delete
		
		// Test Insert
		
	}

}
