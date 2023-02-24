package com.github.cesar1287.pingpongx.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    val onPlayerHomeScore: MutableLiveData<Int> = MutableLiveData()
    val onPlayerAwayScore: MutableLiveData<Int> = MutableLiveData()

    init {
        startGame()
    }

    private fun startGame() {
        onPlayerHomeScore.value = 0
        onPlayerAwayScore.value = 0
    }

    fun rematch() {
        startGame()
    }

    fun registerPlayerHomeScore() {
        onPlayerHomeScore.value = onPlayerHomeScore.value?.plus(1)
    }

    fun registerPlayerAwayScore() {
        onPlayerAwayScore.value = onPlayerAwayScore.value?.plus(1)
    }
}