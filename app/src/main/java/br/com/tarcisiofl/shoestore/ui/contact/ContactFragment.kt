package br.com.tarcisiofl.shoestore.ui.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import br.com.tarcisiofl.shoestore.R
import br.com.tarcisiofl.shoestore.databinding.FragmentContactBinding


class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentContactBinding>(
            inflater, R.layout.fragment_contact, container, false
        )

        return binding.root
    }
}