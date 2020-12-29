import java.text.DecimalFormat;

/**
 * @author <Alexander-Stadler>
 * Matrikelnummer: 01427369
 */

public class Pkw extends Fahrzeug  {
	
	private static final long serialVersionUID = 1L;
	private int ServiceJahr;
	public Pkw(int Id, String Marke, String Modell, int Baujahr, double Grundpreis, int ServiceJahr) {
		super(Id, Marke, Modell, Baujahr, Grundpreis);
		if(ServiceJahr < Baujahr) throw new IllegalArgumentException("Error: Servicejahr ungueltig.");
		this.ServiceJahr = ServiceJahr;
	}
	
	
	public int getServiceJahr() {
		return ServiceJahr;
	}




	@Override
	public double getRabatt() {
		double ret = (double) ( super.getAlter()*5 + (2020 - getServiceJahr())*2);
		if(ret > 15) ret = 15;
		return (getGrundpreis()*(ret/100));
	}





	@Override
	public String toString() {
		DecimalFormat df = Fahrzeug.getDecimalFormat();
		String ret = "Typ:         PKW" + super.toString() + "\nServicejahr: " + ServiceJahr + "\nPreis:       " + df.format(this.getGrundpreis() - this.getRabatt());;
		return ret;
	}

	
}
