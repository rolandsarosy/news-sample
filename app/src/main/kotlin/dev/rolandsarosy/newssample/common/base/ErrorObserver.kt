package dev.rolandsarosy.newssample.common.base

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Observer
import dev.rolandsarosy.newssample.common.event.Event

interface ErrorObserver {
    // TODO - This is a placeholder error showcase until a generic method is developed.
    fun defaultErrorObserver(context: Context): Observer<Event<String>> = Observer<Event<String>> { errorEvent ->
        errorEvent.getContentIfNotHandled()?.let { errorMessage -> Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show() }
    }
}
