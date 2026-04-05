package com.hig.lgpd_facil.screens.legislacao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hig.lgpd_facil.data.repository.LgpdRepository
import com.hig.lgpd_facil.model.Lei
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LegislacaoViewModel(private val repository: LgpdRepository) : ViewModel() {

    private val _leis = MutableStateFlow<List<Lei>>(emptyList())
    val leis: StateFlow<List<Lei>> = _leis.asStateFlow()


    private val _carregando = MutableStateFlow(true)
    val carregando: StateFlow<Boolean> = _carregando.asStateFlow()

    fun carregarLeis() {
        viewModelScope.launch {
            _carregando.value = true
            _leis.value = repository.buscarLeis() //API
            _carregando.value = false
        }
    }
}