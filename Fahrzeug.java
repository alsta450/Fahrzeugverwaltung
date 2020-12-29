/**
 * @author <Alexander-Stadler>
 * Matrikelnummer: 01427369
 */

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


public abstract class Fahrzeug implements Serializable {
	private static final long serialVersionUID = 1L;
	private String Marke;
	private String Modell;
	private int Baujahr;
	private double Grundpreis;
	private int Id;
	
	public Fahrzeug(int Id, String Marke, String Modell, int Baujahr, double Grundpreis) {
		if(Baujahr > 2020) throw new IllegalArgumentException("Error: Baujahr ungueltig.");
		if(Grundpreis < 0) throw new IllegalArgumentException("Error: Grundpreis ungueltig.");
		if(Marke.isEmpty() || Modell.isEmpty() || Id <0) throw new IllegalArgumentException("Error: Parameter ungueltig.");
		this.Marke = Marke;
		this.Modell = Modell;
		this.Baujahr = Baujahr;
		this.Grundpreis = Grundpreis;
		this.Id = Id;
	}
	
	
	@Override
	public String toString() {
		DecimalFormat df = getDecimalFormat();
		return "\nId:          " + Id + "\nMarke:       " + Marke + "\nModell:      " + Modell + "\nBaujahr:     " + Baujahr + "\nGrundpreis:  " + df.format(Grundpreis);
	}

	public int getAlter() {
		return (2020 - getBaujahr());
	}
	
	public abstract double getRabatt();
	
	public double getPreis() {
		if(getRabatt() > Grundpreis) throw new IllegalArgumentException("Error: Parameter ungueltig.");
		return (this.Grundpreis - getRabatt());
	}
	
	//Getters and Setters
	public String getMarke() {
		return Marke;
	}
	public void setMarke(String marke) {
		Marke = marke;
	}
	public String getModell() {
		return Modell;
	}
	public void setModell(String modell) {
		Modell = modell;
	}
	public int getBaujahr() {
		return Baujahr;
	}
	public void setBaujahr(int baujahr) {
		Baujahr = baujahr;
	}
	public double getGrundpreis() {
		return Grundpreis;
	}
	public void setGrundpreis(double grundpreis) {
		Grundpreis = grundpreis;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	
	
	public static DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		dfs.setDecimalSeparator('.');
		return new DecimalFormat("0.00", dfs);
	}		
}
