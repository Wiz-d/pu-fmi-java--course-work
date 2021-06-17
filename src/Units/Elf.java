package Units;

import map.MapCreation;

public class Elf extends Unit {

    public Elf(String unitSymbol, int attack, int armor, int health, int attackRange, int moveRange, TeamEnum team, boolean isUnitAlive) {
        super(unitSymbol, attack, armor, health, attackRange, moveRange, team, isUnitAlive);
    }

    @Override
    public boolean isMovePossible(int toRow, int toCol) {
        int rowCoefficient = Math.abs(toRow - this.unitRow);
        int colCoefficient = Math.abs(toCol - this.unitCol);

        boolean isMoveStraight = toRow == this.unitRow || toCol == this.unitCol;
        boolean isHorseMove = false;
        boolean isMoveWithinRange = (rowCoefficient <= 3 && colCoefficient <= 3);
        boolean isDesiredBlockObstructed = (MapCreation.listOfRows.get(toRow).get(toCol).getBlockMarker().equals("."));

        if (!isMoveStraight) {
            if (2 == rowCoefficient && 1 == colCoefficient) {
                isHorseMove = true;
            }
            if (2 == colCoefficient && 1 == rowCoefficient) {
                isHorseMove = true;
            }
        } else if (isMoveWithinRange && isDesiredBlockObstructed) {
            return true;
        }

        return isHorseMove && isDesiredBlockObstructed;
    }

    @Override
    public void moveUnit(int toRow, int toCol, int selectedUnitIndex) {
        MapCreation.listOfRows.get(this.unitRow).get(this.unitCol).setBlockMarker(".");
        this.unitRow = toRow;
        this.unitCol = toCol;
        MapCreation.listOfRows.get(this.unitRow).get(this.unitCol).setBlockMarker(UnitPlacement.units.get(selectedUnitIndex).getUnitSymbolForRendering());
    }

    @Override
    public boolean isAttackPossible() {

        boolean isUpOutOfBounds = (this.unitRow - 3 < 0);
        boolean isBellowOutOfBounds = (this.unitRow + 3 > 6);
        boolean isLeftOutOfBounds = (this.unitCol - 3 < 0);
        boolean isRightOutOfBounds = (this.unitCol + 3 > 8);

        boolean isAboveAttackPossible = false;
        boolean isBellowAttackPossible = false;
        boolean isLeftAttackPossible = false;
        boolean isRightAttackPossible = false;


        if (!isUpOutOfBounds)
            isAboveAttackPossible = (!MapCreation.listOfRows.get(this.unitRow - 3).get(this.unitCol).getBlockMarker().equals(".") && !MapCreation.listOfRows.get(this.unitRow - 3).get(this.unitCol).getBlockMarker().equals("#"));
        if (!isBellowOutOfBounds)
            isBellowAttackPossible = (!MapCreation.listOfRows.get(this.unitRow + 3).get(this.unitCol).getBlockMarker().equals(".") && !MapCreation.listOfRows.get(this.unitRow + 3).get(this.unitCol).getBlockMarker().equals("#"));
        if (!isLeftOutOfBounds)
            isLeftAttackPossible = (!MapCreation.listOfRows.get(this.unitRow).get(this.unitCol - 3).getBlockMarker().equals(".") && !MapCreation.listOfRows.get(this.unitRow).get(this.unitCol - 3).getBlockMarker().equals("#"));
        if (!isRightOutOfBounds)
            isRightAttackPossible = (!MapCreation.listOfRows.get(this.unitRow).get(this.unitCol + 3).getBlockMarker().equals(".") && !MapCreation.listOfRows.get(this.unitRow).get(this.unitCol + 3).getBlockMarker().equals("#"));


        return isAboveAttackPossible ||
                isBellowAttackPossible ||
                isLeftAttackPossible ||
                isRightAttackPossible;
    }


}
