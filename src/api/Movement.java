package api;


public enum Movement {
    UP('W'), DOWN('S'), LEFT('A'), RIGHT('D'), NULL(' ');

    char butt;

    Movement(char b) {
        this.butt = b;
    }

    // Метод для поиска движения по символу
    public static Movement fromChar(char input) {
        for (Movement movement : Movement.values()) {  // Используем автоматически сгенерированный метод values()
            if (movement.butt == input) {
                return movement;
            }
        }
        return Movement.NULL; // Возвращает NULL, если символ не соответствует ни одному движению
    }
}

