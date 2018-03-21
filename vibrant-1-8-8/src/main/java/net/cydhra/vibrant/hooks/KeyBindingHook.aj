package net.cydhra.vibrant.hooks;

import net.cydhra.vibrant.aspects.AbstractKeyBindingHook;

/**
 *
 */
public aspect KeyBindingHook extends AbstractKeyBindingHook {
    @Override
    public pointcut keypress(int keycode, boolean pressed):
            call(void net.minecraft.client.settings.KeyBinding.setKeyBindState(int, boolean))
                    && args(keycode, pressed);
}
