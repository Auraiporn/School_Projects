//
// Name: Auraiporn Auksorn
// Project: 4
// Due: Monday December 9, 2019
// Course: cs-1400-02-F19
// Description:
// CruiseShip.java is a sub class, and it is derived from a Ship.java. This class contains the information about the capacity of maximum
// passengers that a cruise can carry.

public class CruiseShip extends Ship {
	private int maxPassengers;

	public CruiseShip(String name, String yearBuilt, int passengers){
		super(name,yearBuilt);
		maxPassengers = passengers;
	}
	
	public void setPassengers(int passengers){
		maxPassengers = passengers;
	}
 
	public int getPassengers(){
		return maxPassengers;
	}

	@Override
	public String toString(){
		return String.format("%-20s Cruise:%s", getName(), maxPassengers);
	}
}
