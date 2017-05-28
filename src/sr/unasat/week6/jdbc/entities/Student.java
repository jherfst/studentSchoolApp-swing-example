package sr.unasat.week6.jdbc.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;

public class Student {

	private int id; 
	private String naam;
	private String adres;
	private String studierichting;
	private int leeftijd;
	private double cijfergemiddelde;
	private ArrayList<Vak> vakken;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getStudierichting() {
		return studierichting;
	}

	public void setStudierichting(String studierichting) {
		this.studierichting = studierichting;
	}

	public int getLeeftijd() {
		return leeftijd;
	}

	public void setLeeftijd(int leeftijd) {
		this.leeftijd = leeftijd;
	}

	public double getCijfergemiddelde() {
		return cijfergemiddelde;
	}

	public void setCijfergemiddelde(double cijfergemiddelde) {
		this.cijfergemiddelde = cijfergemiddelde;
	}

	public ArrayList<Vak> getVakken() {
		return vakken;
	}

	public void setVakken(ArrayList<Vak> vakken) {
		this.vakken = vakken;
	}

	@Override
	public String toString()
	{
	    return ToStringBuilder.reflectionToString(this);
	}
	
}
