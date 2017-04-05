import java.util.*;
import java.io.*;

// This class implements a google-like search engine
public class SearchEngine {

    public HashMap<String, LinkedList<String> > wordIndex;                  // this will contain a set of pairs (String, LinkedList of Strings)	
    public DirectedGraph internet;             // this is our internet graph
    
    
    
    // Constructor initializes everything to empty data structures
    // It also sets the location of the internet files
    SearchEngine() {
	// Below is the directory that contains all the internet files
	HtmlParsing.internetFilesLocation = "internetFiles";
	wordIndex = new HashMap<String, LinkedList<String> > ();		
	internet = new DirectedGraph();				
    }
    
    
    // Returns a String description of a searchEngine
    public String toString () {
	return "wordIndex:\n" + wordIndex + "\ninternet:\n" + internet;
    }
    
    
    void traverseInternet(String url) throws Exception {
    
    	//Update wordIndex
		LinkedList<String> content = HtmlParsing.getContent(url);				//Get list of words 
		Iterator<String> i = content.iterator();
		while(i.hasNext()){														//For each word,
			String word = i.next();
			if (wordIndex.containsKey(word)) wordIndex.get(word).add(url);		//Add url to linkedlist associated with the word
			else {
				LinkedList<String> urlList = new LinkedList<String> ();			//If word is not yet in dictionary, create linkedlist with url
				urlList.add(url);
				wordIndex.put(word, urlList);									//Add key-value (word/linkedlist) pair
			}
		}
		
		//Update internet
		LinkedList<String> outlinks = HtmlParsing.getLinks(url);
		internet.addVertex(url);
		internet.setVisited(url, true);											//Update visited
		i = outlinks.iterator();
		while(i.hasNext()){														//For each outgoing link,
			internet.addEdge(url, i.next());									//Create edge between current url and outgoing link
		}
		
		//Recursive DFS
    	i = outlinks.iterator();												//Reset iterator
    	while(i.hasNext()){
    		String neighbour = i.next();
    		if (!internet.getVisited(neighbour)) traverseInternet(neighbour);	//Visit each neighbour that has not been visited
    	}
	
    } // end of traverseInternet
    
    
    void computePageRanks() {
    	
    	LinkedList<String> allLinks = internet.getVertices();
    	Iterator<String> i = allLinks.iterator();
    	
    	while (i.hasNext()) internet.setPageRank(i.next(), 1);					//Set page rank to 1 for 0th iteration
    	
    	for (int j=0; j<100; j++){												//Iterate 100 times
    		i = allLinks.iterator();											//Reset iterator
    		while (i.hasNext()){												//For each vertex in graph,
    			String link = i.next();
        		Iterator<String> k = internet.getEdgesInto(link).iterator();	//Get list of inbound links
        		double sum = 0;
        		while (k.hasNext()){											//For each inbound link,
        			String neighbour = k.next();
        			sum += internet.getPageRank(neighbour)/internet.getOutDegree(neighbour); //Add PR/C to sum
        		}
        		internet.setPageRank(link, 0.5 + 0.5*sum);						//Update page rank, use dampening factor
    		}
    	}
    } // end of computePageRanks
    
	
   
    String getBestURL(String query) {
    	String bestURL = "";
    	double bestRank = 0;
    	if (wordIndex.containsKey(query)){										//If internet contains query
	    	Iterator<String> i = wordIndex.get(query).iterator();				//Get list of urls with query
		    	while(i.hasNext()){
		    		String url = i.next();
		    		if (internet.getPageRank(url)>bestRank){					//Find url with highest page rank
		    			bestRank = internet.getPageRank(url);
		    			bestURL = url;
		    		}
		    	}
    	}
    	return bestURL;
    } // end of getBestURL
    
    
	
    public static void main(String args[]) throws Exception{		
	SearchEngine mySearchEngine = new SearchEngine();
	mySearchEngine.traverseInternet("http://www.cs.mcgill.ca");
	
	
	mySearchEngine.computePageRanks();
	
	BufferedReader stndin = new BufferedReader(new InputStreamReader(System.in));
	String query;
	do {
	    System.out.print("Enter query: ");
	    query = stndin.readLine();
	    if ( query != null && query.length() > 0 ) {
		System.out.println("Best site = " + mySearchEngine.getBestURL(query));
	    }
	} while (query!=null && query.length()>0);			
    } // end of main
}