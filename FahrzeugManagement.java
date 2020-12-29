
import java.util.List;
import java.util.NoSuchElementException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
/**
 * @author <Alexander-Stadler>
 * Matrikelnummer: 01427369
 */

public class FahrzeugManagement {
	private FahrzeugDAO fahrzeugDAO;
	public FahrzeugManagement(SerializedFahrzeugDAO fahrzeugDAO) {
		this.fahrzeugDAO = fahrzeugDAO;
	}

	public List<Fahrzeug> show() {
		/*List<Fahrzeug> l = fahrzeugDAO.getFahrzeugList();
		String s = "";
		boolean help = false;
		for(Fahrzeug a : l) {
			if(help) s += "\n";
			s += a.toString();
			help = true;
		}*/
		return fahrzeugDAO.getFahrzeugList();
	}

	public void add(Fahrzeug f) {
		fahrzeugDAO.speichereFahrzeug(f);
	}

	public Fahrzeug show(int id) {
		Fahrzeug f = fahrzeugDAO.getFahrzeugbyId(id);
		return f;
		
	}
	
	public void del(int id) {
		fahrzeugDAO.loescheFahrzeug(id);
	}
	
	public int count() {
		List<Fahrzeug> l = fahrzeugDAO.getFahrzeugList();
		return l.size();
	}
	public int countpkw() {
		List<Fahrzeug> l = fahrzeugDAO.getFahrzeugList();
		int counter = 0;
		for(Fahrzeug f : l) {
			if(f instanceof Pkw) counter +=1;
		}
		return counter;
	}
	public int countbike() {
		List<Fahrzeug> l = fahrzeugDAO.getFahrzeugList();
		int counter = 0;
		for(Fahrzeug f : l) {
			if(f instanceof Bike) counter +=1;
		}
		return counter;
	}
	public int countlkw() {
		List<Fahrzeug> l = fahrzeugDAO.getFahrzeugList();
		int counter = 0;
		for(Fahrzeug f : l) {
			if(f instanceof Lkw) counter +=1;
		}
		return counter;
	}
	public String meanprice() {
    	DecimalFormat df = Fahrzeug.getDecimalFormat();
		List<Fahrzeug> l = fahrzeugDAO.getFahrzeugList();
		int n = l.size();
		double sum = 0;
		for(Fahrzeug f : l) {
			sum += f.getGrundpreis() - f.getRabatt();
		}
		double help = (double) (sum/n);
		return df.format(help);
	}
	public List<Integer> oldest() {
		List<Fahrzeug> list = fahrzeugDAO.getFahrzeugList();
		List<Integer> help = new ArrayList<Integer>();
		
		Fahrzeug old = Collections.max(list, (ll,r) -> {if(((Fahrzeug) r).getAlter() < ((Fahrzeug) ll).getAlter()) return 1; else return 0;});
		for(Fahrzeug y : list) {
			if(y.getAlter() == old.getAlter()) help.add(y.getId());
		}

		int l = list.stream()
		.mapToInt(v -> v.getAlter())
	     .max().orElseThrow(NoSuchElementException::new);
		
		list.stream()
		.filter(v -> v.getAlter() == l)
		.forEach(v -> help.add(v.getId()));
			
		
		return help;
	}
	
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
	public int lambda(int i){
		List<Fahrzeug> l = fahrzeugDAO.getFahrzeugList();
		return (int) l.stream()
		.filter(f -> f.getGrundpreis() > i && f instanceof Pkw)
		.count();
		
		
	}
	public List<Integer> service(int i, int j){
		List<Fahrzeug> l = fahrzeugDAO.getFahrzeugList();
		List<Integer> help = new ArrayList<Integer>();
		for(Fahrzeug f : l) {
			if(f instanceof Pkw && ((Pkw) f).getServiceJahr() >= i && ((Pkw) f).getServiceJahr() <= j) help.add(f.getId());
				
		}
		return help;
		
		
	}
}
