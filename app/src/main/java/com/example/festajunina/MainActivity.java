package com.example.festajunina;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MainActivity extends AppCompatActivity implements FragmentNavigation {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_collapsing);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getString(R.string.app_name));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_cadastrar) {
                navigateToMainFragment();
            } else if (id == R.id.nav_listar) {
                navigateToListarBrincadeiras();
            } else if (id == R.id.nav_editar) {
                navigateToEditarBrincadeiras();
            } else if (id == R.id.nav_excluir) {
                navigateToExcluirBrincadeiras();
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        if (savedInstanceState == null) {
            navigateToListarBrincadeiras();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateToMainFragment() {
        replaceFragment(new MainFragment());
    }

    @Override
    public void navigateToListarBrincadeiras() {
        replaceFragment(new ListarBrincadeirasFragment());
    }

    public void navigateToEditarBrincadeiras() {
        replaceFragment(new EditarBrincadeirasFragment());
    }

    public void navigateToExcluirBrincadeiras() {
        replaceFragment(new ExcluirBrincadeirasFragment());
    }

    @Override
    public void navigateToEditarBrincadeira(Brincadeira brincadeira) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putParcelable("brincadeira", brincadeira);
        fragment.setArguments(args);
        replaceFragment(fragment);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
