#!/bin/bash
clear

echo "Yanwen Jin"
#java -cp "/home1/y/yanwen/workspace/CCchallenge"

javac src/DataScience/tweeted.java
java -cp src DataScience.tweeted /tweet_input/tweets.txt /tweet_output/ft1.txt

javac src/DataScience/medianUnique.java
java -cp src DataScience.medianUnique /tweet_input/tweets.txt /tweet_output/ft2.txt