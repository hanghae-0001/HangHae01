package com.hanghae.commerce.common.extension

import com.hanghae.commerce.logging.LogConstants
import jakarta.servlet.http.HttpServletRequest

fun HttpServletRequest.log(): String {
    return "[${LogConstants.WEB}] [${this.method} ${this.requestURI}]"
}
