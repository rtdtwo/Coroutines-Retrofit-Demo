package blog.rishabh.coroutinesretrofitdemo.presentation

import blog.rishabh.coroutinesretrofitdemo.model.ApiAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter

/**
 * @author Rishabh Tatiraju & Alex Vortex
 *
 * Our class which realize all business logic
 * */

//The annotation gives presenter access to the activity methods via ViewState layer
@InjectViewState
class MainPresenter : MvpPresenter<MainView>(), CoroutineScope by MainScope() {
    fun onButtonClicked() {
        // Use launch and pass Dispatchers.Main to tell that
        // the result of this Coroutine is expected on the main thread.
        launch(Dispatchers.Main) {
            try {
                val response = ApiAdapter.apiClient.getRandomDogImage()
                // Check if response was successful
                if (response.isSuccessful && response.body() != null) {
                    // Retrieve data and sent it to MainView
                    viewState.setImage(response.body()!!)
                } else {
                    // Send API error to MainView
                    // This is when the server responded with an error.
                    val err = String.format("Error Occurred: %s",response.message())
                    viewState.showToast(err)
                }
            } catch (e: Exception) {
                // Send API error to MainView. This is the error raised by the client.
                // The API probably wasn't called in this case, so better check before assuming.{
                e.printStackTrace()
                val err = String.format("Error Occurred: %s",e.toString())
                viewState.showToast(err)
            }
        }
    }
}