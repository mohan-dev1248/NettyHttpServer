import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import java.net.InetSocketAddress
import java.nio.charset.Charset

fun main(){
    startServer()
}


fun startServer(){
    //ToDo - In Linux to use port between 1-1024, we need to run the program as root user. So using 8080 instead
    //ToDo - Need to find a solution for this either by port forwarding or some other way
    val httpPort = 8080
    val nioGroup = NioEventLoopGroup()
    try{
        val serverBootstrap = ServerBootstrap()
        serverBootstrap
                .group(nioGroup)
                .channel(NioServerSocketChannel::class.java)
                .localAddress(InetSocketAddress(httpPort))
                .childHandler(CChannelInitializer())
        val future: ChannelFuture = serverBootstrap.bind().sync()
        future.channel().closeFuture().sync()
    }finally {
        nioGroup.shutdownGracefully().sync()
    }
}