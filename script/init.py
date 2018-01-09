import subprocess
import sys

parameter1_projectName = sys.argv[1]

cmd1 = "cmd /c rmdir /s/q .\\Repository\\" + parameter1_projectName
cmd2 = "gradle -v"
cmd3 = "git --version"

subprocess.call(cmd1, shell=True)
subprocess.call(cmd2, shell=True)
log = subprocess.call(cmd3, shell=True)

print log
