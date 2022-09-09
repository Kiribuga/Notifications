package com.example.notifications.ui.message

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notifications.data.RepositoryMessage
import kotlinx.coroutines.launch

class ViewModelMessage(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryMessage(application)

    fun messageNotification(userId: Long, userName: String, message: String) {
        viewModelScope.launch {
            try {
                repository.messageNotification(userId, userName, message)
            } catch (t: Throwable) {
                Log.d("ViewModelMessage", "error show notification message", t)
            }
        }
    }
}