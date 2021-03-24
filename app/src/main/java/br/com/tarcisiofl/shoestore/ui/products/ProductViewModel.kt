package br.com.tarcisiofl.shoestore.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.tarcisiofl.shoestore.models.Shoe

class ProductViewModel : ViewModel() {
    private val _listProducts = MutableLiveData<ArrayList<Shoe>>()
    val listProducts: LiveData<ArrayList<Shoe>>
        get() = _listProducts

    init {
        _listProducts.value = arrayListOf()
    }

    fun saveProduct(shoe: Shoe) {
        val list = _listProducts.value
        list?.add(shoe)
        _listProducts.value = list
    }
}