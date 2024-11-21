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

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    // Método plantilla (Template Method)
    public final void ejecutar(Tarro tarro, float deltaTime, float velocidad) {
        actualizarPosicion(deltaTime, velocidad); // Paso 1: Actualizar posición
        if (verificarColision(tarro)) { // Paso 2: Verificar colisión con el tarro
            aplicarEfecto(tarro); // Paso 3: Aplicar efecto específico
        }
    }

<<<<<<< HEAD
    // Método concreto para actualizar posición
=======
    // Métodos concretos
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    @Override
    public void actualizarPosicion(float deltaTime, float velocidad) {
        hitbox.y -= velocidad * deltaTime;
    }
<<<<<<< HEAD

    // Método común para verificar colisión
    protected boolean verificarColision(Tarro tarro) {
        return hitbox.overlaps(tarro.getArea());
    }

    // Métodos abstractos que deben implementar las subclases
    @Override
    public abstract void aplicarEfecto(Tarro tarro);

    // Getters
=======
=======
    @Override
    public abstract void aplicarEfecto(Tarro tarro);
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e

>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

    @Override
    public Texture getTextura() {
        return textura;
    }
<<<<<<< HEAD
}


=======

<<<<<<< HEAD
    // Verificación común de colisión
    protected boolean verificarColision(Tarro tarro) {
        return hitbox.overlaps(tarro.getArea());
    }

    // Método abstracto para definir el efecto de cada gota
    @Override
    public abstract void aplicarEfecto(Tarro tarro);
}
=======
    @Override
    public void actualizarPosicion(float deltaTime, float velocidad) {
        hitbox.y -= velocidad * deltaTime;
    }
}
>>>>>>> b6da7ac1292ed6e54357a3894fd4e87d7bb9165e
>>>>>>> 1c34354e13680c6da4057ccab0ad775e4ffa4753
