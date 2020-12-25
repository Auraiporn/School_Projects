//
// Name: 		Auraiporn Auksorn
// Project: 	4
// Due: 		Monday December 9, 2019
// Course: 		cs-1400-02-F19
// Description:
//				ShipDemo.java demonstrates the implementation of Ship class, Cruise class, and Cargo class. First, it will read the data from a file that is
// 				from the command line(default data file). While reading the context the program will recognize "C" means Cargo Ship and "c" means Cruise Ship,
// 				then the program will print out the information about the ship. It will also gives the sum of tonnage of Cargo Ship and total number of
// 				passengers that the Cruise ship can carry.
	
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class ShipDemo {
	public static void main(String[] args) {
		try{
			if(args.length == 1){
				File myFile = new File(args[0]);
				Scanner inputFile = new Scanner(myFile);
				String company_name;
				company_name = inputFile.nextLine();
				char type;
				String name, yearBuilt;
				int param;
				ArrayList <Ship> alShips = new ArrayList<>();
				
				while(inputFile.hasNext()){
					type = inputFile.next().charAt(0);
					name = inputFile.next();
					yearBuilt = inputFile.next();
					param = inputFile.nextInt();
					
					if(type == ('c')){
						alShips.add(new CruiseShip(name.replaceAll("_"," "), yearBuilt, param));
					} if(type == ('C')){
						alShips.add(new CargoShip(name.replaceAll("_", " "), yearBuilt, param));
					}
				}
				inputFile.close();

				Ship[] myShip = new Ship[alShips.size()];
				for(int i = 0; i < myShip.length ; i++){
					myShip[i] = alShips.get(i);
					alShips = null;
					System.out.println();
					System.out.println("Welcome to " + company_name.replaceAll("_", " ") + " by A. Auksorn\n");
					System.out.printf("%-20s", "Ship name Type");
					System.out.println("\n-------------------- ---------------");
					
					int totalPassengers = 0, totalTonnage = 0;
					for(Ship ship: myShip){
						if(ship instanceof CruiseShip){
							totalPassengers += ((CruiseShip)ship).getPassengers();
							System.out.println(((CruiseShip)ship));
						}if(ship instanceof CargoShip) {
							totalTonnage += ((CargoShip)ship).getTonnage();
							System.out.println(((CargoShip)ship));
						}
					}
					System.out.println("\nTotal Ships = " + myShip.length);
					System.out.println("Total Passengers = " + totalPassengers);
					System.out.println("Total Tonnage = " + totalTonnage);
				}
			}else {
					System.out.println("File Not Found");
				}

		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
