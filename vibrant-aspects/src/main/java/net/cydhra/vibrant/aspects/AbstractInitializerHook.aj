package net.cydhra.vibrant.aspects;

public abstract aspect AbstractInitializerHook {

    public abstract pointcut startGame();

    after(): startGame() {
        this.startGame();
    }

    public abstract void startGame();
}
