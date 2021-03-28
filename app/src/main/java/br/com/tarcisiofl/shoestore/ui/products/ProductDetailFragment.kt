package br.com.tarcisiofl.shoestore.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import br.com.tarcisiofl.shoestore.R
import br.com.tarcisiofl.shoestore.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {

    private val sharedViewModel: ProductViewModel by activityViewModels()
    private lateinit var binding: FragmentProductDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product_detail, container, false
        )

        binding.productViewModel = sharedViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.cancelButton.setOnClickListener { view: View ->
            sharedViewModel.resetProduct()
            view.findNavController().navigateUp()
        }

        return binding.root
    }
}

@BindingAdapter("android:text")
fun bindDoubleInText(tv: EditText, value: Double) {
    tv.setText(value.toString())
    tv.text?.let {
        tv.setSelection(it.length)
    }
}

@InverseBindingAdapter(attribute = "android:text")
fun getDoubleFromBinding(view: TextView): Double {
    val string = view.text.toString()
    return if (string.isEmpty()) 0.0 else string.toDouble()
}