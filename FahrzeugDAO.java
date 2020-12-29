/**
 * @author <Alexander-Stadler>
 * Matrikelnummer: 01427369
 */

import java.util.List;

public interface FahrzeugDAO {

	public List<Fahrzeug> getFahrzeugList();

	public Fahrzeug getFahrzeugbyId(int id);
	
	public void speichereFahrzeug(Fahrzeug fahrzeug);
	
	public void loescheFahrzeug(int id);
}
