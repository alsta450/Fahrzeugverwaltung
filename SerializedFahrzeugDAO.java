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

public class SerializedFahrzeugDAO implements FahrzeugDAO, Serializable {
	private String Dateiname;
	public SerializedFahrzeugDAO(String Dateiname) {
		if(Dateiname.isEmpty()) throw new IllegalArgumentException("Error: Parameter ungueltig.");
		this.Dateiname = Dateiname;
	}
	@Override
	@SuppressWarnings("unchecked")
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
	public Fahrzeug getFahrzeugbyId(int id) {
		List<Fahrzeug> fahrzeuge = this.getFahrzeugList();
		for(Fahrzeug f : fahrzeuge) {
			if(f.getId() == id) return f;
		}
		return null;
	}

	@Override
	public void speichereFahrzeug(Fahrzeug fahrzeug) {
		File file = new File(Dateiname);
		if (file.exists()) {
			try {
				List<Fahrzeug> l = this.getFahrzeugList();
				for(Fahrzeug a : l) {
					if(a.getId() == fahrzeug.getId()) throw new IllegalArgumentException("Error: Fahrzeug bereits vorhanden. (id=<id>)");
				}
				l.add(fahrzeug);
				file.delete();
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
	public void loescheFahrzeug(int id) {
        try{
		List<Fahrzeug> l = this.getFahrzeugList();
		boolean help = true;
		for(Fahrzeug f : l) {
			if(f.getId() == id) {
				l.remove(f);
				help = false;
				break;
			}
		}
		if(help) throw new IllegalArgumentException("Error: Fahrzeug nicht vorhanden. (id=" + id + ")");
        File file = new File(Dateiname);
        file.delete();
		for(Fahrzeug f : l) {
			speichereFahrzeug(f);
		}
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
	}
	
	
}
