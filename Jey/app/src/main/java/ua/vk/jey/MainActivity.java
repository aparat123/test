package ua.vk.jey;

import android.app.*;
import android.os.*;
import android.content.*;
import ua.vk.jey.api.*;
import android.widget.*;
import java.io.*;
import ua.vk.jey.models.FriendsModel;

import java.util.*;
import android.support.v7.widget.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity 
{
	
	private final int REQUEST_LOGIN=1;

	RecyclerView recyclerView;
    List<FriendsModel> posts;
    Account account=new Account();
    
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        posts = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        PostsAdapter adapter = new PostsAdapter(posts);
        recyclerView.setAdapter(adapter);

        try {
            Response response = App.getApi().getData("bash","qqq", 123).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        App.getApi().getData("bash", "44", 50).enqueue(new Callback<List<FriendsModel>>() {
				@Override
				public void onResponse(Call<List<FriendsModel>> call, Response<List<FriendsModel>> response) {
					posts.addAll(response.body());
					recyclerView.getAdapter().notifyDataSetChanged();
				}

				@Override
				public void onFailure(Call<List<FriendsModel>> call, Throwable t) {
					Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
				}
			});
    
		
        //Если сессия есть создаём API для обращения к серверу
        if(account.access_token!=null){
			Toast.makeText(getApplicationContext(), "restore access", Toast.LENGTH_LONG).show();
			
		}
            else{
				startLoginActivity();
			}
		
		}
	private void startLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {
                //авторизовались успешно 
                account.access_token=data.getStringExtra("token");
                account.user_id=data.getLongExtra("user_id", 0);
                account.save(MainActivity.this);
                Toast.makeText(getApplicationContext(), "auth access", Toast.LENGTH_LONG).show();
				
            }
        }
    }
    
}
