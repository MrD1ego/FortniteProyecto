package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class GotaLlama extends Gota {
    private Sound soundLlama;

    public GotaLlama(Texture textura, Sound soundLlama, float x, float y) {
        super(textura, x, y);
        this.soundLlama = soundLlama;
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.aumentarVida();
        soundLlama.play();
    }
}
