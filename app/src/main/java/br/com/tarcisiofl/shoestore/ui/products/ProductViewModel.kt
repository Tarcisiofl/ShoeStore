package br.com.tarcisiofl.shoestore.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.tarcisiofl.shoestore.models.Shoe

class ProductViewModel : ViewModel() {
    private val _listProducts = MutableLiveData<MutableList<Shoe>>()
    val listProducts: LiveData<MutableList<Shoe>>
        get() = _listProducts

    init {
        _listProducts.value = mutableListOf(
            Shoe(
                "Air Jordan",
                7.5,
                "Nike",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ac tortor vitae purus faucibus ornare suspendisse sed."
            ),
            Shoe(
                "Samba",
                7.0,
                "Adidas",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ac tortor vitae purus faucibus ornare suspendisse sed."
            ),
            Shoe(
                "Fetto",
                8.5,
                "Jimmy Choo",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ac tortor vitae purus faucibus ornare suspendisse sed."
            )
        )
    }

    fun saveProduct(shoe: Shoe) {
        val list = _listProducts.value
        list?.add(shoe)
        _listProducts.value = list
    }
}