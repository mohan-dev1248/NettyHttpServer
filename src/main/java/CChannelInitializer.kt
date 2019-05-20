import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel

class CChannelInitializer : ChannelInitializer<SocketChannel>() {
    override fun initChannel(socketChannel: SocketChannel){
        socketChannel.pipeline()?.addLast(CChannelHandler())
    }
}