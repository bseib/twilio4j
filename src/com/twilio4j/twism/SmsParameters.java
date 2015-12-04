/*
 * Copyright 2015 broc.seib@gentomi.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.twilio4j.twism;

/**
 * Twilio will pass the following parameters in addition to the standard TwiML Voice request parameters with its request
 * to the 'statusCallback' URL of {@link com.twilio4j.twiml.Sms}.
 * 
 * @author broc.seib@gentomi.com
 *
 */
public interface SmsParameters {

	/**
	 * @return The Sid for the Sms message
	 */
	public String getMessageSid();

	/**
	 * @return The Sid for the Sms message
	 */
	@Deprecated
	public String getSmsSid();

	/**
	 * @return The 34 character id of the Messaging Service associated to the message.
	 */
	public String getMessagingServiceSid();

	/**
	 * @return The text body of the message. Up to 1600 characters long. (That's up to 10 concatenated messages of
	 *         length 160.)
	 */
	public String getBody();

	/**
	 * @return The number of media items associated with your message
	 */
	public int getNumMedia();

	/**
	 * The ContentTypes for the Media stored at MediaUrl{N}. The order of MediaContentType{N} matches the order of
	 * MediaUrl{N}. If more than one media element is indicated by NumMedia than MediaContentType{N} will be used, where
	 * N is the count of the Media
	 * 
	 * @param n
	 *        which media element.
	 * @return the content type for media element 'n'. If 'n' is out of range, null is returned.
	 */
	public String getMediaContentType(int n);

	/**
	 * A URL referencing the content of the media received in the Message. If more than one media element is indicated
	 * by NumMedia than MediaUrl{N} will be used, where N is the count of the Media
	 * 
	 * @param n
	 *        which media element.
	 * @return the url for media element 'n'. If 'n' is out of range, null is returned.
	 */
	public String getMediaUrl(int n);

	/**
	 * @return The current status of the Sms message. Either 'sent' or 'failed'. This seems to be undocumented now.
	 */
	public String getSmsStatus();
}
