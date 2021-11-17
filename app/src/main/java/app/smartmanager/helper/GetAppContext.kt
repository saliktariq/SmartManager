package app.smartmanager.helper

import android.app.Application
import android.content.Context
import app.smartmanager.repository.SmartManagerRepo


/*
Declaration: Main concept of this application is from the following stackoverflow post:
https://stackoverflow.com/questions/2002288/static-way-to-get-context-in-android?rq=1
 */

class GetAppContext : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        //Initialising the Repository
        SmartManagerRepo.initialize(this)

    }

    companion object {
        @Volatile
        private var context: Context? = null
        val appContext: Context?
            get() = context
    }
}