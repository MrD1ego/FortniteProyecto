
package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public interface IGota {
    void aplicarEfecto(Tarro tarro);
    void actualizarPosicion(float deltaTime, float velocidad);
    Texture getTextura();
    Rectangle getHitbox();
}