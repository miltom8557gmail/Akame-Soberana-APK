package com.akame.soberana;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends Activity {
    private TextView terminal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Criando layout via código para evitar erros de XML no compilador Termux
        android.widget.LinearLayout layout = new android.widget.LinearLayout(this);
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.setBackgroundColor(0xFF000000);
        layout.setPadding(30, 30, 30, 30);

        TextView title = new TextView(this);
        title.setText("🔱 AKAME SOBERANA V7.4");
        title.setTextColor(0xFFFF0000);
        title.setTextSize(24);
        layout.addView(title);

        terminal = new TextView(this);
        terminal.setText("[SISTEMA]: Pronto para o Cliente.");
        terminal.setTextColor(0xFF00FF00);
        layout.addView(terminal);

        Button btn = new Button(this);
        btn.setText("SINCRONIZAR COM GITHUB");
        btn.setOnClickListener(v -> runAkameProtocol());
        layout.addView(btn);

        setContentView(layout);
    }

    private void runAkameProtocol() {
        terminal.setText("🚀 Iniciando Protocolo Nexus...");
        // Lógica de execução do script Python no Termux via App
    }
}
