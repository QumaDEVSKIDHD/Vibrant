package net.cydhra.vibrant.aspects;

import net.cydhra.vibrant.VibrantClient;

public abstract aspect AbstractTickHook {

    public abstract pointcut tick();

    after(): tick() {
        VibrantClient.INSTANCE.tick();
    }
}
