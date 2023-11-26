package com.example.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class CommentsAdapter(var context: Context, var CommentList:List<Comments>): RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {
    class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postID: TextView
        var Id: TextView
        var Name: TextView
        var Email: TextView
        var Body: TextView

        init {
            postID = itemView.findViewById(R.id.postID)
            Id = itemView.findViewById(R.id.id_)
            Name = itemView.findViewById(R.id.Name)
            Email = itemView.findViewById(R.id.email)
            Body = itemView.findViewById(R.id.body_)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        var inflater = LayoutInflater.from(context)
        var view: View = inflater.inflate(R.layout.itemcomment,parent,false)
        return CommentsViewHolder(view)

    }

    override fun getItemCount(): Int {
        return CommentList.size
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        var comments = CommentList[position]
        holder.postID.text = comments.postId
        holder.Id.text = comments.id
        holder.Name.text = comments.name
        holder.Email.text = comments.email
        holder.Body.text = comments.body
    }
}