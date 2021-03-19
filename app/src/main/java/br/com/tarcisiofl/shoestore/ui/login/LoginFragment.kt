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
        if (validateEmail() && validatePassword()) {
            loggedIn()
            binding.emailInput.text = null
            binding.passwordInput.text = null
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

    private fun validateEmail(): Boolean {
        if (binding.emailInput.text.toString().trim().isEmpty()) {
            binding.emailText.isErrorEnabled = true
            binding.emailText.error = getString(R.string.errorEmail)
            binding.emailInput.requestFocus()
            return false
        } else {
            binding.emailText.isErrorEnabled = false
        }
        return true
    }

    private fun validatePassword(): Boolean {
        if (binding.passwordInput.text.toString().trim().isEmpty()) {
            binding.passwordText.isErrorEnabled = true
            binding.passwordText.error = getString(R.string.errorPassword)
            binding.passwordInput.requestFocus()
            return false
        } else {
            binding.passwordText.isErrorEnabled = false
        }
        return true
    }
}