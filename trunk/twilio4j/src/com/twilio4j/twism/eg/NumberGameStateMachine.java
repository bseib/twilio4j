package com.twilio4j.twism.eg;

import com.twilio4j.twiml.TwiML;
import com.twilio4j.twism.TwilioParameters;
import com.twilio4j.twism.TwilioStateMachine;

import static com.twilio4j.twism.eg.NumberGameState.*;

public class NumberGameStateMachine extends TwilioStateMachine<NumberGameState> {
	private static final long serialVersionUID = 1L;
	
	public NumberGameStateMachine() {
		handler(PICK_NUMBER).respondsWith(
			gather(
				say("pick a number between zero and nine.")
			)
			.action(CHECK_NUMBER)
			.numDigits(1)
		);
		handler(CHECK_NUMBER).respondsWith(new TwilioHandler() {
			@Override
			public TwiML getTwiML(TwilioParameters params) {
				char digit = params.Gather().getDigits().charAt(0);
				if ( digit == '5' ) {
					return say("You win! Goodbye.");
				} else if ( digit < '5' ) {
					return gather(
						say("Pick again, higher.")
					)
					.action(CHECK_NUMBER)
					.numDigits(1);
				} else {
					return gather(
						say("Pick again, lower.")
					)
					.action(CHECK_NUMBER)
					.numDigits(1);
				}
			}
		});
	}

	@Override
	public NumberGameState getInitialState() {
		return PICK_NUMBER;
	}

	@Override
	public NumberGameState lookupState(String pathInfo) {
		return NumberGameState.valueOf(pathInfo);
	}

	@Override
	public String getFortyCharacterSecret() {
		return "3440e0fa2eae0a28e5dc58d76793eb151c19acf7";
	}

}