package net.cydhra.vibrant.aspects;

import net.cydhra.eventsystem.EventManager;
import net.cydhra.vibrant.events.minecraft.KeyboardEvent;

public abstract aspect AbstractKeyBindingHook {

    public abstract pointcut keypress(int keycode, boolean pressed);

    void around(int keycode, boolean pressed): keypress(keycode, pressed) {
        if (keycode == 0) proceed(keycode, pressed);
        else {
            final KeyboardEvent event;
            EventManager.callEvent(event = new KeyboardEvent(pressed ? KeyboardEvent.KeyboardEventType.PRESS : KeyboardEvent
                    .KeyboardEventType.RELEASE, keycode));

            if (!event.isCancelled()) proceed(keycode, pressed);
        }
    }
}
