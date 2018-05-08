import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class BitMap {

    private int[][] map;
    private int carAmount = 0;

    public BitMap(int size) {
        map = new int[size][size];
    }

    public BitMap(int[][] map) {
        this.map = map.clone();
    }

    public int getSize() {
        return map.length;
    }

    public int[][] getMap() {
        return map;
    }

    public void createRandomMap(final int amountOfCars) {
        map = new int[map.length][map.length];
        int currentCar = 2;                             //Start at 1 due to always having player car
        map[2][1] = 1;                                  //Setting player's car
        map[2][2] = 1;
        carAmount++;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (currentCar <= amountOfCars) {
                    if (ThreadLocalRandom.current().nextInt(0, 2) == 0) {
                        int currentCarLength = ThreadLocalRandom.current().nextInt(2,4);
                        if (i != 2 && canPlaceCar(i, j, currentCarLength, false)) {
                            for (int k = j; k < j + currentCarLength; k++) {
                                map[i][k] = currentCar;
                            }
                            currentCar++;
                            carAmount++;
                        } else if (canPlaceCar(i, j, currentCarLength, true)) {
                            for (int k = i; k < i + currentCarLength; k++) {
                                map[k][j] = currentCar;
                            }
                            currentCar++;
                            carAmount++;
                        }
                    }
                }
            }
        }
    }

    private boolean canPlaceCar(int i, int j, final int length, final boolean vertical) {
        if (!vertical) {
            if (map.length - j > length) {
                for (int k = j; k < j + length; k++) {
                    if (map[i][k] != 0) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else {
            if (map.length - i > length) {
                for (int k = i; k < i + length; k++) {
                    if (map[k][j] != 0) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public int getCarsToMove() {
        int playerLoc = -1;
        int carsToMove = 0;
        for (int j = 0; j < map.length; j++) {
            if (map[2][j] == 1) {
                playerLoc = j;
            } else if (playerLoc != -1 && map[2][j] != 0) {
                carsToMove++;
            }
        }
        return carsToMove;
    }

    public Set<int[][]> getAllPossibleMoves() {
        Set<int[][]> nextStates = new HashSet<>();
        Set<Integer> carsMoved = new HashSet<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                int car = map[i][j];
                if (car != 0 && !carsMoved.contains(car)) {
                    int carLengthUp = getCarLength(car, i, j, Direction.UP);
                    int carLengthDown = getCarLength(car, i, j, Direction.DOWN);
                    int carLengthLeft = getCarLength(car, i, j, Direction.LEFT);
                    int carLengthRight = getCarLength(car, i, j, Direction.RIGHT);
                    if (canMoveUp(car, carLengthUp, i, j)) {
                        nextStates.add(moveUp(car, carLengthUp));
                        //System.out.println(car + " Up");
                    }
                    if (canMoveDown(car, carLengthDown, i, j)) {
                        //System.out.println(car + " Down");
                        nextStates.add(moveDown(car, carLengthDown));
                    }
                    if (canMoveLeft(car, carLengthLeft, i, j)) {
                        //System.out.println(car + " Left");
                        nextStates.add(moveLeft(car, carLengthLeft));
                    }
                    if (canMoveRight(car, carLengthRight, i, j)) {
                        //System.out.println(car + " Right");
                        nextStates.add(moveRight(car, carLengthRight));
                    }
                    carsMoved.add(car);
                }
            }
        }
        return nextStates;
    }

    public boolean canMoveUp(int car, int carLength, int i, int j) {
        return i > 0 && i < map.length - carLength && map[i - 1][j] == 0 && map[i + 1][j] == car;
    }

    public boolean canMoveDown(int car, int carLength,int i, int j) {
        return i < map.length - carLength && i >= 0 && map[i + carLength][j] == 0 && map[i + 1][j] == car;
    }

    public boolean canMoveLeft(int car, int carLength, int i, int j) {
        return j > 0 && j <= map.length - carLength && map[i][j - 1] == 0 && map[i][j + 1] == car;
    }

    public boolean canMoveRight(int car, int carLength, int i, int j) {
        return j < map.length - carLength && j >= 0 && map[i][j + carLength] == 0 && map[i][j + 1] == car;
    }

    public int getCarLength(int car, int i, int j, Direction d) {
        int length = -1;
        if (d.equals(Direction.UP) || d.equals(Direction.DOWN)) {
            if (i + 2 < map.length && map[i + 2][j] == car) {
                length = 3;
            } else {
                length = 2;
            }
        } else if (d.equals(Direction.LEFT) || d.equals(Direction.RIGHT)) {
            if (j + 2 < map.length && map[i][j + 2] == car) {
                length = 3;
            } else {
                length = 2;
            }
        }
        return length;
    }

    public int[][] moveUp(int car, int carLength) {
        int[][] newMap = new int[map.length][map.length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == car) {
                    if (carLength != -1) {
                        newMap[i + carLength - 1][j] = 0;
                        newMap[i - 1][j] = car;
                        return newMap;
                    }
                }
            }
        }
        return newMap;
    }

    public int[][] moveDown(int car, int carLength) {
        int[][] newMap = new int[map.length][map.length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == car) {
                    if (carLength != -1) {
                        newMap[i][j] = 0;
                        newMap[i + carLength][j] = car;
                        return newMap;
                    }
                }
            }
        }
        return newMap;
    }

    public int[][] moveLeft(int car, int carLength) {
        int[][] newMap = new int[map.length][map.length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == car) {
                    if (carLength != -1) {
                        newMap[i][j + carLength - 1] = 0;
                        newMap[i][j - 1] = car;
                        return newMap;
                    }
                }
            }
        }
        return newMap;
    }

    public int[][] moveRight(int car, int carLength) {
        int[][] newMap = new int[map.length][map.length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == car) {
                    if (carLength != -1) {
                        newMap[i][j] = 0;
                        newMap[i][j + carLength] = car;
                        return newMap;
                    }
                }
            }
        }
        return newMap;
    }

    public String toString() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        return null;
    }

}
