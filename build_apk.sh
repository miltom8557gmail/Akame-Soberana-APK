#!/bin/bash
set -e # Faz o script parar se der erro

echo "🔨 [AKAME FORGE V7.4] INICIANDO..."
rm -rf build
mkdir -p build/gen build/obj build/bin

# 1. Gerar R.java
echo "📦 Gerando R.java..."
aapt package -f -m -J build/gen -M app/src/main/AndroidManifest.xml -S app/src/main/res -I $PREFIX/share/java/android.jar

# 2. Compilar Java
echo "🧬 Compilando Java..."
ecj -d build/obj -cp $PREFIX/share/java/android.jar app/src/main/java/com/akame/soberana/MainActivity.java build/gen/com/akame/soberana/R.java

# 3. DEX
echo "⚡ Convertendo para DEX..."
dx --dex --output=build/bin/classes.dex build/obj

# 4. APK Final
echo "🏗️ Criando APK..."
aapt package -f -M app/src/main/AndroidManifest.xml -S app/src/main/res -I $PREFIX/share/java/android.jar -F build/bin/Akame_Soberana_V7.4.apk build/bin

echo "✅ SUCESSO REAL!"
