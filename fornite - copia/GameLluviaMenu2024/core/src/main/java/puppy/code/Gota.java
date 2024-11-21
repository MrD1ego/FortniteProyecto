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

    // Método plantilla (Template Method)
    public final void ejecutar(Tarro tarro, float deltaTime, float velocidad) {
        actualizarPosicion(deltaTime, velocidad); // Paso 1: Actualizar posición
        if (verificarColision(tarro)) { // Paso 2: Verificar colisión con el tarro
            aplicarEfecto(tarro); // Paso 3: Aplicar efecto específico
        }
    }

    // Método concreto para actualizar posición
    @Override
    public void actualizarPosicion(float deltaTime, float velocidad) {
        hitbox.y -= velocidad * deltaTime;
    }

    // Método común para verificar colisión
    protected boolean verificarColision(Tarro tarro) {
        return hitbox.overlaps(tarro.getArea());
    }

    // Métodos abstractos que deben implementar las subclases
    @Override
    public abstract void aplicarEfecto(Tarro tarro);

    // Getters
    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

    @Override
    public Texture getTextura() {
        return textura;
    }
}


