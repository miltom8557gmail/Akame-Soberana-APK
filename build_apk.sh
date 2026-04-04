#!/bin/bash

echo "--------------------------------------------------"
echo "🔨 [AKAME FORGE V7.4] INICIANDO COMPILAÇÃO..."
echo "--------------------------------------------------"

# 1. Limpando forjas antigas
rm -rf build
mkdir -p build/gen build/obj build/bin

# 2. Processando Recursos (XML e Manifesto)
echo "📦 Empacotando XML e Manifesto..."
aapt package -f -m -J build/gen -M app/src/main/AndroidManifest.xml -S app/src/main/res -I $PREFIX/share/java/android.jar

# 3. Compilando o DNA Java
echo "🧬 Compilando Sistema Nervoso (Java)..."
ecj -d build/obj -cp $PREFIX/share/java/android.jar app/src/main/java/com/akame/soberana/MainActivity.java build/gen/com/akame/soberana/R.java

# 4. Transformando em DEX (Onde o Android entende)
echo "⚡ Convertendo para Formato DEX..."
dx --dex --output=build/bin/classes.dex build/obj

# 5. Criando o APK Final
echo "🏗️ Construindo o APK Soberano..."
aapt package -f -M app/src/main/AndroidManifest.xml -S app/src/main/res -I $PREFIX/share/java/android.jar -F build/bin/Akame_Soberana_V7.4.apk build/bin

# 6. Finalização
cd build/bin
echo "--------------------------------------------------"
echo "✅ [STATUS]: APK GERADO COM SUCESSO!"
echo "📂 LOCAL: ~/Akame-Soberana-APK/build/bin/Akame_Soberana_V7.4.apk"
echo "--------------------------------------------------"
