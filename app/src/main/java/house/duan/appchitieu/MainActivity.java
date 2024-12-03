package house.duan.appchitieu;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;

import house.duan.appchitieu.fragment.AboutFragment;
import house.duan.appchitieu.fragment.DoimatkhauFragment;
import house.duan.appchitieu.fragment.Homefragment;
import house.duan.appchitieu.fragment.Profilefragment;
import house.duan.appchitieu.fragment.Searchfragment;
import house.duan.appchitieu.fragment.SettingsFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Map<Integer, Fragment> fragmentMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set up the Toolbar as ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);  // Set the toolbar as the ActionBar
        // Initialize DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);
        // Set up the ActionBar toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // Initialize Fragment Map
        initializeFragmentMap();
        // Set up BottomNavigationView
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = fragmentMap.get(item.getItemId());
            if (fragment != null) {
                loadFragment(fragment);
            }
            return true;
        });
        // Set up Navigation Drawer View
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Load default fragment
        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.nav_home);
        }

    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit();
    }

    private void initializeFragmentMap() {
        fragmentMap.put(R.id.nav_home, new Homefragment());
        fragmentMap.put(R.id.nav_search, new Searchfragment());
        fragmentMap.put(R.id.nav_profile, new Profilefragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_home) {
            loadFragment(new Homefragment());
        }
        else if(item.getItemId() == R.id.nav_bieudo){
            loadFragment(new Profilefragment());
        }
        else if(item.getItemId() == R.id.nav_settings){
            loadFragment(new SettingsFragment());
        }
        else if(item.getItemId() == R.id.nav_settings){
            loadFragment(new SettingsFragment());
        }
        else if(item.getItemId() == R.id.nav_doimatkhau){
            loadFragment(new DoimatkhauFragment());
        }
        else if (item.getItemId() == R.id.nav_dangxuat) {
          //  loadFragment(new AboutFragment());
            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear(); // Xóa tất cả dữ liệu người dùng
            editor.apply();
            // Chuyển đến màn hình đăng nhập
            Intent intent = new Intent(this, dangnhap.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Kết thúc Activity hiện tại
            return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //
}
