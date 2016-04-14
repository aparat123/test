package com.vk.vktestapp;
import android.app.*;
import android.os.*;
import java.util.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import com.vk.sdk.api.*;
import java.text.*;
import com.vk.vktestapp.adapter.*;

public class SendMessage extends Activity
{
ArrayList<String> inlist = new ArrayList<>();
ArrayList<String> outlist = new ArrayList<>();
int id = 0;
EditText text;
Button btn;
ListView listView;



	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog);
	
		inlist = getIntent().getStringArrayListExtra("in");
		outlist = getIntent().getStringArrayListExtra("out");
		id = getIntent().getIntExtra("id", 0);
		
		//Arrays.sort(inlist.toArray(),Collections.reverseOrder());
		//Arrays.sort(outlist.toArray(),Collections.reverseOrder());
		
		text = (EditText) findViewById(R.id.dialogEdit);
		btn = (Button) findViewById(R.id.dialogButton);
		listView = (ListView) findViewById(R.id.dialogList);
		listView.setAdapter(new DialogAdapter(this, inlist, outlist));
		btn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					VKRequest request = new VKRequest("messages.send", VKParameters.from(VKApiConst.USER_ID, id,
																						 VKApiConst.MESSAGE, text.getText().toString()));
					request.executeWithListener(new VKRequest.VKRequestListener(){
							@Override
							public void onComplete(VKResponse response){
								super.onComplete(response);
								Toast.makeText(SendMessage.this, "send is access", Toast.LENGTH_LONG).show();
							}
						});
				}

			
		});
		
		}
	
}
