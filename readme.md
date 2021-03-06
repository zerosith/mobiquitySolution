# Solution to challenge


Hello developer here are the instructions to run the solition for the mobiquity inc challenge

  - Rationale
  - Run the code
  - Edit input file
  - Run the tests
  
You will see two folders: *packerSolution* and *packerSolutionTest*.

*packerSolution* is the solution to the challenge, this maven project creates the **packerSolution-0.1.jar** library file

packerSolution requires [maven](https://maven.apache.org)  to run.

### Rationale

The approach used for the solution was a dynamic programming technique.
In order to obtain the values of the items and the weights of the items
Some string manipulation was needed.

After obtaining the data in the Array data structure, a 2-dimensional array is created
To store the possible values of the sum of the values of the different items in each test
case.


### Run the Code

To run the code you need packerSolutionTest which is the app that imports the **packerSolution-0.1.jar library**

But first you need to install the **packerSolution-0.1.jar** library inside your maven local repository:

```sh
$ cd packerSolution
$ mvn install:install-file -Dfile=target/packerSolution-0.1.jar -DgroupId=com.mobiquityinc -DartifactId=packerSolution -Dversion=0.1 -Dpackaging=jar 
```
 This will install the **packerSolution-0.1.jar** into your local maven repo
 
 Then run the packerSolutionTest app in order to check if the import of **packerSolution-0.1.jar** is correct
 ```sh
$ cd packerSolutionTest  
$ mvn exec:java 
```

### Edit input file
To edit the test case use file using the packerSolutionTest app 
edit *input.txt* file under directory ```packerSolutionTest/src/main/resources```
 
### Run the Tests

To run the tests in packerSolution

```sh
$ cd packerSolution
$ mvn clean test
```

