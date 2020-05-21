package com.example.navigation;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

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
}
