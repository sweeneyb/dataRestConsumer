package com.sweeneyb

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonUnwrapped
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.config.EnableHypermediaSupport
import java.time.LocalDate
import java.util.*
import javax.annotation.Resource

class MenuSupport : ResourceSupport() {
    @JsonUnwrapped
    var menu : Menu = Menu()
}

//@EnableHypermediaSupport(type = [EnableHypermediaSupport.HypermediaType.HAL])
//@Resource
class Menu {

    var id: Long? = null

//    var restaurant: Restaurant? = null

//    var date: LocalDate? = null
    @JsonProperty("date2")
      var date: Date? = null
//    var date2: Date? = null

    var title: String? = null

    var text: String? = null

//        override fun toString() = "Menu($id, ${restaurant?.id}, $date, $date2, $title, $text)"
//    override fun toString() = "Menu($id, $date, $date2, $title, $text)"
    override fun toString() = "Menu($id,  $date, $title, $text)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Menu

        if (id != other.id) return false
        if (date != other.date) return false
        if (title != other.title) return false
        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
//        result = 31 * result + (restaurant?.id?.hashCode() ?: 0)
        result = 31 * result + (date?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (text?.hashCode() ?: 0)
        return result
    }
}