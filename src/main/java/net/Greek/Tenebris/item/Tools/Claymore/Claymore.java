package net.Greek.Tenebris.item.Tools.Claymore;

//import net.Greek.Tenebris.util.ItemRenderer.ClaymoreItemRenderer;
//import net.Greek.Tenebris.util.ItemRenderer.ClaymoreModel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import static net.minecraft.world.item.SwordItem.createAttributes;

public class Claymore extends SwordItem {
    //int ClaymoreMode = 0;

    public Claymore(Tier tier, Properties properties ) {
        super(tier, properties.attributes(createAttributes(tier, 15, -2.5f)));

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
    } //ooga booga checks to see if item in hand is the claymore


//    public static void ItemProperties(){
//        ItemProperties.register(ModItems.CLAYMORE.get(), new ResourceLocation(Tenebris.MOD_ID, "trnsc"),
//                (ClampedItemPropertyFunction) (pStack, pLevel, pEntity, pSeed) -> {
//
////            if(IsTransformed){
////                Minecraft.getInstance().player.sendSystemMessage(Component.literal("properties say true"));
////            }else{
////                Minecraft.getInstance().player.sendSystemMessage(Component.literal("properties call you the nword"));
////            }
//
//
//            return pEntity != null && pEntity.isUsingItem() && pEntity.getUseItem() == pStack ? 1.0F : 0.0F;
//        });
//    };


    //    @Override
//    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
//        consumer.accept(new IClientItemExtensions() {
//
//            @Override
//            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
//                return new ClaymoreItemRenderer();
//            }
//        });
//    }//rendering bullshit i got no idea

}


