package com.example.viewmodelnavigation.navigator

import com.example.viewmodelnavigation.MainActivity

typealias MainActivityAction = (MainActivity) -> Unit

class MainActivityActions {

    var activity: MainActivity? = null
        set(value) {
            field = value
            if (value != null) {
                actions.forEach { action ->
                    action(value)
                    actions.clear()
                }
            }
        }

    private val actions = mutableListOf<MainActivityAction>()

    operator fun invoke(action: MainActivityAction) {
        val activity = this.activity
        if (activity == null) {
            actions.add(action)
        } else {
            action(activity)
        }
    }

    fun clear() {
        actions.clear()
    }
}