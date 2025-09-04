package com.harbdm.urbanexplorer

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

class HiltTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        // This is the standard, correct setup.
        // It tells the test framework to use Hilt's default, generated test application.
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}