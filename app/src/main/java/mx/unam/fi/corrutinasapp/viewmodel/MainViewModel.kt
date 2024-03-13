package mx.unam.fi.corrutinasapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

    var resultState by mutableStateOf("")
        private set
    var counTime by mutableStateOf(0)
        private set
    var counTime2 by mutableStateOf(0)
        private set
    var oneCount by mutableStateOf(false)
        private set
    private var job: Job? = null

    fun fetchData() {
        job = viewModelScope.launch {
            for (i in 1..5) {
                delay(1000)
                counTime = i
            }
            for (u in 6..10) {
                delay(1000)
                counTime2 = u
            }
            oneCount = true
        }
        if (oneCount) {
            cancelCountdowns()
        }
        viewModelScope.launch {
            delay(10000)
            resultState = "Respuesta de la API o la Web"
        }
    }

    fun cancelCountdowns() {
        job?.cancel()
    }
}