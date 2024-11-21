package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class PausaScreen implements Screen {

	private final GameLluviaMenu game;
	private GameScreen juego;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private OrthographicCamera camera;

	public PausaScreen (final GameLluviaMenu game, GameScreen juego) {
<<<<<<< HEAD
            this.game = game;
            this.juego = juego;
            this.batch = game.getBatch();
            this.font = game.getFont();
            camera = new OrthographicCamera();
            camera.setToOrtho(false, 800, 480);
=======
		this.game = game;
        this.juego = juego;
        this.batch = game.getBatch();
        this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
	}

	@Override
        public void render(float delta) {
            ScreenUtils.clear(0, 0, 1.0f, 0.5f);

            camera.update();
            batch.setProjectionMatrix(camera.combined);

            batch.begin();
            font.draw(batch, "Juego en Pausa ", 100, 150);
            font.draw(batch, "Toca en cualquier lado para continuar o presiona ESC/P", 100, 100);
            batch.end();

         // Reanudar el juego al tocar o presionar ESC o P
        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            game.setScreen(juego);
            dispose();
            }
        }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

