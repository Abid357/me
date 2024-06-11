import requests

URL = "https://maps.googleapis.com/maps/api/directions/json"
api_key = "AIzaSyA7yx7n6C6jq0PpG1U0RkDGuyJ-Le_vAxI"


# Opens the locations.txt file and stores each of its elements in a list
locations = open("locations.txt", "r")
locList = locations.read().split("\n")

# Opens outputDuration.csv and outputDistance.csv files for the output to be written in it
outputDur = open("outputDuration.csv", "a")
outputDist = open("outputDistance.csv", "a")

# The nested loops runs each destination against others, getting their distance and duration and storing in the output files
for x in locList:
    outputDur.write("\n" + x)
    outputDist.write("\n" + x)
    for y in locList:
        outputDur.write(",")
        outputDist.write(",")
        if(x != y):
            PARAMS = {'origin':x, 'destination':y ,'key':api_key}
            req = requests.get(URL, PARAMS)
            data = req.json()
            distance = data['routes'][0]['legs'][0]['distance']['value'] / 1000
            duration = data['routes'][0]['legs'][0]['duration']['value']
            outputDur.write(str(duration))
            outputDist.write(str(distance))
            print("Processed an element...")

# Finally, closing the output files when the writing process is completed
locations.close()
outputDur.close()
outputDist.close()