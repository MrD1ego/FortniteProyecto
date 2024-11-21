package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class GotaJetpack extends Gota {
    private Sound soundJetpack;
    private float tiempoActivacion;
    private boolean activada;
    private float tiempoRestante;

    public GotaJetpack(Texture textura, Sound soundJetpack, float x, float y) {
        super(textura, x, y);
        this.soundJetpack = soundJetpack;
        this.tiempoActivacion = 10; // 10 segundos de activación
        this.activada = false;
        this.tiempoRestante = 0;
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        if (!activada) {
            soundJetpack.play();
            activada = true;
            tiempoRestante = tiempoActivacion;
            tarro.activarJetpack();
        }
    }

    public void actualizar(float delta) {
        if (activada) {
            tiempoRestante -= delta;
            if (tiempoRestante <= 0) {
                activada = false;
            }
        }
    }

    public boolean isActivada() {
        return activada;
    }
}
