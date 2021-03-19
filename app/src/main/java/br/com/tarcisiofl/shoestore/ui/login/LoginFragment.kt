package br.com.tarcisiofl.shoestore.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import br.com.tarcisiofl.shoestore.R
import br.com.tarcisiofl.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventLogin.observe(viewLifecycleOwner, Observer { hasLoggedIn ->
            if (hasLoggedIn)
                onSubmit()
        })

        viewModel.eventCreateAccount.observe(viewLifecycleOwner, Observer { hasCreatedAccount ->
            if (hasCreatedAccount)
                createdAccount()
        })

        return binding.root
    }

    private fun onSubmit() {
        val email = binding.emailInput.text.toString()
        val password = binding.passwordInput.text.toString()

        if (viewModel.isValidCredentials(email, password)) {
            setErrorTextField(false)
            loggedIn()
        } else {
            setErrorTextField(true)
        }

        viewModel.loggedIn()
    }

    /**
     * Called when the login button is clicked
     */
    private fun loggedIn() {
        val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
        findNavController(this).navigate(action)
    }

    /**
     * Called when the create account button is clicked
     */
    private fun createdAccount() {
        val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
        findNavController(this).navigate(action)
        viewModel.createdAccount()
    }

    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding.passwordText.isErrorEnabled = true
            binding.passwordText.error = getString(R.string.error)
        } else {
            binding.passwordText.isErrorEnabled = false
            binding.emailInput.text = null
            binding.passwordInput.text = null
        }
    }
}