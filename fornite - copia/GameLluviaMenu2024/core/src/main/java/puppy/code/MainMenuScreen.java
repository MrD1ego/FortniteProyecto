package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    final GameLluviaMenu game;
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;

    public MainMenuScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

        // Configuración de la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.getData().setScale(2, 2);
        font.draw(batch, "Bienvenido a FORTNITE", 100, camera.viewportHeight / 2 + 50);

        // Mostrar puntaje más alto desde el Singleton
        GlobalSettings settings = GlobalSettings.getInstance();
        font.draw(batch, "Puntaje más alto: " + settings.getHighScore(), 100, camera.viewportHeight / 2);
        font.draw(batch, "Toca en cualquier lugar para comenzar", 100, camera.viewportHeight / 2 - 50);

        batch.end();

        // Cambiar a la pantalla del juego al tocar
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}