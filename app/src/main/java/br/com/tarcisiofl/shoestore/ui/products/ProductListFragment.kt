package br.com.tarcisiofl.shoestore.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import br.com.tarcisiofl.shoestore.R
import br.com.tarcisiofl.shoestore.databinding.FragmentInstructionBinding
import br.com.tarcisiofl.shoestore.databinding.FragmentProductListBinding

class ProductListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentProductListBinding>(
            inflater, R.layout.fragment_product_list, container, false
        )

        return binding.root
    }
}