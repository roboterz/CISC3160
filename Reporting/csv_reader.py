import csv

def myFunc(e):
  return e[0]
  
with open('regional-global-daily-latest.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    artistList = []
    for row in csv_reader:
        if line_count == 0 or line_count ==1:
            line_count += 1
        else:
            line_count += 1
            artistList.append([f'{row[2]}',f'{row[3]}'])

artistList.sort(key=myFunc)

newList = []
index = 0
for i in range(len(artistList)):
    if i == 0:
        newList.append(artistList[i])
        index = 0
    else: 
        if artistList[i][0] == newList[index][0]:
            newList[index][1] = int(newList[index][1]) + int(artistList[i][1])
        else:
            newList.append(artistList[i])
            index += 1

print('Artist\t\tStreams')
for i in range(len(newList)):
    print(f'{newList[i][0]}\t\t{newList[i][1]}')