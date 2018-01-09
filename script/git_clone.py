import subprocess
import sys

parameter1_projectName = sys.argv[1]
parameter2_projectUrl = sys.argv[2]
parameter3_targetBranch = sys.argv[3]

cmd1 = "C:\\Users\\lpy\\.babun\\cygwin\\bin\\git.exe clone -b " + parameter3_targetBranch + " " + parameter2_projectUrl + " ./Repository/" + parameter1_projectName

log = subprocess.call(cmd1, shell=True)

print log
