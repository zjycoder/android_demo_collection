package test.tabhost.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ActivityOne extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_info);
		
		TextView output = (TextView) findViewById(R.id.output);
		
		output.setText("��һ��Activity");
		
		Log.e("aa", "�ڶ�����֧�����޸�");
		
	}
	

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
}
