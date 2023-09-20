package com.hanghae.health.common

import com.hanghae.health.product.feature.api.RegisterProductApi

class Scenario {

    companion object {
        fun registerProduct(): RegisterProductApi {
            return RegisterProductApi()
        }
    }

}
