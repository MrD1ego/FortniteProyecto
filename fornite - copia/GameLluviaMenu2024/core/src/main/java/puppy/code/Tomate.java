/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package puppy.code;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Tomate {
    private Texture tomateTexture;
    private Rectangle hitbox;
    private float velocidadX;
    private boolean cayendo = true;
    private boolean reducido = false; // Controla si el tamaño ya fue reducido
    private float tiempoCaida = 0;
    private final float DURACION_CAIDA = 1.0f; // Duración de la caída (1 segundo)
    private int vida; // Vida del jefe

    private static final int VIDA_INICIAL = 20000; // Vida inicial del tomate

    public Tomate(Texture tomateTexture) {
        this.tomateTexture = tomateTexture;
        this.hitbox = new Rectangle(400, 480, 64, 64); // Inicialmente en el centro superior de la pantalla
        this.velocidadX = 150f; // Velocidad de movimiento horizontal
        this.vida = VIDA_INICIAL; // Inicializar la vida del jefe
    }

    public void actualizar(float deltaTime) {
        if (cayendo) {
            tiempoCaida += deltaTime;
            hitbox.y -= 100 * deltaTime;

            if (tiempoCaida >= DURACION_CAIDA) {
                cayendo = false;
            }
        } else {
            hitbox.x += velocidadX * deltaTime;

            if (hitbox.x <= 0 || hitbox.x >= 800 - hitbox.width) {
                velocidadX = -velocidadX;
            }
        }
    }

    public void reducirTamano() {
        if (!reducido) { // Solo reducir una vez
            hitbox.width *= 0.6;
            hitbox.height *= 0.6;
            reducido = true;
        }
    }

    public void dibujar(SpriteBatch batch) {
        batch.draw(tomateTexture, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getVida() {
        return vida;
    }

    public void reducirVida(int cantidad) {
        vida -= cantidad;
        if (vida < 0) {
            vida = 0; // La vida no puede ser negativa
        }
    }

    public boolean estaMuerto() {
        return vida <= 0;
    }
}


