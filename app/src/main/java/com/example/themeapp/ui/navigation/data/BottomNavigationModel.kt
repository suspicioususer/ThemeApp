package com.example.themeapp.ui.navigation.data

import com.example.themeapp.R

open class BottomNavigationModel(var titleId: Int, var iconId: Int, var navigationPath: String) {
    object A: BottomNavigationModel(R.string.screen_A, R.drawable.ic_apps, "screenA")
    object B: BottomNavigationModel(R.string.screen_B, R.drawable.ic_apps, "screenB")
    object C: BottomNavigationModel(R.string.screen_C, R.drawable.ic_apps, "screenC")
}
