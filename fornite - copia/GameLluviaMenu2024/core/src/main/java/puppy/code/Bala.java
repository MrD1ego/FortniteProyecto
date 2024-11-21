/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package puppy.code;

/**
 *
 * @author Diego Escobar
 */
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bala {
    private Rectangle hitbox;
    private Texture textura;
    private float velocidad;
    private static final float ESCALA = 0.5f; // Escala de 50%

    public Bala(Texture textura, float x, float y, float velocidad) {
        this.textura = textura;
        // Ajustar el hitbox a la mitad del tamaño de la textura
        this.hitbox = new Rectangle(x, y, textura.getWidth() * ESCALA, textura.getHeight() * ESCALA);
        this.velocidad = velocidad;
    }

    public void actualizarPosicion(float deltaTime) {
        hitbox.y += velocidad * deltaTime;
    }

    public void dibujar(SpriteBatch batch) {
        // Dibujar la textura escalada al tamaño especificado
        batch.draw(textura, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}

