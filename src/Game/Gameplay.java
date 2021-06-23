package Game;

import Units.TeamEnum;
import Units.UnitPlacement;
import Util.InputOutput;

import java.util.Random;

public class Gameplay {

    public static int turnCounter = 0;
    public static TeamEnum currentPlayerTeam;
    public static int blackTeamPoints = 0;
    public static int redTeamPoints = 0;

    public static void moveUnit() {

        int selectedUnit = InputOutput.selectPlayerUnit(currentPlayerTeam, "Input row of the unit that you want to move: ", "Input col of the unit that you want to move: ");
        int row;
        int col;

        int[] coordinates;


        do {

            coordinates = InputOutput.getCoordinates();
            row = coordinates[0];
            col = coordinates[1];

        } while (!UnitPlacement.units.get(selectedUnit).isMovePossible(row, col));
        UnitPlacement.units.get(selectedUnit).moveUnit(row, col, selectedUnit);
    }


    private static String calculateAttackSuccess(int currentEnemyHealth) {

        Random random = new Random();
        String diceResult = "";
        int dice1 = random.nextInt(6);
        int dice2 = random.nextInt(6);
        int dice3 = random.nextInt(6);

        if (dice1 == 0) {
            dice1++;
        }
        if (dice2 == 0) {
            dice2++;
        }
        if (dice3 == 0) {
            dice3++;
        }

        int diceSum = dice1 + dice2 + dice3;

        boolean hasAttackFailed = (diceSum == currentEnemyHealth);
        boolean isAttackHalfSuccessful = (diceSum == 3);


        if (hasAttackFailed) {
            diceResult = "fail";
        }
        if (isAttackHalfSuccessful) {
            diceResult = "half";
        }

        if (!hasAttackFailed && !isAttackHalfSuccessful) {
            diceResult = "success";
        }


        return diceResult;
    }


    public static void attackUnit() {

        TeamEnum enemyTeam;

        enemyTeam = getEnemyTeam();

        int selectedUnitIndex = InputOutput.selectPlayerUnit(currentPlayerTeam, "Input row of your chosen hero: ", "Input col of your chosen hero: ");

        if (!UnitPlacement.units.get(selectedUnitIndex).isAttackPossible()) {
            System.out.println("Please select the unit that can attack");
            Application.gameLoop();
        }

        int selectedEnemyUnitIndex = InputOutput.selectPlayerUnit(enemyTeam, "Input row of enemy hero: ", "Input col of enemy hero: ");
        int enemyUnitCurrentHealth = UnitPlacement.units.get(selectedUnitIndex).getHealth();
        String attackSuccess = calculateAttackSuccess(enemyUnitCurrentHealth);

        if (attackSuccess.equals("fail")) {
            System.out.println("Атаката не беше успешна,много кофти за вас");
        }

        int damage;
        int halfDamage;
        if (attackSuccess.equals("half")) {
            damage = UnitPlacement.units.get(selectedUnitIndex).getAttack() - UnitPlacement.units.get(selectedEnemyUnitIndex).getArmor();
            halfDamage = damage / 2;
            enemyUnitCurrentHealth = enemyUnitCurrentHealth - halfDamage;

            if (currentPlayerTeam == TeamEnum.BLACK) {
                blackTeamPoints = blackTeamPoints + halfDamage;
            } else redTeamPoints = redTeamPoints + halfDamage;


        }

        if (attackSuccess.equals("success")) {
            damage = UnitPlacement.units.get(selectedUnitIndex).getAttack() - UnitPlacement.units.get(selectedEnemyUnitIndex).getArmor();
            enemyUnitCurrentHealth = enemyUnitCurrentHealth - damage;

            if (currentPlayerTeam == TeamEnum.BLACK) {
                blackTeamPoints = blackTeamPoints + damage;
            } else redTeamPoints = redTeamPoints + damage;

        }

        if (enemyUnitCurrentHealth > 0) {
            UnitPlacement.units.get(selectedEnemyUnitIndex).setHealth(enemyUnitCurrentHealth);
        }
        if (enemyUnitCurrentHealth <= 0) {
            UnitPlacement.units.get(selectedEnemyUnitIndex).setIsUnitAlive(false);
        }


    }

    private static TeamEnum getEnemyTeam() {
        TeamEnum enemyTeam;
        if (currentPlayerTeam == TeamEnum.BLACK) {
            enemyTeam = TeamEnum.RED;
        } else {
            enemyTeam = TeamEnum.BLACK;
        }
        return enemyTeam;
    }

    public static boolean isAttackPossibleForRound() {
        for (int i = 0; i < UnitPlacement.units.size(); i++) {
            if (UnitPlacement.units.get(i).getTeam() == currentPlayerTeam && UnitPlacement.units.get(i).isAttackPossible())
                return true;
        }
        return false;
    }

    public static void printCurrentPlayerTurn() {

        String phrase = (currentPlayerTeam == TeamEnum.RED)
                ? "Red team's turn"
                : "Black team's turn";
        System.out.println(phrase);

    }


    public static void healUnit() {

        Random random = new Random();
        System.out.println("Choose the unit that you would like to heal");

        int selectedUnit = InputOutput.selectPlayerUnit(currentPlayerTeam, "Input your unit row: ", "Input your unit col: ");
        int pointsHealed = random.nextInt(6);
        int currentUnitHealth = UnitPlacement.units.get(selectedUnit).getHealth();


        if (pointsHealed == 0) {
            pointsHealed++;
        }

        int maxAllowedHealth = selectMaxAllowedHealth(selectedUnit);

        if (pointsHealed > maxAllowedHealth) {
            pointsHealed = 0;
        }

        currentUnitHealth = currentUnitHealth + pointsHealed;

        UnitPlacement.units.get(selectedUnit).setHealth(currentUnitHealth);

        int extraRoundChance = random.nextInt();
        if (Util.ExtraFunctions.isNumberNonPrime(extraRoundChance)) {
            System.out.println("You just won an extra action, congratulations !");
            Application.gameLoop();
        }


    }

    private static int selectMaxAllowedHealth(int selectedUnit) {

        int maxHealth = 0;
        String unit = UnitPlacement.units.get(selectedUnit).getUnitSymbol();
        if (unit.equals("%"))
            maxHealth = 15;
        if (unit.equals("@"))
            maxHealth = 12;
        if (unit.equals("&")) {
            maxHealth = 10;
        }

        return maxHealth;
    }


}
