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

        if (deadRedUnits == 1) {
            areAllRedUnitsDead = true;
        }

        if (deadBlackUnits == 1) {
            areAllBlackUnitsDead = true;
        }

    }

    private static String getNameForKilledUnit(String killedUnitSymbol) {

        String unitName = "";

        if (killedUnitSymbol.equals("%"))
            unitName = "Рицар";
        if (killedUnitSymbol.equals("@"))
            unitName = "Джуде";
        if (killedUnitSymbol.equals("&"))
            unitName = "Елф";

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
            System.out.println("Червените спечелиха. Поздравления!");
        }
        if (areAllRedUnitsDead) {
            System.out.println("Черните спечелиха. Поздравления!");
        }


        System.out.println("Играта е приключила за " + Gameplay.turnCounter + " рунда");
        System.out.println("Точки на червените: " + Gameplay.redTeamPoints);
        System.out.println("Точки на черните: " + Gameplay.blackTeamPoints);


        System.out.println("Убити червени герои в хронологичен ред");
        printRedDeadUnits();
        printBlackDeadUnits();

        System.out.println("Aко искате да играете пак натиснете зеленото триъгълниче горе в дясно *намиг* *намиг*");
        System.exit(0);

    }


}
