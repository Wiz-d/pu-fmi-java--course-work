package Game;

import Units.TeamEnum;
import Units.UnitPlacement;

import java.util.ArrayList;

public class EndGame {
    private static ArrayList<String> killedRedUnits = new ArrayList<>();
    private static ArrayList<String> killedBlackUnits = new ArrayList<>();
    public static boolean areAllRedUnitsDead = false;
    public static boolean areAllBlackUnitsDead = false;


    private static TeamEnum findKilledUnitTeam(int unitIndex) {

        TeamEnum team = TeamEnum.RED;
        if (UnitPlacement.units.get(unitIndex).getTeam() == TeamEnum.BLACK) {
            team = TeamEnum.BLACK;
        }

        return team;
    }

    private static void putKilledUnitIntoList(String unitSymbol, TeamEnum team) {

        if (team == TeamEnum.RED) {
            killedRedUnits.add(unitSymbol);
        }

        if (team == TeamEnum.BLACK) {
            killedBlackUnits.add(unitSymbol);
        }

    }

    public static void killedUnitCheckerForRound() {

        TeamEnum team;
        for (int i = 0; i < UnitPlacement.units.size(); i++) {
            if (!UnitPlacement.units.get(i).getIsUnitAlive()) {

                String unitSymbol = UnitPlacement.units.get(i).getUnitSymbol();
                team = findKilledUnitTeam(i);
                putKilledUnitIntoList(unitSymbol, team);

            }
        }
    }

    public static void hasGameEnded() {
        int deadRedUnits = 0;
        int deadBlackUnits = 0;

        for (int i = 0; i < UnitPlacement.units.size(); i++) {
            if (!UnitPlacement.units.get(i).getIsUnitAlive() && UnitPlacement.units.get(i).getTeam() == TeamEnum.RED)
                deadRedUnits++;
        }

        for (int i = 0; i < UnitPlacement.units.size(); i++) {
            if (!UnitPlacement.units.get(i).getIsUnitAlive() && UnitPlacement.units.get(i).getTeam() == TeamEnum.BLACK)
                deadBlackUnits++;
        }

        if (deadRedUnits == 6) {
            areAllRedUnitsDead = true;
        }

        if (deadBlackUnits == 6) {
            areAllBlackUnitsDead = true;
        }

    }

    private static String getNameForKilledUnit(String killedUnitSymbol) {

        String unitName = "";

        if (killedUnitSymbol.equals("%"))
            unitName = "Knight";
        if (killedUnitSymbol.equals("@"))
            unitName = "Dwarf";
        if (killedUnitSymbol.equals("&"))
            unitName = "Elf";

        return unitName;
    }


    private static void printBlackDeadUnits() {
        for (int i = killedBlackUnits.size() - 1; i >= 0; i--) {
            System.out.println(killedBlackUnits.get(i) + " " + getNameForKilledUnit(killedBlackUnits.get(i)));
        }
    }

    private static void printRedDeadUnits() {
        for (int i = killedRedUnits.size() - 1; i >= 0; i--) {
            System.out.println("(" + i + ")" + killedRedUnits.get(i) + " " + getNameForKilledUnit(killedRedUnits.get(i)));
        }
    }

    public static void printEndGameMessage() {
        if (areAllBlackUnitsDead) {
            System.out.println("Red won. Congratulations!");
        }
        if (areAllRedUnitsDead) {
            System.out.println("Black won. Congratulations!");
        }


        System.out.println("The game has ended for " + Gameplay.turnCounter + " rounds");
        System.out.println("Red team points: " + Gameplay.redTeamPoints);
        System.out.println("Black team points: " + Gameplay.blackTeamPoints);


        System.out.println("Killed red units in chronological order: ");
        printRedDeadUnits();
        System.out.println("Killed black units in chronological order: ");
        printBlackDeadUnits();

        System.out.println("If you want to play again feel free to click the green triangle in the top right :)");
        System.exit(0);

    }


}
