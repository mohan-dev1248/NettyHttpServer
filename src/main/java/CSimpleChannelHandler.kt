import io.netty.buffer.ByteBufUtil
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.*
import io.netty.util.CharsetUtil

class CSimpleChannelHandler: SimpleChannelInboundHandler<FullHttpRequest>() {
    override fun channelRead0(ctx: ChannelHandlerContext?, request: FullHttpRequest?) {
        //ToDo - need to change it so that the server will send a proper request and corresponding request method
        //println("I am catching here")
        if(!isValidHttpRequestMethod(request!!)){
            println("Invalid request")
            return
        }
        println(request.method())
        println(request.headers().toString())
        println(" ------ ")
        println(request.content()?.toString(CharsetUtil.UTF_8))


        ctx?.write(DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK))
        //ctx?.writeAndFlush(Unpooled.EMPTY_BUFFER)
    }

    override fun channelActive(ctx: ChannelHandlerContext?) {
        super.channelActive(ctx)
        println("Channel active triggered")
        //ctx?.channel()?.writeAndFlush(Unpooled.EMPTY_BUFFER)
    }
    override fun channelReadComplete(ctx: ChannelHandlerContext?) {
        println("Channel read complete")
        ctx?.writeAndFlush(Unpooled.EMPTY_BUFFER)?.addListener(ChannelFutureListener.CLOSE)
    }

    override fun channelInactive(ctx: ChannelHandlerContext?) {
        super.channelInactive(ctx)
        println("Channel inactive triggered")
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
        ctx?.close()
    }


    private fun isValidHttpRequestMethod(request: FullHttpRequest): Boolean{
        if(request.method() == HttpMethod.GET ||
                request.method() == HttpMethod.CONNECT ||
                request.method() == HttpMethod.DELETE ||
                request.method() == HttpMethod.HEAD ||
                request.method() == HttpMethod.OPTIONS ||
                request.method() == HttpMethod.PATCH ||
                request.method() == HttpMethod.POST ||
                request.method() == HttpMethod.TRACE)
            return true
        return false
    }
}