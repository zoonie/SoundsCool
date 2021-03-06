package com.dynious.soundscool.network.packet.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.DimensionManager;

import com.dynious.soundscool.SoundsCool;
import com.dynious.soundscool.handler.SoundHandler;
import com.dynious.soundscool.network.packet.server.UploadedSoundsPacket;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class GetUploadedSoundsPacket implements IMessage
{
    int entityID;
    int worldID;

    public GetUploadedSoundsPacket()
    {
    }

    public GetUploadedSoundsPacket(EntityPlayer player)
    {
        this.entityID = player.getEntityId();
        this.worldID = player.getEntityWorld().provider.dimensionId;
    }

    @Override
    public void fromBytes(ByteBuf bytes)
    {
        entityID = bytes.readInt();
        worldID = bytes.readInt();

        Entity entity = DimensionManager.getWorld(worldID).getEntityByID(entityID);
        if (entity != null && entity instanceof EntityPlayer)
        {
        	SoundHandler.findSounds();
            SoundsCool.network.sendTo(new UploadedSoundsPacket(), (EntityPlayerMP) entity);
        }
    }

    @Override
    public void toBytes(ByteBuf bytes)
    {
        bytes.writeInt(entityID);
        bytes.writeInt(worldID);
    }
    
    public static class Handler implements IMessageHandler<GetUploadedSoundsPacket, IMessage> {
        @Override
        public IMessage onMessage(GetUploadedSoundsPacket message, MessageContext ctx) {
            return null;
        }
    }
}
