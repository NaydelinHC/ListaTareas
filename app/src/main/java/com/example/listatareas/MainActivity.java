package com.example.listatareas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Adaptador para la lista de tareas
    private ArrayAdapter<Tarea> adaptador;
    // Lista de tareas
    private ArrayList<Tarea> listaTareas;
    // Contador de tareas
    private int contadorTareas = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar la lista de tareas y el adaptador
        listaTareas = new ArrayList<>();
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaTareas);

        // Obtener referencias a los elementos de la vista
        EditText editTextTareaNombre = findViewById(R.id.tarea_nombre);
        EditText editTextTareaDescripcion = findViewById(R.id.tarea_descripcion);
        Button botonAgregarTarea = findViewById(R.id.btn_agregar_tarea);
        ListView listViewTareas = findViewById(R.id.lista_tareas);

        // Configurar el adaptador para la lista de tareas
        listViewTareas.setAdapter(adaptador);

        // Agregar una tarea cuando se presione el botón "Agregar tarea"
        botonAgregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados por el usuario
                String nombre = editTextTareaNombre.getText().toString();
                String descripcion = editTextTareaDescripcion.getText().toString();

                // Crear una nueva tarea y agregarla a la lista de tareas
                Tarea tarea = new Tarea(contadorTareas++, nombre, descripcion);
                listaTareas.add(tarea);

                // Actualizar el adaptador para reflejar la nueva tarea
                adaptador.notifyDataSetChanged();

                // Limpiar los campos de texto
                editTextTareaNombre.setText("");
                editTextTareaDescripcion.setText("");
            }
        });


        // Eliminar una tarea cuando se seleccione un elemento de la lista de tareas
        listViewTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                eliminarTarea(position);
            }
        });
    }

    // Clase interna para representar una tarea
    private class Tarea {
        private int numero;
        private String nombre;
        private String descripcion;

        public Tarea(int numero, String nombre, String descripcion) {
            this.numero = numero;
            this.nombre = nombre;
            this.descripcion = descripcion;
        }

        public int getNumero() {
            return numero;
        }

        public String getNombre() {
            return nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        @Override
        public String toString() {
            return "Tarea " + numero + ": " + nombre + ": " + descripcion;
        }
    }


    // Método para eliminar una tarea de la lista de tareas
    private void eliminarTarea(int position) {
        listaTareas.remove(position);
        if (listaTareas.isEmpty()) {
            contadorTareas = 1;
        }
        adaptador.notifyDataSetChanged();
    }

}

