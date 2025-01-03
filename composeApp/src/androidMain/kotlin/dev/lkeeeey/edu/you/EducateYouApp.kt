package dev.lkeeeey.edu.you

import android.app.Application
import dev.lkeeeey.edu.di.initKoin
import org.koin.android.ext.koin.androidContext

class EducateYouApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@EducateYouApp)
        }
    }
}
