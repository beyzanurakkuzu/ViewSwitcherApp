package com.beyzaakkuzu.viewswitcherapplication

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext val context: Context) : ViewModel() {
    sealed class PageState {
        object A : PageState()
        object B : PageState()
    }

    private val _pageState = MutableLiveData<PageState>(PageState.A)
    fun getPageState() = _pageState

    init {
        setA()
    }
    fun setA() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            viewModelScope.launch {
                _pageState.value = (PageState.A)
            }

        }
    }

    fun setB() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            viewModelScope.launch {
                _pageState.value  = PageState.B
            }

        }
    }
}