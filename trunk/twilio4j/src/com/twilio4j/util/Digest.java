package com.twilio4j.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Digest {
	
	// Whoops. BigInt does not provide leading zero's. So doing a SHA1 hash in any other
	// code other than this java lib may produce an incorrect "mismatch". So Instead, take
	// the other incoming SHA1 has and turn it into a BigInteger, then do the compare.
	// see the fn isEqualSHA1Hashes()
	//
	// Dont' get tempted to change the hexHashSHA1 function to put the leading zero in there
	// because the database is full of these strings in the database.
	
	static public String hexHashSHA1(String phrase) {
		
		byte[] phraseBytes = phrase.getBytes();
		try {
			MessageDigest hasher = MessageDigest.getInstance("SHA");
			hasher.reset();
			hasher.update(phraseBytes);
			BigInteger digest = new BigInteger(1, hasher.digest());
			return digest.toString(16);
		}
		catch (NoSuchAlgorithmException e) {
			// I never expect to get this message unless I supply
			// the wrong digest string
			throw new RuntimeException(e);
		}
	}
	
	static public boolean isEqualSHA1Hashes(String hash1, String hash2) {
		BigInteger check1 = new BigInteger(hash1, 16);
		BigInteger check2 = new BigInteger(hash2, 16);
		return (check1.equals(check2));
	}

}
