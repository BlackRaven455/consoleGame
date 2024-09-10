package game.Items;

import api.Item;

public class Shield extends Item {
    public Shield(){
        super.attackPower = -1;
        super.defensePower = 7;
        super.itemID = 4;
        super.itemName = "Shield";
    }
}
