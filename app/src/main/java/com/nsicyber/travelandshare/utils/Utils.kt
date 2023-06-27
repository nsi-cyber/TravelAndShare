package com.nsicyber.travelandshare.utils

import com.google.common.reflect.TypeToken
import com.google.gson.Gson

inline fun <reified T> parse(src: Any?):T? {
    src?.let {
        return Gson().
        fromJson<T>(
            Gson().toJson(src), object: TypeToken<T>() {}.type)
    }
    return null
}