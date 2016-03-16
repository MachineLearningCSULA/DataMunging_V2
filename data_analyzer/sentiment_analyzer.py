'''

import csv
import re
from textblob import TextBlob
import os


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

'''