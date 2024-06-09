package com.example.deveem.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.deveem.R
import com.example.deveem.databinding.FragmentCartBinding
import com.example.deveem.presentation.viewmodel.CartViewModel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductAdapter(emptyList()) { product ->
            viewModel.addToCart(product)
        }

        binding.cartRecyclerView.adapter = adapter

        viewModel.getCartContents()

//        viewModel.cartContents.observe(viewLifecycleOwner) { cartContents ->
//            adapter.updateProducts(cartContents)
//        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }

        binding.buyButton.setOnClickListener {
            val cartContents = viewModel.cartContents.value ?: emptyList()
            if (cartContents.isNotEmpty()) {
                viewModel.clearCart()
                Toast.makeText(context, "Purchased: $cartContents", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.action_cartFragment_to_productListFragment)
                Toast.makeText(context, "Add something", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
