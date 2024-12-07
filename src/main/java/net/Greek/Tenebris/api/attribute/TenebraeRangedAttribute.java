package net.Greek.Tenebris.api.attribute;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class TenebraeRangedAttribute extends RangedAttribute implements ITenebraeAttribute {
    public TenebraeRangedAttribute(String pDescriptionId, double pDefaultValue, double pMin, double pMax) {
        super(pDescriptionId, pDefaultValue, pMin, pMax);
    }
}
