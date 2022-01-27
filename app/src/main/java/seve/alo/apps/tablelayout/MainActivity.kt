package seve.alo.apps.tablelayout

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.github.johnpersano.supertoasts.library.Style
import com.github.johnpersano.supertoasts.library.SuperActivityToast
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils.getSolidColor

class MainActivity : AppCompatActivity() {

    // Creamos tres Variables:
    // jugador1 almacenara las casillas que marque el primer jugador
    var jugador1 = ArrayList<Int>()
    // jugador2 almacenara las casillas que marque el segundo jugador
    var jugador2 = ArrayList<Int>()
    // jugadorActivo nos indicara a que jugador le toca marcar
    var jugadorActivo = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // esta funcion la utilizamos para saber que posicion a marcado el jugador
    fun btnCliqueado(view: android.view.View) {
        // Aqui creamos una variable en la cual almacenamos el boton en si que ha clickado
        val btnSeleccionado = view as Button
        // En esta variable guardaremos el id de la posicion del boton que ha clickado
        var idCelda = 0

        // Asignamos a nuestra variable idCelda el id correspondiente al boton que ha clickado
        when (btnSeleccionado.id) {
            R.id.btn1 -> idCelda = 1
            R.id.btn2 -> idCelda = 2
            R.id.btn3 -> idCelda = 3
            R.id.btn4 -> idCelda = 4
            R.id.btn5 -> idCelda = 5
            R.id.btn6 -> idCelda = 6
            R.id.btn7 -> idCelda = 7
            R.id.btn8 -> idCelda = 8
            R.id.btn9 -> idCelda = 9
        }
        // Llamamos a nuestra funcion partida para que marque una X o una O dependiendo del jugador
        partida(idCelda, btnSeleccionado)
    }

    // Creamos la funcion partida en la cual tendremos dos parametros idCelda y btnSeleccionado
    fun partida(idCelda: Int, btnSeleccionado: Button) {

        // Utilizamos un condicional para marcar una X o una O dependiendo del jugador que haya marcado
        if (jugadorActivo == 1) {
            btnSeleccionado.text = "X"
            btnSeleccionado.setBackgroundColor(Color.BLUE)
            jugador1.add(idCelda)
            jugadorActivo = 2
        } else{
            btnSeleccionado.text = "O"
            btnSeleccionado.setBackgroundColor(Color.GREEN)
            jugador2.add(idCelda)
            jugadorActivo = 1
        }

        // Con esto lo que conseguimos es deshabilitar el boton que han seleccionado
        btnSeleccionado.isEnabled = false
        // Llamamos a la funcion ganador para comprobar si ese jugador ha ganado o no
        ganador()
    }

    // Creamos una funcion llamada ganador
    fun ganador() {

        // Creamos una variable llamada ganador, con la cual vamos a determinar si ha ganado o no
        var ganador = -1

        // Ahora hacemos todas las posibles combinaciones para que un jugador pueda ganar

        // linea 1
        if (jugador1.contains(1) && jugador1.contains(2) && jugador1.contains(3)) {
            ganador = 1
        }
        if (jugador2.contains(1) && jugador2.contains(2) && jugador2.contains(3)) {
            ganador = 2
        }

        // linea 2
        if (jugador1.contains(4) && jugador1.contains(5) && jugador1.contains(6)) {
            ganador = 1
        }
        if (jugador2.contains(4) && jugador2.contains(5) && jugador2.contains(6)) {
            ganador = 2
        }

        // linea 3
        if (jugador1.contains(7) && jugador1.contains(8) && jugador1.contains(9)) {
            ganador = 1
        }
        if (jugador2.contains(7) && jugador2.contains(8) && jugador2.contains(9)) {
            ganador = 2
        }

        // columna 1
        if (jugador1.contains(1) && jugador1.contains(4) && jugador1.contains(7)) {
            ganador = 1
        }
        if (jugador2.contains(1) && jugador2.contains(4) && jugador2.contains(7)) {
            ganador = 2
        }

        // columna 2
        if (jugador1.contains(2) && jugador1.contains(5) && jugador1.contains(8)) {
            ganador = 1
        }
        if (jugador2.contains(2) && jugador2.contains(5) && jugador2.contains(8)) {
            ganador = 2
        }

        // columna 3
        if (jugador1.contains(3) && jugador1.contains(6) && jugador1.contains(9)) {
            ganador = 1
        }
        if (jugador2.contains(3) && jugador2.contains(6) && jugador2.contains(9)) {
            ganador = 2
        }

        // linea en X 1
        if (jugador1.contains(1) && jugador1.contains(5) && jugador1.contains(9)) {
            ganador = 1
        }
        if (jugador2.contains(1) && jugador2.contains(5) && jugador2.contains(9)) {
            ganador = 2
        }

        // linea en X 2
        if (jugador1.contains(3) && jugador1.contains(5) && jugador1.contains(7)) {
            ganador = 1
        }
        if (jugador2.contains(3) && jugador2.contains(5) && jugador2.contains(7)) {
            ganador = 2
        }

        // Comprobamos si ganador es diferente de -1 para saber si ha ganado alguien
        if (ganador != -1) {
            // Hacemos la comprobacion para ver si ha ganado el jugador 1 o el 2
            if (ganador == 1) {
                //Toast.makeText(this, "¡Jugador 1 ha ganado!", Toast.LENGTH_LONG).show()
                val superActivityToast = SuperActivityToast(this)
                superActivityToast.text = "¡¡¡JUGADOR 1 HA GANADO!!!"
                superActivityToast.duration = Style.DURATION_LONG
                superActivityToast.color = PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_CYAN)
                superActivityToast.textColor = Color.DKGRAY
                superActivityToast.setTouchToDismiss(true)
                superActivityToast.isIndeterminate = true
                superActivityToast.progressIndeterminate = true
                superActivityToast.show()
            } else{
                //Toast.makeText(this, "¡Jugador 2 ha ganado!", Toast.LENGTH_LONG).show()
                val superActivityToast = SuperActivityToast(this)
                superActivityToast.text = "¡¡¡JUGADOR 2 HA GANADO!!!"
                superActivityToast.duration = Style.DURATION_LONG
                superActivityToast.color = PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_CYAN)
                superActivityToast.textColor = Color.DKGRAY
                superActivityToast.setTouchToDismiss(true)
                superActivityToast.isIndeterminate = true
                superActivityToast.progressIndeterminate = true
                superActivityToast.show()
            }
        }
    }
}