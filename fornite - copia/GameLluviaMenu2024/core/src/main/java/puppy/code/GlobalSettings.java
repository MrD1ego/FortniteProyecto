package puppy.code;

public class GlobalSettings {
    private static GlobalSettings instance; // Única instancia de la clase
    private float volume = 0.5f; // Valor predeterminado del volumen
    private int highScore = 0;  // Puntaje más alto registrado

    // Constructor privado para evitar instanciación externa
    private GlobalSettings() {}

    // Método para obtener la única instancia de la clase
    public static GlobalSettings getInstance() {
        if (instance == null) {
            instance = new GlobalSettings();
        }
        return instance;
    }

    // Métodos para gestionar el volumen
    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    // Métodos para gestionar el puntaje más alto
    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
