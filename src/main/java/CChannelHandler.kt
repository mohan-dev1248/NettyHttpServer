import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufUtil
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.DefaultHttpResponse
import io.netty.handler.codec.http.FullHttpRequest
import io.netty.util.CharsetUtil

class CChannelHandler : ChannelInboundHandlerAdapter() {
    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        val buff = msg as ByteBuf
        val response = parseHttpRequest(buff) as String
        println(" ------- ")
        println(response)
        if (response != null) {
            buff.clear()
            ByteBufUtil.writeUtf8(buff, response)
            ByteBufUtil.writeUtf8(buff, "\n")
            ctx?.write(buff)
            //ctx?.writeAndFlush(Unpooled.EMPTY_BUFFER)?.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE)
        }
       // ctx?.write(buff)
    }

    override fun channelReadComplete(ctx: ChannelHandlerContext?) {
//        println("Read complete")
        ctx?.writeAndFlush(Unpooled.EMPTY_BUFFER)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
        ctx?.close()
    }
}