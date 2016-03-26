package com.vk.vktestapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKBatchRequest;
import com.vk.sdk.api.VKBatchRequest.VKBatchRequestListener;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKRequest.VKRequestListener;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiCaptcha;
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKAttachments;
import com.vk.sdk.api.model.VKPhotoArray;
import com.vk.sdk.api.model.VKWallPostResult;
import com.vk.sdk.api.photo.VKImageParameters;
import com.vk.sdk.api.photo.VKUploadImage;
import com.vk.sdk.dialogs.VKShareDialog;
import com.vk.sdk.dialogs.VKShareDialogBuilder;
import com.vk.sdk.payments.VKPaymentsCallback;

import org.json.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.vk.sdk.*;
import android.widget.*;
import com.vk.sdk.api.model.*;

public class TestActivity extends ActionBarActivity {

	private static final int[] IDS = {R.id.users_get, R.id.friends_get, R.id.messages_get, R.id.dialogs_get,
			R.id.captcha_force, R.id.upload_photo, R.id.wall_post, R.id.wall_getById, R.id.test_validation,
			R.id.test_share, R.id.upload_photo_to_wall, R.id.upload_doc, R.id.upload_several_photos_to_wall,
			R.id.test_send_request};

	public static final int TARGET_GROUP = 60479154;
	public static final int TARGET_ALBUM = 181808365;

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		listView = (ListView) findViewById(R.id.listView);

		VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "first_name, last_name"));
		request.executeWithListener(new VKRequest.VKRequestListener() {
				@Override
				public void onComplete(VKResponse response){
					super.onComplete(response);

					VKList list = (VKList) response.parsedModel;
					ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TestActivity.this,
																				 android.R.layout.simple_list_item_1, list);

					listView.setAdapter(arrayAdapter);

				}
			});
		Toast.makeText(getApplicationContext(), "Good", Toast.LENGTH_LONG).show();
		}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	
	
		

	}
