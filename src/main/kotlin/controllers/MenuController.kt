package controllers

import mu.KotlinLogging
import views.MainScreen


class MenuController {
    private val mainScreen = MainScreen()
    private val logger = KotlinLogging.logger {}


    init {
        logger.info {"Launching Grattan Swimming Pool APP Console"}
        println(" Swimming Pool App Version 3.0")
    }

    fun menu() : Int {
        return mainScreen.menu()
    }
}