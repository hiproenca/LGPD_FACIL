package com.hig.lgpd_facil.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hig.lgpd_facil.data.local.DiagnosticoEntity
import com.hig.lgpd_facil.data.repository.LgpdRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: LgpdRepository) : ViewModel() {


    private val _diagnostico = MutableStateFlow<DiagnosticoEntity?>(null)
    val diagnostico: StateFlow<DiagnosticoEntity?> = _diagnostico.asStateFlow()

    fun carregarDados() {

        viewModelScope.launch {
            _diagnostico.value = repository.obterDiagnostico()
        }
    }
}