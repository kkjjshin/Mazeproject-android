package edu.skku.cs.pa2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MazeActivity extends AppCompatActivity {

    int user_x =0;
    int user_y = 0;
    List<Map<String, Object>> list;
    Map<String, Object> map;
    int[][] map_maze;
    int num_maze;
    ArrayList<GridItem> items;
    GridViewAdapter mAdapter;
    String cango = "no";
    int dpi;
    int count = 0;
    int count_hint = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze);

        DisplayMetrics metrics = new DisplayMetrics();
        MazeActivity.this.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        dpi = metrics.densityDpi;


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://115.145.175.57:10099/maze/map").newBuilder();
        urlBuilder.addQueryParameter("name",name);

        String url = urlBuilder.build().toString();

        Request req = new Request.Builder().url(url).build();

        GridView gv_maze = findViewById(R.id.gv_maze);

        Button btn_left = findViewById(R.id.button2);
        Button btn_right = findViewById(R.id.button5);
        Button btn_up = findViewById(R.id.button3);
        Button btn_down = findViewById(R.id.button4);
        Button btn_hint = findViewById(R.id.button);
        TextView tv_count = findViewById(R.id.textView2);

        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String myResponse = response.body().string();
                // myResponse 파싱하고 배열에 담아준 후, 배열 돌면서 items.add로 꽂아줌.

                String[] mazz = myResponse.split("n");


                int num_maze = mazz.length - 2;

                int[][] map_maze = new int[num_maze][num_maze];
                
                //num_maze에 map 정보담김
                for(int i=0; i<num_maze; i++){
                    for(int j=0; j<num_maze; j++){
                        String[] ak = mazz[i+1].split(" ");
                        map_maze[i][j] = Integer.parseInt(ak[j]);
                    }
                }


                ArrayList<GridItem> items = new ArrayList<>();

                for(int i=0; i<num_maze; i++){
                    for(int j=0; j<num_maze; j++){
                        if(i==0 && j==0){
                            items.add(new GridItem(map_maze[i][j], num_maze, "left"));
                        }
                        else if(i==num_maze-1 && j==num_maze-1){
                            items.add(new GridItem(map_maze[i][j], num_maze, "finish"));
                        }
                        else{
                            items.add(new GridItem(map_maze[i][j], num_maze, cango));
                        }
                    }
                }


                GridViewAdapter mAdapter = new GridViewAdapter(getApplicationContext(), items, dpi);

                //MapTF 만들기

                list = new ArrayList<>();




                //1은 벽있음, 0은 벽없음
                for(int i=0; i<num_maze; i++){
                    for(int j=0; j<num_maze; j++) {
                        map = new HashMap<>();
                        if(map_maze[i][j] == 8){
                            map.put("top","1"); map.put("bottom", "0"); map.put("right","0"); map.put("left","0");
                        }
                        else if(map_maze[i][j]==4){
                            map.put("top","0"); map.put("bottom", "0"); map.put("right","0"); map.put("left","1");
                        }
                        else if(map_maze[i][j]==1){
                            map.put("top","0"); map.put("bottom", "0"); map.put("right","1"); map.put("left","0");
                        }
                        else if(map_maze[i][j]==2){
                            map.put("top","0"); map.put("bottom", "1"); map.put("right","0"); map.put("left","0");
                        }
                        else if(map_maze[i][j]==10){
                            map.put("top","1"); map.put("bottom", "1"); map.put("right","0"); map.put("left","0");
                        }
                        else if(map_maze[i][j]==5){
                            map.put("top","0"); map.put("bottom", "0"); map.put("right","1"); map.put("left","1");
                        }
                        else if(map_maze[i][j]==12){
                            map.put("top","1"); map.put("bottom", "0"); map.put("right","0"); map.put("left","1");
                        }
                        else if(map_maze[i][j]==9){
                            map.put("top","1"); map.put("bottom", "0"); map.put("right","1"); map.put("left","0");
                        }
                        else if(map_maze[i][j]==3){
                            map.put("top","0"); map.put("bottom", "1"); map.put("right","1"); map.put("left","0");
                        }
                        else if(map_maze[i][j]==6){
                            map.put("top","0"); map.put("bottom", "1"); map.put("right","0"); map.put("left","1");
                        }
                        else if(map_maze[i][j]==13){
                            map.put("top","1"); map.put("bottom", "0"); map.put("right","1"); map.put("left","1");
                        }
                        else if(map_maze[i][j]==11){
                            map.put("top","1"); map.put("bottom", "1"); map.put("right","1"); map.put("left","0");
                        }
                        else if(map_maze[i][j]==7){
                            map.put("top","0"); map.put("bottom", "1"); map.put("right","1"); map.put("left","1");
                        }
                        else if(map_maze[i][j]==14){
                            map.put("top","1"); map.put("bottom", "1"); map.put("right","0"); map.put("left","1");
                        }
                        list.add(map);
                    }
                }






                MazeActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        LayoutInflater infl = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View vv = infl.inflate(R.layout.grid_item, null);
                        
                        //left 클릭
                        btn_left.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                        int list_user = user_x*num_maze + user_y;
                        Map<String, Object> m = list.get(list_user);
                        if(m.get("left").equals("0")){
                            if(user_y!=0){
                                int list_user_left = list_user - 1;
                                Map<String, Object> m_left = list.get(list_user_left);
                                if(m_left.get("right").equals("0")){
                                    items.set(list_user, new GridItem(map_maze[user_x][user_y],num_maze,"no"));
                                    items.set(list_user_left, new GridItem(map_maze[user_x][user_y-1],num_maze,"left"));
                                    user_y-=1;
                                    count++;
                                    tv_count.setText("Turn : "+count);
                                    mAdapter.notifyDataSetChanged();
                                    gv_maze.setAdapter(mAdapter);

                                }
                            }
                        }

                                if(user_x == num_maze-1 && user_y == num_maze-1){ //finishtoast
                                    Toast.makeText(MazeActivity.this, "Finish!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        //right 클릭
                        btn_right.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                int list_user = user_x*num_maze + user_y;
                                Map<String, Object> m = list.get(list_user);
                                if(m.get("right").equals("0")){
                                    if(user_y!=num_maze-1){ //맨 끝이 아닐때
                                        int list_user_right = list_user + 1;
                                        Map<String, Object> m_right = list.get(list_user_right);
                                        if(m_right.get("left").equals("0")){
                                            items.set(list_user, new GridItem(map_maze[user_x][user_y],num_maze,"no"));
                                            items.set(list_user_right, new GridItem(map_maze[user_x][user_y+1],num_maze,"right"));
                                            user_y+=1;
                                            count++;
                                            tv_count.setText("Turn : "+count);
                                            mAdapter.notifyDataSetChanged();
                                            gv_maze.setAdapter(mAdapter);
                                        }
                                    }
                                }

                                if(user_x == num_maze-1 && user_y == num_maze-1){ //finishtoast
                                    Toast.makeText(MazeActivity.this, "Finish!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        //top 클릭
                        btn_up.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                int list_user = user_x*num_maze + user_y;
                                Map<String, Object> m = list.get(list_user);
                                if(m.get("top").equals("0")){
                                    if(user_x!=0){ //맨 끝이 아닐때
                                        int list_user_top = list_user - num_maze;
                                        Map<String, Object> m_top = list.get(list_user_top);
                                        if(m_top.get("bottom").equals("0")){
                                            items.set(list_user, new GridItem(map_maze[user_x][user_y],num_maze,"no"));
                                            items.set(list_user_top, new GridItem(map_maze[user_x-1][user_y],num_maze,"top"));
                                            user_x-=1;
                                            count++;
                                            tv_count.setText("Turn : "+count);
                                            mAdapter.notifyDataSetChanged();
                                            gv_maze.setAdapter(mAdapter);
                                        }
                                    }
                                }
                                if(user_x == num_maze-1 && user_y == num_maze-1){ //finishtoast
                                    Toast.makeText(MazeActivity.this, "Finish!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        //bottom클릭
                        btn_down.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int list_user = user_x*num_maze + user_y;
                                Map<String, Object> m = list.get(list_user);
                                if(m.get("bottom").equals("0")){
                                    if(user_x!=num_maze-1){ //맨 끝이 아닐때
                                        int list_user_bottom = list_user + num_maze;
                                        Map<String, Object> m_bottom = list.get(list_user_bottom);
                                        if(m_bottom.get("top").equals("0")){
                                            Log.d("whrereisitb2: ", "ok");
                                            items.set(list_user, new GridItem(map_maze[user_x][user_y],num_maze,"no"));
                                            items.set(list_user_bottom, new GridItem(map_maze[user_x+1][user_y],num_maze,"bottom"));
                                            user_x+=1;
                                            count++;
                                            tv_count.setText("Turn : "+count);
                                            mAdapter.notifyDataSetChanged();
                                            gv_maze.setAdapter(mAdapter);
                                        }
                                    }
                                }
                                if(user_x == num_maze-1 && user_y == num_maze-1){ //finishtoast
                                    Toast.makeText(MazeActivity.this, "Finish!", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });

                        //hint 클릭
                        btn_hint.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (count_hint == 1) {


                                    int visited[][] = new int[num_maze][num_maze]; //갔으면 1 안갔으면 0
                                    int nowX = user_x;
                                    int nowY = user_y;
                                    String nowPath = "";
                                    String path = "";

                                    LinkedList<Rcp> queue = new LinkedList<Rcp>();
                                    queue.add(new Rcp(nowX, nowY, nowPath));
                                    visited[user_x][user_y] = 1;


                                    while (!queue.isEmpty()) {

                                        Rcp rcp = (Rcp) queue.poll();
                                        nowX = rcp.row;
                                        nowY = rcp.col;
                                        nowPath = rcp.path;
                                        path = nowPath;

                                        int list_user = nowX * num_maze + nowY;
                                        Map<String, Object> m = list.get(list_user);


                                        // 목적지에 도착하면 루핑 종료
                                        if (nowX == num_maze - 1 && nowY == num_maze - 1) {
                                            break;
                                        }

                                        // 위로 갈수 있으면

                                        if (m.get("top").equals("0")) {
                                            if (nowX != 0) { //맨 끝이 아닐때
                                                int list_user_top = list_user - num_maze;
                                                Map<String, Object> m_top = list.get(list_user_top);
                                                if (m_top.get("bottom").equals("0")) {
                                                    if (visited[nowX - 1][nowY] != 1) {
                                                        visited[nowX - 1][nowY] = 1;
                                                        queue.add(new Rcp(nowX - 1, nowY, nowPath));
                                                    }
                                                }
                                            }
                                        }

                                        // 아래로 갈수 있으면

                                        if (m.get("bottom").equals("0")) {
                                            if (nowX != num_maze - 1) { //맨 끝이 아닐때
                                                int list_user_bottom = list_user + num_maze;
                                                Map<String, Object> m_bottom = list.get(list_user_bottom);
                                                if (m_bottom.get("top").equals("0")) {
                                                    if (visited[nowX + 1][nowY] != 1) {
                                                        visited[nowX + 1][nowY] = 1;
                                                        queue.add(new Rcp(nowX + 1, nowY, nowPath));
                                                    }
                                                }
                                            }
                                        }

                                        // 왼쪽으로 갈수 있으면

                                        if (m.get("left").equals("0")) {
                                            if (nowY != 0) {
                                                int list_user_left = list_user - 1;
                                                Map<String, Object> m_left = list.get(list_user_left);
                                                if (m_left.get("right").equals("0")) {
                                                    if (visited[nowX][nowY - 1] != 1) {
                                                        visited[nowX][nowY - 1] = 1;
                                                        queue.add(new Rcp(nowX, nowY - 1, nowPath));
                                                    }
                                                }
                                            }
                                        }

                                        // 오른쪽으로 갈수 있으면

                                        if (m.get("right").equals("0")) {
                                            if (nowY != num_maze - 1) { //맨 끝이 아닐때
                                                int list_user_right = list_user + 1;
                                                Map<String, Object> m_right = list.get(list_user_right);
                                                if (m_right.get("left").equals("0")) {
                                                    if (visited[nowX][nowY + 1] != 1) {
                                                        visited[nowX][nowY + 1] = 1;
                                                        queue.add(new Rcp(nowX, nowY + 1, nowPath));
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    //힌트 위치 파싱
                                    String[] patharr = path.split(">");
                                    String[] patharr2 = patharr[1].split(",");
                                    int hint_x = Integer.parseInt(patharr2[0]);
                                    int hint_y = Integer.parseInt(patharr2[1]);

                                    //힌트 소모
                                    count_hint -= 1;

                                    if(hint_x==num_maze-1 && hint_y==num_maze-1) {
                                    }
                                    else{
                                        items.set(hint_x * num_maze + hint_y, new GridItem(map_maze[hint_x][hint_y], num_maze, "hint"));
                                        mAdapter.notifyDataSetChanged();
                                        gv_maze.setAdapter(mAdapter);
                                    }


                                }
                            }

                        });


                        gv_maze.setNumColumns(num_maze);
                        gv_maze.setAdapter(mAdapter);


                    }
                });
            }
        });

    }


}