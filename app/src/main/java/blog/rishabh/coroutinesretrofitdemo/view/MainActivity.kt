package blog.rishabh.coroutinesretrofitdemo.view

import android.os.Bundle
import android.widget.Toast
import blog.rishabh.coroutinesretrofitdemo.R
import blog.rishabh.coroutinesretrofitdemo.model.DogImageModel
import blog.rishabh.coroutinesretrofitdemo.presentation.MainPresenter
import blog.rishabh.coroutinesretrofitdemo.presentation.MainView
import coil.api.load
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

/**
 * @author Rishabh Tatiraju & Alex Vortex
 *
 * Our UI class which implements CoroutineScope by MainScope()
 * */

class MainActivity : MvpAppCompatActivity(), MainView {
    //The annotation gives us access to the presenter methods
    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_get_image.setOnClickListener {
            //We tell the presenter what happened in the view
            presenter.onButtonClicked()
        }
    }

    //Overrided methods from MainView
    //Just show toast message
    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    //Just show image of cute dog
    override fun setImage(data: DogImageModel) {
        data.message?.let {
            // Load URL into the ImageView using Coil.
            iv_dog_image.load(it)
        }
    }


}
