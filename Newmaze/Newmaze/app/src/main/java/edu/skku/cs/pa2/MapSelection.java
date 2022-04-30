package edu.skku.cs.pa2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MapSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_selection);

        ListView lv_maze = findViewById(R.id.lv_maze);
        TextView tv_nowuser = findViewById(R.id.tv_nowuser);

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://115.145.175.57:10099/maps").newBuilder();

        String url = urlBuilder.build().toString();

        Request req = new Request.Builder().url(url).build();

        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String myResponse = response.body().string();

               // Gson gson = new GsonBuilder().create();
                JsonParser jsonParser = new JsonParser();

                JsonArray jsonArray = (JsonArray) jsonParser.parse(myResponse);
                ArrayList<MazeItem> items = new ArrayList<>();
                for(int i=0; i<jsonArray.size(); i++){
                    JsonObject object = (JsonObject) jsonArray.get(i);
                    String mzName = object.get("name").getAsString();
                    String mzSize = object.get("size").getAsString();
                    items.add(new MazeItem(mzName, mzSize));
                }

                MyListViewAdapter mAdapter = new MyListViewAdapter(getApplicationContext(), items);



                MapSelection.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = getIntent();
                        String user = intent.getStringExtra("User");
                        tv_nowuser.setText(user);
                        lv_maze.setAdapter(mAdapter);

                    }
                });

            }
        });

    }
}