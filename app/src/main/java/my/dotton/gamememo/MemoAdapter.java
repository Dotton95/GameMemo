package my.dotton.gamememo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamememo.R;
import com.example.gamememo.databinding.ItemMemoBinding;

import java.util.ArrayList;
import java.util.Collections;

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
        if(list.size()<1){
            MainActivity.binding.mainRvMemos.setVisibility(View.GONE);
            MainActivity.binding.mainIvNodata.setVisibility(View.VISIBLE);
        }else {
            MainActivity.binding.mainRvMemos.setVisibility(View.VISIBLE);
            MainActivity.binding.mainIvNodata.setVisibility(View.GONE);
        }

        notifyDataSetChanged();
    }

    @Override
    public boolean onItemDrag(int from_pos, int to_pos) {
        Collections.swap(list, from_pos, to_pos);
        notifyItemMoved(from_pos,to_pos);
        return true;
    }

    public void setItems(ArrayList<Memo> list){
        this.list = list;
        notifyDataSetChanged();
    }
    public ArrayList<Memo> getItems() {
        return list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemMemoBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = ItemMemoBinding.bind(itemView);
        }

        public void onBind(Memo item, int pos){
            int icon = 0;
            switch (item.code){
                case 1: icon = R.drawable.lol; break;
                case 2: icon = R.drawable.lostark; break;
                case 3: icon = R.drawable.fifa4; break;
                case 4: icon = R.drawable.steam; break;
                case 5: icon = R.drawable.blizard; break;
                case 6: icon = R.drawable.outline_desktop_mac_24; break;
            }

            binding.memoIvIcon.setClipToOutline(true);
            binding.memoIvIcon.setImageResource(icon);
            binding.memoTvTitle.setText(item.title);
            binding.memoTvId.setText(item.id);
            if(item.pwd2.equals(""))  {
                binding.memoTvPwd2.setVisibility(View.GONE);
                binding.memoTvPwd.setText("1차 "+item.pwd);
            }
            else {
                binding.memoTvPwd2.setVisibility(View.VISIBLE);
                binding.memoTvPwd.setText("1차 : "+item.pwd);
                binding.memoTvPwd2.setText("2차 : "+item.pwd2);
            }
            item.sort = pos;
        }
    }

}
