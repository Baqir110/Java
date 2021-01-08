package WebCrawlerBFS.src.webcrawlerbfs;

import java.io.*;
import java.util.*;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Baqir
 */
public class WebCrawlerBfs {

	// defining my string queue and assigning linked_list to it.
	static Queue<String> myqueue = new LinkedList<>();

// defining a new set of already visited URLS and setting it as HashSet 
	// hash set is unordered collection of data in our case URLs
	// hash set uses hash map to store elements(URls)
	static Set<String> visited = new HashSet<>();
	// now we need to understand how to catch URL using REGEX
	// wo we will use pattern and assign the regex code of URL.
	static String regex = "http[s]*://(\\w+\\.)*(\\w+)";

	// here the regex will look for the similar pattern to catch URL which matches
	// this pattern.
	// now we need a URL
	static String root = "https://www.okan.edu.tr";

	public static void bfs() throws IOException {

		// inserts my root(url) to the queue
		myqueue.add(root);
		// checks the condition if my queue is not empty will execute
		while (!myqueue.isEmpty()) {
			// .poll() removes the element from
			String remove = myqueue.poll();
			// limits my web crawler upto 300 sites.
			if (visited.size() > 300)
				return;
			{

			}
			// initializing my values to false and null to use them later on.
			boolean b = false;
			URL url = null;
			BufferedReader buffer = null;
// while my boolean is not false this while loop will execute.
			while (!b) {
				try {
// sets my url
					url = new URL(remove);
					// using buffer_reader and using to to access/open the url using open stream
					buffer = new BufferedReader(new InputStreamReader(url.openStream()));
					// fixing my b as true to continue the loop.
					b = true;
// here if there is an issue URL java library will show MALformedURL Exception..
				} catch (MalformedURLException e) {
					// prints out the url in which exception occurred.
					System.out.println("exception occured in URL" + remove);
					// now use our stored URL from Queues

					// this is removes the 1st element from the queue and prints

					// but if the queue is empty it will return Null.
					remove = myqueue.poll();
					b = false;
					// so here my boolean will be false if the exception occurs.

					// here if there is an issue URL java library will show IOException..
				} catch (IOException e) {
					System.out.println("exception occured in URL" + remove);
					// now use our stored URL from Queues

					// this is removes the 1st element from the queue and prints

					// but if the queue is empty it will return Null.

					remove = myqueue.poll();
					b = false;
					// so here my boolean will be false if the exception occurs.
				}
			}
			// Initializing String builder to create mutable strings

			// so we can change strings without using another object.
			StringBuilder stringbuilder = new StringBuilder();
// while my buffer is not null this will execute the loop
			while (null != (remove = buffer.readLine())) {
				// append increases the length of string
				// in other words combines characters/strings together.
				stringbuilder.append(remove);

			}
			// converts to string
			remove = stringbuilder.toString();
			// this is to recognize the pattern of URL in order to catch and compile.
			Pattern p = Pattern.compile(regex);
			// matcher matches the pattern defined in regex.
			Matcher matcher = p.matcher(remove);
// find() here basically finds the match of URL/regex code/ pattern and
			// if it's found the loop will execute
			while (matcher.find()) {
				// once the string is found we combine it together.

				String finded = matcher.group();
				// if the urls contains the pattern it will execute this code.
				if (!visited.contains(finded)) {
// adds to the finded urls and then it gets grouped.
					visited.add(finded);
					// finally gets printed and
					System.out.println("found site :" + finded);
					// adds founded urls to the queue.
					myqueue.add(finded);

				}

			}
		}

	}

// this will display the results of 
	public static void websitesCralwed() {
// prints out the output
		System.out.println("WEB CRAWLING RESULTS");
		System.out.println("\n Web sited crawled :");
		// prints the URL found and visited.
		System.out.println(+visited.size() + "\n");
		// prints the URLS.
		for (String remove : visited) {
			System.out.println(remove);

		}
	}

// Main Method To Run the program.
	public static void main(String[] args) throws IOException {
		// here i am calling bfs in try function because it may
		// throw and exception to catch those we need to use
		// try
		// catch.
		try {
			// calling by bfs method.
			bfs();
			// calling by websitesCrawled method

			websitesCralwed();
			// in order to print results and execute them.
		} catch (IOException e) {
			// finally prints the exception if caught
			System.out.println("IOException caught : " + e);
		}
	}
}
