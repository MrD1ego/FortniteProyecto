package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Tarro {
    private Rectangle bucket;
    private Texture bucketImage;
    private Sound sonidoHerido;
    private Texture texturaBala;
    private Array<Bala> balas;
    private long tiempoUltimoDisparo = 0;
    private final long INTERVALO_DISPARO = 200000000L; // 5 disparos por segundo
    private int vidas = 3;
    private int puntos = 0;
    private int velx = 400;
    private boolean herido = false;
    private int tiempoHeridoMax = 50;
    private int tiempoHerido;
    private Sound sonidoJetpack;

    private boolean jefeActivo = false; // Variable para controlar si el jefe está activo

    // Variables para el efecto Jetpack
    boolean jetpackActivo = false;
    private float posicionYOriginal;
    private long tiempoInicioJetpack;
    private long duracionJetpack;
    private final float anchoOriginal = 64;
    private final float altoOriginal = 64;

    // Atributo para manejar la habilidad
    private HabilidadStrategy habilidadStrategy;

    public Tarro(Texture tex, Sound ss, Texture texturaBala, Sound sonidoJetpack) {
        bucketImage = tex;
        sonidoHerido = ss;
        this.texturaBala = texturaBala;
        this.balas = new Array<>();
        this.sonidoJetpack = sonidoJetpack;
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
        bucket.width = anchoOriginal;
        bucket.height = altoOriginal;
    }

    public void dañar() {
        vidas--;
        herido = true;
        tiempoHerido = tiempoHeridoMax;
        sonidoHerido.play();

        if (vidas <= 0) {
            detenerSonidoJetpack(); // Detiene el sonido del jetpack si el tarro muere
        }
    }

    public void dibujar(SpriteBatch batch) {
        if (!herido) {
            batch.draw(bucketImage, bucket.x, bucket.y, bucket.width, bucket.height);
        } else {
            batch.draw(bucketImage, bucket.x, bucket.y + MathUtils.random(-5, 5), bucket.width, bucket.height);
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }

        if (jetpackActivo) {
            batch.setColor(1, 1, 1, 0.5f);
            batch.draw(bucketImage, bucket.x, posicionYOriginal, bucket.width, bucket.height);
            batch.setColor(1, 1, 1, 1);
        }

        for (Bala bala : balas) {
            bala.dibujar(batch);
        }
    }

    public void aumentarVida() {
        vidas++;
    }

    public void activarJetpack() {
        if (!jetpackActivo) {
            jetpackActivo = true;
            posicionYOriginal = this.bucket.y;
            tiempoInicioJetpack = TimeUtils.nanoTime();
            duracionJetpack = 10000000000L; // Duración predeterminada (10 segundos)
            sonidoJetpack.play(); // Reproduce el sonido del jetpack
        }
    }

    
    public void reproducirSonidoJetpack() {
        sonidoJetpack.stop(); // Detiene cualquier reproducción previa
        sonidoJetpack.play(); // Reproduce el sonido desde el inicio
    }


    public void reiniciarTiempoJetpack(float duracion) {
        if (jetpackActivo) {
            tiempoInicioJetpack = TimeUtils.nanoTime(); // Reinicia el inicio del temporizador
            duracionJetpack = (long) (duracion * 1000000000L); // Actualiza la duración
        } else {
            activarJetpack(); // Si no está activo, activa el Jetpack
        }
    }

    public void actualizarMovimiento() {
        if (jetpackActivo) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
                bucket.y += velx * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
                bucket.y -= velx * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                bucket.x -= velx * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                bucket.x += velx * Gdx.graphics.getDeltaTime();
            }

            if (TimeUtils.nanoTime() - tiempoInicioJetpack > duracionJetpack) {
                jetpackActivo = false; // Termina el efecto del Jetpack
                bucket.y = posicionYOriginal;
            }
        } else {
            // Movimiento normal sin Jetpack
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                bucket.x -= velx * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                bucket.x += velx * Gdx.graphics.getDeltaTime();
            }
        }

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

    // Nuevo método: Asignar habilidad
    public void setHabilidad(HabilidadStrategy habilidad) {
        this.habilidadStrategy = habilidad;
    }

    // Nuevo método: Activar habilidad
    public void activarHabilidad(boolean jefeActivo) {
        if (jefeActivo && habilidadStrategy != null) {
            habilidadStrategy.activar(this);
        }
    }

    // Nuevo método: Obtener textura de las balas
    public Texture getTexturaBala() {
        return texturaBala;
    }

    public void setVelx(int velocidad) {
        this.velx = velocidad;
    }
    
    
    public void detenerSonidoJetpack() {
        if (sonidoJetpack != null) {
            sonidoJetpack.stop(); // Detiene cualquier reproducción en curso
        }
    }
}
