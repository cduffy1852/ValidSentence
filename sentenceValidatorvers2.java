package sentenceValidator;

public class sentenceValidatorvers2 {

	public static void main(String[] args) {
		// calling my unit test
		unitTest();
	}

	static void unitTest() {

		// putting all my test sentences into an array
		String[] strg = { "The number is 1.", "The number is 2.", "The number is 3.", "The number is 4.",
				"The number is 5.", "The number is 6.", "The number is 7.", "The number is 8.", "The number is 9.",
				"The number is 10.", "The number is 11.", "The number is 12.", "The number is 13.", "The number is 14.",
				"The number is 15.", "The number is 111.", "The quick brown fox said “hello Mr lazy dog”.",
				"The quick brown fox said hello Mr lazy dog.", "One lazy dog is too few, 13 is too many.",
				"One lazy dog is too few, thirteen is too many.", "How many \"lazy dogs\" are there?",
				"The quick brown fox said \"hello Mr. lazy dog\".", "the quick brown fox said “hello Mr lazy dog\".",
				"\"The quick brown fox said “hello Mr lazy dog.\"", "One lazy dog is too few, 12 is too many.",
				"Are there 11, 12, or 13 lazy dogs?", "There is no punctuation in this sentence"};

		// for each string in the array put it into the validator
		for (int i = 0; i < strg.length; i++) {
			if (validator(strg[i]) == true) {
				// if the validator returns true the sentence is valid
				System.out.println(strg[i]);
				System.out.println("Valid sentence");
				System.out.println(" ");
			} else {
				// if the validator returns false the sentence is invalid
				System.out.println(strg[i]);
				System.out.println("Invalid sentence");
				System.out.println(" ");
			}
		}
	}

	static boolean validator(String sentence) {

		// getting the last character in the sentence
		char last = sentence.charAt(sentence.length() - 1);
		// creating a variable for the frequency of quotation marks
		int occurrence = 0;
		// string that holds the names of the errors
		String errStr = "";

		// if the sentence doesn't start with a capital letter sentence is invalid
		if (!Character.isUpperCase(sentence.charAt(0))) {
			errStr += "InvBeg-";
		}

		// if the sentence doesn't end with ., ?, ! sentence is invalid
		if (last != '.' && last != '?' && last != '!') {
			errStr += "InvEnd-";
		}

		//used when multiple digits are in the sentence
		boolean bigNumFound = false;

		// for loop to get the char at i
		for (int i = 0; i < sentence.length() - 1; i++) {

			// a is the character at i as an integer
			int a = Character.getNumericValue(sentence.charAt(i));

			if (bigNumFound == true) {
				if (a >= 0 && a < 10) {
					// if there is a big number and a is a number and continue
				//	System.out.println("bigNum");
					continue;
				} else {
					bigNumFound = false;
				//	System.out.println("finished bigNum");
					// otherwise big number = false and continue
					continue;		
				}
			}
			
			int b = Character.getNumericValue(sentence.charAt(i + 1));
			// b is the character after i as an integer

			if (a >= 0 && a < 10) {
				// a is a number between 0 - 9

				if (b >= 0 && b < 10) {
					// b is a number between 0 - 9
					
				//	System.out.println("a " + a);
				//	System.out.println("b " + b);

					if (a == 1 && (b >= 0 && b < 3)) {

						// a == 1 , b is between 0 and 3 
						// number is 10, 11, 12 or 100, 101 etc
						// need to check if next char is a digit
						
						int c = -1;
						
						if ( i + 2 <= sentence.length()) {
							// if i + 2 isn't out of bounds of the string then c = the character after b as an integer
							 c = Character.getNumericValue(sentence.charAt(i + 2));
						}
		
					//	System.out.println("c " + c);
						
						if (c >= 0 && c < 9) {
							// c is a digit so we have a three digit big number
							bigNumFound = true;
							
						} else {
							// number is 10, 11 or 12 so sentence is invalid
							errStr += "InvTeenDig-";
						}
					} else {
						// number is 13, 14, 15,...99 therefore sentence is valid
						bigNumFound = true;
					}

				} else {
					// otherwise the number is only between 0 - 9 and therefore invalid in the sentence
					errStr += "InvSngDig-";
				}
			} 
			
			if (sentence.charAt(i) == '"') {
				// if the char at i is a quotation mark add a count to occurrences
				occurrence++;
			}

			if (sentence.charAt(i) == '.' || sentence.charAt(i) == '!' || sentence.charAt(i) == '?') {
				// if i is a ., ! or ? at any point other than the end sentence is invalid
				errStr += "InvMid-";
			}
		}

		// if occurrences is an odd number sentence is invalid
		if (occurrence % 2 == 1) {
			errStr += "InvQuo";
		}
		
		// if errStg is bigger than 0 then there has been an error in the sentence and its invalid
		if (errStr.length() > 0) {
			// system.out i used to test
		//	System.out.println("====" + errStr);
			return false;
		}
		return true;
	}

}
