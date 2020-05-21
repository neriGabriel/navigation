package com.example.navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    TextView textViewNumber;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_second, container, false);
        this.textViewNumber = v.findViewById(R.id.textViewNumber);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Verifico se veio algum argumento
        if(getArguments() != null) {
            //Se sim pego do bundle, o método getNumber foi gerado automaticamente com o processo de build pelo navigation
            //getNumber = referencia do atributo criado via interface, (poderia ser getNome)
            int number = SecondFragmentArgs.fromBundle(getArguments()).getNumber();
            this.textViewNumber.setText(String.valueOf(number));
        }
    }
}
