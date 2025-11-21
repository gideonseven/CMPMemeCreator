package com.gt.cmpmemecreator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform