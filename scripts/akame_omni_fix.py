import os, subprocess, time

def run_cmd(cmd, retries=10, delay=5):
    for attempt in range(retries):
        try:
            result = subprocess.check_output(cmd, shell=True, stderr=subprocess.STDOUT)
            output = result.decode('utf-8').strip()
            if "software caused connection abort" in output or "unreachable" in output:
                raise subprocess.CalledProcessError(1, cmd, output=result)
            return output
        except subprocess.CalledProcessError as e:
            print(f"⚠️ [REDE INSTÁVEL - Tentativa {attempt+1}/10] Reconectando...")
            time.sleep(delay)
    return "ERRO_MAX_RETENTATIVAS"

print("--------------------------------------------------")
print("🛡️ [AKAME OMNI-RECOVERY V7.4 - IMORTAL] INICIADO")
print("--------------------------------------------------")

run_cmd("termux-wake-lock")
work_dir = os.path.expanduser("~/starter-workflows")
if os.path.exists(work_dir):
    os.chdir(work_dir)

run_cmd("rm -f .git/index.lock")
run_cmd("rm -rf /sdcard/Pictures/Akame_Soberana_V6/*")

print("🛰️ Sincronizando com GitHub...")
run_cmd("git fetch --all")
run_cmd("git reset --hard origin/main")

run_id = run_cmd("gh run list --workflow=akame_sync.yml --limit 1 --json databaseId --jq '.[0].databaseId'")

if run_id and run_id.isdigit():
    print(f"📥 Baixando Missão ID: {run_id}")
    download_status = run_cmd(f"gh run download {run_id} --name akame-v7-final --dir /sdcard/Pictures/Akame_Soberana_V6")
    
    if "ERRO_MAX_RETENTATIVAS" in download_status or "no valid artifacts" in download_status.lower():
        print("⚠️ Disparando Nova Forja...")
        run_cmd("gh workflow run akame_sync.yml")
    else:
        print("✅ VÍDEO RESGATADO COM SUCESSO!")
else:
    print("❌ Falha crítica de conexão.")

print("--------------------------------------------------")
print("💬 STATUS: Sincronia Nível 7.4 Concluída.")
