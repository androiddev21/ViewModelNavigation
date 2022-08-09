package com.example.viewmodelnavigation.navigator

import android.app.Application
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.viewmodelnavigation.Event
import com.example.viewmodelnavigation.MainActivity
import com.example.viewmodelnavigation.R
import com.example.viewmodelnavigation.view.base.BaseScreen

const val ARGS_SCREEN = "screen"

//special kind of view model that has android dependencies
class MainNavigator(application: Application) : AndroidViewModel(application), Navigator {

    val whenActivityActive = MainActivityActions()

    private val _result = MutableLiveData<Event<Any>>()
    val result: LiveData<Event<Any>> = _result

    override fun launch(screen: BaseScreen) = whenActivityActive {
        launchFragment(it, screen)
    }

    override fun goBack(result: Any?) = whenActivityActive {
        if (result != null){
            _result.value = Event(result)
        }
        it.onBackPressed()
    }

    override fun toast(messageRes: Int) {
        Toast.makeText(getApplication(), getString(messageRes), Toast.LENGTH_SHORT).show()
    }

    override fun getString(messageRes: Int): String =
        getApplication<Application>().getString(messageRes)

    fun launchFragment(activity: MainActivity, screen: BaseScreen, addToBackStack: Boolean = true) {
        val fragment = screen.javaClass.enclosingClass.newInstance() as Fragment
        fragment.arguments = bundleOf(
            ARGS_SCREEN to screen
        )
        val transaction = activity.supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.replace(
            R.id.container,
            fragment
        ).commit()
    }
}