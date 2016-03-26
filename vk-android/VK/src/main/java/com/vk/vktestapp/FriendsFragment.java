package com.vk.vktestapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.vk.sdk.api.*;
import com.vk.sdk.api.model.*;
import android.support.v4.app.*;


public class FriendsFragment extends ListFragment
{

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
		//listView = (ListView) getView().findViewById(R.id.listViewfrd);

		VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "first_name, last_name"));
		request.executeWithListener(new VKRequest.VKRequestListener() {
				@Override
				public void onComplete(VKResponse response){
					super.onComplete(response);

					VKList list = (VKList) response.parsedModel;
					ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
																				 android.R.layout.simple_list_item_1, list);
					setListAdapter(arrayAdapter);

				

				}
			});
		Toast.makeText(getContext(), "Good", Toast.LENGTH_LONG).show();
	}
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.friends_fragment, container, false);
		
		
		
		
		
		
		
    }

}
