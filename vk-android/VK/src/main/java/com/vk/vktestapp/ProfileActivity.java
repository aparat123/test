package com.vk.vktestapp;
import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;
import android.os.*;
import com.vk.sdk.api.model.*;
import com.vk.sdk.api.*;
import android.widget.*;
import android.graphics.*;
import com.squareup.picasso.*;
import android.util.*;
import android.content.*;
import java.util.concurrent.*;

public class ProfileActivity extends AppCompatActivity {

	Toolbar toolbar;
	
	Picasso picasso;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		
		
		Bundle extras = getIntent().getExtras();
		final int user_id = extras.getInt("user_id");
		
		
		
		
		
		
		
		
		//Toast.makeText(this, user_id, Toast.LENGTH_LONG).show();

		
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		final TextView ful_name = (TextView) findViewById(R.id.ful_name);
		final TextView city = (TextView) findViewById(R.id.city);
		final ImageView phots = (ImageView) findViewById(R.id.phot);
		
		VKRequest req = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "id, first_name, last_name, photo_50, photo_200, city", VKApiConst.USER_ID, user_id));
		req.executeWithListener(new VKRequest.VKRequestListener() {
				@Override
				public void onComplete(VKResponse response) {
					final VKApiUserFull user = ((VKList<VKApiUserFull>)response.parsedModel).get(0);
					ful_name.setText(user.toString());
					
						 String url = user.photo_200;
					Picasso
						.with(ProfileActivity.this)
						.load(url)
						.into(phots);
				}
				});
	}
	
	
	
	
	
	}
