package com.withintegrity.restclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.InputStream

@RestController
@SpringBootApplication
class RestClientApplication {
    @PostMapping("/octet", consumes = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun octet(input: InputStream) = println("""
        <OCTET>
        ${input.readAllBytes().toString(Charsets.UTF_8)}
        </OCTET>
    """.trimIndent())

    @PostMapping("/part", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun multipart(@RequestPart("file") file: MultipartFile, @RequestParam("json") json: PostPart) {
        println("""
<FILE>
${file.bytes.toString(Charsets.UTF_8)}
</FILE>
""".trimIndent())
        println("""
<JSON>
${json.get()}
</JSON>
""".trimIndent())
    }
}

fun main(args: Array<String>) {
    runApplication<RestClientApplication>(*args)
}

