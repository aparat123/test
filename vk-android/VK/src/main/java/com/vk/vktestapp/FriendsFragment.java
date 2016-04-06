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
import com.vk.vktestapp.adapter.*;
import java.util.*;
import com.vk.sdk.api.methods.*;


public class FriendsFragment extends ListFragment
{
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
		VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "id, first_name, last_name, photo_50"));
		request.executeWithListener(new VKRequest.VKRequestListener() {

				private FriendsAdapter FriendsAdapter;

				@Override
				public void onComplete(VKResponse response){
					super.onComplete(response);

					VKList list = (VKList) response.parsedModel;
					
					FriendsAdapter = new FriendsAdapter(getActivity(), list);
					setListAdapter(FriendsAdapter);
					
					

				}
			});
		
		
	}
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.friends_fragment, container, false);
		
		
    }
	
}
