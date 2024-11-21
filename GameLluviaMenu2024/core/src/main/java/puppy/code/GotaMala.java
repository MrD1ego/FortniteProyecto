/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package puppy.code;

import com.badlogic.gdx.graphics.Texture;

public class GotaMala extends Gota {
    private float dx = 0; // Dirección en x, por defecto hacia abajo
    private float dy = -1; // Dirección en y, por defecto hacia abajo
    private boolean reducido = false;
    private final float anchoOriginal;
    private final float altoOriginal;

    public GotaMala(Texture textura, float x, float y) {
        super(textura, x, y);
        this.tipo = 1; // Gota mala
        this.anchoOriginal = hitbox.width;
        this.altoOriginal = hitbox.height;
    }

    public void setDireccion(float dx, float dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void actualizarPosicion(float deltaTime, float velocidad) {
        hitbox.x += dx * velocidad * deltaTime;
        hitbox.y += dy * velocidad * deltaTime;
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.dañar();
    }

    public void setTextura(Texture nuevaTextura) {
        this.textura = nuevaTextura;
    }

    public void reducirTamano() {
        if (!reducido) {
            hitbox.width *= 0.5;
            hitbox.height *= 0.5;
            reducido = true;
        }
    }

    public void restaurarTamano() {
        hitbox.width = anchoOriginal;
        hitbox.height = altoOriginal;
        reducido = false;
    }
}



