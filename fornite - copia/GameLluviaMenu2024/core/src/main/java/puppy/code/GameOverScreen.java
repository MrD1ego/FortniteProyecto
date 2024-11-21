package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

<<<<<<< HEAD
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen { 
=======
public class GameOverScreen implements Screen {
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
	private final GameLluviaMenu game;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private OrthographicCamera camera;

	public GameOverScreen(final GameLluviaMenu game) {
<<<<<<< HEAD
		this.game = game;
		this.batch = game.getBatch();
		this.font = game.getFont();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, 800, 480);
=======
<<<<<<< HEAD
            this.game = game;
            this.batch = game.getBatch();
            this.font = game.getFont();
            camera = new OrthographicCamera();
            camera.setToOrtho(false, 800, 480);
=======
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		font.draw(batch, "Te Bajaron!! ", 100, 200);
		font.draw(batch, "Toca en cualquier lado para reiniciar.", 100, 100);
		batch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

	@Override
	public void show() {
<<<<<<< HEAD
		// Implementación si es necesaria
=======
		// TODO Auto-generated method stub
		
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
	}

	@Override
	public void resize(int width, int height) {
<<<<<<< HEAD
		// Implementación si es necesaria
=======
		// TODO Auto-generated method stub
		
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
	}

	@Override
	public void pause() {
<<<<<<< HEAD
		// Implementación si es necesaria
=======
		// TODO Auto-generated method stub
		
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
	}

	@Override
	public void resume() {
<<<<<<< HEAD
		// Implementación si es necesaria
=======
		// TODO Auto-generated method stub
		
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
	}

	@Override
	public void hide() {
<<<<<<< HEAD
		// Implementación si es necesaria
=======
		// TODO Auto-generated method stub
		
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
	}

	@Override
	public void dispose() {
<<<<<<< HEAD
		// Implementación si es necesaria
	}
}

=======
		// TODO Auto-generated method stub
		
	}

}
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
