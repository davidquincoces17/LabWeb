package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import managers.ManageUsers;
import utils.DB;

public class Login {

	private String mail = "";
	private String pass = "";
	private boolean[] error = {false,false};
	
	public String getMail(){
		return mail;
	}
	
	public void setMail(String mail){
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		
		if (matcher.matches() && mail.length() <= 50) {
			this.mail = mail;
		} else {
			error[0]=true;
		}
	}
	
	public String getPass(){
		return pass;
	}
	
	public void setPass(String pass){
		String regex = "^[A-Za-z\\d@$!%?&_+\\-*\\/#]{8,50}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pass);
		
		if (matcher.matches()) {
			this.pass = pass;
		} else {
			error[0]=true;
		}
	}

	public boolean[] getError() {
		return error;
	}
	
	public boolean isComplete() {
	    return(hasValue(getMail()) && hasValue(getPass()));
	}
	
	public boolean canLogin() {
		String query = "SELECT * FROM users WHERE mail='" + this.mail + "' AND pwd='"+ this.pass + "'";

		PreparedStatement statement = null;
		
		DB db = null ;
		
		try {
			db = DB.getDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean can = false;
		
		try {
			statement = db.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			Boolean bool = result.next(); //found a matching element
			statement.close();
			can = bool;
			
		} catch (SQLException e) {
			e.printStackTrace();
			can = false;
		}
		
		if (!can) {
			error[1] = true;
			mail = "";
			pass = "";
		}
		return can;
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	
}