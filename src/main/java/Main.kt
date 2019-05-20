import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import java.net.InetSocketAddress

fun main(){
    startServer()
}


fun startServer(){
    val port = 9999
    val nioGroup = NioEventLoopGroup()
    try{
        val serverBootstrap = ServerBootstrap()
        serverBootstrap
                .group(nioGroup)
                .channel(NioServerSocketChannel::class.java)
                .localAddress(InetSocketAddress(port))
                .childHandler(CChannelInitializer())
        val future: ChannelFuture = serverBootstrap.bind().sync()
        future.channel().closeFuture().sync()
    }finally {
        nioGroup.shutdownGracefully().sync()
    }

}