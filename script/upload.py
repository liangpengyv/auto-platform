import sys
import requests
import json

parameter1_projectType = sys.argv[1]
parameter2_bundleId = sys.argv[2]
parameter3_firToken = sys.argv[3]
parameter4_changeLog = sys.argv[4]
parameter5_repositoryName = sys.argv[5]

headers = {"Content-Type": "application/json"}
payload = "{\"type\" : \"" + parameter1_projectType + "\",\"bundle_id\" : \"" + parameter2_bundleId + "\",\"api_token\" : \"" + parameter3_firToken + "\"}"
r = requests.post("http://api.fir.im/apps", headers=headers, data=payload)

temp = json.loads(r.text)
preview_url = "https://fir.im/" + temp["short"]
key = temp["cert"]["binary"]["key"]
token = temp["cert"]["binary"]["token"]
payload = (('key', key), ('token', token), ('x:name', parameter5_repositoryName), ('x:version', '1.0'), ('x:build', '1'), ('x:changelog', parameter4_changeLog))
files = {'file' : open("./Repository/" + parameter5_repositoryName + "/app/build/outputs/apk/debug/app-debug.apk", "rb")}
r = requests.post("https://upload.qbox.me", data=payload, files=files)

temp = json.loads(r.text)
download_url = temp["download_url"]

print("preview_url : " + preview_url + "\n" + "download_url : " + download_url + "\n0")
