package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class GameOverScreen implements Screen {

    private final GameLluviaMenu game;
    private final SpriteBatch batch;
    private OrthographicCamera camera;

    // Texturas
    private Texture fondo;
    private Texture textoTeBajaron;
    private Texture botonVolver;
    private Texture botonVolverEmpezar;

    // Áreas de botones
    private Rectangle botonVolverArea;
    private Rectangle botonVolverEmpezarArea;

    // Escala de botones
    private static final float BUTTON_WIDTH_RATIO = 0.4f; // 40% del ancho de la pantalla
    private static final float BUTTON_HEIGHT_RATIO = 0.1f; // 10% del alto de la pantalla

    public GameOverScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Cargar texturas
        fondo = new Texture("Fondo.jpg");
        textoTeBajaron = new Texture("TeBajaron.png");
        botonVolver = new Texture("Volver.png");
        botonVolverEmpezar = new Texture("VolverEmpezar.png");

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

        // Tamaño y posición del botón "Volver a empezar"
        float volverEmpezarButtonY = screenHeight * 0.2f;
        botonVolverEmpezarArea = new Rectangle(buttonX, volverEmpezarButtonY, buttonWidth, buttonHeight);
    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Dibujar fondo ajustado al tamaño de la pantalla
        batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Dibujar el texto "Te Bajaron" centrado en la parte superior
        batch.draw(textoTeBajaron, (Gdx.graphics.getWidth() - 300) / 2, Gdx.graphics.getHeight() - 150, 300, 100);

        // Dibujar botones con efecto si están seleccionados
        boolean isOverVolver = manejarBoton(batch, botonVolver, botonVolverArea);
        boolean isOverVolverEmpezar = manejarBoton(batch, botonVolverEmpezar, botonVolverEmpezarArea);

        // Cambiar el cursor si el ratón está sobre alguno de los botones
        if (isOverVolver || isOverVolverEmpezar) {
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
                Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Arrow); // Cambiar a cursor normal
                game.setScreen(new MainMenuScreen(game));
                dispose();
            } else if (botonVolverEmpezarArea.contains(touchX, touchY)) {
                Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Arrow); // Cambiar a cursor normal
                game.setScreen(new GameScreen(game));
                dispose();
            }
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
        textoTeBajaron.dispose();
        botonVolver.dispose();
        botonVolverEmpezar.dispose();
    }
}
