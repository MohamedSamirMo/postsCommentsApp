package com.example.postsapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postsapp.databinding.ItemCommentBinding
import com.example.postsapp.databinding.ItemPostBinding

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.Holder>() {

    var Comments :ArrayList<CommentsModel>?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemCommentBinding.inflate(android.view.LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return if (Comments==null) 0 else Comments!!.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Comments?.let {
            holder.bind(it.get(position))
        }
    }


    inner class Holder(val binding: ItemCommentBinding)
    :RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {

            }
        }
    fun bind(commentsModel: CommentsModel) {
        binding.apply {
            textName.text=commentsModel.name
            body.text=commentsModel.body
        }

    }
    }



}