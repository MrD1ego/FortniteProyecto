package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class PausaScreen implements Screen {

    private final GameLluviaMenu game;
    private final GameScreen juego;
    private final SpriteBatch batch;
    private OrthographicCamera camera;

    // Texturas y áreas
    private Texture fondo;
    private Texture pausaImagen;
    private Texture botonVolver;
    private Texture botonContinuar;
    private Rectangle botonVolverArea;
    private Rectangle botonContinuarArea;

    // Ratios para escalado
    private static final float BUTTON_WIDTH_RATIO = 0.4f; // 40% del ancho de la pantalla
    private static final float BUTTON_HEIGHT_RATIO = 0.1f; // 10% del alto de la pantalla

    public PausaScreen(final GameLluviaMenu game, GameScreen juego) {
        this.game = game;
        this.juego = juego;
        this.batch = game.getBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Cargar texturas
        fondo = new Texture("Fondo.jpg");
        pausaImagen = new Texture("Pausa.png");
        botonVolver = new Texture("Volver.png");
        botonContinuar = new Texture("Continuar.png");

        // Inicializar áreas de botones
        actualizarBotonHitbox();
    }

    private void actualizarBotonHitbox() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        // Tamaño y posición del botón "Volver al menú"
        float buttonWidth = screenWidth * BUTTON_WIDTH_RATIO;
        float buttonHeight = screenHeight * BUTTON_HEIGHT_RATIO;
        float buttonX = (screenWidth - buttonWidth) / 2;
        float volverButtonY = screenHeight * 0.4f;

        botonVolverArea = new Rectangle(buttonX, volverButtonY, buttonWidth, buttonHeight);

        // Tamaño y posición del botón "Continuar"
        float continuarButtonY = screenHeight * 0.2f;

        botonContinuarArea = new Rectangle(buttonX, continuarButtonY, buttonWidth, buttonHeight);
    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Dibujar fondo ajustado al tamaño de la pantalla
        batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Dibujar la imagen de pausa
        batch.draw(pausaImagen, (Gdx.graphics.getWidth() - 300) / 2, Gdx.graphics.getHeight() - 150, 300, 100);

        // Dibujar botones con efecto si están seleccionados
        boolean isOverVolver = manejarBoton(batch, botonVolver, botonVolverArea);
        boolean isOverContinuar = manejarBoton(batch, botonContinuar, botonContinuarArea);

        // Cambiar el cursor si el ratón está sobre alguno de los botones
        if (isOverVolver || isOverContinuar) {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Hand);
        } else {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Arrow);
        }

        batch.end();

        // Lógica de interacción con botones
        if (Gdx.input.isTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (botonVolverArea.contains(touchX, touchY)) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            } else if (botonContinuarArea.contains(touchX, touchY)) {
                game.setScreen(juego);
                dispose();
            }
        }

        // Lógica de interacción con teclas
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            game.setScreen(juego); // Continuar el juego al presionar ESC o P
            dispose();
        }
    }

    private boolean manejarBoton(SpriteBatch batch, Texture boton, Rectangle area) {
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        boolean isOverButton = area.contains(mouseX, mouseY);
        float buttonWidth = area.width;
        float buttonHeight = area.height;

        if (isOverButton) {
            buttonWidth *= 1.1f; // Agrandar un 10%
            buttonHeight *= 1.1f;
        }

        batch.draw(boton,
                area.x - (buttonWidth - area.width) / 2,
                area.y - (buttonHeight - area.height) / 2,
                buttonWidth, buttonHeight);

        return isOverButton; // Retorna si el mouse está sobre el botón
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        actualizarBotonHitbox();
    }

    @Override
    public void show() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        fondo.dispose();
        pausaImagen.dispose();
        botonVolver.dispose();
        botonContinuar.dispose();
    }
}