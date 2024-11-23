package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
    private Array<IGota> gotas;
    private long lastDropTime;
    private Texture gotaBuena;
    private Texture gotaMala;
    private Texture gotaEspecial;
    private Texture gotaBuenaNueva;
    private Texture gotaEspecialNueva;
    private Sound dropSound;
    private Texture gotaLlama;
    private Sound soundLlama;
    private Music rainMusic;
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
    private IGotaFactory fabrica;

    public Lluvia(Texture gotaBuena, Texture gotaMala, Texture gotaEspecial, Texture gotaBuenaNueva,
                  Texture gotaEspecialNueva, Texture gotaLlama, Texture gotaJetpack, Sound jetpackSound,
                  Sound soundLlama, Sound dropSound, Music rainMusic, Texture tomateTexture, Music jefeMusic) {
        this.rainMusic = rainMusic;
        this.dropSound = dropSound;
        this.gotaBuena = gotaBuena;
        this.gotaMala = gotaMala;
        this.gotaEspecial = gotaEspecial;
        this.gotaBuenaNueva = gotaBuenaNueva;
        this.gotaEspecialNueva = gotaEspecialNueva;
        this.gotaLlama = gotaLlama;
        this.soundLlama = soundLlama;
        this.gotaJetpack = gotaJetpack;
        this.jetpackSound = jetpackSound;
        this.rainMusic.setVolume(0.4f);
        this.tomateTexture = tomateTexture;
        this.tomate = new Tomate(tomateTexture);
        this.jefeMusic = jefeMusic;
        this.fabrica = new GotaFactoryA();
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
            }
        }
    }

    public boolean actualizarMovimiento(Tarro tarro) {
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

        // Activar el jefe si es necesario
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
            jefeActivado = true;
            tiempoInicioPatron = TimeUtils.nanoTime();
            iniciarMusicaJefe();

            tarro.setHabilidad(new DisparoMultiple()); // Asignar habilidad al tarro
        }

        // Patrón de disparo del jefe
        if (jefeActivo) {
            tomate.actualizar(Gdx.graphics.getDeltaTime());
            alternarPatronSecuencialmente();
            detectarImpactosConBalas(tarro);

            if (TimeUtils.nanoTime() - tiempoUltimoDisparo > INTERVALO_DISPARO) {
                tarro.disparar(); // Tarro dispara al jefe
                tiempoUltimoDisparo = TimeUtils.nanoTime();
            }

            // Lógica de patrones
            switch (indicePatron) {
                case 0: // Disparos en círculo
                    if (TimeUtils.nanoTime() - tiempoUltimoDisparoCircular > INTERVALO_DISPARO_CIRCULAR) {
                        dispararGotasEnCirculoDesdeTomate();
                        tiempoUltimoDisparoCircular = TimeUtils.nanoTime();
                    }
                    break;
                case 1: // Disparos diagonales
                    if (TimeUtils.nanoTime() - tiempoUltimoDisparoDiagonal > INTERVALO_DISPARO_DIAGONAL) {
                        dispararGotasDiagonalesDesdeTomate();
                        tiempoUltimoDisparoDiagonal = TimeUtils.nanoTime();
                    }
                    break;
                case 2: // Disparos diagonales antihorario
                    if (TimeUtils.nanoTime() - tiempoUltimoDisparoDiagonal > INTERVALO_DISPARO_DIAGONAL) {
                        dispararGotasDiagonalesAntihorarioDesdeTomate();
                        tiempoUltimoDisparoDiagonal = TimeUtils.nanoTime();
                    }
                    break;
            }

            if (tomate.getVida() <= 0) { // Jefe derrotado
                jefeActivo = false;
                jefeActivado = false;
                proximoPuntajeJefe += 3000;
                detenerMusicaJefe();
                tarro.sumarPuntos(1000);
                tarro.restaurarTamano();
                velocidadCaidaActual = 300;
                intervaloGeneracionActual = intervaloGeneracionOriginal;
                gotas.clear();
            }
        } else {
            if (TimeUtils.nanoTime() - lastDropTime > intervaloGeneracionActual) {
                crearGotaDeLluvia();
            }
        }

        for (int i = gotas.size - 1; i >= 0; i--) {
            IGota gota = gotas.get(i);
            if (gota == null) {
                gotas.removeIndex(i);
                continue;
            }

            if (gota instanceof Gota) {
                ((Gota) gota).ejecutar(tarro, Gdx.graphics.getDeltaTime(), velocidadCaidaActual);
            }

            if (gota.getHitbox().y + 64 < 0) {
                gotas.removeIndex(i);
                continue;
            }

            if (gota.getHitbox().overlaps(tarro.getArea())) {
                gotas.removeIndex(i);
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
        if (rainMusic.isPlaying()) {
            rainMusic.pause();
        }
        if (jefeMusic.isPlaying()) {
            jefeMusic.pause();
        }   
    }

    public void continuar() {
        if (jefeActivo && !jefeMusic.isPlaying()) {
            jefeMusic.play();
        } else if (!jefeActivo && !rainMusic.isPlaying()) {
            rainMusic.play();
        }
    }

    public void crearGotaDeLluvia() {
        float x = MathUtils.random(0, 800 - 64);
        float y = 480;

        int tipoGota = MathUtils.random(1, 100);
        IGota nuevaGota;

        if (tipoGota <= 2) {
            nuevaGota = fabrica.crearGotaJetpack(gotaJetpack, jetpackSound, x, y);
        } else if (tipoGota <= 47) {
            nuevaGota = fabrica.crearGotaBuena(gotaBuena, dropSound, x, y);
        } else if (tipoGota <= 87) {
            nuevaGota = fabrica.crearGotaMala(gotaMala, x, y);
        } else {
            nuevaGota = fabrica.crearGotaEspecial(gotaEspecial, dropSound, x, y);
        }

        gotas.add(nuevaGota);
        lastDropTime = TimeUtils.nanoTime();
    }

    private void crearGotaLlama() {
        float x = MathUtils.random(0, 800 - 64);
        float y = 480;

        IGota nuevaGota = fabrica.crearGotaLlama(gotaLlama, soundLlama, x, y);
        gotas.add(nuevaGota);
    }

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
    
    
    public void setFactory(IGotaFactory fabrica) {
        this.fabrica = fabrica;
    }
}
