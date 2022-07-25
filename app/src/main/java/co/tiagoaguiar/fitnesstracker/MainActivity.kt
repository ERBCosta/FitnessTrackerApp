package co.tiagoaguiar.fitnesstracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    //declaramos aqui o botão que escutaremos o evento de touch criando o botão:
    private lateinit var btnImc: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //abaixo é dado um valor para a variável btn para ela não ficar nula
        btnImc = findViewById(R.id.btn_imc)

        //para escutar os eventos de touch precisa do SetOnClickListener
        btnImc.setOnClickListener {
            //Para navegar para a proxima tela sempre precisaremos de um intent
            //primeiro passo é declarar uma variável nesse caso chamada de i e construimos o código
            //padrão da linha abaixo
            val i = Intent(this,ImcActivity::class.java)
            //agora que criamos a inteção na linha de cima, é necessário inicializar a intenção
            //com mais uma função padrão: StartActivity
            startActivity(i)
        }
    }
}