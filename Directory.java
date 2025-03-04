package part1;

/************************************************************************/
/*Author: Eigminas Slavinskas*/

/*Purpose: Directory interface gives us the intuition of required
  functionality for phone directory.                                    */

/************************************************************************/

public interface Directory<Entry> {
	// Method inserts new entry to the direcory and returns true if successful
	public boolean insert(Entry obj);
	// Method deles new entry  from the directory and returns true ifsuccesful
	public boolean delete(String str);
	// Method finds and returns extension no.
	public String findExNumber(String str);
	// Method changes person;s number and returns true if successful
	public boolean changeNumber(String str, String newNubmer);
	// Method prints the telephone directory in neatly tabulated fashion
	public void printDirectory();
}
