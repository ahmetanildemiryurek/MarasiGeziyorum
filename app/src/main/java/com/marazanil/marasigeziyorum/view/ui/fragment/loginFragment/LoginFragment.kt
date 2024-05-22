package com.marazanil.marasigeziyorum.view.ui.fragment.loginFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.marazanil.marasigeziyorum.databinding.FragmentLoginBinding
import com.marazanil.marasigeziyorum.data.db.AppDatabase
import com.marazanil.marasigeziyorum.viewmodel.UserViewModel
import com.marazanil.marasigeziyorum.viewmodel.UserViewModelFactory
import android.widget.Toast
import com.marazanil.marasigeziyorum.data.repo.UserRepository

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels {
        val userDao = AppDatabase.getDatabase(requireContext()).userDao()
        UserViewModelFactory(UserRepository(userDao))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            loginUser()
        }
        binding.btnToRegisterScreen.setOnClickListener {
            navigateToRegister()
        }
    }

    private fun loginUser() {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        if (username.isNotEmpty() && password.isNotEmpty()) {
            userViewModel.getUserByUsername(username) { user ->
                if (user != null && user.password == password) {
                    Toast.makeText(requireContext(), "Giriş Başarılı!", Toast.LENGTH_SHORT).show()
                    navigateToMainActivity()
                } else {
                    Toast.makeText(requireContext(), "Geçersiz Şifre ya da Kullanıcı Adı", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(requireContext(), "Lütfen Tüm Alanları Doldurunuz!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToMainActivity() {
        val action = LoginFragmentDirections.actionLoginFragmentToMainActivity()
        findNavController().navigate(action)
    }

    private fun navigateToRegister() {
        val action = LoginFragmentDirections.actionFragmentLoginToFragmentRegister()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
