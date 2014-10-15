package hr.ponge.pfa.web;

import javax.validation.Payload;

public class Severity {
	public static class Info implements Payload {
	};
	
	public static class Warn implements Payload {
	};

	public static class Error implements Payload {
	};

}
