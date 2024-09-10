package game.Items;

import api.Item;

public class Sword extends Item {
    public Sword(){
        super.attackPower = 10;
        super.defensePower= -1;
        super.itemName = "Sword";
        super.itemID = 3;
    }
}
