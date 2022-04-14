package mca.cobalt.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

import java.io.*;

public interface Message extends Serializable {

    static Message decode(PacketByteBuf b) {
        byte[] data = new byte[b.readableBytes()];
        b.readBytes(data);

        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return (Message)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("SneakyThrows", e);
        }
    }

    default void encode(PacketByteBuf b) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException("SneakyThrows", e);
        }

        b.writeBytes(baos.toByteArray());
    }

    default void receive() {
        // N/A
    }

    default void receive(ServerPlayerEntity player) {
        // N/A
    }
}