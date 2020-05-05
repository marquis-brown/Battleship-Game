import java.util.*;

public class BattleshipApp {

	public static void main(String[] args) {
		PlayerCPU cpu = new PlayerCPU(); //cpu sets ships
		Player p1 = new Player(); //player1 sets ships
		//play game	
		while(true) {		
			System.out.println("* CPU has " + cpu.getNumOfShips() + " ships left *");
			System.out.println();
			if(cpu.getNumOfShips() == 0) {
				System.out.println("Player 1 has won!");
				break;
			}

			if(p1.getNumOfShips() == 0) {
				System.out.println("CPU has won!");
				break;
			}
			Scanner playerInput = new Scanner(System.in);
			System.out.println("Choose a coordinate to fire: ");
			Coordinate missle = new Coordinate(playerInput.nextInt(), playerInput.nextInt());
			if(p1.validMissle(missle)) {
				System.out.println("Player 1 Guesses: " + p1.getCoordGuesses());
				cpu.printShips();
				if(cpu.hitOrMiss(missle)){
					System.out.println(missle + " HIT!!!");
					cpu.removeBattleship();
					continue;
				} else {
					System.out.println(missle + " MISS!!!");
				}
			} else {
				System.out.println("***YOU ALREADY CHOSE THAT COORDINATE***");
				continue;
			}

			System.out.println("* Player 1 has " + p1.getNumOfShips() + " ships left *");
			System.out.println();
			Boolean cpuTurn = true;
			while(cpuTurn) {
				Coordinate cpuMissle = cpu.fire();
				System.out.println("THE CPU FIRES AT: " + cpuMissle);
				p1.printShips();
				if(p1.hitOrMiss(cpuMissle)){
					System.out.println(cpuMissle + " HIT!!!");
					p1.removeBattleship();
					continue;
				} else {
					System.out.println(cpuMissle + " MISS!!!");
					cpuTurn = false;
				}
			} 
		}
	}
}
