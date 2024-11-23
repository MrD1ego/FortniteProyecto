package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class MainMenuScreen implements Screen {
    final GameLluviaMenu game;
    private SpriteBatch batch;
    private BitmapFont fontPuntaje;
    private OrthographicCamera camera;

    // Texturas
    private Texture fondo;
    private Texture logo;
    private Texture botonEmpezar;

    // Áreas
    private Rectangle botonArea;

    // Música
    private Music mainTheme;

    // Escala del botón
    private static final float BUTTON_WIDTH_RATIO = 0.4f; // 40% del ancho de la pantalla
    private static final float BUTTON_HEIGHT_RATIO = 0.15f; // 15% del alto de la pantalla

    public MainMenuScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();

        // Fuente para puntaje más alto
        this.fontPuntaje = new BitmapFont();
        this.fontPuntaje.getData().setScale(2, 2); // Escala de la fuente

        // Configuración de la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Cargar texturas
        fondo = new Texture("Fondo.jpg");
        logo = new Texture("Logo.png");
        botonEmpezar = new Texture("Empezar.png");

        // Cargar música
        mainTheme = Gdx.audio.newMusic(Gdx.files.internal("MainTheme.mp3"));
        mainTheme.setLooping(true); // Reproducción en loop
        mainTheme.setVolume(0.2f); // Volumen al 40%
        mainTheme.play(); // Comienza a reproducirse

        // Inicializar el área del botón
        actualizarBotonHitbox();
    }

    private void actualizarBotonHitbox() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        // Calcular tamaño y posición del botón
        float buttonWidth = screenWidth * BUTTON_WIDTH_RATIO;
        float buttonHeight = screenHeight * BUTTON_HEIGHT_RATIO;
        float buttonX = (screenWidth - buttonWidth) / 2;
        float buttonY = screenHeight * 0.1f; // 10% desde la parte inferior

        // Actualizar el área del botón
        botonArea = new Rectangle(buttonX, buttonY, buttonWidth, buttonHeight);
    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Dibujar fondo ajustado al tamaño de la pantalla
        batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Dibujar logo ajustado y centrado arriba
        float logoWidth = Gdx.graphics.getWidth() * 0.3f; // 30% del ancho de la pantalla
        float logoHeight = logoWidth / 3; // Proporcional
        float logoX = (Gdx.graphics.getWidth() - logoWidth) / 2;
        float logoY = Gdx.graphics.getHeight() - logoHeight - 20;
        batch.draw(logo, logoX, logoY, logoWidth, logoHeight);

        // Detectar si el cursor está sobre el botón
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
        boolean isOverButton = botonArea.contains(mouseX, mouseY);

        // Dibujar el botón con efecto si está seleccionado
        float buttonWidth = botonArea.width;
        float buttonHeight = botonArea.height;
        if (isOverButton) {
            buttonWidth *= 1.1; // Agrandar un 10%
            buttonHeight *= 1.1;
        }
        batch.draw(botonEmpezar,
                botonArea.x - (buttonWidth - botonArea.width) / 2,
                botonArea.y - (buttonHeight - botonArea.height) / 2,
                buttonWidth, buttonHeight);

        // Mostrar puntaje más alto
        GlobalSettings settings = GlobalSettings.getInstance();
        fontPuntaje.draw(batch, "Puntaje más alto: " + settings.getHighScore(), 10, Gdx.graphics.getHeight() - 10);

        batch.end();

        // Cambiar el cursor si el ratón está sobre el botón
        if (isOverButton) {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Hand);
        } else {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Arrow);
        }

        // Detectar clic en el botón
        if (Gdx.input.isTouched() && isOverButton) {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Arrow);
            mainTheme.pause(); // Pausar la música
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        // Ajustar la cámara al nuevo tamaño
        camera.setToOrtho(false, width, height);

        // Actualizar hitbox de los botones
        actualizarBotonHitbox();
    }

    @Override
    public void show() {
        mainTheme.play(); // Reanudar la música al mostrar la pantalla
    }

    @Override
    public void pause() {
        mainTheme.pause(); // Pausar la música al pausar la pantalla
    }

    @Override
    public void resume() {
        mainTheme.play(); // Reanudar la música al reanudar la pantalla
    }

    @Override
    public void hide() {
        mainTheme.pause(); // Pausar la música al ocultar la pantalla
    }

    @Override
    public void dispose() {
        fondo.dispose();
        logo.dispose();
        botonEmpezar.dispose();
        fontPuntaje.dispose();
        mainTheme.dispose(); // Liberar recursos de la música
    }
}
