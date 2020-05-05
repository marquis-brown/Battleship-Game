import java.util.*;

public class Battleship {

	private Coordinate shipHead; //Starting coordinate for each ship
	private ArrayList<Coordinate> shipCoords; //Set of ship coordinates
	private String orientation; //Whether vertical or horizontal 
	private int size; //Amount of coordinates this ship contains
	private boolean status; //False = sunk, true = afloat 
	
	public Battleship(Coordinate c, String vertOrHoriz, int length) {
		shipHead = c;
		orientation = vertOrHoriz;
		size = length;
		status = true;
		shipCoords = buildShip(shipHead, orientation, size);
	}
	
	// Builds the arraylist of coordinates of the ship
	public ArrayList<Coordinate> buildShip(Coordinate c, String vertOrHoriz, int length) {
		ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
		if(vertOrHoriz.equalsIgnoreCase("VERTICAL")) {
			//Prevents the ship from having y-coordinates beyond (10,10)
			if(c.getYPos() >= length) {
				for(int i = 0; i < length; i++) {
					Coordinate temp = new Coordinate(c.getXPos(), (c.getYPos()-i));
					coords.add(temp);
				}
			} else {
				for(int i = 0; i < length; i++) {
					Coordinate temp = new Coordinate(c.getXPos(), (c.getYPos()+i));
					coords.add(temp);
				}
			}
		} else {
			//Prevents the ship from having x-coordinates beyond (10,10)
			if(c.getXPos() >= length) {
				for(int i = 0; i < length; i++) {
					Coordinate temp = new Coordinate((c.getXPos()-i), c.getYPos());
					coords.add(temp);
				}
			} else {
				for(int i = 0; i < length; i++) {
					Coordinate temp = new Coordinate((c.getXPos()+i), (c.getYPos()));
					coords.add(temp);
				}
			}
		}
		return coords;
	}
	
	//Checks if the Coordinate c matches any of this ships coordinates
	// If it does it removes that coordinate and records the hit
	public boolean hitOrMiss(Coordinate c) {
		for(int i = 0; i < shipCoords.size(); i++) {
		 	if(shipCoords.get(i).equals(c)) {
				shipCoords.remove(i);
				size--;
				if(shipCoords.isEmpty()) {
					status = false;
					System.out.println("THIS SHIP HAS SUNK!");
					return true;
				}
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String ship = "{";
		for(int i = 0; i < shipCoords.size(); i++) {
			ship += " " + shipCoords.get(i);
		}
		ship += "}";
		return ship;
	}
	
	public Coordinate getShipHead() {
		return shipHead;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public int getSize() {
		return size;
	}
	
	public String getOrientation() {
		return orientation;
	}
	
	public ArrayList<Coordinate> getShipCoords() {
		return shipCoords;
	}
	
	public boolean contains(Coordinate c) {
		for(int i = 0; i < shipCoords.size(); i++) {
			if(c.equals(shipCoords.get(i)))
				return true;
		}
		return false;
	}
	// Checks if a ship shares a coordinate with this ship
	public boolean validShip(Battleship check)//public void remove(Coordinate c) {
		//	shipCoords.remove(c);
		//} {
		ArrayList<Coordinate> checkCoords = check.getShipCoords();
		for(int i = 0; i < checkCoords.size(); i++){
			if(this.contains(checkCoords.get(i)))
				return false;
		}
		return true;
	}
}

