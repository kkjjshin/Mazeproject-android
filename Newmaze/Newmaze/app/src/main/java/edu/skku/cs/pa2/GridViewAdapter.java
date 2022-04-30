package edu.skku.cs.pa2;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {
    private ArrayList<GridItem> items = new ArrayList<>();
    private Context mContext;
    private int dpi;

    public GridViewAdapter(Context mContext, ArrayList<GridItem> items, int dpi){
        this.mContext = mContext;
        this.items = items;
        this.dpi = dpi;
    }



    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){


            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false); //질문


            ImageView immgg = view.findViewById(R.id.imageView5);

            ImageView sub_img = view.findViewById(R.id.iv_sub); // 유저

            ConstraintLayout.LayoutParams parms = (ConstraintLayout.LayoutParams) immgg.getLayoutParams();
            //parms.width 및 height 식 즉, 마진포함하지 않은 셀의 너비

            Context context;








            int half_m = (int)((((float)dpi/(float)160))*((float)3));
            int full_m = half_m*2;

            parms.width = (int)((float)(((float)350/(float) items.get(i).num_maze)*((float)dpi/(float)160)))-full_m;
            parms.height = parms.width;







            //유저 첫시작점 위치시킴
            if((items.get(i).cango.equals("right"))||(items.get(i).cango.equals("left"))||(items.get(i).cango.equals("top"))
                    ||(items.get(i).cango.equals("bottom"))||(items.get(i).cango.equals("finish"))||(items.get(i).cango.equals("hint"))){


                if(items.get(i).cango.equals("hint")){

                    sub_img.setImageResource(R.drawable.hint);
                }
                else if(items.get(i).cango.equals("finish")){
                    sub_img.setImageResource(R.drawable.goal);
                }

                else if(items.get(i).cango.equals("right")){
                    sub_img.setImageResource(R.drawable.user);
                    sub_img.setRotation(90);
                    sub_img.bringToFront(); //이미지 맨 앞에 둠

                }
                else if(items.get(i).cango.equals("left")){
                    sub_img.setImageResource(R.drawable.user);
                    sub_img.setRotation(270);
                    sub_img.bringToFront(); //이미지 맨 앞에 둠

                }
                else if(items.get(i).cango.equals("top")){
                    sub_img.setImageResource(R.drawable.user);
                    sub_img.bringToFront(); //이미지 맨 앞에 둠

                }
                else if(items.get(i).cango.equals("bottom")){
                    sub_img.setImageResource(R.drawable.user);
                    sub_img.setRotation(180);
                    sub_img.bringToFront(); //이미지 맨 앞에 둠

                }

            }
            else{
                sub_img.setImageResource(0);
                sub_img.bringToFront(); //이미지 맨 앞에 둠
            }
            
            //setscale로 width 30dp 아래로 내려갈시 조정
            if(parms.width < (int)((((float)dpi/(float)160))*((float)30))){

                sub_img.setScaleX((float)parms.width/((((float)dpi/(float)160))*((float)30)));
                sub_img.setScaleY((float)parms.width/((((float)dpi/(float)160))*((float)30)));
            }





//            if(i ==items.size()-1){
//                sub_img.setImageResource(R.drawable.goal);
//            }


            if(items.get(i).num == 8){
                parms.topMargin = half_m;
                parms.bottomMargin = 0;
                parms.leftMargin = 0;
                parms.rightMargin = 0;
                parms.width +=full_m;
                parms.height += half_m;
                immgg.setLayoutParams(parms);
            }
            else if(items.get(i).num == 4){
                parms.topMargin = 0;
                parms.bottomMargin = 0;
                parms.leftMargin = half_m;
                parms.rightMargin = 0;
                parms.width += half_m;
                parms.height += full_m;
                immgg.setLayoutParams(parms);
            }
            else if(items.get(i).num == 1){
                parms.topMargin = 0;
                parms.bottomMargin = 0;
                parms.leftMargin = 0;
                parms.rightMargin = half_m;
                parms.width +=half_m;
                parms.height += full_m;
                immgg.setLayoutParams(parms);
            }
            else if(items.get(i).num == 2){
                parms.topMargin = 0;
                parms.bottomMargin = half_m;
                parms.leftMargin = 0;
                parms.rightMargin = 0;
                parms.width += full_m;
                parms.height += half_m;

                immgg.setLayoutParams(parms);
            }
            else if(items.get(i).num == 10){
                parms.topMargin = half_m;
                parms.bottomMargin = half_m;
                parms.leftMargin = 0;
                parms.rightMargin = 0;
                parms.width += full_m;

                immgg.setLayoutParams(parms);
            }
            else if(items.get(i).num == 5){
                parms.topMargin = 0;
                parms.bottomMargin = 0;
                parms.leftMargin = half_m;
                parms.rightMargin = half_m;
                parms.height += full_m;

                immgg.setLayoutParams(parms);
            }
            else if(items.get(i).num == 12){
                parms.topMargin = half_m;
                parms.bottomMargin = 0;
                parms.leftMargin = half_m;
                parms.rightMargin = 0;
                parms.width += half_m;
                parms.height += half_m;
                immgg.setLayoutParams(parms);
            }
            else if(items.get(i).num == 9){
                parms.topMargin = half_m;
                parms.bottomMargin = 0;
                parms.leftMargin = 0;
                parms.rightMargin = half_m;
                parms.width += half_m;
                parms.height += half_m;
                immgg.setLayoutParams(parms);
            }
            else if(items.get(i).num == 3){
                parms.topMargin = 0;
                parms.bottomMargin = half_m;
                parms.leftMargin = 0;
                parms.rightMargin = half_m;
                parms.width += half_m;
                parms.height += half_m;
                immgg.setLayoutParams(parms);
            }
            else if(items.get(i).num == 6){
                parms.topMargin = 0;
                parms.bottomMargin = half_m;
                parms.leftMargin = half_m;
                parms.rightMargin = 0;
                parms.width += half_m;
                parms.height += half_m;
                immgg.setLayoutParams(parms);
            }
            else if(items.get(i).num == 13){
                parms.topMargin = half_m;
                parms.bottomMargin = 0;
                parms.leftMargin = half_m;
                parms.rightMargin = half_m;
                parms.height += half_m;
                immgg.setLayoutParams(parms);
            }
            else if(items.get(i).num == 11){
                parms.topMargin = half_m;
                parms.bottomMargin = half_m;
                parms.leftMargin = 0;
                parms.rightMargin = half_m;
                parms.width += half_m;
                immgg.setLayoutParams(parms);
            }
            else if(items.get(i).num == 7){
                parms.topMargin = 0;
                parms.bottomMargin = half_m;
                parms.leftMargin = half_m;
                parms.rightMargin = half_m;
                parms.height += half_m;
                immgg.setLayoutParams(parms);
            }
            else if(items.get(i).num == 14){
                parms.topMargin = half_m;
                parms.bottomMargin = half_m;
                parms.leftMargin = half_m;
                parms.rightMargin = 0;
                parms.width += half_m;
                immgg.setLayoutParams(parms);
            }


        }


        return view;
    }
}
