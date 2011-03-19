package com.twilio4j.twism.eg;

public enum VoiceRecordState {
	// by convention handlers start with a H_
	H_GATHER_CALL_IN_CODE,
	H_CHECK_CALL_IN_CODE,
	H_RECORD_MESSAGE,
	H_REVIEW_MESSAGE,
	H_REVIEW_MESSAGE_CHOICE,
	H_MESSAGE_READY_GOODBYE,

	// by convention callbacks start with a C_
	C_HANGUP,
}
