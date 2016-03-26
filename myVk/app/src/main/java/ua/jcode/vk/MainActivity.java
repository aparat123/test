package ua.jcode.vk;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

	public class MainActivity extends AppCompatActivity {

		private Toolbar toolbar;
		private TabLayout tabLayout;
		private ViewPager viewPager;
		private int[] tabIcons = {
            R.drawable.ic_news,
            R.drawable.ic_friends,
            R.drawable.ic_mess,
			R.drawable.ic_video,
			R.drawable.ic_audio
		};

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);

			toolbar = (Toolbar) findViewById(R.id.toolbar);
			setSupportActionBar(toolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);

			viewPager = (ViewPager) findViewById(R.id.viewpager);
			setupViewPager(viewPager);

			tabLayout = (TabLayout) findViewById(R.id.tabs);
			tabLayout.setupWithViewPager(viewPager);
			setupTabIcons();
		}

		private void setupTabIcons() {
			tabLayout.getTabAt(0).setIcon(tabIcons[0]);
			tabLayout.getTabAt(1).setIcon(tabIcons[1]);
			tabLayout.getTabAt(2).setIcon(tabIcons[2]);
			tabLayout.getTabAt(3).setIcon(tabIcons[3]);
			tabLayout.getTabAt(4).setIcon(tabIcons[4]);
		}

		private void setupViewPager(ViewPager viewPager) {
			ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
			adapter.addFrag(new Newsfragment(), "news");
			adapter.addFrag(new FriendsFragment(), "friends");
			adapter.addFrag(new MessFragment(), "mess");
			adapter.addFrag(new VideoFragment(), "Video");
			adapter.addFrag(new AudioFragment(), "audio");
			viewPager.setAdapter(adapter);
		}

		class ViewPagerAdapter extends FragmentPagerAdapter {
			private final List<Fragment> mFragmentList = new ArrayList<>();
			private final List<String> mFragmentTitleList = new ArrayList<>();

			public ViewPagerAdapter(FragmentManager manager) {
				super(manager);
			}

			@Override
			public Fragment getItem(int position) {
				return mFragmentList.get(position);
			}

			@Override
			public int getCount() {
				return mFragmentList.size();
			}

			public void addFrag(Fragment fragment, String title) {
				mFragmentList.add(fragment);
				mFragmentTitleList.add(title);
			}

        @Override
		public CharSequence getPageTitle(int position) {
			return null;
		}
    }
}
