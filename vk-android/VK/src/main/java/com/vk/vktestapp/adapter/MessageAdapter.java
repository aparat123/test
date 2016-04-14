package com.vk.vktestapp.adapter;
import android.content.*;
import android.view.*;
import android.widget.*;
import com.vk.sdk.api.model.*;
import java.util.*;
import java.util.zip.*;
import com.vk.vktestapp.*;
import android.view.View.*;
import com.vk.sdk.api.*;
import org.json.*;
import android.graphics.*;
import com.squareup.picasso.*;

public class MessageAdapter extends BaseAdapter
{
private ArrayList<String> users, messages;
Picasso picasso;
private Context context;
	private VKList<VKApiDialog> list;
	
	public MessageAdapter(Context context, ArrayList<String> users, ArrayList<String> messages,  VKList<VKApiDialog> list){
		this.users=users;
		this.messages=messages;
		this.context=context;
		this.list=list;
	}
	

	public MessageAdapter(Context context, ArrayList<String> users, ArrayList<String> messages){
		this.users=users;
		this.messages=messages;
		this.context=context;
		
	}
	
	@Override
	public int getCount()
	{
		// TODO: Implement this method
		return users.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO: Implement this method
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO: Implement this method
		return position;
	}

	@Override
	public View getView(final int position, View p2, ViewGroup p3)
	{
		final SetData setData = new SetData();
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.messages_item, null);
		
		setData.user = (TextView) view.findViewById(R.id.user);
		setData.msg = (TextView) view.findViewById(R.id.msg);
		setData.photo = (ImageView) view.findViewById(R.id.photo);
		
		VKRequest req = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "id, first_name, last_name, photo_50", VKApiConst.USER_ID, users.get(position)));
		req.executeWithListener(new VKRequest.VKRequestListener() {
				@Override
				public void onComplete(VKResponse response) {
					final VKApiUser us = ((VKList<VKApiUserFull>)response.parsedModel).get(0);
					
					setData.user.setText(us.first_name + " " + us.last_name);
					picasso
						.with(context)
					.load(us.photo_50)
						.into(setData.photo);
				}
			});
	

		
		setData.msg.setText(messages.get(position));
		
		
		view.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				final ArrayList<String> inlist = new ArrayList<>();
				final ArrayList<String> outlist = new ArrayList<>();
				final int id = list.get(position).message.user_id;
				System.out.println("click");
				VKRequest request = new VKRequest("messages.getHistory", VKParameters.from(VKApiConst.USER_ID, id));
				request.executeWithListener(new VKRequest.VKRequestListener(){
					@Override
					public void onComplete(VKResponse response){
					super.onComplete(response);
						
						try
						{
							JSONArray array = response.json.getJSONObject("response").getJSONArray("items");
							VKApiMessage [] msg = new VKApiMessage[array.length()];
							for(int i = 0; i < array.length(); i++) {
								VKApiMessage mes = new VKApiMessage(array.getJSONObject(i));
								msg[i] = mes;
							}
						for(VKApiMessage mess : msg){
							if(mess.out){
								outlist.add(mess.body);
							}
							else{
								inlist.add(mess.body);
							}
						}
						MessageAdapter.this.context.startActivity(new Intent(context, SendMessage.class).putExtra("id",id).putExtra("out", outlist).putExtra("in", inlist));
						
							}
							
						catch (JSONException e)
						{
							e.printStackTrace();
						}
							
					}
					});
				
				
				
			}
		});
		
		return view;
	}

	public class SetData{
		TextView user, msg;
		ImageView photo;
	}
	
}
