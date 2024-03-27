package ru.yangel.auth_data.storage.storage

import android.content.Context


class AuthDataStorage(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("auth_data", Context.MODE_PRIVATE)

    private companion object {
        const val FIELD_IS_USER_LOGIN = "is_user_exists"
    }

    fun isUserLogin(): Boolean {
        return sharedPreferences.getBoolean(FIELD_IS_USER_LOGIN, false)
    }

    fun setUserLogin(isUserLogin: Boolean) {
        sharedPreferences.edit().putBoolean(FIELD_IS_USER_LOGIN, isUserLogin).apply()
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

}
