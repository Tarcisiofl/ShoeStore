package br.com.tarcisiofl.shoestore.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.tarcisiofl.shoestore.models.Shoe

class ProductViewModel : ViewModel() {
    private val _listProducts = MutableLiveData<Shoe>()
    val listProducts: LiveData<Shoe>
        get() = _listProducts
}