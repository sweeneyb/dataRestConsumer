package com.sweeneyb

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.hateoas.PagedResources
import org.springframework.hateoas.hal.Jackson2HalModule
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

open class DataRestApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            DataRestApplication().doWork()
        }
    }

    fun doWork() {

        val mapper = ObjectMapper()
        mapper.registerModule(Jackson2HalModule())
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        val messageConverter = MappingJackson2HttpMessageConverter()
        messageConverter.objectMapper = mapper

        val restTemplate = RestTemplate(listOf(messageConverter))
        val uri = "http://localhost:8080/menus"
        val re = restTemplate.getForEntity("http://localhost:8080/menus", Foo::class.java)
        println(re)
        re.body.content.forEach({ println(it.menu.title) })
    }

    class Foo<Menu> : PagedResources<MenuSupport>()
}