from TwitterAPI import TwitterAPI, TwitterRestPager
import csv
from textblob import TextBlob
import os
import time
import re

# get page tweets using Twitter API
count = 1
os.chdir("../data_extractor/output")
movie_data = open('movies.csv', 'rb')
move_reader = csv.reader(movie_data)
next(move_reader, None)
films = list(move_reader)

os.chdir("../../data_analyzer/")
if not os.path.exists("tweet_data"):
    os.makedirs("tweet_data")

print "Gathering tweets from Twitter API"
for film in films:
    print(count)

    SEARCH_TERM = film[1] + " movie"
    print("We're on film: " + film[1])

    CONSUMER_KEY = ''
    CONSUMER_SECRET = ''
    ACCESS_TOKEN_KEY = ''
    ACCESS_TOKEN_SECRET = ''

    api = TwitterAPI(CONSUMER_KEY,
                     CONSUMER_SECRET,
                     ACCESS_TOKEN_KEY,
                     ACCESS_TOKEN_SECRET)

    f = csv.writer(open("tweet_data/" + film[0] + ".csv", "wb+"))

    f.writerow([film[1]])

    r = api.request('search/tweets', {'q': SEARCH_TERM, 'count': 100})
    for item in r.get_iterator():
        if 'text' in item:

            f.writerow([item["text"].encode('utf-8')])
        elif 'message' in item:
            print '%s (%d) %s' % (item['message'], item['code'], film[1])
    count += 1
    if (count % 180 == 0):
        time.sleep(910)



# now clean tweets and gather sentiment scores
def cleanTweets( tweet ):
    # strip out the movie title - "Good Fellas" Some word in title may affect sentiment
    sentence = re.sub(title, "", tweet)
    # make lowercase, strip out all hashtags, links, usernames
    cleanTweet = re.sub(r"(http|#|@)(\S+)", "", sentence.lower())
    # strip out non alphabets
    cleanTweet = re.sub(r"[^a-z ]+", "", cleanTweet).strip()
    return cleanTweet

stopwords = ["full movie", "free movie"]

os.chdir("./tweet_data")

scores = {}

print "Cleaning tweet data"
# process all tweets in tweet_data and store in 'scores'
for file in os.listdir(os.getcwd()):
    if file.endswith(".csv"):
        with open(file, 'rb') as f:
            reader = csv.reader(f)
            title = next(reader, None)[0]
            tweets = list(reader)

        tweetset = set()
        sentiment = 0
        count = 0
        for tweetl in tweets:
            tweet = tweetl[0]
            if tweet not in tweetset:
                tweetset.add(tweet)
                cleanTweet = cleanTweets(tweet)

                #if tweet doesn't contain stopwords (ads, full link to movie)
                if len(cleanTweet) > 0 and not any(word in cleanTweet for word in stopwords):
                    blob = TextBlob(cleanTweet)

                    # if sentiment is is not neutral (has positivity or negativity)
                    if blob.sentiment[0] != 0.0:
                        sentiment += blob.sentiment[0]
                        count += 1

        if count == 0:
            sentiment_score = 0
        else:
            sentiment_score = round(sentiment/count * 100)

        movieID = re.sub(".csv", "", file)
        scores[movieID] = sentiment_score


#now 'scores' contains all sentiment scores

#print scores
print "Creating movie data with sentiment scores"
os.chdir("../")
writer = csv.writer(open('movies_with_sentiment.csv', 'wb'))

os.chdir("../data_extractor/output")

with open("movies.csv", "rb") as f:
    reader = csv.reader(f)
    header = next(reader, None)
    header.append('sentiment')
    writer.writerow(header)
    for row in reader:
        movieID = row[0]
        row.append(scores[movieID])
        writer.writerow(row)
