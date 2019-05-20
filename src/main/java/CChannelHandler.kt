import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

class CChannelHandler : ChannelInboundHandlerAdapter() {
    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {

    }

    override fun channelReadComplete(ctx: ChannelHandlerContext?) {

    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {

    }
}