package net.cydhra.vibrant.interfaces.client;

import net.minecraft.client.settings.GameSettings;

/**
 *
 */
public aspect GameSettingsInterface {
    
    declare parents:(GameSettings)implements net.cydhra.vibrant.api.client.VibrantGameSettings;
    
    public boolean GameSettings.getRenderEntityShadows() {
        return this.field_181151_V;
    }

    public void GameSettings.setRenderEntityShadows(boolean renderEntityShadows) {
        this.field_181151_V = renderEntityShadows;
    }

    public int GameSettings.getGuiScale() {
        return this.guiScale;
    }

    public void GameSettings.setGuiScale(int guiScale) {
        this.guiScale = guiScale;
    }

    public float GameSettings.getGammaSetting() { return this.gammaSetting; }

    public void GameSettings.setGammaSetting(float gamma) { this.gammaSetting = gamma; }
}
