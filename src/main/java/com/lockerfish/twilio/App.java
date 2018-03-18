package com.lockerfish.twilio;

import java.util.Scanner;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;

/**
 * HelloWorld
 */
public class App {

	public static void main(String[] args) {

		String accountSid = System.getProperty("TWILIO_ACCOUNT_SID"); // Your Account SID from www.twilio.com/user/account
		String authToken = System.getProperty("TWILIO_AUTH_TOKEN"); // Your Auth Token from www.twilio.com/user/account
		String fromNumber = System.getProperty("TWILIO_OWNED_NUMBER"); // A number owned by this account

		Scanner reader = new Scanner(System.in);
		System.out.print("Enter number to SMS: ");
		String toNumber = reader.nextLine();

		System.out.println("Enter message: ");
		String sms = reader.nextLine();

		Twilio.init(accountSid, authToken);

		Message message = Message.creator(new PhoneNumber(toNumber), // To number
				new PhoneNumber(fromNumber), // From number
				sms // SMS body
		).create();

		System.out.println("message sid: " + message.getSid() + " sent.");
	}
}
