import java.text.DecimalFormat;

public class Bike extends Fahrzeug{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ServiceJahr;
	public Bike(int Id, String Marke, String Modell, int Baujahr, double Grundpreis, int ServiceJahr) {
		super(Id, Marke, Modell, Baujahr, Grundpreis);
		if(ServiceJahr < Baujahr) throw new IllegalArgumentException("Error: Servicejahr ungueltig.");
		this.ServiceJahr = ServiceJahr;
	}
	public int getServiceJahr() {
		return ServiceJahr;
	}

	@Override
	public double getRabatt() {
		double ret = (double) ( super.getAlter()*4 + (2020 - getServiceJahr())*3);
		if(ret > 40) ret = 40;
		return (getGrundpreis()*(ret/100));
	}
	@Override
	public String toString() {
		DecimalFormat df = Fahrzeug.getDecimalFormat();
		String ret = "Typ:         BIKE" + super.toString() + "\nServicejahr: " + ServiceJahr + "\nPreis:       " + df.format(this.getGrundpreis() - this.getRabatt());;
		return ret;
	}
}
