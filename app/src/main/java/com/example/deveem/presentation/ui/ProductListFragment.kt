package com.example.deveem.presentation.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deveem.R
import com.example.deveem.databinding.FragmentProductListBinding
import com.example.deveem.presentation.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils

@Suppress("DEPRECATION")
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
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductAdapter(emptyList()) { product ->
            viewModel.addToCart(product)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.products.observe(viewLifecycleOwner) { products ->
            adapter.updateProducts(products)
        }

        viewModel.cartItemCount.observe(viewLifecycleOwner) { count: Int ->
            activity?.invalidateOptionsMenu()
        }

        viewModel.loadProducts()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val cartItem = menu.findItem(R.id.action_cart)
        val cartActionView = cartItem.actionView
        if (cartActionView != null) {
            updateBadge(cartActionView, viewModel.cartItemCount.value ?: 0)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cart -> {
                val navController = findNavController(R.id.nav_host_fragment)
                navController.navigate(R.id.cartFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    @androidx.annotation.OptIn(com.google.android.material.badge.ExperimentalBadgeUtils::class)
    private fun updateBadge(view: View, count: Int) {
        val badge = BadgeDrawable.create(requireContext())
        badge.number = count
        badge.isVisible = count > 0
        BadgeUtils.attachBadgeDrawable(badge, view)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
