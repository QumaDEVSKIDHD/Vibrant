package net.cydhra.vibrant.aspects;

public abstract aspect AbstractRenderOverlayHook {

    pointcut renderGameOverlay(float partialTicks);

    after(float partialTicks): renderGameOverlay(partialTicks) {
        this.renderGameOverlay(partialTicks);
    }

    public abstract void renderGameOverlay(float partialTicks);
}
