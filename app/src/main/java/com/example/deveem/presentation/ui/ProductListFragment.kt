package com.example.deveem.presentation.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deveem.R
import com.example.deveem.databinding.FragmentProductListBinding
import com.example.deveem.presentation.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductViewModel by viewModels()
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.btnCategory.setOnClickListener {
//
//        }

        binding.icCart.setOnClickListener {
            findNavController().navigate(R.id.cartFragment)
        }


        adapter = ProductAdapter(emptyList())
        { product ->
            viewModel.addToCart(product)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.products.observe(viewLifecycleOwner)
        { products ->
            adapter.updateProducts(products)
        }

        viewModel.loadProducts()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

