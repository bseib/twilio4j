package com.twilio4j.twism;

import com.twilio4j.twiml.Record;

/**
 * These are the possible values that can be returned as a parameter from a transcribeCallback
 * of the {@link Record} verb. It is part of the {@link TranscribeParameters} parameters,
 * which are available via {@link TwilioParameters}
 * 
 * @author broc.seib@gentomi.com
 * @see TranscribeParameters
 * @see TwilioParameters
 */
public enum TranscriptionStatus {
	completed, failed
}
