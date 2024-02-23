package com.withintegrity.restclient

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class PostConverter(val om: ObjectMapper) : Converter<String, PostPart> {
    override fun convert(source: String): PostPart? {
        return object : PostPart {
            override fun get(): Post {
                return om.readValue(source, Post::class.java)
            }
        }
    }
}
