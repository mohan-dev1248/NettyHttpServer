import io.netty.buffer.ByteBuf
import io.netty.util.CharsetUtil

fun getRequestComponents(requestString: String): Pair<String, Pair<String, String>> {
    val splitResponse = requestString.split("\n")
    val startLine: String = splitResponse[0].trimEnd()
    var headers = ""
    var index = 0
    for (i in 1 until splitResponse.size) {
        if (splitResponse[i].length == 1 && splitResponse[i][0].toInt() == 13) {
            index = i
            break
        }
        headers += splitResponse[i] + "\n"
    }
    headers = headers.trimEnd()
    var body = ""
    for (i in index + 1 until splitResponse.size) {
        body += splitResponse[i] + "\n"
    }
    body = body.trimEnd()

    println(startLine)
    println(" ------- ")
    println(headers)
    println(" ------- ")
    println(body)
    return Pair(startLine, Pair(headers, body))
}

fun parseHttpRequest(request: ByteBuf): Any? {
    val requestComponents = getRequestComponents(request.toString(CharsetUtil.UTF_8))

    if (requestComponents.first.startsWith("GET")) {
        var response = ""
        val startLineComponents = requestComponents.first.split(" ")
        if (startLineComponents[2].endsWith("1.1")) {
            response += "HTTP/1.1" + " "
        } else if (startLineComponents[2].endsWith("1.0")) {
            response += "HTTP/1.0" + " "
        }

        response += "200 OK\n"
        response += "Content-Type: text\n"
        response += "Content-Length: 10\n"
        response += "\n"
        response += "HelloWorld"

        return response
    }

    if (requestComponents.first.startsWith("HEAD")) {

    }

    if (requestComponents.first.startsWith("POST")) {

    }

    if (requestComponents.first.startsWith("PUT")) {

    }

    if (requestComponents.first.startsWith("DELETE")) {

    }

    if (requestComponents.first.startsWith("CONNECT")) {

    }

    if (requestComponents.first.startsWith("OPTIONS")) {

    }

    if (requestComponents.first.startsWith("TRACE")) {

    }

    return null
}