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

    // Métodos concretos
    @Override
    public void actualizarPosicion(float deltaTime, float velocidad) {
        hitbox.y -= velocidad * deltaTime;
    }

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

    @Override
    public Texture getTextura() {
        return textura;
    }

    // Verificación común de colisión
    protected boolean verificarColision(Tarro tarro) {
        return hitbox.overlaps(tarro.getArea());
    }

    // Método abstracto para definir el efecto de cada gota
    @Override
    public abstract void aplicarEfecto(Tarro tarro);
}
