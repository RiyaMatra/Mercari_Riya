# Mercari-android-automation

### Project related relevant information
**Pre-Requisite**
1. This is considered as a hypothetical situation and the code written is with the reference to other similar
   shopping applications like Nykaa and Amazon
2. The framework used is POM where each class has its own repository to avoid duplicate code, increase the re-usability
   of common methods & increase the performance.
3. All the dependencies are defined in pom.xml file.

### Base Class : src/main/java/utilities : UIHelper
1. I have created framework in such a way that same framework can be optimised
   further with ios & web by adding more parameters in UIHelper class : setBy method
2. ios_launchApp &  web_launchApp method can be created similarly in UIHelper class
3. **The framework is designed taken into account Web element & not Mobile Element specifically
   considering the future scope of using the same framework to work on web automation in future.**
   Hence, setBy method is used to define the web elements instead of using mobile-specific annotations like : 
   @AndroidFindBy,@iOSXCUITFindBy OR @FindBy to define the mobile elements.
4. All the necessary common methods are defined in this class.

### src/main/java : Common Screen classes
1. Common screen classes like Login, Cart screen, etc - Each screen has its own repository where the elements
   displayed in respective screen & the methods for that respective elements are defined.
2. The segregation of common screen classes results into proper structuring, avoiding duplicate code & helps
   significantly to modify/add/delete easily when app is revamped

### src/main/java : testdata
1. config.properties defines all the pre-requisites/conditions for your application to run on automation like:
   OS platform, execution=local/cloud (like Perfecto/Experitest), envs. if any specific (QA/Staging,etc)
2. Also, same TC can run on cloud device by creating execution=cloudPlatformName
   & creating one more if-loop with change in host name
3. Like android_config.properties, we can create ios_config.properties to pass the data specific to iOS automation.

### Optimisation of code : /test/java folder
1. The End-to-end test cases is written in such a way i.e. they are segregated depending upon the
   functionality of app to be tested
2. Priority attribute ensures the sequence of application is in order w.r.t. functionality of app
3. I have used dependsOnMethods attribute here to save further time by not executing TC2 if TC1 fails
   For example : if login is failed -> There is no point in validating ShowCase screen.
4. This hugely is time-saving when the number of TCs in the automation suite are large in the number.
5. We can write new method to handle if login fails in respective TC.java file itself. I have not added it here at the
   moment because this was a hypothetical scenario.

  
  
 
