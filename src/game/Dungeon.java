package game;

import api.Enemy;
import api.Movement;
import api.iLevels;
import game.Items.Shield;
import game.Items.Sword;
import game.enemies.Slime;
import game.lvls.DungeonA;
import game.lvls.DungeonB;

import java.util.ArrayList;
import java.util.Scanner;

public class Dungeon {
    Player player = new Player();
    Scanner scanner = new Scanner(System.in);
    int[][] level;
    iLevels[] dungeonLevels = new iLevels[]{new DungeonA(), new DungeonB()}; // Инициализация уровней
    ArrayList<Enemy> enemy = new ArrayList<>();
    int numberOfEnemies;
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
            } else if (level[newY][newX] == 3) {
                player.equip(new Sword());
            } else if (level[newY][newX] == 4) {
                player.equip(new Shield());
            } else if (level[newY][newX] == 5) {
                player.attack(enemy.get(0));
                System.out.println("Slime attack power:" + enemy.get(0).getAttackPower() + ".Player HP" + player.getHP());
                System.out.println("Slime Hp:" + enemy.get(0).getHP());
                player.setPosition(prevX, prevY);
                if (enemy.get(0).getHP() <=0){
                    level[enemy.get(0).getPosY()][enemy.get(0).getPosX()] = '0';
                }
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
                } else if (level[i][j] == 5) {
                    enemy.add(new Slime());
                    numberOfEnemies++;
                    enemy.get(numberOfEnemies).setPosX(j);
                    enemy.get(numberOfEnemies).setPosY(i);
                    System.out.print("E ");
                } else if (level[i][j] == 4) {
                    System.out.print("Q ");
                } else if (level[i][j] == 3) {
                    System.out.print("S ");
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
