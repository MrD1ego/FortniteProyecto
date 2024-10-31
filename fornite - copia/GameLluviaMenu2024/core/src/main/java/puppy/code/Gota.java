/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Gota implements IGota {
    protected Texture textura;
    protected Rectangle hitbox;
    protected int tipo; // 1: mala, 2: buena, 3: especial

    public Gota(Texture textura, float x, float y) {
        this.textura = textura;
        this.hitbox = new Rectangle(x, y, 64, 64);
    }

    @Override
    public abstract void aplicarEfecto(Tarro tarro);

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

    @Override
    public Texture getTextura() {
        return textura;
    }

    @Override
    public void actualizarPosicion(float deltaTime, float velocidad) {
        hitbox.y -= velocidad * deltaTime;
    }
}