package desarrollojhlibreros.com.ejercicio3fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoCustom extends android.app.Fragment {

    private View view;
    final static  int OK = 0;
    final static  int CANCEL = 1;
    private FragmentoListener fragmentoListener;

    public FragmentoCustom() {
        // Required empty public constructor
    }

    public interface  FragmentoListener{
        void digitado(int resultado,String texto);
    }


    @Override
    public void onAttach(Activity a){
        super.onAttach(a);
        if(a instanceof  FragmentoListener){
            fragmentoListener = (FragmentoListener) a;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_custom, container, false);

        ((Button) view.findViewById(R.id.btnAceptar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonDigitado(v);
                Toast.makeText(getActivity(),"Desde mi fragmento",Toast.LENGTH_LONG).show();
            }
        });

        ((Button) view.findViewById(R.id.btnCancelar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonDigitado(v);
            }
        });

        return view;
    }


    public void botonDigitado(View v){
        if(fragmentoListener == null)
            return ;

        if(((Button)v).getText().equals("Aceptar")){
            fragmentoListener.digitado(OK,((EditText)getActivity().findViewById(R.id.textFragment))
                    .getText().toString());
        }else
            fragmentoListener.digitado(CANCEL,"");
    }

}
