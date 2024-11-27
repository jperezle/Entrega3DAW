package es.studium.TablaMultiplicar;

import java.util.ArrayList;

public class Modelo {
    // Constructor vacío
    public Modelo() {}

    // Método para calcular las tablas de multiplicar
    public ArrayList<String> calcularTablas(ArrayList<Integer> numeros) {
        ArrayList<String> resultados = new ArrayList<>();

        for (Integer numero : numeros) {
            StringBuilder tabla = new StringBuilder("<b>Tabla del " + numero + ":</b><br>");
            for (int i = 1; i <= 10; i++) {
                tabla.append(numero).append(" x ").append(i).append(" = ").append(numero * i).append("<br>");
            }
            resultados.add(tabla.toString());
        }

        return resultados;
    }
}
