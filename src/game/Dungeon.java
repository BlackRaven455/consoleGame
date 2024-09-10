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
    private static final int WALL = 1;
    private static final int GOAL = 2;
    private static final int SWORD = 3;
    private static final int SHIELD = 4;
    private static final int ENEMY = 5;

    Player player = new Player();
    Scanner scanner = new Scanner(System.in);
    int[][] level;
    iLevels[] dungeonLevels = new iLevels[]{new DungeonA(), new DungeonB()};
    ArrayList<Enemy> enemies = new ArrayList<>();
    int numberOfEnemies;

    public Dungeon(int[][] level) {
        this.level = level;
        enemyPosition();
    }

    public void enemyPosition() {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == ENEMY) {
                    Enemy newEnemy = new Slime();
                    newEnemy.setPosX(j);
                    newEnemy.setPosY(i);
                    enemies.add(newEnemy);
                }
            }
        }
    }

    public void startLevel() {
        int[] playerCurrPos;
        this.drawLevel(player.CurrentPos()[1], player.CurrentPos()[0]);

        boolean continueLevel = true;
        while (continueLevel) {
            String input = scanner.nextLine().toUpperCase();
            if (input.isEmpty()) {
                continue;
            }

            Movement movement = Movement.fromChar(input.charAt(0));

            int prevX = player.CurrentPos()[1];
            int prevY = player.CurrentPos()[0];
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

            playerCurrPos = player.CurrentPos();
            int newX = playerCurrPos[1];
            int newY = playerCurrPos[0];

            if (newY < 0 || newY >= level.length || newX < 0 || newX >= level[0].length || level[newY][newX] == WALL) {
                System.out.println("You hit a wall or boundary! Returning to previous position.");
                player.setPosition(prevX, prevY);
            } else if (level[newY][newX] == GOAL) {
                System.out.println("Congratulations! You have cleared a level!");
                this.level = dungeonLevels[1].getMapArr();
                enemyPosition();
                player.setPosition(1, 1);
            } else if (level[newY][newX] == SWORD) {
                Sword sword = new Sword();
                player.equip(sword);
                level[newY][newX] = 0;
                System.out.println("Sword(AP = " + sword.getAttackPower() + ") equipped! ");

            } else if (level[newY][newX] == SHIELD) {
                Shield shield = new Shield();
                player.equip(shield);
                level[newY][newX] = 0;
                System.out.println("Shield(DP = " + shield.getDefensePower()+") equipped!");
            } else if (level[newY][newX] == ENEMY) {
                for (Enemy enemy : enemies) {
                    if (enemy.getPosX() == newX && enemy.getPosY() == newY) {
                        System.out.println("Enemy stats: AP -  " + enemy.getAttackPower() + " DP - " + enemy.getDefensePower() + "Enemy HP: " + enemy.getHP());

                        player.attack(enemy);
                        enemy.defense(player);
                        player.setPosition(prevX, prevY);
                        System.out.println("Enemy stats: AP -  " + enemy.getAttackPower() + " DP - " + enemy.getDefensePower() + "Enemy HP: " + enemy.getHP());
                        if (enemy.getHP() <= 0) {
                            level[enemy.getPosY()][enemy.getPosX()] = 0;
                            enemies.remove(enemy);
                        }
                        break; // Exit the loop once we've attacked the correct enemy
                    }
                }
            }

            System.out.println("\033[H\033[2J");
            System.out.flush();

            this.drawLevel(player.CurrentPos()[1], player.CurrentPos()[0]);
        }
    }

    public void drawLevel(int playerPosX, int playerPosY) {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (i == playerPosY && j == playerPosX) {
                    System.out.print("P ");
                } else if (level[i][j] == ENEMY) {
                    System.out.print("E ");
                } else if (level[i][j] == SHIELD) {
                    System.out.print("Q ");
                } else if (level[i][j] == SWORD) {
                    System.out.print("S ");
                } else if (level[i][j] == GOAL) {
                    System.out.print("% ");
                } else if (level[i][j] == WALL) {
                    System.out.print("■ ");
                } else if (level[i][j] == 0) {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Player stats: AP - " + player.getAttackPower() + " DP - " + player.getDefensePower() + " HP - " + player.getHP());

    }
}
