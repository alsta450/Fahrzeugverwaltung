import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <Alexander-Stadler>
 * Matrikelnummer: 01427369
 */
//This class implements the interface FahrzeugDAO which is used to serialize, alter and deserialize Objects
public class SerializedFahrzeugDAO implements FahrzeugDAO, Serializable {
	private String Dateiname;
	public SerializedFahrzeugDAO(String Dateiname) {
		if(Dateiname.isEmpty()) throw new IllegalArgumentException("Error: Parameter ungueltig.");
		this.Dateiname = Dateiname;
	}
	@Override
	@SuppressWarnings("unchecked")
	//Returns a List of all Fahrzeuge saved in the file "Dateiname"
	public List<Fahrzeug> getFahrzeugList() {
		File file = new File(Dateiname);
		List<Fahrzeug> fahrzeuge = new ArrayList();
		try {
		ObjectInputStream reader;
		reader = new ObjectInputStream(new FileInputStream(Dateiname));
		fahrzeuge = (ArrayList<Fahrzeug>) reader.readObject(); // unchecked
		reader.close();
		}
		catch (Exception e) {
		System.err.println("Fehler bei Serialisierung: " + e.getMessage());
		System.exit(1);
		}
		
		return fahrzeuge;
	}

	@Override
	//Returns the Fahrzeug with the matching id
	public Fahrzeug getFahrzeugbyId(int id) {
		List<Fahrzeug> fahrzeuge = this.getFahrzeugList();
		//Compare the id of each Fahrzeug f with the id we are looking for; returns the Fahrzeug if found
		for(Fahrzeug f : fahrzeuge) {
			if(f.getId() == id) return f;
		}
		return null;
	}

	@Override
	//Saves a Fahrzeug object in a file
	public void speichereFahrzeug(Fahrzeug fahrzeug) {
		File file = new File(Dateiname);
		//if the file exists we try to get a list of all Fahrzeuge saved in this file and add our Fahrzeug to the end.
		//else we skip this part and create a file containing only our new Fahrzeug
		if (file.exists()) {
			try {
				List<Fahrzeug> l = this.getFahrzeugList();
				//search if the file already contained the id, as every id must be unique
				//if found throw an exception
				for(Fahrzeug a : l) { 
					if(a.getId() == fahrzeug.getId()) throw new IllegalArgumentException("Error: Fahrzeug bereits vorhanden. (id=<id>)");
				}
				l.add(fahrzeug);
				file.delete(); //Delete the file and create a new one with the same name containing the new Fahrzeug
				ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(Dateiname, true));
				writer.writeObject(l);
				writer.close();
				}
				catch (Exception e) {
				System.err.println("Fehler bei Serialisierung: " + e.getMessage());
				System.exit(1);
				}
			return;
		}
		//only if the file doesn't exist
		//Create a new file and save our Fahrzeug to it
		//Must be saved in a ArrayList, even if it's only a single Fahrzeug, as we only try to read lists with getFahrzeugList() and would get an error if not
        ArrayList<Fahrzeug> help = new ArrayList<Fahrzeug>();
        help.add(fahrzeug);
		try {
		ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(Dateiname, true));
		writer.writeObject(help);
		writer.close();
		}
		catch (Exception e) {
		System.err.println("Fehler bei Serialisierung: " + e.getMessage());
		System.exit(1);
		}
		
	}


	@Override
	//deletes the Fahrzeug with the matching id from the file
	public void loescheFahrzeug(int id) {
        try{
		List<Fahrzeug> l = this.getFahrzeugList();
		List<Fahrzeug> help = new ArrayList<Fahrzeug>();
		//Reduce the list to all Fahrzeuge, but the one with the matching id
		l.stream()
		.filter(f -> f.getId() != id)
		.forEach(f -> help.add(f));
		//If the size of the list didn't change, the id wasn't found and an exception is thrown
		if(help.size() == l.size()) throw new IllegalArgumentException("Error: Fahrzeug nicht vorhanden. (id=" + id + ")");
		//Save the new list
        File file = new File(Dateiname);
        file.delete();
		for(Fahrzeug f : help) {
			speichereFahrzeug(f);
		}
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
	}
	
	
}
