package net.Greek.Tenebris.dataAttachments;

import net.Greek.Tenebris.GameplayClasses.GameplayClassData;
import net.Greek.Tenebris.GameplayClasses.GameplayPlayerData;
import net.Greek.Tenebris.api.TenebraeData;
import net.Greek.Tenebris.capabilities.PlayerClassProvider;
import net.Greek.Tenebris.capabilities.PlayerDataProvider;
import net.Greek.Tenebris.capabilities.PlayerTenebraeProvider;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import static net.Greek.Tenebris.Tenebris.MOD_ID;

public class DataAttachmentRegistry {

    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, MOD_ID);

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<TenebraeData>> TENEBRAE_DATA = ATTACHMENT_TYPES.register("tenebrae_data",
            () -> AttachmentType.builder((holder) -> holder instanceof ServerPlayer serverPlayer ? new TenebraeData(serverPlayer) : new TenebraeData()).serialize(new PlayerTenebraeProvider()).build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<GameplayClassData>> GAMEPLAY_CLASS_DATA = ATTACHMENT_TYPES.register("gameplay_class_data",
            () -> AttachmentType.builder((holder) -> holder instanceof ServerPlayer serverPlayer ? new GameplayClassData(serverPlayer) : new GameplayClassData()).serialize(new PlayerClassProvider()).build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<GameplayPlayerData>> GAMEPLAY_PLAYER_DATA = ATTACHMENT_TYPES.register("gameplay_player_data",
            () -> AttachmentType.builder((holder) -> holder instanceof ServerPlayer serverPlayer ? new GameplayPlayerData(serverPlayer) : new GameplayPlayerData()).serialize(new PlayerDataProvider()).build());


}
