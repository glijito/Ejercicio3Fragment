package desarrollojhlibreros.com.ejercicio3fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentoCustom.FragmentoListener {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =(TextView) findViewById(R.id.textActivity);
        button =(Button) findViewById(R.id.btnActivity);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = fragmentManager.findFragmentByTag("editor");

                if(fragment==null ){
                    FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.fragmento,new FragmentoCustom(),"editor");
                    fragmentTransaction.commit();
                }
                textView.setText("");
                Toast.makeText(MainActivity.this,"Utilizando Fragment",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void digitado(int resultado, String texto) {

        TextView respuesta = (TextView) findViewById(R.id.textActivity);
        if(resultado==FragmentoCustom.OK){
            respuesta.setText(texto);
        }

        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("editor");
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

}
