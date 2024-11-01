package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Tarro {
    private Rectangle bucket;
    private Texture bucketImage;
    private Sound sonidoHerido;
    private int vidas = 3;
    private int puntos = 0;
    private int velx = 400;
    private boolean herido = false;
    private int tiempoHeridoMax = 50;
    private int tiempoHerido;

    // Variables para el efecto Jetpack
    private boolean jetpackActivo = false;
    private float posicionYOriginal;
    private long tiempoInicioJetpack;
    private final long DURACION_JETPACK = 10000000000L; // 10 segundos en nanosegundos

    public Tarro(Texture tex, Sound ss) {
        bucketImage = tex;
        sonidoHerido = ss;
    }

    public int getVidas() {
        return vidas;
    }

    public int getPuntos() {
        return puntos;
    }

    public Rectangle getArea() {
        return bucket;
    }

    public void sumarPuntos(int pp) {
        puntos += pp;
    }

    public void crear() {
        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2;
        bucket.y = 20;
        bucket.width = 64;
        bucket.height = 64;
    }

    public void dañar() {
        vidas--;
        herido = true;
        tiempoHerido = tiempoHeridoMax;
        sonidoHerido.play();
    }

    public void dibujar(SpriteBatch batch) {
        if (!herido) {
            batch.draw(bucketImage, bucket.x, bucket.y);
        } else {
            batch.draw(bucketImage, bucket.x, bucket.y + MathUtils.random(-5, 5));
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }

        // Dibuja la imagen residual si el jetpack está activo
        if (jetpackActivo) {
            batch.setColor(1, 1, 1, 0.5f); // Cambia el color a blanco con 50% de transparencia
            batch.draw(bucketImage, bucket.x, posicionYOriginal); // Dibuja la imagen original en la posición original
            batch.setColor(1, 1, 1, 1); // Restablece el color a opaco
        }
    }

    public void aumentarVida() {
        vidas++;
    }

    // Método para activar el efecto Jetpack
    public void activarJetpack() {
        if (!jetpackActivo) { // Solo activar si no está ya en uso
            jetpackActivo = true;
            posicionYOriginal = this.bucket.y; // Guarda la posición actual en Y
            tiempoInicioJetpack = TimeUtils.nanoTime();
        }
    }

    public void actualizarMovimiento() {
        if (jetpackActivo) {
            // Permitir movimiento en ambos ejes durante el efecto Jetpack
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                bucket.y += velx * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                bucket.y -= velx * Gdx.graphics.getDeltaTime();
            }

            // Movimiento horizontal
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                bucket.x -= velx * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                bucket.x += velx * Gdx.graphics.getDeltaTime();
            }

            // Revisar si han pasado 10 segundos desde que se activó
            if (TimeUtils.nanoTime() - tiempoInicioJetpack > DURACION_JETPACK) {
                jetpackActivo = false;
                bucket.y = posicionYOriginal; // Restaurar la posición original en Y
            }
        } else {
            // Movimiento normal en X (izquierda y derecha)
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                bucket.x -= velx * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                bucket.x += velx * Gdx.graphics.getDeltaTime();
            }
        }

        // Limitar el tarro dentro de los bordes de la pantalla
        if (bucket.x < 0) bucket.x = 0;
        if (bucket.x > 800 - 64) bucket.x = 800 - 64;
        
        if (bucket.y < 0) bucket.y = 0;
        if (bucket.y > 480 - 64) bucket.y = 480 - 64;
    }

    public void destruir() {
        bucketImage.dispose();
    }

    public boolean estaHerido() {
        return herido;
    }
}