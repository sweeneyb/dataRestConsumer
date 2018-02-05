package com.sweeneyb

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.ParameterizedTypeReference
import org.springframework.hateoas.PagedResources
import org.springframework.hateoas.Resource
import org.springframework.hateoas.Resources
import org.springframework.hateoas.UriTemplate
import org.springframework.hateoas.hal.Jackson2HalModule
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import org.springframework.hateoas.core.DefaultRelProvider
import org.springframework.hateoas.hal.DefaultCurieProvider
import org.springframework.hateoas.hal.CurieProvider
import org.springframework.http.*


open class DataRestApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

//            SpringApplication.run(DataRestApplication::class.java, *args)
            DataRestApplication().doWork()
        }
    }

    fun doWork() {

        val mapper = ObjectMapper()
        mapper.registerModule(Jackson2HalModule())
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        val messageConverter = MappingJackson2HttpMessageConverter()

        messageConverter.supportedMediaTypes = MediaType.parseMediaTypes("application/hal+json")
        messageConverter.objectMapper = mapper
//
//        val curieProvider = DefaultCurieProvider("a", UriTemplate("http://localhost:8080/menus/{rel}"))
//        mapper.setHandlerInstantiator(Jackson2HalModule.HalHandlerInstantiator(DefaultRelProvider(), curieProvider, null))
//
        val restTemplate = RestTemplate(listOf(messageConverter))
        val uri = "http://localhost:8080/menus"
//        val restTemplate = RestTemplate()
//        val resources = PagedResources<Menu>(){}()
//            val quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote::class.java)
//        val menu = restTemplate.getForEntity("http://localhost:8080/menus", Menu::class.java)
//        val re = restTemplate.getForEntity("http://localhost:8080/menus", Foo::class.java)
//        val re = restTemplate.getForObject("http://localhost:8080/menus", Foo::class.java)
//        val re = restTemplate.getForEntity("http://localhost:8080/menus", Bar::class.java).body
        val re = restTemplate.exchange(uri, HttpMethod.GET, HttpEntity.EMPTY, Foo::class.java)
//        println(re.body)
//        println(re.body.content.forEach({println(it!!::class.java.name)}))
        println(re)
//        val content = re.content
//        println(content)

    }


//    class Foo<MenuSupport>() : PagedResources<MenuSupport >() {    }
    class Foo<Menu>() : PagedResources<Menu >() {    }

    class Bar<Menu>() : ParameterizedTypeReference<PagedResources<Menu>>() {

    }

}