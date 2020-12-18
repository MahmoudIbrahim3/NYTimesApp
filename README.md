NYTimesApp 
==========

* The Project designed based on the clean architecture concept and MVVM design
pattern which the project consists of three modules:

#### app module:
consists of 2 layers:-
    1- Presentation Layer: responsible for any thing related to the UI exists in this layer, 
       which the MVVM pattern applied in this layer.
    2- Data Layer: provide the data either from the local database or from the network using the 
       helper class that called `NetworkBoundResource`.
       
#### core module:
consists of two layers:-
    3- Gateways Layer: Abstract definition of all the data sources.
    4- Usecases Layer: defines actions the user can trigger.
    
#### entity module: 
contains one layer:-
    5- Entity Layer: contains the data classes of the whole App.

* I applied the dependency injection using `Dagger2`, you will see it in a separate package called `di`.

* I wrote unit test to test the insertion and loading articles from the local room database, this 
test exists inside the `ArticleDaoTest` class.

* I wrote a two UI tests using espresso, one to test the articles list loaded and refreshed 
properly and another one to test the item of the list clicked to show article details, this 
tests exists inside the `ArticleFragmentTest` class.


