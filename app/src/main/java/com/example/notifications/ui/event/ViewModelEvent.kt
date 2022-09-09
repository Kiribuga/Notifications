package com.example.notifications.ui.event

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notifications.data.RepositoryEvent
import kotlinx.coroutines.launch

class ViewModelEvent(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryEvent(application)

    fun eventNotification(title: String, message: String, image: String?) {
        viewModelScope.launch {
            try {
                repository.eventNotification(title, message, image)
            } catch (t: Throwable) {
                Log.d("ViewModelEvent", "error show notification event", t)
            }
        }
    }
}