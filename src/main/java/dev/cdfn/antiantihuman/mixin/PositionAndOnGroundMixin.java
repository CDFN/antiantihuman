package dev.cdfn.antiantihuman.mixin;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.PositionAndOnGround;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PositionAndOnGround.class)
public abstract class PositionAndOnGroundMixin extends PlayerMoveC2SPacket {

    protected PositionAndOnGroundMixin(double x, double y, double z, float yaw, float pitch, boolean onGround, boolean changePosition, boolean changeLook) {
        super(x, y, z, yaw, pitch, onGround, changePosition, changeLook);
    }

    /**
     * @author CDFN
     * @reason LiveOverflow server hack
     */
    @Overwrite
    public void write(PacketByteBuf buf) {
        buf.writeDouble(Math.round(this.x * 100) / 100d);
        buf.writeDouble(this.y);
        buf.writeDouble(Math.round(this.z * 100) / 100d);
        buf.writeByte(this.onGround ? 1 : 0);
        System.out.println(Math.round(this.x * 100) / 100d);
    }
}
