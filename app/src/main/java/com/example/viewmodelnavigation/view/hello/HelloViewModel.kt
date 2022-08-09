package com.example.viewmodelnavigation.view.hello

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.viewmodelnavigation.R
import com.example.viewmodelnavigation.navigator.Navigator
import com.example.viewmodelnavigation.view.base.BaseViewModel
import com.example.viewmodelnavigation.view.edit.EditFragment

class HelloViewModel(
    private val navigator: Navigator,
    private val screen: HelloFragment.Screen
): BaseViewModel() {

    private val _currentMessageLiveData = MutableLiveData<String>()
    val currentMessageLiveData: LiveData<String> = _currentMessageLiveData

    init {
        _currentMessageLiveData.value = navigator.getString(R.string.hello_world)
    }

    override fun onResult(result: Any) {
        super.onResult(result)
        if (result is String){
            _currentMessageLiveData.value = result
        }
    }

    fun onEditPressed(){
        navigator.launch(
            EditFragment.Screen(_currentMessageLiveData.value!!)
        )
    }
}