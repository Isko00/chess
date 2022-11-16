package front;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
import backs.*;

public class Reader {
	private InputStreamReader isr = new InputStreamReader(System.in);
	private BufferedReader reader = new BufferedReader(isr);
	
    private Pair[] format(String input) {
		StringTokenizer tokenizer;
		tokenizer = new StringTokenizer(input, "-");
        Pair[] answer = new Pair[2];
        int cnt = 0;
        
        while (tokenizer.hasMoreTokens()) {
	        String i = tokenizer.nextToken();
	        int[] arr = { i.charAt(1) - 49, i.charAt(0) - 65 };
            Pair temp = new Pair(arr);
            answer[cnt] = temp;
            cnt++;
        }
        
        return answer;
    }

    private boolean accepted(String input) {
        return input.matches("[A-H][1-8]-[A-H][1-8]");
    }

    public Pair[] formattedInput() {
		String input = "empty input";
    	try {
    		while (!accepted(input = reader.readLine())) {
    			System.out.println("input format MUST be 'A1-H8'");
    		}
    	} catch (IOException e) {
			e.printStackTrace();
		}
        return format(input);
    }
    
    public void closeRrader() {
    	try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
