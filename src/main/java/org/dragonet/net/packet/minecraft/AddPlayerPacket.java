/*
 * GNU LESSER GENERAL PUBLIC LICENSE
 *                       Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 *
 * You can view LICENCE file for details. 
 *
 * @author The Dragonet Team
 */
package org.dragonet.net.packet.minecraft;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.dragonet.entity.metadata.EntityMetaData;
import org.dragonet.utilities.DefaultSkin;
import org.dragonet.utilities.io.PEBinaryWriter;

public class AddPlayerPacket extends PEPacket {

    public long clientID;
    public String username;
    public long eid;
    public float x;
    public float y;
    public float z;
    public float speedX;
    public float speedY;
    public float speedZ;
    public float yaw;
    public float pitch;
    public short item;
    public short meta;
    public boolean slim;
    public byte[] skin;
    public EntityMetaData metadata;

    @Override
    public int pid() {
        return PEPacketIDs.ADD_PLAYER_PACKET;
    }

    @Override
    public void encode() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PEBinaryWriter writer = new PEBinaryWriter(bos);
        try {
            writer.writeByte((byte) (this.pid() & 0xFF));
            writer.writeLong(this.clientID);
            writer.writeString(this.username);
            writer.writeLong(this.eid);
            writer.writeFloat(this.x);
            writer.writeFloat(this.y);
            writer.writeFloat(this.z);
            writer.writeFloat(this.speedX);
            writer.writeFloat(this.speedY);
            writer.writeFloat(this.speedZ);
            writer.writeFloat(this.yaw);
            writer.writeFloat(this.yaw); //Head rotation
            writer.writeFloat(this.pitch);
            writer.writeShort(this.item);
            writer.writeShort(this.meta);
            writer.writeByte(this.slim ? (byte) 1 : (byte) 0);
            writer.writeShort((short) (DefaultSkin.getDefaultSkin().length & 0xFFFF)); //DEFAULT SKIN
            writer.write(DefaultSkin.getDefaultSkin());
            writer.write(this.metadata.encode());
            this.setData(bos.toByteArray());
        } catch (IOException e) {
        }
    }

    @Override
    public void decode() {
    }
}
