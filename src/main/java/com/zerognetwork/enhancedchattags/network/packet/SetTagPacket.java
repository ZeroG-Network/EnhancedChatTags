package com.zerognetwork.enhancedchattags.network.packet;

import com.zerognetwork.enhancedchattags.ConfigHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SetTagPacket {
    private final String tag;

    public SetTagPacket(String tag) {
        this.tag = tag;
    }

    public static void encode(SetTagPacket packet, FriendlyByteBuf buf) {
        buf.writeUtf(packet.tag);
    }

    public static SetTagPacket decode(FriendlyByteBuf buf) {
        return new SetTagPacket(buf.readUtf());
    }

    public static void handle(SetTagPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            if (sender != null) {
                ConfigHandler.setTagFormat(packet.tag);
                sender.sendSystemMessage(Component.literal("Tag format updated to: " + packet.tag));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
