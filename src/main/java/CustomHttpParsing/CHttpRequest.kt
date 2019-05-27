package CustomHttpParsing

import io.netty.buffer.ByteBuf
import io.netty.handler.codec.http.HttpMethod
import io.netty.handler.codec.http.HttpVersion

class CHttpRequest(
        val method: HttpMethod,
        val requestTarget: String? = null,
        val httpVersion: HttpVersion,
        val headers : Map<String, Any>,
        val content: ByteBuf
) {

}