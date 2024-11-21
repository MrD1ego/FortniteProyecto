package puppy.code;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

<<<<<<< HEAD
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
=======
	public class GameLluviaMenu extends Game {

		private SpriteBatch batch;
		private BitmapFont font;
		private int higherScore;

		public void create() {
			batch = new SpriteBatch();
			font = new BitmapFont(); // use libGDX's default Arial font
			this.setScreen(new MainMenuScreen(this));
		}

		public void render() {
			super.render(); // important!
		}

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

		public int getHigherScore() {
			return higherScore;
		}

		public void setHigherScore(int higherScore) {
			this.higherScore = higherScore;
		}
		

	}
>>>>>>> f7315b0b65fa87fb1f17edf4f25b751ce8b4ae01
