package puppy.code;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameLluviaMenu extends Game {
    private SpriteBatch batch;
    private BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(); // Fuente predeterminada de libGDX

        // Configurar valores globales al inicio del juego usando Singleton
        GlobalSettings settings = GlobalSettings.getInstance();
        settings.setVolume(0.8f); // Configurar volumen inicial
        settings.setHighScore(0); // Configurar puntaje más alto inicial

        // Depuración: Mostrar volumen inicial
        System.out.println("Volumen inicial: " + settings.getVolume());

        // Configurar la pantalla inicial
        this.setScreen(new MainMenuScreen(this));
    }
    
    public BitmapFont createFont(float scaleX, float scaleY) {
        BitmapFont font = new BitmapFont(); // Crear nueva fuente
        font.getData().setScale(scaleX, scaleY); // Configurar la escala de esta instancia
        return font;
    }

    @Override
    public void render() {
        super.render(); // Renderizado principal
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }
}
