package com.example.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PostAdapter(private val context: Context, private var postList: List<POST>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {



    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userId: TextView
        var id: TextView
        var title: TextView
        var body: TextView

        init {
            userId = itemView.findViewById(R.id.userId)
            id = itemView.findViewById(R.id.id_)
            title = itemView.findViewById(R.id.title_)
            body = itemView.findViewById(R.id.body_)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item,parent,false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val post: POST
        post = postList[position]

        holder.userId.text = post.userId.toString()
        holder.id.text = post.id.toString()
        holder.body.text = post.body
        holder.title.text = post.title

    }
}