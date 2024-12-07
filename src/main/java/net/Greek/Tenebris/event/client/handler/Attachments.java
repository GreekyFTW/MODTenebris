package net.Greek.Tenebris.event.client.handler;

import net.minecraft.nbt.Tag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static net.Greek.Tenebris.Tenebris.MOD_ID;

public class Attachments {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MOD_ID);

    //public static final Supplier<AttachmentType<Tenebrae>> PLAYER_ATTACHMENTS;

    public Attachments() {
    }

    public static <T> Supplier<AttachmentType<T>> register(String name, Supplier<T> def, IAttachmentSerializer<Tag, T> provider) {
        return ATTACHMENTS.register(name, () -> {
            return AttachmentType.builder(def).serialize(provider).build();
        });
    }


    public static void register(IEventBus eventBus) {
        ATTACHMENTS.register(eventBus);
    }



    static {
        //PLAYER_ATTACHMENTS = register("player_mana", Tenebrae::new, new TenebraeProvider());

    }

}
