package net.Greek.Tenebris.util;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import static net.minecraft.world.item.Item.BASE_ATTACK_DAMAGE_ID;

public class ModAtrributeModifiers {

    public static final AttributeModifier attackDamage =
            new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 66.0, AttributeModifier.Operation.ADD_VALUE);

}
