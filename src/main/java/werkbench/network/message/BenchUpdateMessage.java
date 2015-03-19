package werkbench.network.message;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import werkbench.bench.BenchTileEntity;

public class BenchUpdateMessage implements IMessage, IMessageHandler<BenchUpdateMessage, IMessage>
{

    private int xCoord, yCoord, zCoord;

    public BenchUpdateMessage(BenchTileEntity bench)
    {
        this.xCoord = bench.xCoord;
        this.yCoord = bench.yCoord;
        this.zCoord = bench.zCoord;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        xCoord = buf.readInt();
        yCoord = buf.readInt();
        zCoord = buf.readInt();
    }

    @Override
    public IMessage onMessage(BenchUpdateMessage message, MessageContext ctx)
    {
        TileEntity tile = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.xCoord, message.yCoord, message.zCoord);
        if (tile != null && tile instanceof BenchTileEntity)
        {
            BenchTileEntity benchTileEntity = (BenchTileEntity) tile;
            // @TODO - sync the bench craftmatrix result here
        }
        return null;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(xCoord);
        buf.writeInt(yCoord);
        buf.writeInt(zCoord);
    }

}