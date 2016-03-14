import urllib2
import csv
import json


with open('output/movies.csv', 'rb') as f:
    reader = csv.reader(f)
    films = list(reader)
#print(films[1])
f = csv.writer(open("test.csv", "wb+"))
# Write CSV Header, If you dont need that, remove this line
f.writerow(["title", "year", "rated", "released", "runtime","genre","director"])
for i in range(len(films)):
    url = 'http://www.omdbapi.com/?t='+films[i+1][0].replace(" ","+")+'&y=&plot=short&r=json'
    print(url)
    #response = urllib2.urlopen(url)
    #x = json.load(response)
    x = json.loads(urllib2.urlopen(url).read())

    #x = json.loads(response)
    #print(x)
    f.writerow([x['Title'],
                    x['Year'].encode('utf-8'),
                    x['Rated'].encode('utf-8'),
                    x['Released'].encode('utf-8'),
                    x['Runtime'].encode('utf-8'),
                    x['Genre'].encode('utf-8'),
                    x['Director'].encode('utf-8') ])