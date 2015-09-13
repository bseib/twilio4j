## twilio4j
Java library for creating state machine call flows for Twilio

twilio4j is a Twilio state machine library which allows you to create call flows easily in java. You can install one servlet and implement your code that reacts to Twilio callback hooks with TwiML, etc.

Note: In April 2012, I added the com.twilio.sdk sources for the Twilio java client library (from Twilio), which will allow you to have a Twilio Client on Google App Engine. Read more about the [Original Twilio Java Client Library](https://github.com/bseib/twilio4j/wiki/Original-Twilio-Java-Client-Library).

#### Javadocs
Refer to the twilio4j javadocs here: http://bseib.github.io/twilio4j/javadoc/

#### About this library
The state machine library allows you to create a call flow for Twilio using Java. You compose a state machine in a declarative style of java code, expressing actions with enumerated state names. A servlet advances through the state machine, and carries along a set user parameters from call to call.

Read the [Quick Start](https://github.com/bseib/twilio4j/wiki/Quick-Start) to get started.

#### An example showing TwiML expressed in Java:
```java
    // This coding style is type-safe, has a declarative appearance,
    // and will produce well-formed TwiML.

    handler(PICK_NUMBER).respondsWith(   // http://example.com/some/path/PICK_NUMBER
        gather(
            say("pick a number between zero and nine.")
        )
        .action(CHECK_NUMBER)            // http://example.com/some/path/CHECK_NUMBER
        .numDigits(1)
    );
````

The biggest benefit of this library is that it makes your state machine simpler to express, and simpler to read. Writing state behavior does not involve setting up or decoding urls, or reading parameters at the servlet level. It expresses TwiML completely in Java, in a type-safe manner, so there is no question of constructing well-formed TwiML. Connecting states is expressed with the enumerated types, rather than urls, making it easy to see how the states flow. There is also the benefit of auto-completion in your IDE, if you are using one.

You must of course still understand the underlying mechanism of Twilio. This library is an exact reflection of all the TwiML as [documented by Twilio](http://www.twilio.com/docs/api/twiml/). It just adds the state machine management part.

![Overview image](http://bseib.github.io/twilio4j/img/TwilioCallFlowStateMachine.png)

