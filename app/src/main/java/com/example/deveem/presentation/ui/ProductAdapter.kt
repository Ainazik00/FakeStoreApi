package com.example.deveem.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deveem.R
import com.example.deveem.domain.model.Product

class ProductAdapter(
    private var products: List<Product>,
    private val onAddToCartClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImageView: ImageView = itemView.findViewById(R.id.img)
        val productTextView: TextView = itemView.findViewById(R.id.title)
        val addToCartButton: TextView = itemView.findViewById(R.id.addToCartButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.productTextView.text = product.title

        Glide.with(holder.itemView.context)
            .load(product.image)
            .into(holder.productImageView)

        holder.productTextView.setOnClickListener {
            if (holder.productTextView.maxLines == 1) {
                holder.productTextView.maxLines = Integer.MAX_VALUE
            } else {
                holder.productTextView.maxLines = 1
            }
        }

        holder.addToCartButton.setOnClickListener {
            onAddToCartClick(product)
        }
    }

    override fun getItemCount(): Int = products.size

    fun updateProducts(newProducts: List<Product>) = apply {
        newProducts.also { products = it }
        notifyDataSetChanged()
    }

}
