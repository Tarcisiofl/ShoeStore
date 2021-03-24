package br.com.tarcisiofl.shoestore.ui.products

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import br.com.tarcisiofl.shoestore.R
import br.com.tarcisiofl.shoestore.databinding.FragmentInstructionBinding
import br.com.tarcisiofl.shoestore.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentProductDetailBinding>(
            inflater, R.layout.fragment_product_detail, container, false
        )

        return binding.root
    }
}