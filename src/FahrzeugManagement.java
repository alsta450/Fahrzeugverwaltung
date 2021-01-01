
import java.util.List;
import java.util.NoSuchElementException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
/**
 * @author <Alexander-Stadler>
 * Matrikelnummer: 01427369
 */
//has the interface FahrzeugDAO as an instance variable which is then used to call methods from the class SerialuedFahrzeugDAO where the interface is implemented
public class FahrzeugManagement {
	private FahrzeugDAO fahrzeugDAO;
	public FahrzeugManagement(SerializedFahrzeugDAO fahrzeugDAO) {
		this.fahrzeugDAO = fahrzeugDAO;
	}
	
	//Returns a List of all saved Fahrzeuge, by calling getFahrzeugList()
	public List<Fahrzeug> show() {
		return fahrzeugDAO.getFahrzeugList();
	}
	
	//Adds a Fahrzeug to the file by calling speichereFahrzeug()
	public void add(Fahrzeug f) {
		fahrzeugDAO.speichereFahrzeug(f);
	}

	//Returns the Fahrzeug matching the id, by calling getFahrzeugbyId(id)
	public Fahrzeug show(int id) {
		Fahrzeug f = fahrzeugDAO.getFahrzeugbyId(id);
		return f;
	}
	
	//Deletes the Fahrzeug with the matching id by calling loescheFahrzeug(id)
	public void del(int id) {
		fahrzeugDAO.loescheFahrzeug(id);
	}
	
	//counts the number of Fahrzeuge in the file
	public int count() {
		List<Fahrzeug> l = fahrzeugDAO.getFahrzeugList();
		return l.size();
	}
	
	//Counts the number of pkws in the file, by checking if a an element in the list is an instance of Pkw
	public int countpkw() {
		List<Fahrzeug> l = fahrzeugDAO.getFahrzeugList();
		int counter = 0;
		for(Fahrzeug f : l) {
			if(f instanceof Pkw) counter +=1;
		}
		return counter;
	}
	
	//Counts the number of bikes in the file, by checking if a an element in the list is an instance of Bike
	public int countbike() {
		List<Fahrzeug> l = fahrzeugDAO.getFahrzeugList();
		int counter = 0;
		for(Fahrzeug f : l) {
			if(f instanceof Bike) counter +=1;
		}
		return counter;
	}
	
	//Counts the number of lkws in the file, by checking if a an element in the list is an instance of Lkw
	public int countlkw() {
		List<Fahrzeug> l = fahrzeugDAO.getFahrzeugList();
		int counter = 0;
		for(Fahrzeug f : l) {
			if(f instanceof Lkw) counter +=1; //if element instance of Lkw the counter gets incremented
		}
		return counter;
	}
	
	//Returns the mean price of all Fahrzeuge in the list
	public String meanprice() {
    	DecimalFormat df = Fahrzeug.getDecimalFormat();
		List<Fahrzeug> l = fahrzeugDAO.getFahrzeugList();
		int n = l.size();
		double sum = 0;
		for(Fahrzeug f : l) {
			//Price = Grundpreis - Rabatt
			sum += f.getGrundpreis() - f.getRabatt();
		}
		//Average price = Sum of all prices / Number of elements
		double help = (double) (sum/n);
		return df.format(help);
	}
	
	//Returns the id if the oldest Fahrzeug in the file; More than one possible
	public List<Integer> oldest() {
		List<Fahrzeug> list = fahrzeugDAO.getFahrzeugList();
		List<Integer> help = new ArrayList<Integer>();
		
		//Lambda expression to find the oldest Fahrzeug
		Fahrzeug old = Collections.max(list, (ll,r) -> {if(((Fahrzeug) r).getAlter() < ((Fahrzeug) ll).getAlter()) return 1; else return 0;});
		for(Fahrzeug y : list) {
			if(y.getAlter() == old.getAlter()) help.add(y.getId());
		}
		
		/* Does the same
		int l = list.stream()
		.mapToInt(v -> v.getAlter())
	     .max().orElseThrow(NoSuchElementException::new);
		
		list.stream()
		.filter(v -> v.getAlter() == l)
		.forEach(v -> help.add(v.getId()));
			*/
		
		return help;
	}
	
	//Returns all Fahrzeuge between two chosen years
	public List<Fahrzeug> yearrange(int min, int max){
		List<Fahrzeug> list = fahrzeugDAO.getFahrzeugList();
		List<Fahrzeug> help = new ArrayList<Fahrzeug>();
		for(Fahrzeug a : list) {
			if(a.getBaujahr() >= min && a.getBaujahr() <= max) {
				help.add(a);
			}
		}
		return help;
		
	}
	
	//Returns the number of Pkws with Grundpreis > i
	public int lambda(int i){
		List<Fahrzeug> l = fahrzeugDAO.getFahrzeugList();
		//Filter for Fahrzeuge with Grundpreis > i and instance of Pkw and return them
		return (int) l.stream()
		.filter(f -> f.getGrundpreis() > i && f instanceof Pkw) 
		.count();
	}
	
	//Returns the id of all Pkws with servicejahr between i && j
	public List<Integer> service(int i, int j){
		List<Fahrzeug> l = fahrzeugDAO.getFahrzeugList();
		List<Integer> help = new ArrayList<Integer>();
		for(Fahrzeug f : l) {
			if(f instanceof Pkw && ((Pkw) f).getServiceJahr() >= i && ((Pkw) f).getServiceJahr() <= j) help.add(f.getId());
				
		}
		return help;
	}
	
}
