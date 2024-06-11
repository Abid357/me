# Opens the locations.txt file and stores each of its elements in a list
locations = open("locations.txt", "r")
locList = locations.read().split("\n")

# Opens outputDuration.csv and outputDistance.csv files for the output to be written in it
outputDur = open('outputDuration.csv', 'w')
outputDist = open('outputDistance.csv', 'w')

# Writes the initial first row of the cross tables
for x in locList:
    outputDur.write("," + x)
    outputDist.write("," + x)

outputDur.close()
outputDist.close()