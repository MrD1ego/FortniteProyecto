package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
import com.badlogic.gdx.math.Rectangle;
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
<<<<<<< HEAD
    private Array<IGota> gotas;
=======
<<<<<<< HEAD
    private Array<IGota> gotas;
=======
    private Array<IGota> gotas; // Cambiado para usar objetos de tipo IGota
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    private long lastDropTime;
    private Texture gotaBuena;
    private Texture gotaMala;
    private Texture gotaEspecial;
<<<<<<< HEAD
    private Texture gotaBuenaNueva;
    private Texture gotaEspecialNueva;
=======
<<<<<<< HEAD
    private Texture gotaBuenaNueva;
    private Texture gotaEspecialNueva;
=======
    private Texture gotaBuenaNueva; // Nueva textura para gotas buenas a partir de 1000 puntos
    private Texture gotaEspecialNueva; // Nueva textura para gotas especiales a partir de 1000 puntos
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    private Sound dropSound;
    private Texture gotaLlama;
    private Sound soundLlama;
    private Music rainMusic;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    private Texture gotaJetpack;
    private Sound jetpackSound;
    private boolean texturasCambiadas = false;
    private float velocidadCaidaActual = 150;
    private long intervaloGeneracionActual = 150000000;
    private long intervaloGeneracionOriginal = 100000000;
    private Texture tomateTexture;
    private Tomate tomate;
    private boolean jefeActivo = false;
    private int indicePatron = 0;
    private final long DURACION_PATRON_CIRCULAR = 15000000000L;
    private final long DURACION_PATRON_DIAGONAL = 10000000000L;
    private final long DURACION_PATRON_CONCENTRICO = 10000000000L;
    private long tiempoInicioPatron = 0;
    private float anguloEspiral = 0;
    private long tiempoUltimoDisparoCircular = 0;
    private long tiempoUltimoDisparoDiagonal = 0;
    private final long INTERVALO_DISPARO_CIRCULAR = 500000000L;
    private final long INTERVALO_DISPARO_DIAGONAL = 50000000L;
    private float anguloEspiralHorario = 0;
    private float anguloEspiralAntihorario = 0;
    private long tiempoUltimoDisparo = 0;
    private final long INTERVALO_DISPARO = 200000000L;
    private Music jefeMusic;
    private boolean jefeActivado = false;
    private int ultimoPuntajeLlama = 0;
    private int proximoPuntajeJefe = 2000;
    

    public Lluvia(Texture gotaBuena, Texture gotaMala, Texture gotaEspecial, Texture gotaBuenaNueva,
                  Texture gotaEspecialNueva, Texture gotaLlama, Texture gotaJetpack, Sound jetpackSound,
                  Sound soundLlama, Sound dropSound, Music rainMusic, Texture tomateTexture, Music jefeMusic) {
<<<<<<< HEAD
=======
=======

    private Texture gotaJetpack;
    private Sound jetpackSound;
    private boolean texturasCambiadas = false; // Control para el cambio de texturas

    // Variables de velocidad y tiempo de generación
    private float velocidadCaidaActual = 150; // Velocidad reducida
    private long intervaloGeneracionActual = 150000000; // Intervalo inicial más amplio entre gotas (150 ms)
    private long intervaloGeneracionOriginal = 100000000; // Intervalo original más rápido entre gotas (100 ms)

    public Lluvia(Texture gotaBuena, Texture gotaMala, Texture gotaEspecial, Texture gotaBuenaNueva, Texture gotaEspecialNueva, Texture gotaLlama, Texture gotaJetpack, Sound jetpackSound, Sound soundLlama, Sound dropSound, Music rainMusic) {
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
        this.rainMusic = rainMusic;
        this.dropSound = dropSound;
        this.gotaBuena = gotaBuena;
        this.gotaMala = gotaMala;
        this.gotaEspecial = gotaEspecial;
        this.gotaBuenaNueva = gotaBuenaNueva;
        this.gotaEspecialNueva = gotaEspecialNueva;
        this.gotaLlama = gotaLlama;
        this.soundLlama = soundLlama;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
        this.gotaJetpack = gotaJetpack;
        this.jetpackSound = jetpackSound;
        this.rainMusic.setVolume(0.4f);
        this.tomateTexture = tomateTexture;
        this.tomate = new Tomate(tomateTexture);
        this.jefeMusic = jefeMusic;
    }

    public void crear() {
        gotas = new Array<>();
        crearGotaDeLluvia();
        rainMusic.setLooping(true);
        rainMusic.play();
    }
    
    private void iniciarMusicaJefe() {
        if (rainMusic.isPlaying()) {
            rainMusic.stop(); // Pausar la música de lluvia
        }
        jefeMusic.setLooping(true);
        jefeMusic.play(); // Iniciar la música del jefe
    }

    // Método para finalizar el combate con el jefe
    private void detenerMusicaJefe() {
        if (jefeMusic.isPlaying()) {
            jefeMusic.stop(); // Parar la música del jefe
        }
        rainMusic.play(); // Reanudar la música de lluvia
    }

    public void cambiarGotaMala(Texture nuevaTextura) {
        this.gotaMala = nuevaTextura;
        for (IGota gota : gotas) {
            if (gota instanceof GotaMala) {
                gota.getTextura().dispose();
                ((GotaMala) gota).setTextura(nuevaTextura);
<<<<<<< HEAD
=======
=======

        this.gotaJetpack = gotaJetpack;
        this.jetpackSound = jetpackSound;

        // Establecer el volumen de la música al 40%
        this.rainMusic.setVolume(0.4f); // 0.4f representa el 40% del volumen máximo
    }

    public void cambiarGotaMala(Texture nuevaTextura) {
        this.gotaMala = nuevaTextura; // Actualiza la textura de la gota mala
        for (IGota gota : gotas) { // Cambiado a IGota
            if (gota instanceof GotaMala) {
                gota.getTextura().dispose(); // Liberar la textura anterior
                ((GotaMala) gota).setTextura(nuevaTextura); // Cambiar a la nueva textura
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
            }
        }
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    public boolean actualizarMovimiento(Tarro tarro) {
        // Cambio de texturas y generación de gotas llama cada 1000 puntos
        if (tarro.getPuntos() >= 1000 && !texturasCambiadas) {
            gotaBuena.dispose();
            gotaEspecial.dispose();
            gotaBuena = gotaBuenaNueva;
            gotaEspecial = gotaEspecialNueva;
            for (IGota gota : gotas) {
                if (gota instanceof GotaBuena) {
                    ((GotaBuena) gota).actualizarTextura(gotaBuena);
                } else if (gota instanceof GotaEspecial) {
                    ((GotaEspecial) gota).actualizarTextura(gotaEspecial);
                }
            }
            texturasCambiadas = true;
            velocidadCaidaActual += 100;
        }

        if (tarro.getPuntos() >= ultimoPuntajeLlama + 1000) {
            crearGotaLlama();
            ultimoPuntajeLlama = tarro.getPuntos();
        }

        // Activación del jefe a intervalos de 3000 puntos, empezando desde 2000
        if (tarro.getPuntos() >= proximoPuntajeJefe && !jefeActivo && !jefeActivado) {
            tomate = new Tomate(tomateTexture);
            tomate.reducirTamano();
            tarro.reducirTamano();
            for (IGota gota : gotas) {
                if (gota instanceof GotaMala) {
                    ((GotaMala) gota).reducirTamano();
                }
            }
            jefeActivo = true;
            jefeActivado = true; // Bloquea futuras activaciones mientras esté activo
            tiempoInicioPatron = TimeUtils.nanoTime();
            iniciarMusicaJefe(); // Cambia a música de jefe
        }

        // Comportamiento del jefe
        if (jefeActivo) {
            tomate.actualizar(Gdx.graphics.getDeltaTime());
            alternarPatronSecuencialmente();
            detectarImpactosConBalas(tarro);

            if (tomate.getVida() <= 0) { // Jefe derrotado
                jefeActivo = false;
                jefeActivado = false; // Permite activar el próximo jefe
                proximoPuntajeJefe += 3000; // Incrementar el puntaje para el próximo jefe
                detenerMusicaJefe(); // Cambia a la música de lluvia
                tarro.sumarPuntos(1000); // Bonificación al derrotar al jefe

                // Eliminar gotas malas generadas por el jefe
                for (int i = gotas.size - 1; i >= 0; i--) {
                    IGota gota = gotas.get(i);
                    if (gota instanceof GotaMala) {
                        gotas.removeIndex(i);
                    }
                }

                // Restaurar tamaño y reanudar generación normal de gotas
                tarro.restaurarTamano();
                velocidadCaidaActual = 300; // Restablece velocidad a la normal
                intervaloGeneracionActual = intervaloGeneracionOriginal;
            }

            if (TimeUtils.nanoTime() - tiempoUltimoDisparo > INTERVALO_DISPARO) {
                tarro.disparar();
                tiempoUltimoDisparo = TimeUtils.nanoTime();
            }

            switch (indicePatron) {
                case 0:
                    if (TimeUtils.nanoTime() - tiempoUltimoDisparoCircular > INTERVALO_DISPARO_CIRCULAR) {
                        dispararGotasEnCirculoDesdeTomate();
                        tiempoUltimoDisparoCircular = TimeUtils.nanoTime();
                    }
                    break;
                case 1:
                    if (TimeUtils.nanoTime() - tiempoUltimoDisparoDiagonal > INTERVALO_DISPARO_DIAGONAL) {
                        dispararGotasDiagonalesDesdeTomate();
                        tiempoUltimoDisparoDiagonal = TimeUtils.nanoTime();
                    }
                    break;
                case 2:
                    if (TimeUtils.nanoTime() - tiempoUltimoDisparoDiagonal > INTERVALO_DISPARO_DIAGONAL) {
                        dispararGotasDiagonalesAntihorarioDesdeTomate();
                        tiempoUltimoDisparoDiagonal = TimeUtils.nanoTime();
                    }
                    break;
            }
        } else {
            if (TimeUtils.nanoTime() - lastDropTime > intervaloGeneracionActual) {
                crearGotaDeLluvia();
            }
        }

        // Actualización y detección de colisiones de gotas
    for (int i = gotas.size - 1; i >= 0; i--) {
        IGota gota = gotas.get(i);

        // Verificar si la gota es null
        if (gota == null) {
            gotas.removeIndex(i); // Eliminar gotas nulas
            continue;
        }

        // Actualizar posición y procesar efectos a través del Template Method
        if (gota instanceof Gota) {
            ((Gota) gota).ejecutar(tarro, Gdx.graphics.getDeltaTime(), velocidadCaidaActual);
        }

        // Si la gota sale de la pantalla, eliminarla
        if (gota.getHitbox().y + 64 < 0) {
            gotas.removeIndex(i);
            continue;
        }

        // Si la gota es recogida por el tarro, eliminarla
        if (gota.getHitbox().overlaps(tarro.getArea())) {
            gotas.removeIndex(i); // Eliminar la gota de la lista
        }
    }



    return tarro.getVidas() > 0;
}





    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (IGota gota : gotas) {
            batch.draw(gota.getTextura(), gota.getHitbox().x, gota.getHitbox().y, gota.getHitbox().width, gota.getHitbox().height);
        }

        if (jefeActivo) {
            tomate.dibujar(batch);
        }
    }

    public void destruir() {
        dropSound.dispose();
        rainMusic.dispose();
        gotaBuena.dispose();
        gotaMala.dispose();
        gotaEspecial.dispose();
        gotaBuenaNueva.dispose();
        gotaEspecialNueva.dispose();
        for (IGota gota : gotas) {
            gota.getTextura().dispose();
        }
        jefeMusic.dispose();
    }

    public void pausar() {
        // Pausa la música que esté en reproducción actualmente
        if (rainMusic.isPlaying()) {
            rainMusic.pause();
        }
        if (jefeMusic.isPlaying()) {
            jefeMusic.pause();
        }   
    }

    public void continuar() {
        // Reanuda la música que estaba en reproducción antes de la pausa
        if (jefeActivo && !jefeMusic.isPlaying()) {
            jefeMusic.play(); // Reanuda la música del jefe si el jefe está activo
        } else if (!jefeActivo && !rainMusic.isPlaying()) {
            rainMusic.play(); // Reanuda la música de lluvia si el jefe no está activo
        }
<<<<<<< HEAD
=======
=======
    public void crear() {
        gotas = new Array<>(); // Inicializa el array de gotas
        crearGotaDeLluvia();
        rainMusic.setLooping(true);
        rainMusic.play();
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    }

    private void crearGotaDeLluvia() {
        float x = MathUtils.random(0, 800 - 64);
        float y = 480;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
        int tipoGota = MathUtils.random(1, 100);
        IGota nuevaGota;

        if (tipoGota <= 2) {
            nuevaGota = new GotaJetpack(gotaJetpack, jetpackSound, x, y);
        } else if (tipoGota <= 47) {
            nuevaGota = new GotaBuena(gotaBuena, dropSound, x, y);
        } else if (tipoGota <= 87) {
            nuevaGota = new GotaMala(gotaMala, x, y);
        } else {
<<<<<<< HEAD
=======
=======
        // Determinar el tipo de gota
        int tipoGota = MathUtils.random(1, 100);
        IGota nuevaGota; // Cambiado a IGota


        if (tipoGota <= 2) { // 2% de probabilidad para la gota Jetpack
            nuevaGota = new GotaJetpack(gotaJetpack, jetpackSound, x, y);
        } else if (tipoGota <= 47) { // 45% de probabilidad para gotas buenas (normales)
            nuevaGota = new GotaBuena(gotaBuena, dropSound, x, y);
        } else if (tipoGota <= 87) { // 40% de probabilidad para gotas malas
            nuevaGota = new GotaMala(gotaMala, x, y);
        } else { // 10% de probabilidad para gotas especiales
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
            nuevaGota = new GotaEspecial(gotaEspecial, dropSound, x, y);
        }

        gotas.add(nuevaGota);
        lastDropTime = TimeUtils.nanoTime();
    }

    private void crearGotaLlama() {
        float x = MathUtils.random(0, 800 - 64);
        float y = 480;

        IGota nuevaGota = new GotaLlama(gotaLlama, soundLlama, x, y);
        gotas.add(nuevaGota);
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    private void alternarPatronSecuencialmente() {
        long tiempoTranscurrido = TimeUtils.nanoTime() - tiempoInicioPatron;

        switch (indicePatron) {
            case 0:
                if (tiempoTranscurrido > DURACION_PATRON_CIRCULAR) {
                    indicePatron = 1;
                    tiempoInicioPatron = TimeUtils.nanoTime();
                }
                break;
            case 1:
                if (tiempoTranscurrido > DURACION_PATRON_DIAGONAL) {
                    indicePatron = 2;
                    tiempoInicioPatron = TimeUtils.nanoTime();
                }
                break;
            case 2:
                if (tiempoTranscurrido > DURACION_PATRON_CONCENTRICO) {
                    indicePatron = 0;
                    tiempoInicioPatron = TimeUtils.nanoTime();
                }
                break;
        }
    }

    private void dispararGotasEnCirculoDesdeTomate() {
        int cantidadBalas = 12;
        float radioInicial = 30;

        float centroX = tomate.getHitbox().x + tomate.getHitbox().width / 2;
        float centroY = tomate.getHitbox().y + tomate.getHitbox().height / 2;

        for (int i = 0; i < cantidadBalas; i++) {
            float angulo = (float) (i * (2 * Math.PI / cantidadBalas));
            float x = centroX + radioInicial * MathUtils.cos(angulo) - 16;
            float y = centroY + radioInicial * MathUtils.sin(angulo) - 16;

            GotaMala gotaMala = new GotaMala(this.gotaMala, x, y);
            if (jefeActivo) gotaMala.reducirTamano();
            gotaMala.setDireccion(MathUtils.cos(angulo), MathUtils.sin(angulo));
            gotas.add(gotaMala);
        }
    }

    private void dispararGotasDiagonalesDesdeTomate() {
        float centroX = tomate.getHitbox().x + tomate.getHitbox().width / 2;
        float centroY = tomate.getHitbox().y + tomate.getHitbox().height / 2;
        float radioConstante = 40;
        float x = centroX + radioConstante * MathUtils.cos(anguloEspiral);
        float y = centroY + radioConstante * MathUtils.sin(anguloEspiral);
        GotaMala gotaMala = new GotaMala(this.gotaMala, x, y);

        if (jefeActivo) {
            gotaMala.reducirTamano();
        }

        gotaMala.setDireccion(MathUtils.cos(anguloEspiral), MathUtils.sin(anguloEspiral));
        gotas.add(gotaMala);

        anguloEspiral += MathUtils.PI / 10;

        if (anguloEspiral >= MathUtils.PI2) {
            anguloEspiral = 0;
        }
    }

    private void dispararGotasDiagonalesAntihorarioDesdeTomate() {
        float centroX = tomate.getHitbox().x + tomate.getHitbox().width / 2;
        float centroY = tomate.getHitbox().y + tomate.getHitbox().height / 2;
        float radioConstante = 40;
        float xHorario = centroX + radioConstante * MathUtils.cos(anguloEspiralHorario);
        float yHorario = centroY + radioConstante * MathUtils.sin(anguloEspiralHorario);

        GotaMala gotaMalaHorario = new GotaMala(this.gotaMala, xHorario, yHorario);
        if (jefeActivo) {
            gotaMalaHorario.reducirTamano();
        }
        gotaMalaHorario.setDireccion(MathUtils.cos(anguloEspiralHorario), MathUtils.sin(anguloEspiralHorario));
        gotas.add(gotaMalaHorario);

        float xAntihorario = centroX + radioConstante * MathUtils.cos(anguloEspiralAntihorario);
        float yAntihorario = centroY + radioConstante * MathUtils.sin(anguloEspiralAntihorario);

        GotaMala gotaMalaAntihorario = new GotaMala(this.gotaMala, xAntihorario, yAntihorario);
        if (jefeActivo) {
            gotaMalaAntihorario.reducirTamano();
        }
        gotaMalaAntihorario.setDireccion(MathUtils.cos(anguloEspiralAntihorario), MathUtils.sin(anguloEspiralAntihorario));
        gotas.add(gotaMalaAntihorario);

        anguloEspiralHorario += MathUtils.PI / 8;
        anguloEspiralAntihorario -= MathUtils.PI / 4;

        if (anguloEspiralHorario >= MathUtils.PI2) {
            anguloEspiralHorario = 0;
        }
        if (anguloEspiralAntihorario <= -MathUtils.PI2) {
            anguloEspiralAntihorario = 0;
        }
    }
    
    private void detectarImpactosConBalas(Tarro tarro) {
        for (Bala bala : tarro.getBalas()) {
            if (bala.getHitbox().overlaps(tomate.getHitbox())) {
                tomate.reducirVida(30);
                tarro.getBalas().removeValue(bala, true);
            }
        }
    }

    public boolean isJefeActivo() {
        return jefeActivo;
    }

    public Tomate getTomate() {
        return tomate;
    }

}
<<<<<<< HEAD
=======
=======
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
            for (IGota gota : gotas) { // Cambiado a IGota
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
            IGota gota = gotas.get(i); // Cambiado a IGota
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
        for (IGota gota : gotas) { // Cambiado a IGota
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
        for (IGota gota : gotas) { // Cambiado a IGota
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

>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
