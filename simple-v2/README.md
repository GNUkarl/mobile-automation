The Simple.com Android app recently updated to version 2.1.1 and the UI looks way different.  I'm not sure when the actual 
update occurred, but I've written 4 simple Selendroid tests to demonstrate my skill with Android app automation.


As before, these require the following jars to run:

- Selendroid-client and Selendroid-standalone
        - http://search.maven.org/#search|ga|1|selendroid

- JUnit.jar and hamcrest-core.jar
        - https://github.com/junit-team/junit/wiki/Download-and-Install

So far these tests have just been run with the Eclipse IDE JUnit plugin, but these could easily be added to Jenkins or some other CI


- To do:
	- Find a way to display maps in the "Find ATMs" screen. Currently no map shows up at all during an automated test run.  I believe this is happening because the actual map info is from Android's built-in Google Maps (a different APK) and Selendroid (and every Android automation platform that I've found) can only run and test one single APK at a time.

