## Twilio java client library ##

I have copied into this project and older copy of the com.twilio.sdk sources for the Twilio java client library from Twilio.
This code is the original Twilio java client library (it is 99% written by Twilio, with a couple minor bug fixes.)

Why include this here? Primarily because the [present day official Twilio-java library](https://github.com/twilio/twilio-java) has third
party dependencies that do not run on App Engine, and, well, I'd like to have a drop-in jar to run a Twilio client on App Engine.

I also happen to dislike the sprawl of external dependencies; it makes it a bit harder to grab the jar and be productive. I am also
annoyed by the use of maven. Sorry -- I love you Twilio guys, but I have _very_ little love for maven.


## Javadocs ##

The javadocs for the original Twilio java client library are in the
[com.twilio.sdk namespace.](http://twilio4j.googlecode.com/svn/trunk/twilio4j/javadoc/com/twilio/sdk/package-summary.html) These docs are somewhat sparse and could use some more flesh. My advice is to download the sources for twilio4j and look at
TwilioRestClient.java and TwilioRestExample.java. All the .java files in com.twilio.sdk.verbs have a one-to-one match with the
[docs at twilio.com](http://www.twilio.com/docs/api/rest).

The proper next step would be to provide a GAE-friendly patch to the official twilio-java project on github.