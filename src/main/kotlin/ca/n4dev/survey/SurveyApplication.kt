package ca.n4dev.survey

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder




@SpringBootApplication
class SurveyApplication

fun main(args: Array<String>) {
    //println(BCryptPasswordEncoder().encode("BobEstUnAdmin"))
    runApplication<SurveyApplication>(*args)
}

