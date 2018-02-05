package com.sweeneyb

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
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
        /*
        This next line solves the following exception:
        Can not construct instance of java.time.LocalDate: no String-argument constructor/factory
        method to deserialize from String value ('2018-02-04')
         */
        mapper.registerModule(JavaTimeModule())
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        val messageConverter = MappingJackson2HttpMessageConverter()
        messageConverter.objectMapper = mapper

        val restTemplate = RestTemplate(listOf(messageConverter))
        var uri: String? = "http://localhost:8080/menus"
        do {
            var re = restTemplate.getForEntity(uri, Foo::class.java)
            re.body.content.forEach({ println(it.menu.title) })
            uri = re.body?.nextLink?.href

        }
        while( uri != null)
    }

    class Foo<Menu> : PagedResources<MenuSupport>()
}