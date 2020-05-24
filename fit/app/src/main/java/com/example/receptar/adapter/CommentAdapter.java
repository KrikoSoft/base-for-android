package com.example.receptar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.receptar.R;
import com.example.receptar.java.RecipeComment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {

    private List<Map.Entry<String, RecipeComment>> comments = new ArrayList<>();

    public CommentAdapter() {
        super();
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        RecipeComment current = comments.get(position).getValue();
        String author = comments.get(position).getKey();

        holder.textViewCommentBody.setText(current.getCommentText());
        holder.textViewCommentAuthor.setText(author);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void setComments(List<Map.Entry<String, RecipeComment>> comments) {
        this.comments = comments;
        notifyDataSetChanged();
    }

    static class CommentHolder extends RecyclerView.ViewHolder {
        private TextView textViewCommentAuthor;
        private TextView textViewCommentBody;

        CommentHolder(@NonNull View itemView) {
            super(itemView);
            textViewCommentAuthor = itemView.findViewById(R.id.text_view_comment_author);
            textViewCommentBody = itemView.findViewById(R.id.text_view_comment_body);
        }
    }

}
