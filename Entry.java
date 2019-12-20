package part1;

/************************************************************************/
/*Author: Eigminas Slavinskas*/

/*Purpose: Represent a single entry*/

/************************************************************************/

public class Entry{
	
	// private fields
	
	private String surname;  // surname of the contact
	private String initials; // initials of the contact
	private String number;   // number of the contact
	
	public Entry(String surname, String initials, String number){
		// check if surname start with capital letter
		if(surname.substring(0, 1).hashCode() >= 65 && surname.substring(0, 1).hashCode() <= 90){
			this.surname = surname;
			this.initials = initials;
			this.number = number;
		}
		// if surname starts with lower case letter, capitalise it!
		else{
			this.surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
			this.initials = initials;
			this.number = number;
		}
	}
	// getters and setters
	public String getSurname(){
		return surname;
	}
	public String getInitials(){
		return initials;
	}
	public String getNumber(){
		return number;
	}
	public void setNumber(String number){
		this.number = number;
	}
	/**
	 * The following methods are not used in this project.
	 * They are here for the future use if needed.
	 */
	public void setInitials(String initials){
		this.initials = initials;
	}
	public void setSurname(String surname){
		this.surname = surname;
	}
	// equals method if there is a need to compare entries
	@Override
	public boolean equals(Object obj) {
		// reflexitivity
		if(this == obj)
			return true;
		// non-nullity
		if(!(obj instanceof Entry))
			return false;
		// consistency
		Entry e = (Entry) obj;
		return surname == e.getSurname() && number == e.getNumber();
	}

	/*hash code is calculated by the following formula: 
	 * 
	  if name consists of 3 and more letter then
	  hash = ((first * 100 + second) * 100) + third;
	  
	  if name consists of 2 letters
	  hash = (first * 100 + second) * 100;
	  
	  if name consists of 1 letter
	  hash = first - 64;*/
	public int hashCode() {
			
	int first, second, third;
    	
    	if(surname.length() >= 3){
    		first = surname.charAt(0)-64;
    		second = surname.charAt(1)-96;
    		third = surname.charAt(2)-96;
    		    		
    		return ((first * 100 + second) * 100) + third;
    	}
    	
    	if(surname.length() == 2){
    		first = surname.charAt(0)-64;
    		second = surname.charAt(1)-96;
    		return (first * 100 + second) * 100;
    	}
    	
    	else{
    		first = surname.charAt(0)-64;
    		return first;
    	}
	}
}
 