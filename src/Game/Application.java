package Game;

import Units.TeamEnum;
import Units.UnitPlacement;
import Util.InputOutput;
import map.MapCreation;

import static Game.Gameplay.currentPlayerTeam;

//Поздрав с песента на NF - Layers

public class Application {


    public static void gameLoop() {
        if (Gameplay.turnCounter % 2 == 0) {
            currentPlayerTeam = TeamEnum.BLACK;
        } else {
            currentPlayerTeam = TeamEnum.RED;
        }

        Gameplay.printCurrentPlayerTurn();
        InputOutput.printField();

        int userChoice = InputOutput.getUserDecisionForRound(Gameplay.isAttackPossibleForRound());

        switch (userChoice) {
            case 1: {
                Gameplay.moveUnit();
                break;
            }
            case 2: {
                if (Gameplay.isAttackPossibleForRound()) {
                    Gameplay.attackUnit();
                } else
                    System.out.println("No valid attacks available don't click it unless it shows up in the menu dumbass");
                break;
            }
            case 3: {
                Gameplay.healUnit();
                break;
            }
        }

        Gameplay.turnCounter++;
        EndGame.killedUnitCheckerForRound();
        EndGame.hasGameEnded();

        if (EndGame.areAllRedUnitsDead || EndGame.areAllBlackUnitsDead){
            EndGame.printEndGameMessage();
        }


        gameLoop();
    }


    public static void main(String[] args) {

        MapCreation.bootstrapMap();
        System.out.println("Разполагате с 2 героя от всеки клас. Използвайте ги разумно");
        UnitPlacement.unitPlacement();
        MapCreation.generateObstacles();
        InputOutput.printField();

        gameLoop();

    }

}
