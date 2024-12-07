package net.Greek.Tenebris.util;

import net.Greek.Tenebris.item.ModItems;
import net.Greek.Tenebris.item.TransformingWeapon;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.fml.loading.FMLLoader;
import org.w3c.dom.Text;

import static net.Greek.Tenebris.init.DataComponentRegistry.TRANSFORMED;

public class WeaponUtil {

    //public static final Enchantment[] DAMAGE_ENCHANTS;
    public static final String PREV_TRICK_WEAPON = "trick_weapon_to_transform";
    public static final Item[] TRICK_WEAPONS = {
            ModItems.CLAYMORE.get()
    };


    public WeaponUtil() {
    }

    public static boolean isModLoaded(String modId) {
        return FMLLoader.getLoadingModList().getModFileById(modId) != null;
    }

//    public static Text getSwitchWeaponName(ItemStack stack, TransformingWeapon weapon) {
//
//        TransformingWeapon switchWeapon = (TransformingWeapon) TRICK_WEAPONS[weapon.getSwitchWeaponIndex()];
//        if (stack.has(DataComponents.CUSTOM_DATA) && Boolean.TRUE.equals(stack.getItem().components().get(TRANSFORMED.get()))) {
//            switchWeapon = TRICK_WEAPONS[stack.get()];
//        }
//        return switchWeapon.getName();
//    }

}
