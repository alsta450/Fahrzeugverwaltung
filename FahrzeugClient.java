import java.util.List;

/**
 * @author <Alexander-Stadler>
 * Matrikelnummer: 01427369
 */

public class FahrzeugClient {

	public static void main(String[] args) {
		
		final FahrzeugManagement management;
		//System.out.println(args[0]);
		SerializedFahrzeugDAO s = new SerializedFahrzeugDAO(args[0]);
		management = new FahrzeugManagement(new SerializedFahrzeugDAO(args[0]));
		if(args.length < 2) throw new IllegalArgumentException("Parameter ungueltig.");
		switch (args[1]) {
		case "show":
			//show all
			if(args.length == 2) {
				List<Fahrzeug> allCars = management.show();
				for(Fahrzeug f: allCars) {
					System.out.println(f + "\n");
				}	
			}else {
			//show one by id 
			System.out.println(management.show(Integer.parseInt(args[2])) + "\n"); //catch error?
			}

			break;

        case "add":
		try{
        	if(args[2].equals("pkw")) {
				if(args.length != 9 ) throw new IllegalArgumentException("Error: Parameter ungueltig.");
        		Pkw help = new Pkw(Integer.parseInt(args[3]), args[4], args[5], Integer.parseInt(args[6]), Double.parseDouble(args[7]),  Integer.parseInt(args[8]));
        		management.add(help);
        	}
        	else if(args[2].equals("lkw")) {
				if(args.length != 8 ) throw new IllegalArgumentException("Error: Parameter ungueltig.");
        		Lkw help = new Lkw(Integer.parseInt(args[3]), args[4], args[5], Integer.parseInt(args[6]), Double.parseDouble(args[7]));
        		management.add(help);
        		}
        		else if(args[2].equals("bike")) {
    				if(args.length != 9 ) throw new IllegalArgumentException("Error: Parameter ungueltig.");
            		Bike help = new Bike(Integer.parseInt(args[3]), args[4], args[5], Integer.parseInt(args[6]), Double.parseDouble(args[7]),  Integer.parseInt(args[8]));
            		management.add(help);
        	}else{
				throw new IllegalArgumentException("Error: Parameter ungueltig.");
			}
		}catch( IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
            break;
        case "del":
			management.del(Integer.parseInt(args[2]));
            break;
        case "count":
        	if(args.length == 2) { 
        		System.out.println(management.count());
        	
        	}else if (args[2].equals("pkw")) {
        		System.out.println(management.countpkw());
        	}else if (args[2].equals("lkw")){
        		System.out.println(management.countlkw());
        	}else if (args[2].equals("bike")){
        		System.out.println(management.countbike());
        	}
        	
            break;
        case "meanprice":
          	System.out.println(management.meanprice());
            break;
        case "oldest":
        	for(int a : management.oldest()){
				System.out.println("Id: " + a);
			}
            break;
        case "yearrange":
        	List<Fahrzeug> help = management.yearrange(Integer.parseInt(args[2]),Integer.parseInt(args[3]) );
        	for(Fahrzeug a: help) {
        		System.out.println(a);
        	}
        	break;
        case "lambda":
        	System.out.println(management.lambda(Integer.parseInt(args[2])));
        	break;
        case "service": 
        	List<Integer> liste = management.service(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        	for(Integer i : liste) {
        		System.out.println("Id: " + i);
        	}
        	break;
        default:
            throw new IllegalArgumentException("Error: Parameter ungueltig.");
    }
	}
}