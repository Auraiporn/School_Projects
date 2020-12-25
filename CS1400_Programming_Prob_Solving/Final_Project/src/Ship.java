//
// Name: Auraiporn Auksorn
// Project: 4
// Due: Monday December 9, 2019
// Course: cs-1400-02-F19
// Description:
// Ship.java is a super class. It contains the information about Ship such as name and year built. It provides a constructor
// to allow other sub classes have access to its fields. There is also a method "toString" that will print out the information of Ship
// such as name and year built.

public class Ship {

	private String name;
	private String yearBuilt;

	public Ship (String name, String yearBuilt){
		this.name = name;
		this.yearBuilt = yearBuilt;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setYearBuilt(String yearBuilt){
		this.yearBuilt = yearBuilt;
	}

	public String getName(){
		return name;
	}

	public String getyearBuilt(){
		return yearBuilt;
	}
	
	@Override
	public String toString(){
		return String.format("%-20s: %s", name, yearBuilt);
	}
}