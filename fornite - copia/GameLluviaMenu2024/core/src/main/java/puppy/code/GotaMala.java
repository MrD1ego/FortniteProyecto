/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package puppy.code;

import com.badlogic.gdx.graphics.Texture;

public class GotaMala extends Gota {
    public GotaMala(Texture textura, float x, float y) {
        super(textura, x, y);
        this.tipo = 1; // Gota mala
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.dañar();
    }
}

