package com.example.mysubmission2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mysubmission2.databinding.ItemRowUserBinding
import com.example.mysubmission2.model.UserModel

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val list = ArrayList<UserModel>()
    private var onItemClickCallback : OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(users: ArrayList<UserModel>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: UserModel) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .into(imgAvatar)
                tvUsername.text = user.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: UserModel)
    }
}