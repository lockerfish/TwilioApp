package com.lockerfish.twilio;

import java.util.Scanner;
import java.net.URI;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.Call;

/**
 * HelloWorld
 */
public class App {

	public static void main(String[] args) {

		final String ACCOUNT_SID = System.getProperty("TWILIO_ACCOUNT_SID"); // Your Account SID from www.twilio.com/user/account
		String AUTH_TOKEN = System.getProperty("TWILIO_AUTH_TOKEN"); // Your Auth Token from www.twilio.com/user/account
		String OWNED_NUMBER = System.getProperty("TWILIO_OWNED_NUMBER"); // A number owned by this account

		Scanner reader = new Scanner(System.in);
		System.out.print("Enter number to SMS: ");
		String toNumber = reader.nextLine();

		System.out.println("Enter message: ");
		String sms = reader.nextLine();

		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		// Message message = Message.creator(new PhoneNumber(toNumber), // To number
		// 		new PhoneNumber(OWNED_NUMBER), // From number
		// 		sms // SMS body
		// ).create();
		// System.out.println("message sid: " + message.getSid() + " sent.");
		TwiML twiml = new VoiceResponse.Builder().say(new Say.Builder("Hello World!").build())
				.play(new Play.Builder("https://api.twilio.com/cowbell.mp3").loop(5).build()).build();
		try {
			Call call = Call.creator(new PhoneNumber(toNumber), // To number
					new PhoneNumber(OWNED_NUMBER), // From number
					// Read TwiML at this URL when a call connects (hold music)
					new URI("http://twimlets.com/holdmusic?Bucket=com.twilio.music.ambient")).create();

			System.out.println("message sid: " + call.getSid() + " sent.");
		} catch (Exception e) {
			//TODO: handle exception
		}
	}
}
