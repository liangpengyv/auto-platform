import subprocess
import sys

parameter1_projectName = sys.argv[1]

cmd1 = "gradle -p .\\Repository\\" + parameter1_projectName + " clean"

log = subprocess.call(cmd1, shell=True)

print log
