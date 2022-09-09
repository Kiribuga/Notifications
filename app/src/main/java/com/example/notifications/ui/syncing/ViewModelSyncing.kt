package com.example.notifications.ui.syncing

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notifications.data.RepositorySyncing
import kotlinx.coroutines.launch

class ViewModelSyncing(application: Application) : AndroidViewModel(application) {

    private val repository = RepositorySyncing(application)
    private val loadMLD = MutableLiveData<Boolean>()

    val loadLD: LiveData<Boolean>
        get() = loadMLD

    fun progressNotification() {
        loadMLD.postValue(true)
        viewModelScope.launch {
            try {
                repository.progressNotification()
            } catch (t: Throwable) {
                Log.d("ViewModelSyncing", "error show progress", t)
            } finally {
                loadMLD.postValue(false)
            }
        }
    }
}