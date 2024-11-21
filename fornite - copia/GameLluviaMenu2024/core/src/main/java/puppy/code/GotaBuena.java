/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class GotaBuena extends Gota {
    private Sound dropSound;

    public GotaBuena(Texture textura, Sound dropSound, float x, float y) {
        super(textura, x, y);
        this.tipo = 2; // Gota buena
        this.dropSound = dropSound;
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.sumarPuntos(10);
        dropSound.play(0.4f);
    }

<<<<<<< HEAD
    // Método adicional para actualizar textura
=======
    // Método para actualizar la textura
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
    public void actualizarTextura(Texture nuevaTextura) {
        this.textura = nuevaTextura;
    }
}


<<<<<<< HEAD

=======
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
