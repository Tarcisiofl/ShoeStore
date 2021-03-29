package br.com.tarcisiofl.shoestore.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.tarcisiofl.shoestore.models.Shoe

class ProductViewModel : ViewModel() {
    private val _listProducts = MutableLiveData<MutableList<Shoe>>()
    val listProducts: LiveData<MutableList<Shoe>>
        get() = _listProducts

    private val _eventSave = MutableLiveData<Boolean>()
    val eventSave: LiveData<Boolean>
        get() = _eventSave

    init {
        _listProducts.value = mutableListOf()
    }

    fun onSaveShoe() {
        _eventSave.value = true
    }

    fun savedShoe() {
        _eventSave.value = false
    }

    fun saveProduct(shoe: Shoe) {
        onSaveShoe()
        if (!(shoe.name.trim().isEmpty() || shoe.company.trim()
                .isEmpty() || shoe.size == 0.0 || shoe.description.trim().isEmpty())
        ) {
            val list = _listProducts.value
            list?.add(shoe)
            _listProducts.value = list
        }
    }
}