package net.Greek.Tenebris.item;

import net.minecraft.server.dedicated.Settings;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;


public class TransformingWeapon extends SwordItem  {

    private static final int DAMAGE = 15;

    private final int switchWeaponIndex;
    private final int ownWeaponIndex;



    public TransformingWeapon(Tier toolMaterial, Properties properties, float attackSpeed, Settings settings, int switchWeaponIndex, int ownWeaponIndex) {
        super(toolMaterial, properties.attributes(createAttributes(toolMaterial, DAMAGE, attackSpeed)));
        this.switchWeaponIndex = switchWeaponIndex;
        this.ownWeaponIndex = ownWeaponIndex;

    }

    public int getSwitchWeaponIndex() {
        return this.switchWeaponIndex;
    }

    public int getOwnWeaponIndex() {
        return this.ownWeaponIndex;
    }


}
