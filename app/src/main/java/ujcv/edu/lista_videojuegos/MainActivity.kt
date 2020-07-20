package ujcv.edu.lista_videojuegos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var i: Int = 0
        val items = ArrayList<VideoJuegos>()
        while (i < 100) {
            items.add(VideoJuegos("Call Of Duty", false))
            items.add(VideoJuegos("dragon ball", false))
            i++
        }


        my_recycler_view.adapter = Adaptador(items)
        my_recycler_view.layoutManager = LinearLayoutManager(this)

    }
}