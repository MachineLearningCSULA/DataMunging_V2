from TwitterAPI import TwitterAPI, TwitterRestPager
import csv
import time
import os
from textblob import TextBlob

count = 1
os.chdir("../data_extractor/output")
with open('movies.csv', 'rb') as f:
    reader = csv.reader(f)
    next(reader, None)
    films = list(reader)

os.chdir("../../data_analyzer/")
for film in films:
    print(count)

    SEARCH_TERM = film[1] + " movie"
    print("We're on film: " + film[1])

    CONSUMER_KEY = 'XEQ5mzqqB8YjePzOD1ngTx4ky'
    CONSUMER_SECRET = 'BiQRkI13aMwBqmJUosji4Zu7KoHLNaE6aCw6Dasy45zAH1FeXJ'
    ACCESS_TOKEN_KEY = '126067584-uOjRP0NsUXiiQ1LfZFEqhhjNLXTOjyApfxXwv68o'
    ACCESS_TOKEN_SECRET = 'IdM0ZJYpHBkXRbtLWRzRoO9n3d8Z9OCp173N00Dk92RKq'

    api = TwitterAPI(CONSUMER_KEY,
                     CONSUMER_SECRET,
                     ACCESS_TOKEN_KEY,
                     ACCESS_TOKEN_SECRET)

# pager = TwitterRestPager(api, 'search/tweets', {'q': SEARCH_TERM})
    # blob = TextBlob("This is a sad girl. Of sadness")

    # count = 0;

    f = csv.writer(open("tweet_data/" + film[0] + ".csv", "wb+"))
    # Write CSV Header, If you dont need that, remove this line
    f.writerow([film[1]])
    # values = []
    # for item in pager.get_iterator():
    # f.writerow([item["text"].encode('utf-8')])
    # print(item['text'])
    r = api.request('search/tweets', {'q': SEARCH_TERM, 'count': 100})
    for item in r.get_iterator():
        if 'text' in item:

            f.writerow([item["text"].encode('utf-8')])
        elif 'message' in item:
            print '%s (%d) %s' % (item['message'], item['code'], film[1])
    count += 1
    if (count % 180 == 0):
        time.sleep(910)
        # print("count: " + str(count))
        # print(item['text'] if 'text' in item else item)
        # count+=1
    # values.append(TextBlob(item['text']))
        # print(values)
    #     for value in values:
    #         for sentence in value.sentences:
    #             print(sentence.sentiment.polarity)

