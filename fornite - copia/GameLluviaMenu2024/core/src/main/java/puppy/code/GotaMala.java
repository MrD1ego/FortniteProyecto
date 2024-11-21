/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package puppy.code;

import com.badlogic.gdx.graphics.Texture;

public class GotaMala extends Gota {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    private float dx = 0; // Dirección en x, por defecto hacia abajo
    private float dy = -1; // Dirección en y, por defecto hacia abajo
    private boolean reducido = false;
    private final float anchoOriginal;
    private final float altoOriginal;
<<<<<<< HEAD
    
=======

>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    public GotaMala(Texture textura, float x, float y) {
        super(textura, x, y);
        this.tipo = 1; // Gota mala
        this.anchoOriginal = hitbox.width;
        this.altoOriginal = hitbox.height;
    }
<<<<<<< HEAD
=======

>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    public void setDireccion(float dx, float dy) {
        this.dx = dx;
        this.dy = dy;
    }
<<<<<<< HEAD
=======

>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    @Override
    public void actualizarPosicion(float deltaTime, float velocidad) {
        hitbox.x += dx * velocidad * deltaTime;
        hitbox.y += dy * velocidad * deltaTime;
<<<<<<< HEAD
    }
=======
=======
    public GotaMala(Texture textura, float x, float y) {
        super(textura, x, y);
        this.tipo = 1; // Gota mala
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
    }

>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.dañar();
    }
<<<<<<< HEAD
    public void setTextura(Texture nuevaTextura) {
        this.textura = nuevaTextura;
    }
=======
<<<<<<< HEAD

    public void setTextura(Texture nuevaTextura) {
        this.textura = nuevaTextura;
    }

>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    public void reducirTamano() {
        if (!reducido) {
            hitbox.width *= 0.5;
            hitbox.height *= 0.5;
            reducido = true;
        }
    }
<<<<<<< HEAD
=======

>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    public void restaurarTamano() {
        hitbox.width = anchoOriginal;
        hitbox.height = altoOriginal;
        reducido = false;
    }
}
<<<<<<< HEAD
=======



=======
    
    public void setTextura(Texture nuevaTextura) {
        this.textura = nuevaTextura;
    }
}

>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
