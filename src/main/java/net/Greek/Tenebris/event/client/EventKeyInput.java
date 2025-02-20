package net.Greek.Tenebris.event.client;


import net.Greek.Tenebris.GUI.ClassSelectGui;
import net.Greek.Tenebris.GUI.TenebrisDisplayScreen;
import net.Greek.Tenebris.GameplayClasses.Abilities.Malerin.Misc.PaintBall;
import net.Greek.Tenebris.GameplayClasses.Abilities.Malerin.PaintBallAbility;
import net.Greek.Tenebris.GameplayClasses.GameplayClassData;
import net.Greek.Tenebris.GameplayClasses.GameplayPlayerData;
import net.Greek.Tenebris.Tenebris;
import net.Greek.Tenebris.item.Tools.Claymore.Claymore;
import net.Greek.Tenebris.network.PacketUtils;
import net.Greek.Tenebris.network.packets.PacketClaymoreModeChange;
import net.Greek.Tenebris.network.packets.SyncGameplayPlayerDataPacket;
import net.Greek.Tenebris.sound.ModSounds;
import net.Greek.Tenebris.util.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.checkerframework.checker.units.qual.K;

import java.util.Objects;

import static net.Greek.Tenebris.init.DataComponentRegistry.TRANSFORMED;


//Minecraft.getInstance().player.sendSystemMessage(Component.literal("fuck you"));
@EventBusSubscriber(modid = Tenebris.MOD_ID, value = Dist.CLIENT)
public class EventKeyInput {

    private static boolean DashKeyWasDown = false;
    private static boolean TransformKeyWasDown = false;
    private static boolean SpellOneKeyWasDown = false;
    private static boolean SpellTwoKeyWasDown = false;
    private static boolean SpellThreeKeyWasDown = false;
    private static boolean SpellFourKeyWasDown = false;
    private static int Cooldown =0;

    @SubscribeEvent
    public static void handleEventInput(ClientTickEvent.Post event) {

        if (KeyBindings.DASH.consumeClick()){
            DashKeyWasDown =true;
        }else if(!KeyBindings.DASH.isDown()){
            DashKeyWasDown =false;
        }

        if (KeyBindings.TransformClaymore.consumeClick()){
            TransformKeyWasDown =true;
        }else if(!KeyBindings.TransformClaymore.isDown()){
            TransformKeyWasDown =false;
        }

        if (KeyBindings.Spell1.consumeClick()){
            SpellOneKeyWasDown =true;
        }else if(!KeyBindings.Spell1.isDown()){
            SpellOneKeyWasDown =false;
        }
        if (KeyBindings.Spell2.consumeClick()){
            SpellTwoKeyWasDown =true;
        }else if(!KeyBindings.Spell2.isDown()){
            SpellTwoKeyWasDown =false;
        }
        if (KeyBindings.Spell3.consumeClick()){
            SpellThreeKeyWasDown =true;
        }else if(!KeyBindings.Spell3.isDown()){
            SpellThreeKeyWasDown =false;
        }
        if (KeyBindings.Spell4.consumeClick()){
            SpellFourKeyWasDown =true;
        }else if(!KeyBindings.Spell4.isDown()){
            SpellFourKeyWasDown =false;
        }

    }

    @SubscribeEvent
    public static void handleEventInput(ClientTickEvent.Pre event){

            Minecraft mc = Minecraft.getInstance();
            boolean CastSpellKeyIsDown = KeyBindings.CastSpell.isDown();
            boolean SpellOneKeyIsDown = KeyBindings.Spell1.isDown();
            boolean SpellTwoKeyIsDown = KeyBindings.Spell2.isDown();
            boolean SpellThreeKeyIsDown = KeyBindings.Spell3.isDown();
            boolean SpellFourKeyIsDown = KeyBindings.Spell4.isDown();

            if (mc.player == null)
                return;

        //Objects.requireNonNull(mc.player).sendSystemMessage(Component.literal(ClientGameplayClassData.getPlayerGameplayClassData()));

            if (KeyBindings.TransformClaymore.consumeClick() && !Claymore.getClaymore(mc.player).isEmpty() && !mc.player.getCooldowns().isOnCooldown(Claymore.getClaymore(mc.player).getItem()) && !TransformKeyWasDown) {
                if (Claymore.getClaymore(mc.player).getItem() instanceof Claymore) {
                    Tenebris.packetHandler().AskModeChange();
                    TransformKeyWasDown =true;
                }
            }

            if (CastSpellKeyIsDown && !DashKeyWasDown){
                if(mc.screen == null){
                    Minecraft.getInstance().setScreen(new ClassSelectGui(Component.empty()));
                }
            }

            if (KeyBindings.DASH.consumeClick() && ClientGameplayPlayerData.getDashCount()>0 && !DashKeyWasDown){

                Vec3 playerLook = mc.player.getViewVector(2);
                //Vec3 dashVec = new Vec3(playerLook.x(),mc.player.getDeltaMovement().y(), playerLook.z());
                Vec3 dashVec = new Vec3(playerLook.x(), playerLook.y, playerLook.z());
                mc.player.setDeltaMovement(dashVec);
                Tenebris.packetHandler().GameplayDataUpdateC2S();
                Objects.requireNonNull(mc.player).sendSystemMessage(Component.literal("done" + DashKeyWasDown + ClientGameplayPlayerData.getDashType()));
                DashKeyWasDown =true;
//                Cooldown=20;
            }

            if(SpellOneKeyIsDown && !SpellOneKeyWasDown){
                switch (ClientGameplayClassData.getPlayerGameplayClassData().toString()){
                        case "No_Class":
                            Objects.requireNonNull(mc.player).sendSystemMessage(Component.literal("fck off"+ClientGameplayClassData.getPlayerGameplayClassData()));

                        break;

                        case "Malerin":
                            Tenebris.packetHandler().AbilityCast();


                            Objects.requireNonNull(mc.player).sendSystemMessage(Component.literal("Malerin"+ClientGameplayClassData.getPlayerGameplayClassData()));
                        break;
                }
            }

    }

    @SubscribeEvent
    public static void DashCooldown(ClientTickEvent.Post event){

    }

}

