package com.molerocn.deckly.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molerocn.deckly.data.repository.DeckRepository
import com.molerocn.deckly.domain.model.Deck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeckDetailViewModel @Inject constructor(
    private val repository: DeckRepository
) : ViewModel() {

    val deck = MutableLiveData<Deck>()
    
    fun onCreate(deckId: Int) {
        // buscar desde la base de datos

        viewModelScope.launch {
            val findDeck = repository.getDeckFromDatabase(deckId)
            deck.postValue(findDeck)
        }
    }

}
