import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			HashMap<String, Integer> hashMap = new HashMap<>();
			String userInput = null;
			do {
				System.out.println("Do you want to Search?");
				System.out.println("Please enter your choice:");
				System.out.println("1 : Web Crawling");
				System.out.println("2 : Word Completion");
				System.out.println("3 : Spell Checker");
				System.out.println("4 : Search Frequecy ");
				System.out.println("5 : Keyword Frequency Count");
				System.out.println("6 : Page Ranking ");
				System.out.println("7 : Exit");
				String input = sc.next();
				switch (input) {
				case "1": {
					try {
						Crawler.crawl();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
				case "2": {
					System.out.println("********Provie Input Search*************************");
					String keyword = sc.next();
					if (!hashMap.containsKey(keyword)) {
						hashMap.put(keyword, 1);
					}else {
						hashMap.put(keyword, hashMap.get(keyword)+1);
					}
					TrieTree.autoSuggest(keyword);
					break;
				}
				case "3": {
					try {
						System.out.println("**********Provide Your Input Search************");
						String keyword = sc.next();
						if (!hashMap.containsKey(keyword)) {
							hashMap.put(keyword, 1);
						}else {
							hashMap.put(keyword, hashMap.get(keyword)+1);
						}
						SpellCorrector corrector = new SpellCorrector();
						corrector.useDictionary("src//dictonary.txt");
						String suggestion = corrector.suggestSimilarWord(keyword);
						if (suggestion == null) {
							suggestion = "No similar word found";
						}
						System.out.println("Suggestion is: " + suggestion);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
				case "4":{
					System.out.println("Here is the list of Search Words and their counts:");
					for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
						String key = entry.getKey();
						Integer val = entry.getValue();
						System.out.println("Word: "+ key + " Count: "+ val);
					}
				}
				case "5":{
					System.out.println("Enter a word to count its frequency : ");
					String wordToCount = sc.next();
					CountFrequency countFrequency = new CountFrequency();
					try {
						countFrequency.countWords(wordToCount);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
				case "6": {
					System.out.println("Please enter Search Query");
//					Scanner sc1 = new Scanner(System.in);
				    String key = sc.nextLine();
				    System.out.println("------------------Search Result-------------------");
				    File folder = new File("src\\TxtFiles\\");
		            File[] listOfFiles = folder.listFiles();
		            ArrayList<String> files = new ArrayList<String>();
		            for (File file : listOfFiles) {
		                if (file.isFile()) {
		                	   int i=0;
		                       files.add(file.getName());
		                       i++;          
		                }
		            }
		            try {
					PageRank.FrqBuilder(files, key.toLowerCase());
		            }
		            catch(IndexOutOfBoundsException e)
		            {
		            	System.out.println("No Results Found");
		            }
//		            sc1.close();
				}
				case "7": {
					System.out.println("Exited...");
					System.exit(0);
				}
				default: {
					System.out.println("Please provide valid input!!!!!!!!!!");
					break;
				}
				}
				System.out.println("Do you want to continue?");
				userInput = sc.next();
			} while (!userInput.equalsIgnoreCase("no"));
			sc.close();
		}
		
		System.out.println("Closing our Web Search Engine!!!!!!!!!!!!!!!");
	}
}
