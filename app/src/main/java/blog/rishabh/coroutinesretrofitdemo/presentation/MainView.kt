package blog.rishabh.coroutinesretrofitdemo.presentation

import blog.rishabh.coroutinesretrofitdemo.model.DogImageModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

/**
 * @author Rishabh Tatiraju & Alex Vortex
 *
 * Our interface which implements view methods for ViewState
 * */

interface MainView : MvpView {
    // We don't need to repeat this method after recreating the activity
    @Skip
    fun showToast(msg: String)

    // This method will repeat after recreating the activity
    fun setImage(data: DogImageModel)
}