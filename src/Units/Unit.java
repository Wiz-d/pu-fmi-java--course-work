package Units;

public abstract class Unit {

    protected String unitSymbol;
    protected int attack;
    protected int armor;
    protected int health;
    protected int attackRange;
    protected int moveRange;
    protected int unitRow;
    protected int unitCol;
    protected TeamEnum team;
    protected boolean isUnitAlive;
    protected boolean isUnitPlaced = false;
    protected int unitsPlaced = 0;

    public Unit(String unitSymbol, int attack, int armor, int health, int attackRange, int moveRange, TeamEnum team, boolean isUnitAlive) {
        this.unitSymbol = unitSymbol;
        this.attack = attack;
        this.armor = armor;
        this.health = health;
        this.attackRange = attackRange;
        this.moveRange = moveRange;
        this.team = team;
        this.isUnitAlive = isUnitAlive;
    }

    public void setUnitRow(int unitRow) {
        this.unitRow = unitRow;
    }

    public void setUnitCol(int unitCol) {
        this.unitCol = unitCol;
    }

    public int getUnitRow() {
        return this.unitRow;
    }

    public int getUnitCol() {
        return this.unitCol;
    }

    public String getUnitSymbol() {
        return this.unitSymbol;
    }

    public String getUnitSymbolForRendering() {
        String unitSymbolForRender = "";
        if (this.team == TeamEnum.BLACK) {
            unitSymbolForRender = "b" + this.unitSymbol;
        }

        if (this.team == TeamEnum.RED) {
            unitSymbolForRender = "r" + this.unitSymbol;
        }

        return unitSymbolForRender;
    }

    public TeamEnum getTeam() {
        return this.team;
    }

    public void increaseUnitsPlaced() {
        this.unitsPlaced++;
    }

    public boolean getCanUnitBePlaced() {
        return this.unitsPlaced < 2;
    }

    public void setIsUnitPlaced(boolean isUnitPlaced) {
        this.isUnitPlaced = isUnitPlaced;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getArmor() {
        return this.armor;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setIsUnitAlive(boolean isUnitAlive) {
        this.isUnitAlive = isUnitAlive;
    }

    public boolean getIsUnitAlive() {
        return this.isUnitAlive;
    }


    public abstract boolean isMovePossible(int toRow, int toCol);

    public abstract void moveUnit(int toRow, int toCol, int selectedUnitIndex);

    public abstract boolean isAttackPossible();

}
