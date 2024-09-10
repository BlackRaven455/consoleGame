package game;

import api.Movement;
import api.iLevels;

import java.util.Scanner;

public class Dungeon implements iLevels {
    Player player = new Player();
    Scanner scanner = new Scanner(System.in);
    Movement movement;
    int[][] level = new int[][]{};
    Dungeon(int[][] level){
        this.level = level;
    }
    @Override
    public void startLevel() {
        int[] playerCurrPos;
        this.drawLevel(player.CurrentPos()[0], player.CurrentPos()[1]); // Первоначальная отрисовка уровня


        boolean continueLevel = true;
        while (continueLevel) {
            String input = scanner.nextLine().toUpperCase();
            int prevX = player.CurrentPos()[0]; // Сохраняем предыдущую позицию X
            int prevY = player.CurrentPos()[1]; // Сохраняем предыдущую позицию Y
            if (input.isEmpty()) {
                continue;
            }
            switch (movement) {
                case UP:
                    player.moveUP();
                    break;
                case DOWN:
                    player.moveDown();
                    break;
                case LEFT:
                    player.moveLeft();
                    break;
                case RIGHT:
                    player.moveRight();
                    break;
                default:
                    System.out.println("Invalid input. Use W, A, S, D to move.");
                    continue;
            }

            // Получение новой позиции игрока
            playerCurrPos = player.CurrentPos();
            int newX = playerCurrPos[0];
            int newY = playerCurrPos[1];

            // Проверка на столкновение с границами карты или препятствиями
            if (newX < 0 || newX >= level.length || newY < 0 || newY >= level[0].length || level[newX][newY] == 1) {
                // Если игрок вышел за границы или попал на стену (1), откатываем его обратно
                System.out.println("You hit a wall or boundary! Returning to previous position.");
                player.positionX = prevX;
                player.positionY = prevY;
            }

            // Очистка консоли
            System.out.println("\033[H\033[2J");
            System.out.flush();

            // Перерисовка уровня с новой (или предыдущей) позицией игрока
            this.drawLevel(player.CurrentPos()[0], player.CurrentPos()[1]);
        }
    }
    @Override
    public void drawLevel(int playerPosX, int playerPosY) {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level.length; j++) {
                if (i == playerPosX && j == playerPosY) {
                    System.out.print("P ");
                } else {
                    System.out.print(level[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();

    }
}
