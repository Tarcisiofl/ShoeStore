package br.com.tarcisiofl.shoestore.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.tarcisiofl.shoestore.models.Shoe

class ProductViewModel : ViewModel() {
    private val _productItem = MutableLiveData<Shoe>()
    val productItem: LiveData<Shoe>
        get() = _productItem

    private val _listProducts = MutableLiveData<MutableList<Shoe>>()
    val listProducts: LiveData<MutableList<Shoe>>
        get() = _listProducts

    init {
        _listProducts.value = mutableListOf()
    }

    fun saveProduct() {
        val list = _listProducts.value
        _productItem.value?.let {
            list?.add(it)
        }
        _listProducts.value = list
    }
}