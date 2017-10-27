package gameObjects;


public class TriviaCard {
	
	private final static int MAX_CHOICES = 4;
	
	private String question;
	private String correct;
	private String[] allChoices;
	
	/**
	 * Constructor to compose {@code AQuiz} from a list of string.<br>
	 * List-first will become the question.<br>
	 * List-second should be the correct answer.<p>
	 * Note:<br>
	 * This constructor will insert the correct answer with random position.<br>
	 * @param list of strings with question, answer, wrong answers
	 */
	public TriviaCard(List<String> list){
		question = list.get(0);
		correct = list.get(1);

		if (list.size()-1 > MAX_QUESTIONS)			
			allAnswers = new String[MAX_QUESTIONS]; 
		else
			allAnswers = new String[list.size()-1];
		
		int correctIndex = (int) (Math.random() * allAnswers.length);
		int listIndex = 2;
		for (int i=0; i < allAnswers.length; i++){
			if (i == correctIndex){
				allAnswers[i] = correct;
			} else {
			allAnswers[i] = list.get(listIndex);
			listIndex = listIndex + 1;
			}
		}
	}

	/**
	 * Getter for the question
	 * @return the question as {@code String}
	 */
	public String getQuestion() { return new String (question); }

	/**
	 * Getter for the correct answer
	 * @return the answer as {@code String}
	 */
	public String getCorrect() { return new String (correct); }

	/**
	 * Getter for the array of all possible answers
	 * @return allAnswers as a string-array
	 */
	public String[] getAllAnswers() { return allAnswers.clone(); }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = "Question: " + question;
		for (int i = 0; i < allAnswers.length; i++)
			str = str + "Answer " + i + ":" + allAnswers[i] + "\n";
		return question + "?\n"
			+ "Correct:" + correct + "\n"
			+ str;
	}
}
