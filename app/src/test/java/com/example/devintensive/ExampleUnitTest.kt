package com.example.devintensive

import com.example.devintensive.extensions.TimeUnits
import com.example.devintensive.extensions.add
import com.example.devintensive.extensions.humanizeDiff
import com.example.devintensive.models.*
import com.example.devintensive.utils.Utils
import org.junit.Test

import org.junit.Assert.*
import java.lang.IllegalArgumentException
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user = User("2", "John", "Cena")
        assertEquals("2", user.id)
        assertEquals("John", user.firstName)
        assertEquals("Cena", user.lastName)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_factory_null_lastname() {
        val user = User.makeUser("John")
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_factory_null_fullname() {
        val user = User.makeUser("")
    }

    @Test
    fun test_parseFullName_null() {
        val parsedFullName = Utils.parseFullName(null)
        val parsedFullName2 = Utils.parseFullName("")
        val parsedFullName3 = Utils.parseFullName(" ")
        val parsedFullName4 = Utils.parseFullName("John")

        assertEquals("(null, null)", parsedFullName.toString())
        assertEquals("(null, null)", parsedFullName2.toString())
        assertEquals("(null, null)", parsedFullName3.toString())
        assertEquals("(John, null)", parsedFullName4.toString())
    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Ekaterina Chub")
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "Any text message", type = "text")
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "Any image message", type = "image")

        assertEquals("id:0 Ekaterina отправил сообщение \"Any text message\" только что", txtMessage.formatMessage())
        assertEquals("id:1 Ekaterina отправил изображение \"Any image message\" только что", imgMessage.formatMessage())

    }

    @Test
    fun test_data_add() {
        val date1 = Date().add(2, TimeUnits.SECOND)
        val date2 = Date().add(-4, TimeUnits.DAY)
        println(date1)
        println(date2)
//        TODO() add a specific date setting
    }

    @Test
    fun test_humanizeDiff() {
        assertEquals("2 часа назад", Date().add(-2, TimeUnits.HOUR).humanizeDiff())
        assertEquals("5 дней назад",Date().add(-5, TimeUnits.DAY).humanizeDiff())
        assertEquals("через 2 минуты",Date().add(2, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("через 7 дней", Date().add(7, TimeUnits.DAY).humanizeDiff())
        assertEquals("более года назад", Date().add(-400, TimeUnits.DAY).humanizeDiff())
        assertEquals("более чем через год", Date().add(400, TimeUnits.DAY).humanizeDiff())

    }

}
