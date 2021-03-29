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
import androidx.navigation.fragment.NavHostFragment
import br.com.tarcisiofl.shoestore.R
import br.com.tarcisiofl.shoestore.databinding.FragmentProductDetailBinding
import br.com.tarcisiofl.shoestore.models.Shoe

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

        binding.shoe = Shoe("", 0.0, "", "")
        binding.productViewModel = sharedViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        sharedViewModel.eventSave.observe(viewLifecycleOwner, Observer { isSaving ->
            if (isSaving) {
                onSaving()
            }
        })

        binding.cancelButton.setOnClickListener { view: View ->
            sharedViewModel.savedShoe()
            view.findNavController().navigateUp()
        }

        return binding.root
    }

    private fun onSaving() {
        if (validateShoeName() && validateBrandName() && validateSize() && validateDescription()) {
            NavHostFragment.findNavController(this).navigateUp()
        }

        sharedViewModel.savedShoe()
    }

    private fun validateShoeName(): Boolean {
        if (binding.shoeNameEditText.text.toString().trim().isEmpty()) {
            binding.shoeNameLabel.isErrorEnabled = true
            binding.shoeNameLabel.error = getString(R.string.errorShoeName)
            binding.shoeNameEditText.requestFocus()
            return false
        } else {
            binding.shoeNameLabel.isErrorEnabled = false
        }
        return true
    }

    private fun validateBrandName(): Boolean {
        if (binding.brandEditText.text.toString().trim().isEmpty()) {
            binding.brandNameLabel.isErrorEnabled = true
            binding.brandNameLabel.error = getString(R.string.errorBrandName)
            binding.brandEditText.requestFocus()
            return false
        } else {
            binding.brandNameLabel.isErrorEnabled = false
        }
        return true
    }

    private fun validateSize(): Boolean {
        if (binding.sizeEditText.text.toString().trim()
                .isEmpty() || binding.sizeEditText.text.toString().trim() == "0.0"
        ) {
            binding.sizeLabel.isErrorEnabled = true
            binding.sizeLabel.error = getString(R.string.errorSize)
            binding.sizeEditText.requestFocus()
            return false
        } else {
            binding.sizeLabel.isErrorEnabled = false
        }
        return true
    }

    private fun validateDescription(): Boolean {
        if (binding.descriptionEditText.text.toString().trim().isEmpty()) {
            binding.descriptionLabel.isErrorEnabled = true
            binding.descriptionLabel.error = getString(R.string.errorDescription)
            binding.descriptionEditText.requestFocus()
            return false
        } else {
            binding.descriptionLabel.isErrorEnabled = false
        }
        return true
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