/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

public interface IGotaFactory {
    GotaMala crearGotaMala(Texture textura, float x, float y);
    GotaBuena crearGotaBuena(Texture textura, Sound sonido, float x, float y);
    GotaEspecial crearGotaEspecial(Texture textura, Sound sonido, float x, float y);
    GotaJetpack crearGotaJetpack(Texture textura, Sound sonido, float x, float y);
    GotaLlama crearGotaLlama(Texture textura, Sound sonido, float x, float y);
}
