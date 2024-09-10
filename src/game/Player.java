package game;
import api.iPlayer;

public class Player implements iPlayer {
     public int positionY = 1; // Represents column
     public int positionX = 1; // Represents raw

    // Getter for current position
    public int[] CurrentPos(){
        return new int[]{positionY, positionX}; // Returning the current position as an array
    }

    @Override
    public void moveUP() {
        // Moving up decreases the row number (currentPosI)
        positionY--;
    }

    @Override
    public void moveDown() {
        // Moving down increases the row number (currentPosI)
        positionY++;
    }

    @Override
    public void moveLeft() {
        // Moving left decreases the column number (currentPosJ)
        positionX--;
    }

    @Override
    public void moveRight() {
        // Moving right increases the column number (currentPosJ)
        positionX++;
    }

    public void setPosition(int prevX, int prevY) {
        positionX = prevX;
        positionY = prevY;
    }
}

