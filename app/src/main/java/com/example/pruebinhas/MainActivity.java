package com.example.pruebinhas;
import android.annotation.SuppressLint;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Spinner sp1_VL, sp2_VL;
    private EditText et1_Vl;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hago la conexión
        et1_Vl = (EditText) findViewById(R.id.valor1);
        sp1_VL= (Spinner) findViewById(R.id.spinner1);
        sp2_VL= (Spinner) findViewById(R.id.spinner2);
        ImageView imageView_VL = findViewById(R.id.imagen);





        //creo objeto que tendrá toda la información (conexiones tamb) que tiene que contener el espinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones_array, android.R.layout.simple_spinner_item);
        //diseño spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //aplico el adapdador
        sp1_VL.setAdapter(adapter);
        sp2_VL.setAdapter(adapter);
           //declaro la clase
            sp1_VL.setOnItemSelectedListener(new operacionesValeria());
              sp2_VL.setOnItemSelectedListener(new operacionesValeria());




    }
    private class operacionesValeria implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            // Obtener la posición del elemento seleccionado en los Spinners
            int posicionSpinner1 = sp1_VL.getSelectedItemPosition();
            int posicionSpinner2 = sp2_VL.getSelectedItemPosition();
            //conexion vista
            TextView vistaval1 = findViewById(R.id.vistaval1);
            TextView vistaval2 =findViewById(R.id.vistaval2);
            //conexion botton
            Button miBoton = findViewById(R.id.miBoton);
            Button miBoton2 = findViewById(R.id.miBoton2);

            // Obtener el valor seleccionado de ambos Spinners
            String valorSpinner1 = sp1_VL.getSelectedItem().toString();
            String valorSpinner2 = sp2_VL.getSelectedItem().toString();

            //resta de posiciones para multiplicarlo despues
            int resta = posicionSpinner1 - posicionSpinner2;
            int resta2 = posicionSpinner2-posicionSpinner1;



            miBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText valor = findViewById(R.id.valor1);

                //declaro double fuera para evitar error
                double numero = 0;


                // hago el parse para pasar de string a double para después operar con el número
                try {
                    String textoIngresado = valor.getText().toString();
                    numero = Double.parseDouble(textoIngresado);

                } catch (NumberFormatException e) {
                    System.out.println("hay que ingresar un numero");
                }

          /*NOTA:
          a =spiner1, b=spiner2, x=numero ingresado
          a>b--------- a-b=resta  ==division(baja)  {x/(1024^resta)}
          a<b --------b-a=resta  ==multiplicar(sube)  {x.(1024^resta)}
          */
                //mensaje por si no se introduce valor
                if (numero == 0) {
                    String alerta = "Introduce un valor";
                    vistaval1.setText(alerta);
                } else {

                    if (posicionSpinner1 < posicionSpinner2) {
                        int restar = posicionSpinner2 - posicionSpinner1;
                        int potencia = (int) Math.pow(1024, restar);
                        double elevar1 = numero / potencia;

                        String eleva1String = String.valueOf(elevar1);
                        vistaval1.setText(eleva1String);


                    } else {
                        System.out.println("no funciona");

                    }
                    if (posicionSpinner1 > posicionSpinner2) {

                        int restar = posicionSpinner1 - posicionSpinner2;
                        int potencia = (int) Math.pow(1024, restar);
                        double elevar1 = numero * potencia;
                        //formato x.xxE5
                        String eleva1String = String.valueOf(elevar1);
                        vistaval1.setText(eleva1String);

                    } else {
                        System.out.println("no funciona ");

                    }
                }
            }

            });


            //Boton 2
            miBoton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    EditText valor = findViewById(R.id.valor2);

                    //declaro double fuera para evitar error
                    double numero1 = 0;

                    // hago el parse para pasar de string a double para después operar con el número
                    try {
                        String textoIngresado1 = valor.getText().toString();
                        numero1 = Double.parseDouble(textoIngresado1);

                    } catch (NumberFormatException e) {
                        System.out.println("hay que ingresar un numero");
                    }
//mensaje aviso
                    if (numero1 == 0 ){
                        String alerta = "Introduce un valor";
                        vistaval2.setText(alerta);
                    }else {
//misma operación pero con simbolos cambiados
                        if (posicionSpinner1 < posicionSpinner2) {
                            int restar2 = posicionSpinner2 - posicionSpinner1;
                            int potencia2 = (int) Math.pow(1024, restar2);
                            double elevar2 = numero1 * potencia2;
                            String elevar2String = String.valueOf(elevar2);
                            vistaval2.setText(elevar2String);

                        } else {
                            System.out.println("no funciona");

                        }
                        if (posicionSpinner1 > posicionSpinner2) {

                            int restar2 = posicionSpinner1 - posicionSpinner2;
                            int potencia2 = (int) Math.pow(1024, restar2);
                            double elevar2 = numero1 / potencia2;
                            String elevar2String = String.valueOf(elevar2);
                            vistaval2.setText(elevar2String);

                        } else {
                            System.out.println("no funciona ");

                        }
                    }
                    }
            });






        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
//accion que se realiza si no se elige nada en el spinner
        }

    }
}



