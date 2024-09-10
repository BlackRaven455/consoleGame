import api.iLevels;
import game.Dungeon;
import game.lvls.DungeonA;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the MISSION game");
        Scanner input = new Scanner(System.in);
        boolean continueGame = true;
        while(continueGame){
            Dungeon dungeon;

            System.out.println("Please enter a number between 1 and 10");
            System.out.println("1 - Start game;\n2 - About;\n3 - Exit");
            String number = input.nextLine();
            switch(number){
                case "1":
                    System.out.println("Welcome to the MISSION game");
                    System.out.println("Use W (up), A (left), S (down), D (right) to move the player: ");

                    dungeon = new Dungeon(DungeonA.mapArr);
                    dungeon.startLevel();
                    break;
                case "2": System.out.println("This game is made for...");
                break;
                case "3": continueGame = false;
                break;
            }
        }
        System.out.println("Bye!");
    }
}