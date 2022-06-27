package com.example.app_cgd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class DrawerBase extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    @Override
    public void setContentView(View view) {

        drawerLayout = (DrawerLayout)getLayoutInflater().inflate(R.layout.activity_drawer_base, null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);

        super.setContentView(drawerLayout);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){

            case R.id.nav_deslogar:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Tela_Login.class));
                break;

            case R.id.nav_favoritos:
                startActivity(new Intent(getApplicationContext(), Tela_Catao.class));
                break;

            case R.id.nav_pdf:
                startActivity(new Intent(getApplicationContext(), Tela_Sintomas.class));
                break;

//            case R.id.nav_configuracao:
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(getApplicationContext(), Tela_Configuracao.class));
//                break;
        }
        finish();
        return false;
    }

    protected void allocate(String titleString){

        if(getSupportActionBar() !=null){

            getSupportActionBar().setTitle(titleString);
        }
    }
}