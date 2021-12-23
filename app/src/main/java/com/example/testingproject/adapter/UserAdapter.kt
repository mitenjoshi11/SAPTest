package com.example.testingproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testingproject.databinding.UserItemLayoutBinding
import com.example.testingproject.models.UserResponseItem

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(private val binding: UserItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserResponseItem) {
            binding.apply {
                Glide.with(binding.imageView.context).load(item.image).into(imageView)
                txtTitle.text = item.name
                txtDescription.text = item.description
                root.setOnClickListener {

                }
            }
        }
    }

    private val differeCallback = object : DiffUtil.ItemCallback<UserResponseItem>() {
        override fun areItemsTheSame(
            oldItem: UserResponseItem,
            newItem: UserResponseItem
        ): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(
            oldItem: UserResponseItem,
            newItem: UserResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, differeCallback)

    fun updateData(list: MutableList<UserResponseItem>) {
        differ.submitList(list)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}