package net.cydhra.vibrant.interfaces.world;

import net.cydhra.vibrant.api.entity.VibrantEntity;
import net.minecraft.entity.Entity;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 */
public aspect WorldInterface {
    
    declare parents:(net.minecraft.client.multiplayer.WorldClient)implements net.cydhra.vibrant.api.world.VibrantWorld;
    
    public List<VibrantEntity> net.minecraft.client.multiplayer.WorldClient.getEntityList() {
        return this.loadedEntityList.stream().map(
                new Function<Entity, VibrantEntity>() {
                    @Override
                    public VibrantEntity apply(final Entity entity) {
                        return (VibrantEntity) entity;
                    }
                }
        ).collect(Collectors.toList());
    }
    
}
