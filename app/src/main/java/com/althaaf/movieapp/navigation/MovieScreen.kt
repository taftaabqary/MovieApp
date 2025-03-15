package com.althaaf.movieapp.navigation

enum class MovieScreen {
    HOME,
    DETAIL;

    companion object {
        fun fromRoute(route: String?): MovieScreen {
            return when(route?.substringBefore("/")) {
                HOME.name -> HOME
                DETAIL.name -> DETAIL
                null -> HOME
                else -> throw IllegalArgumentException("Route $route is not found!")
            }
        }
    }
}