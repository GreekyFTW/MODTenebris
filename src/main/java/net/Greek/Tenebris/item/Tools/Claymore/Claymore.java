package net.Greek.Tenebris.item.Tools.Claymore;

//import net.Greek.Tenebris.util.ItemRenderer.ClaymoreItemRenderer;
//import net.Greek.Tenebris.util.ItemRenderer.ClaymoreModel;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.Greek.Tenebris.init.DataComponentRegistry;
import net.Greek.Tenebris.item.interfaces.ToggleableTool;
import net.Greek.Tenebris.util.values;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.world.item.MaceItem.canSmashAttack;
import static net.minecraft.world.item.SwordItem.createAttributes;

public class Claymore extends SwordItem implements IItemExtension, ToggleableTool {



    public Claymore(Tier tier, Properties properties, float damage,float speed ) {
        super(tier, properties.attributes(createAttributes(tier, damage, speed)));
    }





























//    public static ItemAttributeModifiers createAttributes(Tier tier, float damage, float speed) {
//        return ItemAttributeModifiers.builder()
//                .add(
//                        Attributes.ATTACK_DAMAGE,
//                        new AttributeModifier(
//                                BASE_ATTACK_DAMAGE_ID, (double)((float)damage + tier.getAttackDamageBonus()), AttributeModifier.Operation.ADD_VALUE
//                        ),
//                        EquipmentSlotGroup.MAINHAND
//                )
//                .add(
//                        Attributes.ATTACK_SPEED,
//                        new AttributeModifier(BASE_ATTACK_SPEED_ID, (double)speed, AttributeModifier.Operation.ADD_VALUE),
//                        EquipmentSlotGroup.MAINHAND
//                )
//                .build();
//    }
//    public static void adjustAttributeModifiers(ItemAttributeModifierEvent event) {
//        ItemStack stack = event.getItemStack();
//        if (stack.getItem() instanceof Claymore) {
//            if(stack.get(DataComponentRegistry.TRANSFORMED)){
//                event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 20 , AttributeModifier.Operation.ADD_VALUE));
//            }else{
//                event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 20 , AttributeModifier.Operation.ADD_VALUE));
//            }
//            //event.replaceModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, (double)damage, Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
//        }
//
//    }

//    public static ItemAttributeModifiers createAttributes(int pAttackDamage, float pAttackSpeed) {
//        return ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, (double)((float)pAttackDamage), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, (double)pAttackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();
//    }

    @Override
    public float getAttackDamageBonus(Entity entity, float v, DamageSource damageSource) {
        //return super.getAttackDamageBonus(entity, v, damageSource);
        return 2f;
    }

    public static ItemStack getClaymore(Player player) {
        ItemStack heldItem = player.getMainHandItem();
        if (!(heldItem.getItem() instanceof Claymore)) {
            heldItem = player.getOffhandItem();
            if (!(heldItem.getItem() instanceof Claymore)) {
                return ItemStack.EMPTY;
            }
        }
        return heldItem;
    }
    
}


