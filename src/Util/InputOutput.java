package Util;

import Units.TeamEnum;
import Units.UnitPlacement;
import map.Block;
import map.MapCreation;

import java.util.ArrayList;
import java.util.Scanner;

public class InputOutput {


    public static String chooseUnitForPlacement(TeamEnum team) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        String chosenUnit = "";

        boolean areKnightsAvailable = false;
        boolean areDwarvesAvailable = false;
        boolean areElvesAvailable = false;

        for (int i = 0; i < UnitPlacement.units.size(); i++) {
            if (UnitPlacement.units.get(i).getUnitSymbol().equals("%") && UnitPlacement.units.get(i).getTeam() == team)
                areKnightsAvailable = UnitPlacement.units.get(i).getCanUnitBePlaced();
        }

        for (int i = 0; i < UnitPlacement.units.size(); i++) {
            if (UnitPlacement.units.get(i).getUnitSymbol().equals("@") && UnitPlacement.units.get(i).getTeam() == team)
                areDwarvesAvailable = UnitPlacement.units.get(i).getCanUnitBePlaced();
        }

        for (int i = 0; i < UnitPlacement.units.size(); i++) {
            if (UnitPlacement.units.get(i).getUnitSymbol().equals("&") && UnitPlacement.units.get(i).getTeam() == team)
                areElvesAvailable = UnitPlacement.units.get(i).getCanUnitBePlaced();
        }


        do {
            System.out.println("Разполагате със следните единици:");
            if (areKnightsAvailable)
                System.out.println("(1) Рицар");
            if (areDwarvesAvailable)
                System.out.println("(2) Джудже");
            if (areElvesAvailable)
                System.out.println("(3) Елф");
            System.out.println("Коя единица искате да поставите:");

            choice = scanner.nextInt();
        } while (choice < 1 || choice > 3);


        if (choice == 1) {
            chosenUnit = "%";
        }

        if (choice == 2) {
            chosenUnit = "@";
        }

        if (choice == 3) {
            chosenUnit = "&";
        }

        return chosenUnit;
    }

    public static void printField() {
        for (ArrayList<Block> row : MapCreation.listOfRows) {
            for (Block col : row) {
                System.out.printf("%4s", col.getBlockMarker());
            }
            System.out.println();
        }
    }

    public static int[] getPlacementCoordinates(int minRow, int maxRow, int minCol, int maxCol) {

        Scanner scanner = new Scanner(System.in);
        int[] coordinates = new int[2];
        int row;
        int col;

        do {
            System.out.println("Въведете ред който се намира във валидната територия");
            row = scanner.nextInt();

        } while (row <= minRow || row >= maxRow);

        do {
            System.out.println("Въведете колона която се намира във валидната територия");
            col = scanner.nextInt();

        } while (col <= minCol || col >= maxCol);

        coordinates[0] = row;
        coordinates[1] = col;

        return coordinates;

    }

    public static int selectPlayerUnit(TeamEnum team, String message1, String message2) {
        Scanner scanner = new Scanner(System.in);
        int selectedUnitIndex = -1;
        int row, col;
        boolean hasUnitBeenSelected = false;
        do {
            System.out.println(message1);
            row = scanner.nextInt();
            System.out.println(message1);
            col = scanner.nextInt();

            for (int i = 0; i < UnitPlacement.units.size(); i++) {
                if (UnitPlacement.units.get(i).getUnitRow() == row && UnitPlacement.units.get(i).getUnitCol() == col && UnitPlacement.units.get(i).getTeam() == team && UnitPlacement.units.get(i).getIsUnitAlive()) {

                    selectedUnitIndex = i;
                    hasUnitBeenSelected = true;
                }

            }

        } while (!hasUnitBeenSelected);


        return selectedUnitIndex;
    }

    public static int[] getCoordinates() {

        int[] coordinates = new int[2];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Въведете ред на който искате да преместите избрания герой");
        int row = scanner.nextInt();
        System.out.println("Въведете колона на която искате да преместите избрания герой");
        int col = scanner.nextInt();
        coordinates[0] = row;
        coordinates[1] = col;

        return coordinates;

    }

    public static int getUserDecisionForRound(boolean isAttackAvailable) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("(1) Move unit");
        if (isAttackAvailable)
            System.out.println("(2) Attack enemy unit");
        System.out.println("(3) Heal unit");

        return scanner.nextInt();

    }


}
