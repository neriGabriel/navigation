package com.example.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    //Ver sobre preference fragment android
     //Primeiramente para usar o navigation é necessário fazer o import das bibliotecas nos build.gradles
     /*
     No build.gradle (Project)(Dependencies):
     def nav_version = "2.3.0-beta01"
     classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
     */
     /*
     No build.gradle (Module)(Dependencies):
       def nav_version = "2.3.0-beta01"
       implementation "androidx.navigation:navigation-fragment:$nav_version"
       implementation "androidx.navigation:navigation-ui:$nav_version"
    */

     //Faço a referencia do botão que vai "triggar" a navigation
    Button btnNavigate;

    //Faço referencia ao sharedpreferences
    SharedPreferences sharedPreferences;
    String sharedPrefFileName = "sharedfile";

    //Somente para ficar melhor a obtenção do sharedprefences
    Activity activity;

    public static final String  EDIT_TEXT_KEY = "edit_text_key";
    public static final String  SEEK_BAR_KEY = "seek_bar_key";
    public static final String  SWITCH_CONFIGURACAO_FIRST_KEY = "switch_configuracao_first";
    public static final String  SWITCH_CONFIGURACAO_SECOND_KEY = "switch_configuracao_second";


    EditText editTextValor;
    Switch switchConfig1;
    Switch switchConfig2;
    SeekBar seekBar;
    Button btnReset;
    public FirstFragment() {
        // Required empty public constructor
    }

    //Crio minha view normal, fazendo a referencia do botão
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first, container, false);

        this.btnNavigate = v.findViewById(R.id.btnNewFragment);
        this.editTextValor = v.findViewById(R.id.editTextValor);
        this.switchConfig1 = v.findViewById(R.id.switchConfig1);
        this.switchConfig2 = v.findViewById(R.id.switchConfig2);
        this.seekBar = v.findViewById(R.id.seekBar);
        this.btnReset = v.findViewById(R.id.button);
        this.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetSharedPreferences();
            }
        });

        //Inicio o sharedpreferences
        //Quando utlizamos sharedPreferences é recomendado fazer todos os "salvamentos" no onPause.
        this.activity = this.getActivity();
        this.sharedPreferences = this.activity.getSharedPreferences(this.sharedPrefFileName, Context.MODE_PRIVATE);


        //Pegando a informação do sharedpreferences
        //O sharedpreferences salva mesmo depois que o app é destruido, normalmente é utilizado em configurações
        this.editTextValor.setText(sharedPreferences.getString(this.EDIT_TEXT_KEY, ""));
        this.switchConfig1.setChecked(sharedPreferences.getBoolean(this.SWITCH_CONFIGURACAO_FIRST_KEY, false));
        this.switchConfig2.setChecked(sharedPreferences.getBoolean(this.SWITCH_CONFIGURACAO_SECOND_KEY, false));
        this.seekBar.setProgress(sharedPreferences.getInt(this.SEEK_BAR_KEY, 0));

        return v;
    }

    //Adiciono o método onViewCreate, que será executado depois que a view for criada
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Adiciono ao meu botão um click que terá como função
        //gerar uma action acessando um arquivo java (gerado automaticamente com o navigation ui) e um método que "navegará" para outra fragment
        //e por fim, executar a navegação passando a view e a action
        //O parametro passado no FirstFragmentDirections.actionToSecondFragment, foi definido via ui, Arguments default value, então ele deve ser passado ali.
        this.btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = FirstFragmentDirections.actionToSecondFragment(12);
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        //Acessando o editor do shared preferences
        //Sempre que mexer com shared preferences é necessário acessar um editor
        SharedPreferences.Editor sharedEditor = sharedPreferences.edit();

        //Os métodos são iguais do bundle
        //EDIT_TEXT_KEY
        //SEEK_BAR_KEY
        //SWITCH_CONFIGURACAO_FIRST_KEY
        //SWITCH_CONFIGURACAO_SECOND_KEY
        sharedEditor.putString(this.EDIT_TEXT_KEY, this.editTextValor.getText().toString());
        sharedEditor.putBoolean(this.SWITCH_CONFIGURACAO_FIRST_KEY, this.switchConfig1.isChecked());
        sharedEditor.putBoolean(this.SWITCH_CONFIGURACAO_SECOND_KEY, this.switchConfig2.isChecked());
        sharedEditor.putInt(this.SEEK_BAR_KEY, this.seekBar.getProgress());

        //apply => assincrono
        //commit => sincrono
        //Geralmente quando faz salvamente de info persistentes utilizamos a execução assincrona
        sharedEditor.apply();
    }

    private void resetSharedPreferences() {
        SharedPreferences.Editor sharedEditor = sharedPreferences.edit();
        sharedEditor.clear();
        this.editTextValor.setText("");
        this.switchConfig1.setChecked(false);
        this.switchConfig2.setChecked(false);
        this.seekBar.setProgress(0);
    }
}
