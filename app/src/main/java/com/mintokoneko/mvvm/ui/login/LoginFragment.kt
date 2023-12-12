package com.mintokoneko.mvvm.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mintokoneko.mvvm.R
import com.mintokoneko.mvvm.data.Credentials
import com.mintokoneko.mvvm.databinding.FragmentLoginBinding
import com.mintokoneko.mvvm.ui.login.viewmodel.LoginViewModel
import com.mintokoneko.mvvm.ui.login.viewmodel.LoginViewModelProvider

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()

        setLoginViewModel(this, context)
        initObservers()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.apply {
            login.setOnClickListener {
                val login = binding.loginField.editText?.text.toString()
                val password = binding.passwordField.editText?.text.toString()

                if (login.isNotEmpty() && password.isNotEmpty()) {
                    val credentials = Credentials(login, password)
                    loginViewModel.changeCredentials(credentials)
                }
            }
        }
    }

    private fun setLoginViewModel(fragment: Fragment, context: Context) {
        loginViewModel = LoginViewModelProvider.getViewModel(fragment, context)
    }

    private fun initObservers() {
        loginViewModel.credentials.observe(viewLifecycleOwner) { credentials ->
            if (credentials.login != null) {
                beginContentTransaction()
            }
        }
    }

    private fun beginContentTransaction() {
        val contentAction = R.id.action_loginFragment_to_contentFragment
        findNavController().navigate(contentAction)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}