package puppy.code;

import com.badlogic.gdx.graphics.Texture;

public class DisparoMultiple implements HabilidadStrategy {
    @Override
    public void activar(Tarro tarro) {
        // Generar tres balas con desplazamiento horizontal
        Texture texturaBala = tarro.getTexturaBala();
        float balaXCentro = tarro.getArea().x + tarro.getArea().width / 2 - texturaBala.getWidth() / 2;
        float balaY = tarro.getArea().y + tarro.getArea().height;

        // Bala central
        tarro.getBalas().add(new Bala(texturaBala, balaXCentro, balaY, 300));

        // Bala a la izquierda
        tarro.getBalas().add(new Bala(texturaBala, balaXCentro - 20, balaY, 300));

        // Bala a la derecha
        tarro.getBalas().add(new Bala(texturaBala, balaXCentro + 20, balaY, 300));
    }
}
