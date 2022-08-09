package com.example.viewmodelnavigation.view.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.viewmodelnavigation.Event
import com.example.viewmodelnavigation.R
import com.example.viewmodelnavigation.navigator.Navigator
import com.example.viewmodelnavigation.view.base.BaseViewModel

class EditViewModel(
    private val navigator: Navigator,
    private val screen: EditFragment.Screen
) : BaseViewModel() {

    private val _initialMessageEvent: MutableLiveData<Event<String>> =
        MutableLiveData<Event<String>>()
    val initialMessageEvent: LiveData<Event<String>> = _initialMessageEvent

    init {
        _initialMessageEvent.value = Event(screen.initialValue)
    }

    fun onSavePressed(message: String) {
        if (message.isBlank()) {
            navigator.toast(R.string.message_can_not_be_empty)
            return
        }
        navigator.goBack(message)
    }

    fun onCancelPressed() {
        navigator.goBack()
    }
}