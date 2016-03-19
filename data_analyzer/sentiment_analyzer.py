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


print "Cleaning tweet data"
# process all tweets in tweet_data and store in 'scores'

with open("52.csv", 'rb') as f:
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

                print cleanTweet
                print blob.sentiment[0]
                # if sentiment is is not neutral (has positivity or negativity)
                if blob.sentiment[0] != 0.0:
                    sentiment += blob.sentiment[0]
                    count += 1

    if count == 0:
        sentiment_score = 0
    else:
        sentiment_score = round(sentiment/count * 100)

#now 'scores' contains all sentiment scores
