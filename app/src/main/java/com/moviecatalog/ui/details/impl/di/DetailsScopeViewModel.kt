package com.moviecatalog.ui.details.impl.di

import android.util.Log
import org.koin.viewmodel.scope.ScopeViewModel


/**
 * ViewModel только для управления жизненным циклом скоупа
 * Когда уходим с экрана ViewModel уничтожается вместе с скоупом
 */
internal class DetailsScopeViewModel : ScopeViewModel() {

    init {
        Log.d("DetailsScopeViewModel", "init")
    }

    override fun onCleared() {
        Log.d("DetailsScopeViewModel", "onCleared")
    }
}