package in.dsij.smg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private CompoundButton notificationSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NavigationView nav = (NavigationView) findViewById(R.id.nav_view);
        MenuItem switchItem;
        switchItem = nav.getMenu().findItem(R.id.drawer_nav_notifications);
        notificationSwitch = (CompoundButton) MenuItemCompat.getActionView(switchItem);
        notificationSwitch.setChecked(true);

        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveNotificationPreference(isChecked);
            }
        });
    }

    private void saveNotificationPreference(boolean enabled) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.enable_notifications), enabled);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        boolean closeDrawer = true;

        if (id == R.id.drawer_nav_notifications) {

            notificationSwitch.setChecked(!notificationSwitch.isChecked());
            closeDrawer = false;

        } else if (id == R.id.drawer_nav_terms) {
            Intent intent=new Intent(this,SplashScreen.class);
            startActivity(intent);
           // showTerms();
        } else if (id == R.id.drawer_nav_privacy) {
           // showPrivacy();
        } else if (id == R.id.drawer_nav_rate_us) {
          /*  RatingBottomSheetDialogFragment dialogFragment = RatingBottomSheetDialogFragment.newInstance();
            dialogFragment.show(getSupportFragmentManager(), DIAG_RATE);*/

        } else if (id == R.id.drawer_nav_about_us) {
           // showAbout();
        } else if (id == R.id.drawer_nav_contact_us) {
           // showContactUs();
        } else if (id == R.id.drawer_nav_logout) {
           // makeLogoutCall();
        }  else if (id == R.id.drawer_nav_report_issue) {
         ///   reportApp();
        } else if (id == R.id.nav_change_password) {
         //   showBottomNavigation(false);
         //   setDrawerEnabled(false);
          //  setFragment(ChangePassword.newInstance());
        } else if (id == R.id.drawer_nav_disclamer) {
           // setFragment(WebViewFragment.newInstance(G.net.URL_DISCLAIMER, "Disclaimer"));
        }

        if (closeDrawer) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
        return false;
    }
}
