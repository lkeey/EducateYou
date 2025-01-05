package dev.lkeeeey.edu.you.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Auth : Route
    //    auth routes
    @Serializable
    data object Splash : Route

    @Serializable
    data object Login : Route

    @Serializable
    data object Register : Route
    //    end auth routes


    @Serializable
    data object Main : Route
    //    main routes
    @Serializable
    data object Calendar : Route
    // end main routes

}