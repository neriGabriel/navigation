package com.example.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /* SHAREDPREFERENCES
    * O NAVIGATEUP VOLTA UM ESTADO NA STACK. O BOTÃO BACK CORTA O FLUXO
    * Não esquecer de adicionar o componente navigation via ui, android resources -> opção navigation
    * adicionar as view e etc.
    * */

    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        * Setando a toolbar/icone de retorno
        * Procuro o controller e a fragment principal e seto na navigationui
        * */
        this.navController = Navigation.findNavController(this, R.id.nav_controller_fragment);
        NavigationUI.setupActionBarWithNavController(this, this.navController);
    }

    /*
    * Necessário ter
    * */
    @Override
    public boolean onSupportNavigateUp() {
        /*
        * Retorna o navigationui
        * */
        return NavigationUI.navigateUp(this.navController, (DrawerLayout) null);
    }
}
