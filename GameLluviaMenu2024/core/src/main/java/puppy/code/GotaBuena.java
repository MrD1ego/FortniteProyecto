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

    // Método adicional para actualizar textura
    public void actualizarTextura(Texture nuevaTextura) {
        this.textura = nuevaTextura;
    }
}



