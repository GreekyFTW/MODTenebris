package net.Greek.Tenebris.event.client;


import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;

public class PlayerEvents {
    public static double MEAN_TPS = 20.0;

//    @SubscribeEvent
//    public static void playerOnTick(PlayerTickEvent.Pre e) {
//        Player player = e.getEntity();
//        if (!(player instanceof ServerPlayer) || player.getCommandSenderWorld().getGameTime() % 20 != 0)
//            return;
//
//        //ITenebrae tenebrae = Utils.getPlayerTenebrae(player);
//        ITenebrae tenebrae = Utils.getPlayerTenebrae(player);
//
//        //ITenebrae Localtenebrae = Utils.getPlayerTenebrae(Minecraft.getInstance().player);
//
//        //Localtenebrae.setStoredMana(tenebrae.getStoredMana());
//
//        Objects.requireNonNull(player).sendSystemMessage(Component.literal(""+tenebrae.getStoredMana()));
//        Objects.requireNonNull(player).sendSystemMessage(Component.literal(""+tenebrae.getManaRegenTicks()));
//        Objects.requireNonNull(player).sendSystemMessage(Component.literal(""+tenebrae.getMaxMana()));
//        Objects.requireNonNull(player).sendSystemMessage(Component.literal(""+tenebrae.isManaFull()));
//        Objects.requireNonNull(player).sendSystemMessage(Component.literal(""+player));
//        //PlayerManaManager.updateClientTenebrae((ServerPlayer) player,tenebrae);
//
//
//        //updateClientTenebrae((ServerPlayer)player, tenebrae);
//
//        boolean forceSync = player.level().getGameTime() % 60 == 0;
//        if(!(tenebrae.getStoredMana()>=tenebrae.getMaxMana())) {
//            if (tenebrae.getStoredMana() != tenebrae.getMaxMana() || forceSync) {
//                double regenPerSecond = (double) tenebrae.getManaRegenTicks() / Math.max(1, ((int) MEAN_TPS / 10));
//
//                tenebrae.setStoredMana((int) regenPerSecond + tenebrae.getStoredMana());
//
//            }
//        }
//    }

    private static final long[] UNLOADED = new long[] { 0 };
//    @SubscribeEvent
//    public static void onTick(PlayerTickEvent.Post e) {
//        var player = e.getEntity();
//        if (player.level().isClientSide)
//            return;
//        if (player.level().getGameTime() % 600 == 0 && player.getServer() != null) {
//            long[] tickTimes = player.getServer().getTickTime(player.level().dimension());
//            double meanTickTime = mean(tickTimes == null ? UNLOADED : tickTimes) * 1.0E-6D;
//            double meanTPS = Math.min(1000.0 / meanTickTime, 20);
//            PlayerEvents.MEAN_TPS = Math.max(1, meanTPS);
//        }
//    }

        @SubscribeEvent
    public static void ItemAttributes(ItemAttributeModifierEvent event) {

    }

}
