simple Crawler program written in java
this program includes a Crawler class that is designed to be extended to add aditional utilities(like the CrawlerB and CrawlerImg classes)
Crawler class can be created using two constructurs:
  Crawler(rootURL, nCycle, regex)
  Crawler(rootURL, nCycle, regex, siteBoundary)

  rootURL: is the URL that the crawler will start branching from, branching follows a depth limited BFS procedure
  nCycle: is a number that represents how many parsing cycles the program will go through before calling void Cycle(), its used mostly for debugging and can be used for 
          real time programs that can update the UI or perform another routine check
  regex: is the Regular expression string that will be used to parse the HTML content of the URLs, the parsed subStrings will be added to Crawler.fileBuffer, which is an instance of "Set" Class
  siteBoundary: is a string that the URL parser uses to limit which URLs to explore, requiring the substring siteBoundary to exist in the URL body, in the future it will support replacing the default regex for URLs{"https:[a-zA-Z0-9._%+-/]+"}

  the Set class is a personal implementation of Sets, it stores data in a Container class, the Container contains a{String data} field and a {int score} field, the score is used to evaluate how popular a data string is and also has a utility
  to replace the {List<Container> visited} list that is used with BFS implementations
