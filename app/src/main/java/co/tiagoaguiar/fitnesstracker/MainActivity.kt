package co.tiagoaguiar.fitnesstracker

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    //declaramos aqui o botão que escutaremos o evento de touch criando o botão:
//    private lateinit var btnImc: LinearLayout
    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainItems = mutableListOf<MainItem>()
        mainItems.add(
            MainItem(
                id = 1,
                drawableId = R.drawable.ic_baseline_wb_sunny_24,
                textStringId = R.string.label_imc,
                color = Color.GREEN
            )
        )
        mainItems.add(
            MainItem(
                id = 2,
                drawableId = R.drawable.ic_baseline_visibility_24,
                textStringId = R.string.label_tmb,
                color = Color.BLUE
            )
        )

        //Passo1 para recycler view: Criar o layout xml (main_item.xml)
        //2: onde o recyclerview vai aparecer (tela principal, tela cheia)
        //3: lógica - conectar o xml da celula DENTRO do recyclerView + a sua qtde de elementos dinâmicos

//        val adapter = MainAdapter(mainItems, object : OnItemClickListener {
//            //METODO 2: IMPLEMENTANDO VIA OBJETO ANONIMO
//            override fun onClick(id: Int) {
//                when (id) {
//                    1 -> {
//                        val intent = Intent(this@MainActivity, ImcActivity::class.java)
//                        startActivity(intent)
//                    }
//                    2 -> {
//                        // abrir uma outra activity
//                    }
//                }
//                Log.i("Teste", "Clicou $id")
//            }
//        })

        val adapter = MainAdapter(mainItems) { id ->
            //METODO 3: IMPLEMENTANDO VIA FUNCTIONS
            when (id) {
                1 -> {
                    val intent = Intent(this@MainActivity, ImcActivity::class.java)
                    startActivity(intent)
                }
                2 -> {
                    // abrir uma outra activity
                }
            }
            Log.i("Teste", "Clicou $id")
        }

        rvMain = findViewById(R.id.rv_main)
        rvMain.adapter = adapter
        rvMain.layoutManager = GridLayoutManager(this, 2)

        //classe para administrar a recyclerview e suas celulas (os seus layouts de itens) nome:
        //adapter ->

        //abaixo é dado um valor para a variável btn para ela não ficar nula
//        btnImc = findViewById(R.id.btn_imc)
//
//        //para escutar os eventos de touch precisa do SetOnClickListener
//        btnImc.setOnClickListener {
//            Para navegar para a proxima tela sempre precisaremos de um intent
//            primeiro passo é declarar uma variável nesse caso chamada de i e construimos o código
//            padrão da linha abaixo
//            val i = Intent(this,ImcActivity::class.java)
//            agora que criamos a inteção na linha de cima, é necessário inicializar a intenção
//            com mais uma função padrão: StartActivity
//            startActivity(i)
//        }
    }
// METODO 1 USANDO IMPLEMENTACAO DE INTERFACE VIA ACTIVITY
//    override fun onClick(id: Int) {
//        when (id) {
//            1 -> {
//                val intent = Intent(this, ImcActivity::class.java)
//                startActivity(intent)
//            }
//            2 -> {
//                // abrir uma outra activity
//            }
//        }
//        Log.i("Teste", "Clicou $id")
//    }

    private inner class MainAdapter(
        private val mainItems: List<MainItem>,
        // private val onItemClickListener: OnItemClickListener,
        private val onItemClickListener: (Int) -> Unit,
    ) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

        //1 - Qual é o layout xml da celular específica (item)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        //2 - disparado toda vez que houver uma rolagem na tela e for necessário trocar o conteudo
        //da célula
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val itemCurrent = mainItems[position]
            holder.bind(itemCurrent)
        }

        //3 - informar quantas células essa listagem terá
        override fun getItemCount(): Int {
            return mainItems.size
        }

        //classe da célula em si!(é a célula que busca as referencias de cada botão)
        private inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            fun bind(item: MainItem) {
                val img: ImageView = itemView.findViewById(R.id.item_img_icon)
                val name: TextView = itemView.findViewById(R.id.item_txt_name)
                val container: LinearLayout = itemView.findViewById(R.id.item_container_imc)

                img.setImageResource(item.drawableId)
                name.setText(item.textStringId)
                container.setBackgroundColor(item.color)

                container.setOnClickListener {
                    //aqui é uma função
                    onItemClickListener.invoke(item.id)
                    //aqui é um ref. interface
                    //onItemClickListener.onClick(item.id)

                }
            }
        }

    }


    // 3 maneiras de escutar eventos de click usando celulas (viewholder) activities
    // 1 - implementação de interface
    // 2 - objetos anonimos
    //3 - funcional
}