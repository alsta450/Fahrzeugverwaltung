import java.util.List;

/**
 * @author <Alexander-Stadler>
 * Matrikelnummer: 01427369
 */

//Client with simple command line interface, which can save, alter, delete,.. Fahrzeug objects
public class FahrzeugClient {

	public static void main(String[] args) {
		
		final FahrzeugManagement management;
		//First argument (args[0]) is the filepath
		//Initializing Fahrzeugmanagement with an instance of SerializedFahrzeugDAO
		management = new FahrzeugManagement(new SerializedFahrzeugDAO(args[0]));
		//If too little arguments in the command line are given, an exception is thrown
		if(args.length < 2) throw new IllegalArgumentException("Parameter ungueltig.");
		
		//switch statement for every available command. if a command is not found an exception is thrown (default)
		switch (args[1]) {
		//show can be called without argument to show all Fahrzeuge in a file, or with an argument, to show the Fahrzeug with the matching id
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
		//adds a Fahrzeug to the file. Different call for pkw/lkw/bike. command line arguments have to match.
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
        //deletes a Fahrzeug by id from the file
        case "del":
			management.del(Integer.parseInt(args[2]));
            break;
        //counts all Fahrzeuge in the file or all Pkws/Lkws/Bikes
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
        //Prints the mean price of all Fahrzeuge in the file
        case "meanprice":
          	System.out.println(management.meanprice());
            break;
        //Prints the id(s) of the oldest Fahrzeug(e) in the file
        case "oldest":
        	for(int a : management.oldest()){
				System.out.println("Id: " + a);
			}
            break;
        //Prints all Fahrzeuge between the given years
        case "yearrange":
        	List<Fahrzeug> help = management.yearrange(Integer.parseInt(args[2]),Integer.parseInt(args[3]) );
        	for(Fahrzeug a: help) {
        		System.out.println(a);
        	}
        	break;
        //Prints the number of Fahrzeuge with a higher price, than given integer
        case "lambda":
        	System.out.println(management.lambda(Integer.parseInt(args[2])));
        	break;
        //Prints the ids of all Fahrzeuge with Servicejahr between two given years.
        case "service": 
        	List<Integer> liste = management.service(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        	for(Integer i : liste) {
        		System.out.println("Id: " + i);
        	}
        	break;
        //Catches invalid inputs
        default:
            throw new IllegalArgumentException("Error: Parameter ungueltig.");
		}
	}
}