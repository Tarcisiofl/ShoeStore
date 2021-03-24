package br.com.tarcisiofl.shoestore.ui.products

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import br.com.tarcisiofl.shoestore.R
import br.com.tarcisiofl.shoestore.databinding.FragmentProductListBinding
import br.com.tarcisiofl.shoestore.databinding.ProductItemBinding

class ProductListFragment : Fragment() {

    private lateinit var viewModel: ProductViewModel
    private lateinit var binding: FragmentProductListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product_list, container, false
        )

        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        viewModel.listProducts.observe(viewLifecycleOwner, Observer { products ->
            if (products.size > 0)
                binding.productsViewgroup.removeAllViews()
            products.forEach { product ->
                val inflater = LayoutInflater.from(binding.productsViewgroup.context)
                val binding: ProductItemBinding =
                    ProductItemBinding.inflate(inflater, binding.productsViewgroup, true)
                binding.shoe = product
            }
        })

        binding.createProductButton.setOnClickListener { v: View ->
            v.findNavController()
                .navigate(ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment())
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.product_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}