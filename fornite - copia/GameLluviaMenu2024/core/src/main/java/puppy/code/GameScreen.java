package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final GameLluviaMenu game;
    private OrthographicCamera camera;
    private SpriteBatch batch;	   
    private BitmapFont font;
    private Tarro tarro;
    private Lluvia lluvia;
    private Texture background;
    private boolean fondoCambiado = false;
    private Texture background2;
    private Texture gotaMala2;

    public GameScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

        // Configuración de la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        background2 = new Texture(Gdx.files.internal("background2.png"));
        gotaMala2 = new Texture(Gdx.files.internal("dropBad2.png"));
        
        // Inicialización de elementos del juego
        background = new Texture(Gdx.files.internal("background.png"));
        tarro = new Tarro(new Texture(Gdx.files.internal("bucket.png")), 
                          Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")),
                          new Texture(Gdx.files.internal("Bala.png")));
        lluvia = new Lluvia(new Texture(Gdx.files.internal("drop.png")),
                            new Texture(Gdx.files.internal("dropBad.png")),
                            new Texture(Gdx.files.internal("specialDrop.png")),
                            new Texture(Gdx.files.internal("dropNew.png")),
                            new Texture(Gdx.files.internal("specialDropNew.png")),
                            new Texture(Gdx.files.internal("llama.png")),
                            new Texture(Gdx.files.internal("jetpack.png")),
                            Gdx.audio.newSound(Gdx.files.internal("jetpackSound.mp3")),
                            Gdx.audio.newSound(Gdx.files.internal("soundLlama.mp3")),
                            Gdx.audio.newSound(Gdx.files.internal("drop.wav")),
                            Gdx.audio.newMusic(Gdx.files.internal("rain.mp3")),
                            new Texture(Gdx.files.internal("Tomate.png")),
                            Gdx.audio.newMusic(Gdx.files.internal("jefe.mp3")));
        tarro.crear();
        lluvia.crear();
    }

    @Override
    public void render(float delta) {
        // Actualizar puntaje más alto usando Singleton
        GlobalSettings settings = GlobalSettings.getInstance();
        if (tarro.getPuntos() > settings.getHighScore()) {
            settings.setHighScore(tarro.getPuntos());
        }

        // Renderizar el juego
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        
        
        if (tarro.getPuntos() >= 1000 && !fondoCambiado) {
            fondoCambiado = true;
            background.dispose(); // Liberar el fondo anterior
            background = background2; // Cambiar al nuevo fondo

            // Cambiar la textura de la gota mala
            lluvia.cambiarGotaMala(gotaMala2); // Llamar al método en Lluvia para actualizar la gota mala
        }
        
        batch.begin();
        batch.draw(background, 0, 0, 800, 480);

        // Mostrar puntajes y otros datos
        font.draw(batch, "Puntos totales: " + tarro.getPuntos(), 10, 460);
        font.draw(batch, "Vidas: " + tarro.getVidas(), 10, 430);
        font.draw(batch, "HighScore: " + settings.getHighScore(), 10, 400);

        // Actualizar y dibujar elementos del juego
        tarro.actualizarMovimiento();
        if (!lluvia.actualizarMovimiento(tarro)) {
            game.setScreen(new GameOverScreen(game));
            dispose();
        }
        
        
        if (lluvia.isJefeActivo()) {
            int vidaTomate = lluvia.getTomate().getVida();
            font.draw(batch, "Vida: " + vidaTomate, 650, 450); // Posición de texto de vida del jefe
        }
        tarro.dibujar(batch);
        lluvia.actualizarDibujoLluvia(batch);

        batch.end();

        // Control de pausa
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            lluvia.pausar();
            game.setScreen(new PausaScreen(game, this));
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        background.dispose();
        tarro.destruir();
        lluvia.destruir();
    }
}
