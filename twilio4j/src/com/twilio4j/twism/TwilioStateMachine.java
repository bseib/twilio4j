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

import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.ServletException;

import com.twilio4j.twiml.Conference;
import com.twilio4j.twiml.Dial;
import com.twilio4j.twiml.Gather;
import com.twilio4j.twiml.Hangup;
import com.twilio4j.twiml.NestInDial;
import com.twilio4j.twiml.NestInGather;
import com.twilio4j.twiml.Number;
import com.twilio4j.twiml.Pause;
import com.twilio4j.twiml.Play;
import com.twilio4j.twiml.Record;
import com.twilio4j.twiml.Redirect;
import com.twilio4j.twiml.Reject;
import com.twilio4j.twiml.Say;
import com.twilio4j.twiml.Sms;
import com.twilio4j.twiml.TwiML;


abstract public class TwilioStateMachine<E extends Enum<?>> extends TwilioStateMachineServlet {
	private static final long serialVersionUID = 1L;
	
	final static private Logger logger = Logger.getLogger(TwilioStateMachine.class.getSimpleName());
	
	@Override
	protected String advanceState(String pathInfo, TwilioParameters tp) throws ServletException {
		// return twiml from handler, or return null if it is just a callback.
		// first find out what state is desired.
		E state;
		if ( (pathInfo!=null) && (pathInfo.length()>1) ) {
			state = lookupState(pathInfo.substring(1));
		} else {
			state = getInitialState();
		}
		TwilioHandler handler = handlerMap.get(state);
		if ( handler != null ) {
			TwiML twiml = handler.getTwiML(tp);
			String className = twiml.getClass().getSimpleName();
			logger.info("className="+className);
			if ( !("TwiML".equals(className)) ) {
				// if the root item is not a TwiML, but a Gather, or Say, or some subclass,
				// wrap it in a TwiML so that our code can be uniform
				twiml = new TwiML(twiml);
			}
			String contextPath = tp.getRequest().getContextPath();
			String servletPath = tp.getRequest().getServletPath();
			String baseUrl;
			if ( contextPath.endsWith("/") ) {
				baseUrl = contextPath + servletPath.substring(1) + "/";
			} else {
				baseUrl = contextPath + servletPath + "/";
			}
			return twiml.toXml(baseUrl);
		} else {
			TwilioCallback callback = callbackMap.get(state);
			if ( callback != null ) {
				callback.execute(tp);
				return null;
			} else {
				throw new ServletException("No such state handler found.");
			}
		}
	}

	abstract protected E getInitialState();
	abstract protected E lookupState(String pathInfo);

	
	public interface TwilioHandler {
		public TwiML getTwiML(TwilioParameters params);
	}
	public interface TwilioCallback {
		public void execute(TwilioParameters params);
	}
	
	private HashMap<E, TwilioHandler> handlerMap = new HashMap<E, TwilioHandler>();
	private HashMap<E, TwilioCallback> callbackMap = new HashMap<E, TwilioCallback>();

	public RespondsWith<E> handler(E state) {
		return new RespondsWith<E>(state, handlerMap);
	}

	public Executes<E> callback(E state) {
		return new Executes<E>(state, callbackMap);
	}

	public class RespondsWith<EE> {
		private EE state;
		private HashMap<EE, TwilioHandler> mapp; 
		protected RespondsWith(EE state, HashMap<EE, TwilioHandler> mapp) {
			this.state = state;
			this.mapp = mapp;
		}
		public void respondsWith(TwilioHandler code) {
			mapp.put(state, code);
		}
		public void respondsWith(final TwiML twiml) {
			mapp.put(state, new TwilioHandler() {
				@Override
				public TwiML getTwiML(TwilioParameters params) {
					return twiml;
				}
			});
		}
	}

	public class Executes<EEE> {
		private EEE state;
		private HashMap<EEE, TwilioCallback> callbackMapp; 
		protected Executes(EEE state, HashMap<EEE, TwilioCallback> callbackMapp) {
			this.state = state;
			this.callbackMapp = callbackMapp;
		}
		public void executes(TwilioCallback code) {
			callbackMapp.put(state, code);
		}
	}

	
	/*
	 * These methods will permit code to be written in a "declarative" style.
	 * Attributes of each verb can be chained on in a "builder" style.
	 * Only those verbs that have an "action" need generic typing (since action is the enum state)
	 */
	final public TwiML twiml(TwiML... twiml) {
		return new TwiML(twiml);
	}
	final public Say say(String phrase) {
		return new Say(phrase);
	}
	final public Play play(String audioUrl) {
		return new Play(audioUrl);
	}
	final public Gather<E> gather(NestInGather... nested) {
		return new Gather<E>(nested);
	}
	final public Record<E> record() {
		return new Record<E>();
	}
	final public Sms<E> sms(String textBody) {
		return new Sms<E>(textBody);
	}
	final public Dial<E> dial(NestInDial... nested) {
		return new Dial<E>(nested);
	}
	final public Number<E> number(String number) {
		return new Number<E>(number);
	}
	final public Conference<E> conference(String roomName) {
		return new Conference<E>(roomName);
	}
	final public Hangup hangup() {
		return new Hangup();
	}
	final public Redirect<E> redirect(E nextState) {
		return new Redirect<E>(nextState);
	}
	final public Reject reject() {
		return new Reject();
	}
	final public Pause pause() {
		return new Pause();
	}

}
