import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.http.HttpObjectAggregator
import io.netty.handler.codec.http.HttpServerCodec
import io.netty.handler.stream.ChunkedWriteHandler

class CChannelInitializer : ChannelInitializer<SocketChannel>() {
    override fun initChannel(socketChannel: SocketChannel){
//My implementation for HttpRequest
        socketChannel.pipeline()?.addLast(CChannelHandler())

//Default Implementation that Netty Provides for HttpRequest
//        socketChannel.pipeline()?.addLast(HttpServerCodec())
//        socketChannel.pipeline()?.addLast(HttpObjectAggregator(1048576))
//        socketChannel.pipeline()?.addLast(ChunkedWriteHandler())
//        socketChannel.pipeline()?.addLast(CSimpleChannelHandler())
    }
}