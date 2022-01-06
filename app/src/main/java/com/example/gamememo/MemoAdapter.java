package com.example.gamememo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamememo.databinding.ItemMemoBinding;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> implements ItemTouchHelperListener{

    private ArrayList<Memo> list = new ArrayList<>();

    public MemoAdapter(){}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_memo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position),position);
    }

    @Override
    public int getItemCount() { return list.size(); }

    @Override
    public void onItemSwipe(int pos) {
        list.remove(pos);
        notifyDataSetChanged();
    }

    @Override
    public boolean onItemDrag(int from_pos, int to_pos) {
        Memo item = list.get(from_pos);
        list.remove(from_pos);
        list.add(to_pos,item);
        item.sort = to_pos;
        notifyItemMoved(from_pos,to_pos);
        return true;
    }

    public void setItems(ArrayList<Memo> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemMemoBinding binding;
        public ViewHolder(View itemView) {
            super(itemView);
            binding = ItemMemoBinding.bind(itemView);
        }
        public void onBind(Memo item, int pos){
            binding.memoTvId.setText(item.id);
            binding.memoTvPwd.setText(item.pwd);
            binding.memoTvTitle.setText(item.title);
            item.sort = pos;
        }
    }

}
