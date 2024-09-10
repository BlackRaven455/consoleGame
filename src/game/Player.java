package game;
import api.Enemy;
import api.Item;

import java.util.ArrayList;

public class Player{
    public int positionY = 1; // Represents raw
    public int positionX = 1; // Represents column
    private ArrayList<Item> itemArrayList = new ArrayList<>();

    private int HP = 100;
    private int attackPower = 2;
    private int defensePower = 2;

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }

    public void setItemArrayList(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getDefensePower() {
        return defensePower;
    }

    public void setDefensePower(int defensePower) {
        this.defensePower = defensePower;
    }

    public void equip(Item item){
        itemArrayList.add(item);
        this.attackPower = this.attackPower + item.getAttackPower();
        this.defensePower = this.defensePower + item.getDefensePower();
    }
    public void attack(Enemy enemy){
        enemy.setHP(this.attackPower);
    }
    public int getHP(){
        return this.HP;
    }
    public void defense(Enemy enemy){
        if(enemy.getAttackPower() > this.defensePower){
            this.HP = this.HP+ this.defensePower - enemy.getAttackPower();
        }
    }
    // Getter for current position
    public int[] CurrentPos(){
        return new int[]{positionY, positionX}; // Returning the current position as an array
    }


    public void moveUP() {
        // Moving up decreases the row number (currentPosY)
        positionY--;
    }


    public void moveDown() {
        // Moving down increases the row number (currentPosY)
        positionY++;
    }


    public void moveLeft() {
        // Moving left decreases the column number (currentPosX)
        positionX--;
    }


    public void moveRight() {
        // Moving right increases the column number (currentPosX)
        positionX++;
    }

    public void setPosition(int prevX, int prevY) {
        positionX = prevX;
        positionY = prevY;
    }
}

