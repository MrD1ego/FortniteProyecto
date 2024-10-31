package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
    private Array<Gota> gotas; // Cambiado para usar objetos de tipo Gota
    private long lastDropTime;
    private Texture gotaBuena;
    private Texture gotaMala;
    private Texture gotaEspecial;
    private Texture gotaBuenaNueva; // Nueva textura para gotas buenas a partir de 1000 puntos
    private Texture gotaEspecialNueva; // Nueva textura para gotas especiales a partir de 1000 puntos
    private Sound dropSound;
    private Texture gotaLlama;
    private Sound soundLlama;
    private Music rainMusic;
    private boolean texturasCambiadas = false; // Control para el cambio de texturas
    
    // Variables de velocidad y tiempo de generación
    private float velocidadCaidaActual = 150; // Velocidad reducida
    private long intervaloGeneracionActual = 150000000; // Intervalo inicial más amplio entre gotas (150 ms)
    private long intervaloGeneracionOriginal = 100000000; // Intervalo original más rápido entre gotas (100 ms)
    
    public Lluvia(Texture gotaBuena, Texture gotaMala, Texture gotaEspecial, Texture gotaBuenaNueva, Texture gotaEspecialNueva, Texture gotaLlama, Sound soundLlama, Sound dropSound, Music rainMusic) {
        this.rainMusic = rainMusic;
        this.dropSound = dropSound;
        this.gotaBuena = gotaBuena;
        this.gotaMala = gotaMala;
        this.gotaEspecial = gotaEspecial;
        this.gotaBuenaNueva = gotaBuenaNueva;
        this.gotaEspecialNueva = gotaEspecialNueva;
        this.gotaLlama = gotaLlama;
        this.soundLlama = soundLlama;
        
        // Establecer el volumen de la música al 40%
        this.rainMusic.setVolume(0.4f); // 0.4f representa el 40% del volumen máximo
    }
    
    
    public void cambiarGotaMala(Texture nuevaTextura) {
    this.gotaMala = nuevaTextura; // Actualiza la textura de la gota mala
    for (Gota gota : gotas) {
        if (gota instanceof GotaMala) {
            gota.textura.dispose(); // Liberar la textura anterior
            gota.textura = nuevaTextura; // Cambiar a la nueva textura
        }
    }
    }

    public void crear() {
        gotas = new Array<>(); // Inicializa el array de gotas
        crearGotaDeLluvia();
        rainMusic.setLooping(true);
        rainMusic.play();
    }
    

    private void crearGotaDeLluvia() {
        float x = MathUtils.random(0, 800 - 64);
        float y = 480;

        // Determinar el tipo de gota
        int tipoGota = MathUtils.random(1, 100);
        Gota nuevaGota;

        if (tipoGota <= 45) { // 45% de probabilidad para gotas buenas (normales)
            nuevaGota = new GotaBuena(gotaBuena, dropSound, x, y);
        } else if (tipoGota <= 85) { // 40% de probabilidad para gotas malas
            nuevaGota = new GotaMala(gotaMala, x, y);
        } else { // 15% de probabilidad para gotas especiales
            nuevaGota = new GotaEspecial(gotaEspecial, dropSound, x, y);
        }

        gotas.add(nuevaGota);
        lastDropTime = TimeUtils.nanoTime();
    }
    
    private void crearGotaLlama() {
        float x = MathUtils.random(0, 800 - 64);
        float y = 480;

        Gota nuevaGota = new GotaLlama(gotaLlama, soundLlama, x, y);
        gotas.add(nuevaGota);
    }
    
    private int ultimoPuntajeLlama = -1;
    
    public boolean actualizarMovimiento(Tarro tarro) {
    // Cambiar texturas si el puntaje es 1000 o más
        if (tarro.getPuntos() >= 1000 && !texturasCambiadas) {
        // Liberar las texturas anteriores
            gotaBuena.dispose();
            gotaEspecial.dispose();

        // Cambiar a las nuevas texturas
            gotaBuena = gotaBuenaNueva;
            gotaEspecial = gotaEspecialNueva;

        // Actualizar texturas de las gotas que ya están en pantalla
            for (Gota gota : gotas) {
                if (gota instanceof GotaBuena) {
                    ((GotaBuena) gota).actualizarTextura(gotaBuena);
                } else if (gota instanceof GotaEspecial) {
                    ((GotaEspecial) gota).actualizarTextura(gotaEspecial);
                }
            }

        // Marcar que ya se han cambiado las texturas
            texturasCambiadas = true;
            
            velocidadCaidaActual += 100;
        }
        
        
  
    if (tarro.getPuntos() >= 1000 && (tarro.getPuntos() / 1000) > ultimoPuntajeLlama) {
        crearGotaLlama();
        ultimoPuntajeLlama = tarro.getPuntos() / 1000; // Actualizar el último puntaje de creación
    }
    
    // Generar nuevas gotas de lluvia en función del intervalo de generación actual
        if (TimeUtils.nanoTime() - lastDropTime > intervaloGeneracionActual) {
            crearGotaDeLluvia();
        }

    // Verificar si las gotas cayeron o chocaron con el tarro
        for (int i = 0; i < gotas.size; i++) {
            Gota gota = gotas.get(i);
            gota.actualizarPosicion(Gdx.graphics.getDeltaTime(), velocidadCaidaActual);

        // Si cae al suelo, eliminarla
            if (gota.getHitbox().y + 64 < 0) {
                gotas.removeIndex(i);
                continue;
            }

        // Verificar colisión con el tarro
            if (gota.getHitbox().overlaps(tarro.getArea())) {
                gota.aplicarEfecto(tarro);
                gotas.removeIndex(i);
            }
        } 

        return tarro.getVidas() > 0; // Retornar si el juego sigue activo
    }

   
    public void actualizarDibujoLluvia(SpriteBatch batch) { 
        for (Gota gota : gotas) {
            batch.draw(gota.getTextura(), gota.getHitbox().x, gota.getHitbox().y);
        }
    }

    // Método para liberar los recursos de sonido y música
    public void destruir() {
        dropSound.dispose();
        rainMusic.dispose();
        gotaBuena.dispose();
        gotaMala.dispose();
        gotaEspecial.dispose();
        gotaBuenaNueva.dispose();
        gotaEspecialNueva.dispose();
        for (Gota gota : gotas) {
            gota.getTextura().dispose(); // Descartar las texturas de las gotas
        }
    }

    // Método para pausar la música
    public void pausar() {
        if (rainMusic.isPlaying()) {
            rainMusic.pause();
        }
    }

    // Método para continuar la música desde donde se pausó
    public void continuar() {
        if (!rainMusic.isPlaying()) {
            rainMusic.play();
        }
    }
}
