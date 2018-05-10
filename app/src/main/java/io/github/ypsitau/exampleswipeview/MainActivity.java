package io.github.ypsitau.exampleswipeview;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

	static FragmentFactory[] fragmentFactories = new FragmentFactory[] {
		new PageAFragment.Factory(),
		new PageBFragment.Factory(),
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ViewPager viewPager = findViewById(R.id.viewPager);
		viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public Fragment getItem(int position) { return fragmentFactories[position].create(); }
			@Override
			public int getCount() { return fragmentFactories.length; }
		});
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				Util.Printf("onPageScrolled(%d, %.1f, %d)\n",
						position, positionOffset, positionOffsetPixels);
			}

			@Override
			public void onPageSelected(int position) {
				Util.Printf("onPageSelected(position=%d)\n", position);
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				Util.Printf("onPageScrollStateChanged(state=%d)\n", state);
			}
		});
		Util.init(this, (EditText)findViewById(R.id.editText_log), false);
	}

	public static abstract class FragmentFactory {
		abstract Fragment create();
	}
	public static class PageAFragment extends Fragment {
		public static class Factory extends FragmentFactory {
			@Override
			Fragment create() { return new PageAFragment(); }
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_page_a, container, false);
		}
	}

	public static class PageBFragment extends Fragment {
		public static class Factory extends FragmentFactory {
			@Override
			Fragment create() { return new PageBFragment(); }
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_page_b, container, false);
		}
	}

}
