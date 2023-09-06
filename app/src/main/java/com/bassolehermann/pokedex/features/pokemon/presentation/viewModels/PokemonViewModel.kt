package com.bassolehermann.pokedex.features.pokemon.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import androidx.lifecycle.viewModelScope
import com.bassolehermann.pokedex.core.error.Failure
import com.bassolehermann.pokedex.features.pokemon.domain.entities.Pokemon
import com.bassolehermann.pokedex.features.pokemon.domain.usecases.GetPokemonByIdUseCase
import com.bassolehermann.pokedex.features.pokemon.domain.usecases.GetPokemonByNameUseCase
import com.bassolehermann.pokedex.features.pokemon.domain.usecases.GetPokemonTypeUseCase
import com.bassolehermann.pokedex.features.pokemon.domain.usecases.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {
    val isLoading:LiveData<Boolean>
        get() = _loading

    val failure:LiveData<Failure>
        get() = _failure

    val hasError:LiveData<Boolean>
        get() = _hasError
    val pokemonList: LiveData<List<Pokemon>>
        get() = _pokemonList

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    private val _pokemonList: MutableLiveData<List<Pokemon>> = MutableLiveData<List<Pokemon>>()
    private var _hasError: MutableLiveData<Boolean> = MutableLiveData()
    private var _failure: MutableLiveData<Failure> = MutableLiveData<Failure>()
    fun getPokemons(){
        viewModelScope.launch {
            _loading.postValue(true)
             getPokemonUseCase.call().fold(
                {
                    _failure.postValue(it)
                    _hasError.postValue(true)
                    _loading.postValue(false)

                },
                {
                    _pokemonList.postValue(it)
                    _hasError.postValue(false)
                    _loading.postValue(false)
                })
        }
    }
}