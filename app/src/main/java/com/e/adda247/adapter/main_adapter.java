package com.e.adda247.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.adda247.R;
import com.e.adda247.modal.main_modal;

import java.util.List;

public class main_adapter extends RecyclerView.Adapter<main_adapter.ViewHolder> {
    Context context;
    List<main_modal> lst;

    public main_adapter() {
    }

    public main_adapter(Context context, List<main_modal> lst) {
        this.context = context;
        this.lst = lst;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card,null);
        ViewHolder v=new ViewHolder(view);

        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        main_modal model= lst.get(position);
        holder.email.setText(model.getEmail());
        if(model.getStatus().equals("Active")) {
            holder.status.setText(model.getStatus());
            holder.status.setTextColor(Color.rgb(50,205,50));
            holder.bar.setBackgroundColor(Color.rgb(50,205,50));
        }else{
            holder.status.setText(model.getStatus());
            holder.status.setTextColor(Color.rgb(255,69,0));
            holder.bar.setBackgroundColor(Color.rgb(255,69,0));
        }
        holder.name.setText(model.getName());
        holder.gender.setText(model.getGender());

    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView email,gender,status,name;
        LinearLayout bar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            email= (TextView) itemView.findViewById(R.id.email);
            gender= itemView.findViewById(R.id.gender);
            status =itemView.findViewById(R.id.status);
            name=itemView.findViewById(R.id.name);
            bar=itemView.findViewById(R.id.vertbar);

        }
    }
}
