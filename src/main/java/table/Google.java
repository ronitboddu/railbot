package table;

public class Google {
	private boolean expectUserResponse;
	private RichResponse richResponse;
	private String userStorage;


	public String getUserStorage() {
		return userStorage;
	}

	public void setUserStorage(String userStorage) {
		this.userStorage = userStorage;
	}

	public RichResponse getRichResponse() {
		return richResponse;
	}

	public void setRichResponse(RichResponse richResponse) {
		this.richResponse = richResponse;
	}

	public boolean isExpectUserResponse() {
		return expectUserResponse;
	}

	public void setExpectUserResponse(boolean expectUserResponse) {
		this.expectUserResponse = expectUserResponse;
	}

	
}
