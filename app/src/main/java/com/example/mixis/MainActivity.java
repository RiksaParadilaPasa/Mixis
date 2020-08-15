package com.example.mixis;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.legacy.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mixis.api.BaseApiService;
import com.example.mixis.api.UtilsApi;
import com.example.mixis.home_fragment.Category;
import com.example.mixis.home_fragment.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Context context;

    private final String[] PAGE_TITLES = new String[] {
            "Beranda", "Kategori" };
    private final Fragment[] PAGES = new Fragment[] {
            new Product(), new Category() };

    public static ViewPager viewPager;
    public static TabLayout tabLayout;

    MaterialSearchView searchView;

    NavigationView navigationView;
    CircleImageView header_imgProfile;
    TextView header_txtUsername;

    BaseApiService mApiService;

    private String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        context = this;
        mApiService = UtilsApi.getAPIService();

        this.token = Login.token;

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        header_imgProfile = (CircleImageView) hView.findViewById(R.id.header_imgProfile);
        header_txtUsername = (TextView) hView.findViewById(R.id.header_txtUsername);
        header_imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Profile.class));
            }
        });
        header_txtUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Profile.class));
            }
        });


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MainActivity.MyPagerAdapter(getFragmentManager()));

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                Snackbar.make(viewPager, query, Snackbar.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Upload.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cart, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            startActivity(new Intent(context, Cart.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notif) {
            // Handle the camera action
            startActivity(new Intent(context, Notif.class));
        } else if (id == R.id.nav_trans) {
            startActivity(new Intent(context, Trans.class));
        } else if (id == R.id.nav_like) {
            Snackbar.make(viewPager, "Favorit", Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(context, Profile.class));
        } else if (id == R.id.nav_store) {
            startActivity(new Intent(context, Store.class));
        } else if (id == R.id.nav_help) {
            startActivity(new Intent(context, Help.class));
        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(context, Login.class));
        } else if (id == R.id.nav_mixi) {
            startActivity(new Intent(context, Mixi.class));
        } else if (id == R.id.nav_agenda) {
            startActivity(new Intent(context, Agenda.class));
        } else if (id == R.id.nav_visimisi) {
            startActivity(new Intent(context, VisiMisi.class));
        } else if (id == R.id.nav_tentangkami) {
            startActivity(new Intent(context, TentangKami.class));
        } else if (id == R.id.nav_point) {
            startActivity(new Intent(context, TukarPoint.class));
        } else if (id == R.id.nav_artikel) {
            Intent intent = new Intent(context, Artikel.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return PAGES[position];
        }

        @Override
        public int getCount() {
            return PAGES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return PAGE_TITLES[position];
        }

    }

}
