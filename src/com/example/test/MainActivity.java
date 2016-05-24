package com.example.test;

import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView.ScaleType;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	
	private MyGallery gallery = null;
	private ArrayList<Integer> imgList;
	private ArrayList<ImageView> img;
	//存储上一次选择项的Index
	private int preSelImgIndex = 0;
	private LinearLayout layout_focus_indicator = null;
	private String[] imageUrls = {"http://www.baidu.com",
			"http://www.eoeandroid.com",
			"http://blog.sina.com.cn"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		layout_focus_indicator = (LinearLayout) findViewById(R.id.layout_focus_indicator);
		imgList = new ArrayList<Integer>();
		imgList.add(R.drawable.img1);
		imgList.add(R.drawable.img2);
		imgList.add(R.drawable.img3);
		InitFocus();
		gallery = (MyGallery) findViewById(R.id.gallery);
		ImageAdapter adapter = new ImageAdapter(MainActivity.this, imgList);
		gallery.setAdapter(adapter);
		gallery.setFocusable(true);
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				position = position % imgList.size();
				//修改上一次选中项的背景
				img.get(preSelImgIndex).setImageResource(R.drawable.ic_focus);
				//修改当前选中项的背景
				img.get(position).setImageResource(R.drawable.ic_focus_select);
				preSelImgIndex = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
		
		gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				position = position % imgList.size();
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(imageUrls[position]));
				startActivity(intent);
			}
		});
	}

	private void InitFocus() {
		img = new ArrayList<ImageView>();
		for (int i = 0; i < imgList.size(); i++) {
			ImageView localImage = new ImageView(MainActivity.this);
			localImage.setId(i);
			localImage.setScaleType(ScaleType.FIT_XY);
			localImage.setLayoutParams(new LinearLayout.LayoutParams(24, 24));
			localImage.setPadding(5, 5, 5, 5);
			localImage.setImageResource(R.drawable.ic_focus);
			img.add(localImage);
			this.layout_focus_indicator.addView(localImage);
		}
	}


}
