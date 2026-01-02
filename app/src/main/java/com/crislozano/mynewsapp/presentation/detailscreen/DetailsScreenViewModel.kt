package com.crislozano.mynewsapp.presentation.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crislozano.mynewsapp.domain.model.CustomResult
import com.crislozano.mynewsapp.domain.usecases.IGetNewsDetailsUC
import com.crislozano.mynewsapp.presentation.model.DetailsScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsScreenViewModel(private val getNewsDetailsUC: IGetNewsDetailsUC): ViewModel() {

    private val _state = MutableStateFlow(DetailsScreenState())
    val state: StateFlow<DetailsScreenState> = _state.asStateFlow()

    fun handleIntent(intent: DetailsScreenIntent) {
        when(intent) {
            is DetailsScreenIntent.RequestNew -> { getNewsDetails(intent.uuid) }
        }
    }

    fun getNewsDetails(uuid: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getNewsDetailsUC(uuid).collect {
                if(it is CustomResult.Success) {
                    if(it.data == null) {
                        _state.value = _state.value.copy(isLoading = false, error = "Empty Details")
                    } else {
                        _state.value = _state.value.copy(news = it.data, isLoading = false, error = null)
                    }
                } else {
                    _state.value = _state.value.copy(isLoading = false, error = it.errorMessage)
                }
            }
        }
    }


}


sealed class DetailsScreenIntent {
    class RequestNew(val uuid: String): DetailsScreenIntent()
}