import java.util.*; 

public class PlayerCPU {

	private ArrayList<Coordinate> CPUfield = buildCPUfield();
	private ArrayList<Battleship> battleships = new ArrayList<Battleship>();
	private String[] vertOrHoriz = {"Vertical", "Horizontal"};
	private ArrayList<Coordinate> coordGuess = new ArrayList<Coordinate>();
	private int numOfShips = 5; //
	
	public PlayerCPU() {		
		Random rand = new Random();
		int length = 6;
		int count = 0;
		do {
			Coordinate shiphead = randomCoord();
			Battleship ship = new Battleship(shiphead, vertOrHoriz[rand.nextInt(2)], length);
			if(battleships.isEmpty()){
				battleships.add(ship);
				count++;
				length--;
			} else {
				int validCounter = 0; 
				for(int i = 0; i < battleships.size(); i++){
					if(battleships.get(i).validShip(ship)){
						validCounter++;
					}
					if(validCounter == battleships.size()){
						//The ship doesn't have conflicting coordinates
						battleships.add(ship);
						count++;
						length--;
					}
				}
			}
		} while(count < numOfShips);	
	}
	
	public Coordinate randomCoord() {
		Random rand = new Random();
		int randomIndex = rand.nextInt(CPUfield.size());
		Coordinate random = CPUfield.get(randomIndex);
		return random;		
	}
	
	public Coordinate fire() {
		Coordinate missle = randomCoord();
		for(int i = 0; i < coordGuess.size(); i++) {
			if(coordGuess.get(i).equals(missle)) 
				fire();
		}
		return missle;
	}


	public boolean hitOrMiss(Coordinate missle) {
		int counter = 0;
		for(int i = 0; i < battleships.size(); i++) {
			if(battleships.get(i).hitOrMiss(missle)){
				counter++;
			}
			if(counter > 0)
				return true;
		}
	   return false;
	}
	
	public ArrayList<Coordinate> buildCPUfield() {
		ArrayList<Coordinate> field = new ArrayList<Coordinate>();
		for(int i = 1; i <= 10; i++) {
			for(int j = 1; j <= 10; j++) {
				Coordinate c = new Coordinate(i, j);
				field.add(c);
			}			
		}
		return field;
	}
	
	public ArrayList<Battleship> getBattleships() {
		return battleships;
	}
	
	public void removeBattleship() {
		for(int i = 0; i < battleships.size(); i++) {
			if(!battleships.get(i).getStatus()) {
				battleships.remove(i);
				numOfShips--;
			}
		}	
	}

	public int getNumOfShips(){
		return numOfShips;
	}

	public void printShips() {
		String ships = "";
		for(int i = 0; i < numOfShips; i++) {
			if(battleships.get(i).getSize() != 0)
				ships += battleships.get(i) + "\n";	
			else {
				this.removeBattleship();
			}	
		}
		System.out.println(ships);
	}

}
