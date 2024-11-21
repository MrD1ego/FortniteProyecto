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
<<<<<<< HEAD
        tarro.aumentarVida();
        soundLlama.play();
    }
}
=======
        tarro.aumentarVida(); // Aumentar vida
        soundLlama.play(); // Reproducir sonido
    }
}
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
