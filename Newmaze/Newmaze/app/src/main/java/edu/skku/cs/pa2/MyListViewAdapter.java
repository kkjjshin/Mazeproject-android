package edu.skku.cs.pa2;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyListViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<MazeItem> items;

    public MyListViewAdapter(Context mContext, ArrayList<MazeItem> items){
        this.mContext = mContext;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return  items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.maze_item, viewGroup, false); //질문

        }

        TextView text_nm = view.findViewById(R.id.tv_name);
        TextView text_sz = view.findViewById(R.id.tv_size);

        text_nm.setText(items.get(i).name);
        text_sz.setText(items.get(i).size);

        Button btn_maze = view.findViewById(R.id.btn_maze);
        btn_maze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MazeActivity.class);
                intent.putExtra("name",items.get(i).name);
                mContext.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
            }
        });

//        ImageView img = view.findViewById(R.id.item_image);
//        TextView text = view.findViewById(R.id.item_text);

//        Log.d("intcheck", Integer.toString(i));
//
//        img.setImageResource(items.get(i).imgId);
//        text.setText(items.get(i).name);


        return view;
    }
}
