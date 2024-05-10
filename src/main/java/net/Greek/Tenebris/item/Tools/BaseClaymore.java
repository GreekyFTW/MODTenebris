package net.Greek.Tenebris.item.Tools;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class BaseClaymore extends SwordItem {
    public BaseClaymore(Tier tier, Properties properties) {
        super(tier, properties.attributes(createAttributes(tier, 15, -2.5f)));
    }


}


