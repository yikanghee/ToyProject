from urllib.request import urlopen
import json
import csv

def getFirstPageJsonAreray(url):
    responseBody = urlopen(url).read().decode('utf-8')
    jsonArray = json.loads(responseBody)
    storeInfosArray = jsonArray.get("storeInfos")
    numPage = jsonArray.get("totalPages")
    return storeInfosArray, numPage

def getJsonArray(url):
    responseBody = urlopen(url).read().decode('utf-8')
    jsonArray = json.loads(responseBody)
    storeInfosArray = jsonArray.get("storeInfos")
    return storeInfosArray

def saveCSV(w, jsonArray):
    for i in range(0,len(jsonArray)):
        temp = jsonArray[i]
        w.writerow([temp.get("addr"), temp.get("code"), temp.get("lat"), temp.get("lng"), temp.get("name"), temp.get("type")])

def main():
    f = open('영화데이터.csv','w',encoding='utf-8-sig',newline='')
    w = csv.writer(f,delimiter=',')
    url = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/stores/json?page="
    jsonArray, numPage = getFirstPageJsonAreray(url+"1")
    saveCSV(w, jsonArray)

    for i in range(2,numPage+1):
        jsonArray = getJsonArray(url+str(i))
        saveCSV(w, jsonArray)
    f.close()

if __name__ == '__main__':
    main()