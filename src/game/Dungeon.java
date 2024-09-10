package game;

import api.Movement;
import api.iLevels;
import game.lvls.DungeonA;
import game.lvls.DungeonB;

import java.util.Scanner;

public class Dungeon {
    Player player = new Player();
    Scanner scanner = new Scanner(System.in);
    int[][] level;
    iLevels[] dungeonLevels = new iLevels[]{new DungeonA(), new DungeonB()}; // Инициализация уровней

    public Dungeon(int[][] level) {
        this.level = level;
    }

    public void startLevel() {
        int[] playerCurrPos;
        this.drawLevel(player.CurrentPos()[1], player.CurrentPos()[0]); // Первоначальная отрисовка уровня

        boolean continueLevel = true;
        while (continueLevel) {
            String input = scanner.nextLine().toUpperCase();
            if (input.isEmpty()) {
                continue;
            }

            Movement movement = Movement.fromChar(input.charAt(0));

            int prevX = player.CurrentPos()[1]; // Сохраняем предыдущую позицию X
            int prevY = player.CurrentPos()[0]; // Сохраняем предыдущую позицию Y
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
            int newX = playerCurrPos[1];
            int newY = playerCurrPos[0];

            // Проверка на столкновение с границами карты или препятствиями
            if (newY < 0 || newY >= level.length || newX < 0 || newX >= level[0].length || level[newY][newX] == 1) {
                // Если игрок вышел за границы или попал на стену (1), откатываем его обратно
                System.out.println("You hit a wall or boundary! Returning to previous position.");
                player.setPosition(prevX, prevY);
            } else if (level[newY][newX] == 2) {
                // Если игрок достиг цели, смена уровня
                System.out.println("Congratulations! You have cleared a level!");
                this.level = dungeonLevels[1].getMapArr(); // Получение нового уровня
                player.setPosition(1, 1); // Перемещение игрока в начальную позицию нового уровня
                // Можете добавить логику, чтобы переключаться на следующий уровень, если это необходимо
            }

            // Очистка консоли
            System.out.println("\033[H\033[2J");
            System.out.flush();

            // Перерисовка уровня с новой (или предыдущей) позицией игрока
            this.drawLevel(player.CurrentPos()[1], player.CurrentPos()[0]);
        }
    }

    public void drawLevel(int playerPosX, int playerPosY) {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) { // Используйте level[i].length вместо level.length
                if (i == playerPosY && j == playerPosX) {
                    System.out.print("P ");
                } else if (level[i][j] == 2) {
                    System.out.print("% ");
                } else if (level[i][j] == 1) {
                    System.out.print("■ ");
                } else if (level[i][j] == 0) {
                    System.out.print("  "); // Пустое пространство
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
