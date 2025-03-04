/************************************************************************/
/*Author: Eigminas Slavinskas*/

/*Purpose: Implement directory interface and provide functionality for  */
/*         storing and manipulating entries.                            */

/************************************************************************/
 
package part2;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import part1.Directory;
import part1.Entry;

public class HashDirectory implements Directory<Entry> {
	
	// private fields
	private List<Entry>[] list = new LinkedList[6000]; // array of lists
	
	public HashDirectory() {
		for (int i = 0; i < list.length; i++) {
			list[i] = new LinkedList<Entry>();
		}
	}
	
	public boolean insert(Entry obj) {

		int hash = obj.hashCode(); // get hash code
		
		// List is empty
		if (list[hash].isEmpty()) {
			list[hash].add(obj); // store entry
			return true;
		}

		// Insert new element within the list
		ListIterator<Entry> iterator = list[hash].listIterator();

		do {
			Entry e = iterator.next();
			
			if (obj.getSurname().compareToIgnoreCase(e.getSurname()) < 0) {
				iterator.previous();
				iterator.add(obj); // store entry
				return true;
			}
		} while (iterator.hasNext());

		// Insert new element at the end of the list
		list[hash].add(obj);
		return true;
	}
	
	public boolean delete(String str) {

		// First we need to check if user entered surname or number

		// Case 1: Surname was entered
		if (isAlpha(str)) {

			int hash = new Entry(str, null, null).hashCode();

			ListIterator<Entry> iterator = list[hash].listIterator();
			while (iterator.hasNext()) {
				Entry e = iterator.next();
				if (e.getSurname().equals(str)) {
					iterator.remove();
					return true;
				}
			}
		}
		
		// case 2: Number was entered
		else if (isNumeric(str)) {
			for (int i = 0; i < list.length; i++) {
				ListIterator<Entry> iterator = list[i].listIterator();
				while (iterator.hasNext()) {
					Entry e = iterator.next();
					if (e.getNumber().equals(str)) {
						iterator.remove();
						return true;
					}
				}
			}
		}
		return false;
	}

	public String findExNumber(String str) {

		int hash = new Entry(str, null, null).hashCode(); // hash code depends only on surname

		ListIterator<Entry> iterator = list[hash].listIterator();
		while (iterator.hasNext()) {
			Entry e = iterator.next();
			if (e.getSurname().equals(str)) {
				return e.getNumber();
			}
		}
		return null;
	}

	public boolean changeNumber(String str, String newNubmer) {

		int hash = new Entry(str, null, null).hashCode(); // hash code depends only on surname

		ListIterator<Entry> iterator = list[hash].listIterator();
		while (iterator.hasNext()) {
			Entry e = iterator.next();
			if (e.getSurname().equals(str)) {
				e.setNumber(newNubmer);
				iterator.set(e);
				return true;
			}
		}
		return false;
	}

	public void printDirectory() {
		for (int i = 0; i < list.length; i++) {
			ListIterator<Entry> iterator = list[i].listIterator();
			while (iterator.hasNext()) {
				Entry e = iterator.next();
				System.out.format("%-20s", e.getSurname());
				System.out.format("%-20s", e.getInitials());
				System.out.format("%-20s", e.getNumber());
				System.out.println();
			}
		}
	}

	// toString method is used in GUI to set text to text field
	public String toString() {
		String string = new String();

		String s0 = new String();
		String s1 = new String();
		String s2 = new String();
		String s3 = new String();

		for (int i = 0; i < list.length; i++) {
			ListIterator<Entry> iterator = list[i].listIterator();
			while (iterator.hasNext()) {
				Entry e = iterator.next();
				s1.format("%-20s", e.getSurname());
				s2.format("%-20s", e.getInitials());
				s3.format("%-20s", e.getNumber());

				string = string + String.format("%-20s", e.getSurname()) + String.format("%-20s", e.getInitials())
						+ String.format("%-20s\n", e.getNumber());
			}
		}
		return string;
	}

	// the following private methods are needed for data validation
	
	private boolean isAlpha(String name) {
		return name.matches("[a-zA-Z]+");
	}

	private boolean isNumeric(String name) {
		return name.matches("[0-9]+");
	}

}
