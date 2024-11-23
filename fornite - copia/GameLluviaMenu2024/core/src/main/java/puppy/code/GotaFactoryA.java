
package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

public class GotaFactoryA implements IGotaFactory {
    @Override
    public GotaMala crearGotaMala(Texture textura, float x, float y) {
        return new GotaMala(textura, x, y);
    }
    @Override
    public GotaBuena crearGotaBuena(Texture textura, Sound sonido, float x, float y) {
        return new GotaBuena(textura, sonido, x, y);
    }
    @Override
    public GotaEspecial crearGotaEspecial(Texture textura, Sound sonido, float x, float y) {
        return new GotaEspecial(textura, sonido, x, y);
    }
    @Override
    public GotaJetpack crearGotaJetpack(Texture textura, Sound sonido, float x, float y) {
        return new GotaJetpack(textura, sonido, x, y);
    }
    @Override
    public GotaLlama crearGotaLlama(Texture textura, Sound sonido, float x, float y) {
        return new GotaLlama(textura, sonido, x, y);
    }
}
