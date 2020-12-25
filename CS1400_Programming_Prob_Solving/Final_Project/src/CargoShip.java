//
// Name: 		Auraiporn Auksorn
// Project: 	4
// Due: 		Monday December 9, 2019
// Course: 		cs-1400-02-F19
// Description:
// 				CargoShip.java is a sub class, and it is derived from a Ship.java. This class contains the information about the capacity of tonnage
// 				that a cargo can carry.

public class CargoShip extends Ship {
	private int tonnage;

	public CargoShip(String name, String yearBuilt, int tonnage){
		super(name,yearBuilt);
		this.tonnage = tonnage;
	}
	
	public void setTonnage(int tonnage){
		this.tonnage = tonnage;
	}

	public int getTonnage(){
		return tonnage;
	}

	@Override
	public String toString(){
		String s = null;
		s = String.format("%-20s Cargo:%-24s", getName() , tonnage);
		return s;
	}
}