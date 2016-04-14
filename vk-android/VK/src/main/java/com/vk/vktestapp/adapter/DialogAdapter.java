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

public class DialogAdapter extends BaseAdapter
{
	private ArrayList<String> users, messages;
	private Context context;
	private VKList<VKApiDialog> list;

	


	public DialogAdapter(Context context, ArrayList<String> users, ArrayList<String> messages){
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
		SetData setData = new SetData();
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.messages_item, null);

		setData.user = (TextView) view.findViewById(R.id.user);
		setData.msg = (TextView) view.findViewById(R.id.msg);

		setData.user.setText(users.get(position));
		setData.msg.setText(messages.get(position));


		return view;
	}

	public class SetData{
		TextView user, msg;
	}

}
