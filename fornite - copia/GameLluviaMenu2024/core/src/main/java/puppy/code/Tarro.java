package puppy.code;

<<<<<<< HEAD


=======
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
<<<<<<< HEAD
import com.badlogic.gdx.utils.Array;
=======
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
import com.badlogic.gdx.utils.TimeUtils;

public class Tarro {
    private Rectangle bucket;
    private Texture bucketImage;
    private Sound sonidoHerido;
<<<<<<< HEAD
    private Texture texturaBala;
    private Array<Bala> balas;
    private long tiempoUltimoDisparo = 0;
    private final long INTERVALO_DISPARO = 200000000L; // 5 disparos por segundo
=======
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
    private int vidas = 3;
    private int puntos = 0;
    private int velx = 400;
    private boolean herido = false;
    private int tiempoHeridoMax = 50;
    private int tiempoHerido;

<<<<<<< HEAD
    private boolean jefeActivo = false; // Variable para controlar si el jefe está activo

    // Variables para el efecto Jetpack
    boolean jetpackActivo = false;
    private float posicionYOriginal;
    private long tiempoInicioJetpack;
    private final long DURACION_JETPACK = 10000000000L; // 10 segundos en nanosegundos
    private final float anchoOriginal = 64;
    private final float altoOriginal = 64;

    public Tarro(Texture tex, Sound ss, Texture texturaBala) {
        bucketImage = tex;
        sonidoHerido = ss;
        this.texturaBala = texturaBala;
        this.balas = new Array<>();
=======
    // Variables para el efecto Jetpack
    private boolean jetpackActivo = false;
    private float posicionYOriginal;
    private long tiempoInicioJetpack;
    private final long DURACION_JETPACK = 10000000000L; // 10 segundos en nanosegundos

    public Tarro(Texture tex, Sound ss) {
        bucketImage = tex;
        sonidoHerido = ss;
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
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
<<<<<<< HEAD
        bucket.width = anchoOriginal;
        bucket.height = altoOriginal;
=======
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
    }

    public void dañar() {
        vidas--;
        herido = true;
        tiempoHerido = tiempoHeridoMax;
        sonidoHerido.play();
    }

    public void dibujar(SpriteBatch batch) {
        if (!herido) {
<<<<<<< HEAD
            batch.draw(bucketImage, bucket.x, bucket.y, bucket.width, bucket.height);
        } else {
            batch.draw(bucketImage, bucket.x, bucket.y + MathUtils.random(-5, 5), bucket.width, bucket.height);
=======
            batch.draw(bucketImage, bucket.x, bucket.y);
        } else {
            batch.draw(bucketImage, bucket.x, bucket.y + MathUtils.random(-5, 5));
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }

<<<<<<< HEAD
        if (jetpackActivo) {
            batch.setColor(1, 1, 1, 0.5f);
            batch.draw(bucketImage, bucket.x, posicionYOriginal, bucket.width, bucket.height);
            batch.setColor(1, 1, 1, 1);
        }

        for (Bala bala : balas) {
            bala.dibujar(batch);
=======
        // Dibuja la imagen residual si el jetpack está activo
        if (jetpackActivo) {
            batch.setColor(1, 1, 1, 0.5f); // Cambia el color a blanco con 50% de transparencia
            batch.draw(bucketImage, bucket.x, posicionYOriginal); // Dibuja la imagen original en la posición original
            batch.setColor(1, 1, 1, 1); // Restablece el color a opaco
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
        }
    }

    public void aumentarVida() {
        vidas++;
    }

<<<<<<< HEAD
    public void activarJetpack() {
        if (!jetpackActivo) {
            jetpackActivo = true;
            posicionYOriginal = this.bucket.y;
=======
    // Método para activar el efecto Jetpack
    public void activarJetpack() {
        if (!jetpackActivo) { // Solo activar si no está ya en uso
            jetpackActivo = true;
            posicionYOriginal = this.bucket.y; // Guarda la posición actual en Y
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
            tiempoInicioJetpack = TimeUtils.nanoTime();
        }
    }

    public void actualizarMovimiento() {
        if (jetpackActivo) {
<<<<<<< HEAD
=======
            // Permitir movimiento en ambos ejes durante el efecto Jetpack
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                bucket.y += velx * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                bucket.y -= velx * Gdx.graphics.getDeltaTime();
            }
<<<<<<< HEAD
=======

            // Movimiento horizontal
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                bucket.x -= velx * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                bucket.x += velx * Gdx.graphics.getDeltaTime();
            }

<<<<<<< HEAD
            if (TimeUtils.nanoTime() - tiempoInicioJetpack > DURACION_JETPACK) {
                jetpackActivo = false;
                bucket.y = posicionYOriginal;
            }
        } else {
=======
            // Revisar si han pasado 10 segundos desde que se activó
            if (TimeUtils.nanoTime() - tiempoInicioJetpack > DURACION_JETPACK) {
                jetpackActivo = false;
                bucket.y = posicionYOriginal; // Restaurar la posición original en Y
            }
        } else {
            // Movimiento normal en X (izquierda y derecha)
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                bucket.x -= velx * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                bucket.x += velx * Gdx.graphics.getDeltaTime();
            }
        }

<<<<<<< HEAD
        if (bucket.x < 0) bucket.x = 0;
        if (bucket.x > 800 - 64) bucket.x = 800 - 64;
        if (bucket.y < 0) bucket.y = 0;
        if (bucket.y > 480 - 64) bucket.y = 480 - 64;

        for (Bala bala : balas) {
            bala.actualizarPosicion(Gdx.graphics.getDeltaTime());
            if (bala.getHitbox().y > 480) {
                balas.removeValue(bala, true);
            }
        }
=======
        // Limitar el tarro dentro de los bordes de la pantalla
        if (bucket.x < 0) bucket.x = 0;
        if (bucket.x > 800 - 64) bucket.x = 800 - 64;
        
        if (bucket.y < 0) bucket.y = 0;
        if (bucket.y > 480 - 64) bucket.y = 480 - 64;
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
    }

    public void destruir() {
        bucketImage.dispose();
    }

    public boolean estaHerido() {
        return herido;
    }
<<<<<<< HEAD

    public void reducirTamano() {
        bucket.width *= 0.4;
        bucket.height *= 0.4;
    }

    public void disparar() {
        float balaX = bucket.x + bucket.width / 2 - texturaBala.getWidth() / 2;
        float balaY = bucket.y + bucket.height;
        balas.add(new Bala(texturaBala, balaX, balaY, 300));
    }

    public Array<Bala> getBalas() {
        return balas;
    }

    // Método para activar o desactivar el modo jefe
    public void setJefeActivo(boolean jefeActivo) {
        this.jefeActivo = jefeActivo;
    }
    
    
    public void restaurarTamano() {
        bucket.width = anchoOriginal;
        bucket.height = altoOriginal;
    }

=======
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
}