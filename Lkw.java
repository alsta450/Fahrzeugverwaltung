/**
 * @author <Alexander-Stadler>
 * Matrikelnummer: 01427369
 */
import java.text.DecimalFormat;

public class Lkw extends Fahrzeug {

	private static final long serialVersionUID = 1L;

	public Lkw(int Id, String Marke, String Modell, int Baujahr, double Grundpreis) {
		super(Id, Marke, Modell, Baujahr, Grundpreis);
	}
	
	//Returns the discount, as specified in the requirement
	@Override
	public double getRabatt() {
		double ret = (double) (getAlter() *5);
		if(ret*100 > 20) ret = 20;
		return (getGrundpreis()*(ret/100));
	}
	//Returns the toString() method from Fahrzeug + LKW-specific-variables
	@Override
	public String toString() {
		DecimalFormat df = Fahrzeug.getDecimalFormat();
		return "Typ:         LKW" + super.toString() + "\nPreis:       " + df.format(this.getGrundpreis() - this.getRabatt());
	}
	
	
}
