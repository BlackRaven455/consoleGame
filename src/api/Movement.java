package api;

public enum Movement {
    UP('W'), DOWN('S'), LEFT('A'), RIGHT('D'), NULL(' ');
    char butt;
    final Movement[] values = Movement.values();
    Movement(char b){
        this.butt = b;
    }
    public static Movement fromChar(char input) {
        for (Movement movement : Movement.values()) {
            if (movement.butt == input) {
                return movement;
            }
        }
        return Movement.NULL; // Возвращает NULL, если символ не соответствует ни одному движению
    }
}
