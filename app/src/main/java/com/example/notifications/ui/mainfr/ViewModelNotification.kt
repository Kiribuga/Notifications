package com.example.notifications.ui.mainfr

import android.util.Log
import androidx.lifecycle.*
import com.example.notifications.data.RepositoryMain
import kotlinx.coroutines.launch

class ViewModelNotification : ViewModel() {

    private val repository = RepositoryMain()

    private val tokenMLD = MutableLiveData<String?>()

    val tokenLD: LiveData<String?>
        get() = tokenMLD

    fun getToken() {
        viewModelScope.launch {
            try {
                tokenMLD.postValue(repository.getToken())
                Log.d("ViewModel", "token = ${tokenMLD.value}")
            } catch (t: Throwable) {
                Log.d("ViewModel", "Error get token", t)
            }
        }
    }
}