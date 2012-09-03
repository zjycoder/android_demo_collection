package test.tabhost.demo;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

class DynamicTabHost extends TabHost {

	public DynamicTabHost(Context context) {
		super(context);

		TabWidget tabs = new TabWidget(super.getContext());
		tabs.setId(android.R.id.tabs);
		addView(tabs, new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, Gravity.TOP));

		FrameLayout frame = new FrameLayout(super.getContext());
		frame.setId(android.R.id.tabcontent);
		FrameLayout.LayoutParams params = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT,
				Gravity.BOTTOM);
		params.setMargins(0, 65, 0, 0);
		addView(frame, params);

		super.setup();
	}

	public void addTab(View view, String tabTitle) {
		TabSpec tabSpec = super.newTabSpec(tabTitle);
		// tabSpec.setContent(new PreExistingViewFactory(view));
		tabSpec.setContent(new Intent());
		tabSpec.setIndicator(tabTitle);

		super.addTab(tabSpec);
	}

	public void removeTab(View view) {
		// determine where tab is to get rid of
		ViewGroup content = getTabContentView();
		int nukedIdx = content.indexOfChild(view);
		if (nukedIdx == -1)
			return;

		// prepare to get the remaining tab instances prior to clearAllTabs()
		int nTabs = getTabWidget().getTabCount();
		View[] remainingViews = null;
		String[] remainingTitles = null;

		if (nTabs > 1) {
			remainingViews = new View[nTabs - 1];
			remainingTitles = new String[nTabs - 1];
			for (int i = 0, c = 0; i < nTabs; i++) {
				if (i != nukedIdx) {
					remainingViews[c] = content.getChildAt(i);
					remainingTitles[c++] = getTabTitle(i);
				}
			}
		}
		// only way to get state correct
		clearAllTabs();

		// add back any remaining
		for (int i = 0; i < nTabs - 1; i++) {
			addTab(remainingViews[i], remainingTitles[i]);
		}
	}

	private String getTabTitle(int index) {
		RelativeLayout tab = (RelativeLayout) super.getTabWidget().getChildAt(
				index);
		TextView text = (TextView) tab.getChildAt(1);
		return text.getText().toString();
	}

	// needed to fix Bug in clearAllTabs; TabWidget corrupt
	@Override
	public void clearAllTabs() {
		// TabWidget corrupt when first not current
		super.setCurrentTab(0);
		super.clearAllTabs();
	}
}