package Units;

import Util.InputOutput;
import map.Block;
import map.MapCreation;

import java.util.ArrayList;

public class UnitPlacement {

    public static ArrayList<Unit> units = new ArrayList<>();

    private static void initializeUnits(TeamEnum team) {

        for (int i = 0; i < 2; i++) {
            Knight knight = new Knight("%", 8, 3, 15, 1, 1, team, true);
            units.add(knight);

            Elf elf = new Elf("&", 5, 1, 10, 3, 3, team, true);
            units.add(elf);

            Dwarf dwarf = new Dwarf("@", 6, 2, 12, 2, 2, team, true);
            units.add(dwarf);
        }

    }


    private static void markValidUnitPlacementForRed() {

        for (ArrayList<Block> row : MapCreation.listOfRows) {
            for (Block col : row) {
                if (col.getIsBlockRedTerritory() && col.getBlockMarker().equals(".")) {
                    col.setBlockMarker("X");
                }
            }
        }

    }

    private static void markValidUnitPlacementForBlack() {

        for (ArrayList<Block> row : MapCreation.listOfRows) {
            for (Block col : row) {
                if (col.getIsBlockBlackTerritory() && col.getBlockMarker().equals(".")) {
                    col.setBlockMarker("X");
                }
            }
        }

    }

    private static void removeHelpMarks() {

        for (ArrayList<Block> row : MapCreation.listOfRows) {
            for (Block col : row) {
                if (col.getBlockMarker().equals("X"))
                    col.setBlockMarker(".");
            }
        }

    }

    private static int findUnitIndex(String chosenUnit, TeamEnum team) {
        int unitIndex = 0;
        for (int i = 0; i < UnitPlacement.units.size(); i++) {

            if (units.get(i).getUnitSymbol().equals(chosenUnit) && units.get(i).getCanUnitBePlaced() && units.get(i).getTeam() == team) {
                unitIndex = i;
            }

        }
        return unitIndex;
    }

    private static void placeBlackUnit() {
        System.out.println("Черните са на ход");

        String chosenUnit = InputOutput.chooseUnitForPlacement(TeamEnum.BLACK);
        int unitIndex = findUnitIndex(chosenUnit, TeamEnum.BLACK);

        markValidUnitPlacementForBlack();
        InputOutput.printField();
        int[] coordinates = InputOutput.getPlacementCoordinates(4, 7, -1, 9);
        removeHelpMarks();

        units.get(unitIndex).setUnitRow(coordinates[0]);
        units.get(unitIndex).setUnitCol(coordinates[1]);

        MapCreation.listOfRows.get(coordinates[0]).get(coordinates[1]).setBlockMarker(units.get(unitIndex).getUnitSymbolForRendering());
        units.get(unitIndex).increaseUnitsPlaced();
        units.get(unitIndex).setIsUnitPlaced(true);
        InputOutput.printField();
    }

    private static void placeRedUnit() {
        System.out.println("Червените са на ход");

        String chosenUnit = InputOutput.chooseUnitForPlacement(TeamEnum.RED);
        int unitIndex = findUnitIndex(chosenUnit, TeamEnum.RED);

        markValidUnitPlacementForRed();
        InputOutput.printField();
        int[] coordinates = InputOutput.getPlacementCoordinates(-1, 2, -1, 9);
        removeHelpMarks();

        units.get(unitIndex).setUnitRow(coordinates[0]);
        units.get(unitIndex).setUnitCol(coordinates[1]);

        MapCreation.listOfRows.get(coordinates[0]).get(coordinates[1]).setBlockMarker(units.get(unitIndex).getUnitSymbolForRendering());
        units.get(unitIndex).increaseUnitsPlaced();
        units.get(unitIndex).setIsUnitPlaced(true);
        InputOutput.printField();
    }

    public static void unitPlacement() {

        initializeUnits(TeamEnum.BLACK);
        initializeUnits(TeamEnum.RED);

        for (int i = 0; i < 6; i++) {
            placeBlackUnit();
            placeRedUnit();
        }

    }


}
