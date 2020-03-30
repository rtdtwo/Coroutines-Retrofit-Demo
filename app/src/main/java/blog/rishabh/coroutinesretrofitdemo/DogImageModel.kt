package blog.rishabh.coroutinesretrofitdemo

import androidx.annotation.Keep

/**
 * @author Rishabh Tatiraju
 *
 * Model class, the response from API.
 *
 * Use annotation @Keep if you wish to use ProGuard/R8
 * */

@Keep
class DogImageModel {
    var message: String? = null
}
