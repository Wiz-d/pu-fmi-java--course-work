package map;

import java.util.ArrayList;
import java.util.Random;

public class MapCreation {

    private static final String WALL_MARKER = "#";
    private static final String BARRICADE_MARKER = "$";


    public static ArrayList<ArrayList<Block>> listOfRows = new ArrayList<>();

    public static void bootstrapMap() {
        createMap();
        markRedTerritory();
        markBlackTerritory();
    }

    private static int[] getObstacleCoordinates(Random random) {

        int[] coordinates = new int[2];
        int rowLowerBorder = 2;
        int rowHigherBorder = 3;
        int colHigherBorder = 7;

        int randomRow = random.nextInt(rowHigherBorder);
        int randomCol = random.nextInt(colHigherBorder);

        int tempRow = randomRow;
        randomRow = tempRow + rowLowerBorder;

        coordinates[0] = randomRow;
        coordinates[1] = randomCol;


        return coordinates;
    }

    private static String selectRandomObstacle(Random random) {

        String obstacle;
        int randomObstacle;
        randomObstacle = random.nextInt(2);
        obstacle = (randomObstacle == 0)
                ? WALL_MARKER
                : BARRICADE_MARKER;

        return obstacle;
    }

    private static String selectObstacleType(int iteration, Random random) {

        return (iteration == 0)
                ? WALL_MARKER
                : selectRandomObstacle(random);
    }

    public static void generateObstacles() {
        Random random = new Random();
        int numberOfObstacles;
        numberOfObstacles = random.nextInt(6);
        int[] coordinates;

        if (numberOfObstacles == 0) {
            numberOfObstacles++;
        }

        for (int i = 0; i < numberOfObstacles; i++) {
            coordinates = getObstacleCoordinates(random);
            String obstacle = selectObstacleType(i, random);

            if (!listOfRows.get(coordinates[0]).get(coordinates[1]).getBlockMarker().equals("#")) {

                listOfRows.get(coordinates[0]).get(coordinates[1]).setBlockMarker(obstacle);
                listOfRows.get(coordinates[0]).get(coordinates[1] + 1).setBlockMarker(obstacle);
            }

        }

    }


    private static void markBlackTerritory() {

        for (int row = 5; row < 7; row++) {
            for (int col = 0; col < 9; col++) {
                listOfRows.get(row).get(col).setIsBlockBlackTerritory(true);
            }
        }
    }

    private static void markRedTerritory() {

        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 9; col++) {
                listOfRows.get(row).get(col).setIsBlockRedTerritory(true);
            }
        }
    }

    private static void createMap() {

        for (int row = 0; row < 7; row++) {
            ArrayList<Block> listOfCol = new ArrayList<>();
            for (int col = 0; col < 9; col++) {
                Block mapBlock = new Block(".");
                listOfCol.add(mapBlock);
            }
            listOfRows.add(listOfCol);
        }
    }


}
