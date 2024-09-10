package game.lvls;

import api.iLevels;

public class DungeonA implements iLevels {

    public static int[][] mapArr = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 3, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 1, 5, 1},
            {1, 0, 0, 0, 0, 0, 0, 2, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1}}; //9x9

    @Override
    public  int[][] getMapArr() {
        return mapArr;
    }
}