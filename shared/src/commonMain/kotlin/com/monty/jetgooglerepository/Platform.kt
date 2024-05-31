package com.monty.jetgooglerepository

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform