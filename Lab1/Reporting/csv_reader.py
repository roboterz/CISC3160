import csv

def artist(e):
  return e[0]
  #return Artist
  
with open('regional-global-daily-latest.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    artistList = []
    strArtist =''
    #load the date from csv files to list
    for row in csv_reader:
        if line_count == 0 or line_count ==1:
            line_count += 1
        else:
            #add space after artist for print out
            strArtist = row[2] + ' '*(30-len(row[2]))
            artistList.append([strArtist, f'{row[3]}'])
#sort the list
artistList.sort(key=artist)

#use new list to save the unique name list
newList = []
index = 0
songs = 1
for i in range(len(artistList)):
    if i == 0:
        newList.append(artistList[i])
        index = 0
    else: 
        if artistList[i][0] == newList[index][0]:
            #sum the streams
            newList[index][1] = int(newList[index][1]) + int(artistList[i][1])
            songs += 1
        else:
            #count the average streams per song
            newList[index][1] = int(int(newList[index][1]) / int(songs))
            songs = 1
            newList.append(artistList[i])
            index += 1

print('Artist\t\t\tAverage Streams')
for i in range(len(newList)):
    print(f'{newList[i][0]}\t{newList[i][1]}')
