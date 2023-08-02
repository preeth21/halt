package com.ram.sample2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ram.sample2.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RecyclerHomeAdapter extends RecyclerView.Adapter<RecyclerHomeAdapter.ViewHolderHome> {

    HashMap<String,String> element;
    Iterator<Map.Entry<String, String>> it;
    public RecyclerHomeAdapter(HashMap<String,String> mp){
        element=mp;
        it=element.entrySet().iterator();
    }
    @NonNull
    @Override
    public ViewHolderHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_home_single_row,parent,false);
        return new ViewHolderHome(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHome holder, int position) {


        //if(it.hasNext()){
            Map.Entry<String, String> entry = it.next();
            holder.txt.setText(entry.getKey().toUpperCase()+": "+entry.getValue().toUpperCase());
        //}

    }

    @Override
    public int getItemCount() {

        return element.size();
    }

    class ViewHolderHome extends RecyclerView.ViewHolder{
        TextView txt;
        public ViewHolderHome(@NonNull View itemView) {
            super(itemView);
            txt=itemView.findViewById(R.id.txtSingleRow);

        }
    }
}
