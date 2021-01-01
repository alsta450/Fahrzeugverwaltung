/**
 * @author <Alexander-Stadler>
 * Matrikelnummer: 01427369
 */

import java.util.List;

//Interface implemented in SerializedFahrzeugDAO and used as an instance Variable in FahrzeugManagement
public interface FahrzeugDAO {

	public List<Fahrzeug> getFahrzeugList();

	public Fahrzeug getFahrzeugbyId(int id);
	
	public void speichereFahrzeug(Fahrzeug fahrzeug);
	
	public void loescheFahrzeug(int id);
}
