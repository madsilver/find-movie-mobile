package br.com.silver.findmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.silver.findmovie.model.Movie;

public class MainActivity extends AppCompatActivity implements
        SearchView.OnQueryTextListener,
        MenuItemCompat.OnActionExpandListener,
        TitleMovieFragment.OnClickTitleMovie{

    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private int mOptionSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.app_name,
                R.string.app_name
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        selectOptionMenu(item);
                        return true;
                    }
                }
        );

        if(savedInstanceState == null){
            mOptionSelected = R.id.action_home;
        } else {
            mOptionSelected = savedInstanceState.getInt("menuItem");
        }
        selectOptionMenu(mNavigationView.getMenu().findItem(mOptionSelected));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_research, menu);

        MenuItem searchItem = menu.findItem(R.id.action_research);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("");
        MenuItemCompat.setOnActionExpandListener(searchItem, this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectOptionMenu(MenuItem menuItem){
        if(menuItem == null)
            return;
        mOptionSelected = menuItem.getItemId();
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();

        String title = menuItem.getTitle().toString();

        FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentByTag(title) == null){
            Fragment fragment = new HomeFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, fragment, title)
                    .commit();

        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.length() > 3){
            onSetTitleMovie(newText);
        }
        return false;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        HomeFragment fragment = (HomeFragment) getSupportFragmentManager()
                .findFragmentByTag(HomeFragment.TAG);
        if(fragment == null){
            fragment = new HomeFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, fragment, HomeFragment.TAG)
                    .commit();
        }
        return true;
    }

    public void onSetTitleMovie(String title) {
        TitleMovieFragment fragment = (TitleMovieFragment) getSupportFragmentManager()
                .findFragmentByTag(TitleMovieFragment.TAG);
        if(fragment != null){
            fragment.search(title);
        } else {
            fragment = new TitleMovieFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, fragment, TitleMovieFragment.TAG)
                    .commit();
            //ensure that the transaction is complete
            getSupportFragmentManager().executePendingTransactions();
            fragment.search(title);
        }
    }

    @Override
    public void clickTitleMovie(Movie movie) {
/*        SinopseFragment fragment = (SinopseFragment) getSupportFragmentManager()
                .findFragmentByTag(SinopseFragment.TAG);
        if(fragment == null){
            fragment = new SinopseFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, fragment, SinopseFragment.TAG)
                    .commit();
        }*/

        Intent intent = new Intent(MainActivity.this, SinopseActivity.class);
        intent.putExtra("movie_id", movie.imdbID);
        startActivity(intent);

    }
}
