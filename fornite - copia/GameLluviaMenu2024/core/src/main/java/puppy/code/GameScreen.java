package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
    private Texture background2; // Nuevo fondo para 1000 puntos
    private Texture gotaMala2; // Nueva textura para la gota mala después de 1000 puntos
    private Texture gotaLlama;
    private Sound soundLlama;
    
    private boolean fondoCambiado = false;

    public GameScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		
        // Cargar los fondos
        background = new Texture(Gdx.files.internal("background.png"));
        background2 = new Texture(Gdx.files.internal("background2.png")); // Fondo para 1000 puntos

        // Cargar las texturas para el tarro y las gotas
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        tarro = new Tarro(new Texture(Gdx.files.internal("bucket.png")), hurtSound);
        
        Texture gota = new Texture(Gdx.files.internal("drop.png"));
        Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));
        gotaMala2 = new Texture(Gdx.files.internal("dropBad2.png")); // Nueva textura para la gota mala después de 1000 puntos
        Texture gotaEspecial = new Texture(Gdx.files.internal("specialDrop.png"));
        Texture gotaBuenaNueva = new Texture(Gdx.files.internal("dropNew.png")); // Nueva textura para la gota buena después de 1000 puntos
        Texture gotaEspecialNueva = new Texture(Gdx.files.internal("specialDropNew.png")); // Nueva textura para la gota especial después de 1000 puntos
        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        
        Texture gotaLlama = new Texture(Gdx.files.internal("llama.png")); // Nueva textura para la gota especial después de 1000 puntos
        Sound soundLlama = Gdx.audio.newSound(Gdx.files.internal("soundLlama.mp3"));
        
        Texture gotaJetpack = new Texture(Gdx.files.internal("jetpack.png"));
        Sound jetpackSound = Gdx.audio.newSound(Gdx.files.internal("jetpackSound.mp3"));
        // Inicializar la clase Lluvia con las texturas de gotas
        lluvia = new Lluvia(gota, gotaMala, gotaEspecial, gotaBuenaNueva, gotaEspecialNueva, gotaLlama, gotaJetpack, jetpackSound, soundLlama, dropSound, rainMusic);
        
        // Configuración de la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        
        // Inicializar el tarro y la lluvia
        tarro.crear();
        lluvia.crear();
    }

    @Override
    public void render(float delta) {
        // Verificar si se presiona ESC o P para pausar
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            lluvia.pausar(); // Pausar la música
            game.setScreen(new PausaScreen(game, this));
            return;
        }

        // Cambiar el fondo y la gota mala cuando el puntaje alcanza 1000 puntos o más
        if (tarro.getPuntos() >= 1000 && !fondoCambiado) {
            fondoCambiado = true;
            background.dispose(); // Liberar el fondo anterior
            background = background2; // Cambiar al nuevo fondo

            // Cambiar la textura de la gota mala
            lluvia.cambiarGotaMala(gotaMala2); // Llamar al método en Lluvia para actualizar la gota mala
        }

        // Limpiar la pantalla y actualizar la cámara
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // Dibujar el fondo y demás elementos de juego
        batch.draw(background, 0, 0, 800, 480);
        font.draw(batch, "Puntos totales: " + tarro.getPuntos(), 5, 475);
        font.draw(batch, "Vidas : " + tarro.getVidas(), 670, 475);
        font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth / 2 - 50, 475);
    
        // Dibujar los elementos del juego
        if (!tarro.estaHerido()) {
            tarro.actualizarMovimiento();
            if (!lluvia.actualizarMovimiento(tarro)) {
                if (game.getHigherScore() < tarro.getPuntos())
                    game.setHigherScore(tarro.getPuntos());
                game.setScreen(new GameOverScreen(game));
                dispose();
            }
        }
    
        tarro.dibujar(batch);
        lluvia.actualizarDibujoLluvia(batch);
    
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // Continuar con el sonido de lluvia
        lluvia.continuar();
    }

    @Override
    public void hide() {
        // Método vacío si no hay acciones específicas para ocultar
    }

    @Override
    public void pause() {
        lluvia.pausar();
        game.setScreen(new PausaScreen(game, this)); 
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        tarro.destruir();
        lluvia.destruir();
        background.dispose(); // Liberar el fondo actual
        background2.dispose(); // Liberar el segundo fondo
        gotaMala2.dispose(); // Liberar la nueva textura de la gota mala
    }
}
