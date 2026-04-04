package com.akame.soberana;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private TextView terminal;
    private TextView networkStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        terminal = findViewById(R.id.terminalOutput);
        networkStatus = findViewById(R.id.networkStatus);
        Button btnFix = findViewById(R.id.btnFixAll);

        btnFix.setOnClickListener(v -> executeTermuxOmniProtocol());
    }

    private void executeTermuxOmniProtocol() {
        terminal.setText("🔱 [NEXUS]: Despertando Motor Imortal V7.4...\n");
        networkStatus.setText("[REDE]: Forçando Túnel Direto...");
        
        // Comando que chama o cérebro Python que acabamos de criar
        String command = "python /data/data/com.termux/files/home/starter-workflows/akame_omni_fix.py";
        
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                terminal.append(line + "\n");
            }
            networkStatus.setText("[REDE]: Operação Concluída.");
        } catch (Exception e) {
            terminal.append("[ERRO CRÍTICO]: " + e.getMessage() + "\n");
            networkStatus.setText("[REDE]: Falha Absoluta.");
        }
    }
}
