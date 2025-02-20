package net.Greek.Tenebris.network;

import net.Greek.Tenebris.GameplayClasses.GameplayPlayerData;
import net.Greek.Tenebris.common.lib.Version;

import net.Greek.Tenebris.item.ModItems;
import net.Greek.Tenebris.item.Tools.Claymore.Claymore;

import net.Greek.Tenebris.network.packets.BasePacketHandler;
import net.Greek.Tenebris.network.packets.SyncGameplayPlayerDataPacket;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterConfigurationTasksEvent;

import java.util.Objects;

import static net.Greek.Tenebris.Tenebris.rl;


import static net.Greek.Tenebris.init.DataComponentRegistry.*;
import static net.minecraft.world.item.Item.BASE_ATTACK_DAMAGE_ID;
import static net.minecraft.world.item.Item.BASE_ATTACK_SPEED_ID;


//import static net.Greek.Tenebris.event.client.EventKeyInput.IsTransformed;

public class PacketHandler extends BasePacketHandler {

    public static boolean IsTransformed = false;

    //client to server
    private SimplePacketPayLoad AskModeChange;
    private SimplePacketPayLoad GameplayDataUpdateC2S;
    private SimplePacketPayLoad ManaReset;
    private SimplePacketPayLoad ManaBarUpdateC2S;
    private SimplePacketPayLoad ManaBarUpdateS2C;
    private SimplePacketPayLoad AbilityCast;
    //server to client
    private SimplePacketPayLoad AnswerModeChange;

    public PacketHandler(IEventBus modEventBus, Version version) {
        super(modEventBus, version);
        modEventBus.addListener(RegisterConfigurationTasksEvent.class, event -> {
            ServerConfigurationPacketListener listener = event.getListener();
        });
    }

    public static void sendTo(ServerPlayer player, CustomPacketPayload msg) {
        if (!(player instanceof FakePlayer)) {
            PacketDistributor.sendToPlayer(player, msg, new CustomPacketPayload[0]);
        }

    }

    public void GameplayDataUpdateC2S(){
        PacketUtils.sendToServer((GameplayDataUpdateC2S));
    }

    public void ManaReset(){
        PacketUtils.sendToServer((ManaReset));
    }

    public void ManaBarUpdate(){
        PacketUtils.sendToServer((ManaBarUpdateC2S));
    }

    public void AnswerManaUpdate(ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, ManaBarUpdateS2C);
    }

    public void AskModeChange() {
        PacketUtils.sendToServer(AskModeChange);
    }

    public void AbilityCast() {
        PacketUtils.sendToServer(AbilityCast);
    }

    @Override
    protected void registerClientToServer(PacketRegistrar registrar) {

        AskModeChange = registrar.playInstanced(rl("ask_mode_change"), (ignored, context) -> {

            if (context.player() instanceof ServerPlayer player) {

                int slot = player.getInventory().selected;
                ItemStack stack = player.getMainHandItem();

                //ITenebrae tenebrae = Utils.getPlayerTenebrae(player);

                player.getCooldowns().addCooldown(Claymore.getClaymore(player).getItem(), 20);

                //Claymore.createAttributes(Tiers.NETHERITE, 12,stack.get(ATTACK_SPEED_MODIFIER.value()));

                ItemStack newWeapon = new ItemStack(ModItems.CLAYMORE.get());

                ItemEnchantments enchants = stack.get(DataComponents.ENCHANTMENTS);

                EnchantmentHelper.setEnchantments(newWeapon, enchants);

                newWeapon.setDamageValue(stack.getDamageValue());

                boolean temp = stack.getOrDefault(TRANSFORMED, false);


                    if (!temp) {
                        newWeapon.set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()

                                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 9, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)

                                .build());

                    } else {
                        newWeapon.set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()

                                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -1.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)

                                .build());
                    }

                    newWeapon.set(TRANSFORMED, !temp);

                    player.getInventory().getItem(slot).set(TRANSFORMED, true);

                    player.getInventory().removeItem(Claymore.getClaymore(player));
                    player.getInventory().add(slot, newWeapon);

                    Objects.requireNonNull(player).sendSystemMessage(Component.literal("done " + player.getMainHandItem().get(TRANSFORMED) + " " + Claymore.getClaymore(player).get(TRANSFORMED)));
                }

        });

        GameplayDataUpdateC2S = registrar.playInstanced((rl("gameplay_data_update")),(ignored, context)->{
            if (context.player() instanceof ServerPlayer serverPlayer) {
                GameplayPlayerData gameplayPlayerData = GameplayPlayerData.getGameplayPlayerData(serverPlayer);
                gameplayPlayerData.setGameplayDashCount(gameplayPlayerData.getDashCount()-1);
                //gameplayPlayerData.setGameplayDashCountCooldown(gameplayPlayerData.getDashCount()-1,1);
                //gameplayPlayerData.setGameplayDashCount(gameplayPlayerData.getDashCount()-1);
                PacketDistributor.sendToPlayer(serverPlayer, new SyncGameplayPlayerDataPacket(gameplayPlayerData));
            }
        });

        AbilityCast = registrar.playInstanced((rl("ability_cast")),(ignored, context)->{
            if (context.player() instanceof ServerPlayer player) {
//                PacketDistributor.sendToPlayer(serverPlayer, new FirstAbilityCastPacket());

//                PaintBall arrow = new PaintBallEntity(,player.level());
//                arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 5 * 3.0F, 0.0F);
//
//
//                player.level().addFreshEntity(arrow);



            }
        });
    }

    @Override
    protected void registerServerToClient(PacketRegistrar registrar) {
        ManaBarUpdateS2C = registrar.playInstanced((rl("mana_bar_update_response")),(ignored, context)->{
//            if (context.player() instanceof ServerPlayer player) {
//                ITenebrae tenebrae = Utils.getPlayerTenebrae(player);
//
//                ITenebrae localtenebrae = Utils.getPlayerTenebrae(Minecraft.getInstance().player);
//
//                localtenebrae.setStoredMana(tenebrae.getStoredMana());
//
//                tenebrae.getStoredMana();
//
//            }
        });
    }
}
