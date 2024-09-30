package com.abpatil1.online_bus_resirvation_system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {

    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int i, @NonNull MainModel mainModel) {
        holder.name.setText(mainModel.getName());
        holder.email.setText(mainModel.getEmail());

        Glide.with(holder.img.getContext())
                .load(mainModel.getSurl())
                .placeholder(R.drawable.baseline_saved_search_24)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView name,email;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img=(CircleImageView)itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.nametext);
            email=(TextView)itemView.findViewById(R.id.emailtext);
        }
    }
}
