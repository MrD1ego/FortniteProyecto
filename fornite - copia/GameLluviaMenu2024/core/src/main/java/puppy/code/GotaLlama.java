package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class GotaLlama extends Gota {
    private Sound soundLlama;
<<<<<<< HEAD
=======

>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    public GotaLlama(Texture textura, Sound soundLlama, float x, float y) {
        super(textura, x, y);
        this.soundLlama = soundLlama;
    }
<<<<<<< HEAD
    @Override
    public void aplicarEfecto(Tarro tarro) {
=======

    @Override
    public void aplicarEfecto(Tarro tarro) {
<<<<<<< HEAD
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
        tarro.aumentarVida();
        soundLlama.play();
    }
}
<<<<<<< HEAD
=======
=======
        tarro.aumentarVida(); // Aumentar vida
        soundLlama.play(); // Reproducir sonido
    }
}
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
