package com.example.testingproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testingproject.databinding.UserCityItemLayoutBinding
import com.example.testingproject.databinding.UserItemLayoutBinding
import com.example.testingproject.models.UserResponseItem
import com.example.testingproject.models.UsersWithCityResponseItem

class UserWithCityAdapter : RecyclerView.Adapter<UserWithCityAdapter.UserViewHolder>() {
    inner class UserViewHolder(private val binding: UserCityItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UsersWithCityResponseItem) {
            binding.apply {
                Glide.with(binding.imageView.context).load(item.image).into(imageView)
                txtUser.text = item.name
                txtCity.text = item.city
            }

        }

    }

    private val differeCallback = object : DiffUtil.ItemCallback<UsersWithCityResponseItem>() {
        override fun areItemsTheSame(
            oldItem: UsersWithCityResponseItem,
            newItem: UsersWithCityResponseItem
        ): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(
            oldItem: UsersWithCityResponseItem,
            newItem: UsersWithCityResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differeCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserCityItemLayoutBinding.inflate(
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