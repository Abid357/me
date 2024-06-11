package lab6;

public class LoveLetter {

	// fields
	private MailServer server;
	private MailClient sophie;
	private MailClient juan;

	// constructor
	public LoveLetter() {
		server = new MailServer();
		sophie = new MailClient(server, "Sophie");
		juan = new MailClient(server, "Juan");
	}

	// method
	public void start() {
		sophie.sendMailItem("Juan", "Hi!");
		juan.printNextMailItem();
	}

	// main method
	public static void main(String[] args) {
		LoveLetter l = new LoveLetter();
		l.start();
	}
}
