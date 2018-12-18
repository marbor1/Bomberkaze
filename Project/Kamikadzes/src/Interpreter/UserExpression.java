package Interpreter;

public class UserExpression implements SQLExpression{

	private String username;
	
	public UserExpression(String username) {
		this.username = username;
	}
	
	@Override
	public String execute() {		
		return this.username;
	}

}
