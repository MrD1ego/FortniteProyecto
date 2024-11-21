/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

public class GotaEspecial extends Gota {
    private Sound dropSound;

    public GotaEspecial(Texture textura, Sound dropSound, float x, float y) {
        super(textura, x, y);
        this.tipo = 3; // Gota especial
        this.dropSound = dropSound;
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.sumarPuntos(50);
        dropSound.play(0.4f);
    }
    public void actualizarTextura(Texture nuevaTextura) {
        this.textura = nuevaTextura;
    }
}