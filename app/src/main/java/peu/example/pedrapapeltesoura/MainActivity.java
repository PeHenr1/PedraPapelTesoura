package peu.example.pedrapapeltesoura;
//FEITO POR BEATRIZ CALDEIRA E PEDRO HENRIQUE AISSA
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // atributos
    int pontosUsuario;
    int pontosApp;
    private ImageView imgPedra;
    private ImageView imgPapel;
    private ImageView imgTesoura;
    private ImageView imgApp;
    private Button jogarNov;
    private TextView pontoU;
    private TextView pontoA;
    private TextView lblResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ligar os atributos com os IDs dos objetos
        // ao gráfico da interface)ome = findViewById(R.id.nome);
        imgPedra = findViewById(R.id.imgPedra);
        imgPapel = findViewById(R.id.imgPapel);
        imgTesoura = findViewById(R.id.imgTesoura);
        imgApp = findViewById(R.id.imgApp);
        jogarNov = findViewById(R.id.jogarNov);
        lblResultado = findViewById(R.id.lblResultado);
        pontoU = findViewById(R.id.pontoU);
        pontoA = findViewById(R.id.pontoA);

        EscutadorImg ei = new EscutadorImg();
        imgPedra.setOnClickListener(ei);
        imgPapel.setOnClickListener(ei);
        imgTesoura.setOnClickListener(ei);

        EscutadorBotao eb = new EscutadorBotao();
        jogarNov.setOnClickListener(eb);
    }

    private class EscutadorBotao implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            imgApp.setImageResource(R.drawable.vazio);
            lblResultado.setText("");
        }
    }

    private class EscutadorImg implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // Variável para guardar a escolha do usuário:
            int escolhaUsuario = 0;


            // Precisamos identificar qual imagem o usuário clicou.
            // Devemos converter View (v) em ImageView, e depois utilizar o metodo getId().
            ImageView img = (ImageView) v;
            switch ( img.getId() ) {
                case R.id.imgPedra:
                    escolhaUsuario = 1;
                    break;
                case R.id.imgPapel:
                    escolhaUsuario = 2;
                    break;
                case R.id.imgTesoura:
                    escolhaUsuario = 3;
                    break;
            }

            // Vamos gerar aleatoriamente a escolha do app.
            // O método nextInt(n) gera um número aleatório entre 0 e (n-1).
            // Como queremos de 1 a 3, geramos de 0 a 2, e somamos 1.
            // OBS: Isso é Java básico!!!

            int escolhaApp = new Random().nextInt(3) + 1;
            // Precisamos colocar a imagem correta que reflete

            // A escolha do App:
            switch ( escolhaApp ) {
                case 1:
                    imgApp.setImageResource(R.drawable.pedra);
                    break;
                case 2:
                    imgApp.setImageResource(R.drawable.papel);
                    break;
                case 3:
                    imgApp.setImageResource(R.drawable.tesoura);
                    break;
            }

            // Vamos ver quem ganhou... e informar o resultado.
            if ( ( escolhaApp == 1 && escolhaUsuario == 3 ) ||
                    ( escolhaApp == 2 && escolhaUsuario == 1 ) ||
                    ( escolhaApp == 3 && escolhaUsuario == 2 ) )
            {
                lblResultado.setText("O app ganhou!!!!");
                pontosApp = pontosApp + 1;
                pontoA.setText( Integer.toString(pontosApp));
            }
            else
            {
                if ( (escolhaApp == 3 && escolhaUsuario == 1) ||
                        (escolhaApp == 1 && escolhaUsuario == 2) ||
                        (escolhaApp == 2 && escolhaUsuario == 3) )
                {
                    lblResultado.setText("Você ganhou!!");
                    pontosUsuario = pontosUsuario + 1;
                    pontoU.setText( Integer.toString(pontosUsuario));
                }
                else
                {
                    lblResultado.setText("Deu empate!");
                }
            }

        }
    }
}