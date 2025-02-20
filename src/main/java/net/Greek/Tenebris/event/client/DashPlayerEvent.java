package net.Greek.Tenebris.event.client;

import static com.google.common.math.DoubleMath.mean;

public class DashPlayerEvent {

//    public static int CooldDown = 10;
//
//    @SubscribeEvent
//    public static void OnPlayerTick(PlayerTickEvent.Pre e) {
//        Player player = e.getEntity();
//        if (!(player instanceof ServerPlayer) || player.getCommandSenderWorld().getGameTime() % 20 != 0)
//            return;
//        boolean forceSync = player.level().getGameTime() % 60 == 0;
//
//        Objects.requireNonNull(player).sendSystemMessage(Component.literal(""+CooldDown));
//
//    }
//
//    private static final long[] UNLOADED = new long[] { 0 };
//    @SubscribeEvent
//    public static void onCooldownTick(PlayerTickEvent.Post e) {
//        var player = e.getEntity();
//        if (player.level().isClientSide)
//            return;
//        if(CooldDown==0 && ClientGameplayPlayerData.getDashCount()<=3){
//
//           ClientGameplayPlayerData.setPlayerGameplaySpellData(ClientGameplayPlayerData.getDashCount()+1);
//           CooldDown=10;
//            Objects.requireNonNull(player).sendSystemMessage(Component.literal("done"+CooldDown));
//        }
//        if (player.level().getGameTime() % 60 == 0 && player.getServer() != null && (CooldDown>0)) {
//            long[] tickTimes = player.getServer().getTickTime(player.level().dimension());
//            double meanTickTime = mean(tickTimes == null ? UNLOADED : tickTimes) * 1.0E-6D;
//            double meanTPS = Math.min(1000.0 / meanTickTime, 20);
//            PlayerEvents.MEAN_TPS = Math.max(1, meanTPS);
//            CooldDown = CooldDown-1;
//        }
//    }

}
