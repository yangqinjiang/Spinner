package com.itheima.spinner;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	private List<String> numberList;
	private EditText et_number;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		
	}

	private void initView() {
		et_number = (EditText)findViewById(R.id.et_number);
		findViewById(R.id.ib_down_arrow).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		showNumberDialog();
	}
	/**
	 * 弹出号码选择框
	 */
	private void showNumberDialog(){
	
		prepareData();
		ListView lv = new ListView(this);
		lv.setBackgroundResource(R.drawable.listview_background);
		lv.setAdapter(new NumbersAdapter());
		//
		PopupWindow popup = new PopupWindow(lv,et_number.getWidth() ,200);
		//组合使用
		popup.setOutsideTouchable(true);
		popup.setBackgroundDrawable(new BitmapDrawable());
		popup.showAsDropDown(et_number,0,-5);
	}
	/**
	 * 准备数据
	 */
	private void prepareData(){
		numberList = new ArrayList<String>();
		for(int i=0;i<20;i++){
			numberList.add("1000"+i);
		}
	}
	class NumbersAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return numberList.size();
		}

		@Override
		public Object getItem(int position) {
			return numberList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder mHolder=null;
			if(convertView==null){
				convertView=View.inflate(MainActivity.this, R.layout.listview_item, null);
				
				mHolder = new ViewHolder();
				mHolder.tv_number=(TextView)convertView.findViewById(R.id.tv_listview_item_number);
				mHolder.ib_delete=(ImageButton)convertView.findViewById(R.id.ib_listview_item_delete);
				convertView.setTag(mHolder);
			}else{
				mHolder=(ViewHolder)convertView.getTag();
			}
			mHolder.tv_number.setText(numberList.get(position));
			
			return convertView;
		}
		
	}
	class ViewHolder{
		TextView tv_number;
		ImageButton ib_delete;
	}
}
