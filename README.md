NYTimesApp 
==========

* The Project designed based on the `clean architecture` concept and `MVVM` design
  
  pattern which the project consists of three modules:

1- `app` module: consists of two layers:-

    * Presentation Layer: responsible for any thing related to the UI exists in this
    layer, which the MVVM pattern applied in this layer.
           
    * Data Layer: provide the data either from the local database or from the network 
    using the helper class that called `NetworkBoundResource`.
       
2- `core` module: consists of two layers:-

    * Gateways Layer: Abstract definition of all the data sources.
        
    * Usecases Layer: defines actions the user can trigger.
    
3- `entity` module: one layer:-

    * Entity Layer: contains the data classes of the whole App.


* I applied the dependency injection using `Dagger2`, you will see it in a separate package called `di` .

* I wrote unit test to test the insertion and loading articles from the local room database, this 

  test exists inside the `ArticlesDaoTest` class.

* I wrote a two UI tests using espresso, one to test the articles list loaded and refreshed 

 properly and another one to test the item of the list clicked to show article details, this 
 
 tests exists inside the `ArticlesFragmentTest` class.

* To generate the coverage report run this command in terminal:

 `./gradlew createDebugCoverageReport` and report will be generated in 
 
 `/build/reports/coverage/debug`

* The app show the list of articles in a screen, and when items on the list are tapped,

 the details shown in another separate screen. But, on Tablet, the app use two pane on 

 the same screen, one for the list of articles and the second for the article details.

* I used the navigation component to navigate between fragments.