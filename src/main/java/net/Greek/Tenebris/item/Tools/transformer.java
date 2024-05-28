package net.Greek.Tenebris.item.Tools;

import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.item.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class transformer extends SwordItem {

    public transformer(Tier tier, Properties properties ) {
        super(tier, properties.attributes(createAttributes(tier, 15, -2.5f)));
    }



    public void changeMode(Player player, Entity entity, @NotNull ItemStack item) {

    }

}