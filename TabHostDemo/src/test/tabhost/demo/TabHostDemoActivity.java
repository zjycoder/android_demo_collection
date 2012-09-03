package test.tabhost.demo;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

/**
 * 
 * TabHost���ַ�ʽ��һ�� �� �Լ�дxml�ļ�
 * 
 * 1. TabWidget��ID�̶���@android:id/tabs
 * 	  FrameLayout��ID�̶���@android:id/tabcontent
 * 
 * 
 * 2. ����̳���AcitivytGroup����Ϊsetup������ҪLocalActivityManager
 * 		�����׳��쳣
 * 		java.lang.IllegalStateException:
 * 		 Did you forget to call 'public void setup(LocalActivityManager activityGroup)'?
 * 
 * date: 2012-8-24
 * 
 */
public class TabHostDemoActivity extends ActivityGroup {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        
        tabHost.setup(getLocalActivityManager());
        
        TabSpec tabSpec1 = tabHost.newTabSpec("tab 1")
				.setIndicator("tab 1")
				.setContent(new Intent(this, ActivityOne.class));
        
        tabHost.addTab(tabSpec1);
        
        
        tabHost.addTab(tabHost.newTabSpec("tab 2")
        		.setIndicator("tab 2")
        		.setContent(new Intent(this, ActivityTwo.class)));
        
        
        tabHost.addTab(tabHost.newTabSpec("tab 3")
        		.setIndicator("tab 3")
        		.setContent(new Intent(this, ActivityThree.class)));
        
    }
	
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	
	
}