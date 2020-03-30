package blog.rishabh.coroutinesretrofitdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * @author Rishabh Tatiraju
 *
 * Our UI class which implements CoroutineScope by MainScope()
 * */

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_get_image.setOnClickListener {
            // Use launch and pass Dispatchers.Main to tell that
            // the result of this Coroutine is expected on the main thread.
            launch(Dispatchers.Main) {
                // Try catch block to handle exceptions when calling the API.
                try {
                    val response = ApiAdapter.apiClient.getRandomDogImage()
                    // Check if response was successful
                    if (response.isSuccessful && response.body() != null) {
                        // Retrieve data.
                        val data = response.body()!!
                        data.message?.let {
                            // Load URL into the ImageView using Coil.
                            iv_dog_image.load(it)
                        }
                    } else {
                        // Show API error.
                        // This is when the server responded with an error.
                        Toast.makeText(
                                this@MainActivity,
                                "Error Occurred: ${response.message()}",
                                Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    // Show API error. This is the error raised by the client.
                    // The API probably wasn't called in this case, so better check before assuming.
                    Toast.makeText(this@MainActivity,
                            "Error Occurred: ${e.message}",
                            Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
