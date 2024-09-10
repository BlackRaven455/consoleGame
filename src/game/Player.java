package game;
import api.iPlayer;

public class Player implements iPlayer {
     public int positionY = 1; // Represents raw
     public int positionX = 1; // Represents column

    // Getter for current position
    public int[] CurrentPos(){
        return new int[]{positionY, positionX}; // Returning the current position as an array
    }

    @Override
    public void moveUP() {
        // Moving up decreases the row number (currentPosY)
        positionY--;
    }

    @Override
    public void moveDown() {
        // Moving down increases the row number (currentPosY)
        positionY++;
    }

    @Override
    public void moveLeft() {
        // Moving left decreases the column number (currentPosX)
        positionX--;
    }

    @Override
    public void moveRight() {
        // Moving right increases the column number (currentPosX)
        positionX++;
    }

    public void setPosition(int prevX, int prevY) {
        positionX = prevX;
        positionY = prevY;
    }
}

