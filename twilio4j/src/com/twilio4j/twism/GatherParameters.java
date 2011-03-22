/*
 * Copyright 2011 broc.seib@gentomi.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.twilio4j.twism;

/**
 * Twilio will pass the following parameters in addition to the standard TwiML Voice request parameters with its request to the 'action' URL of {@link com.twilio4j.twiml.Gather}.
 * 
 * @author broc.seib@gentomi.com
 *
 */
public interface GatherParameters {
	/**
	 * @return The digits the caller pressed, excluding the finishOnKey digit if used.
	 */
	public String getDigits();
}
