package com.sweeneyb

import com.fasterxml.jackson.annotation.JsonUnwrapped
import org.springframework.hateoas.ResourceSupport
import java.time.LocalDate
import java.util.*

class MenuSupport : ResourceSupport() {
    @JsonUnwrapped
    var menu: Menu = Menu()
}

class Menu {

    var id: Long? = null

    var date: LocalDate? = null

    var date2: Date? = null

    var title: String? = null

    var text: String? = null

    override fun toString() = "Menu($id, $date, $date2, $title, $text)"

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
        result = 31 * result + (date?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (text?.hashCode() ?: 0)
        return result
    }
}