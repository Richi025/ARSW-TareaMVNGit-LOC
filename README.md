
# TareaMVNGit-LOC


In a program to count the lines of code of a source file, which receives a three-letter parameter and the name of a file with the source code.

For example, a typical invocation would be:
```
java -jar TaskMVNGit-LOC-1.0-SNAPSHOT.jar (phy|loc) "filepath"
```
If the invocation parameter is “**phy**”, the program prints the physical lines of the source code.

If the parameter is “**loc**”, the program will print the lines of code found. These are the physical lines without the comments and blank lines.

In the following path you will have two files "*Example.java*" and "*Example2.java*" with which you can populate the project.

```
src/main/java/edu/escuelaing/arsw/ASE/app/
```
### LOC/h Calculation

To calculate LOC, 198 lines of code are taken into account, in 9 hours. So your productivity for this project is **22 LOC/h**.

![img.png](images%2Fimg.png)

## Starting

In order to use the project on your system, you can access the following link and download a compressed file of it.

You can also clone the file using the following command.

```
git clone https://github.com/Richi025/ARSW-TareaMVNGit-LOC.git
```
if you want to run the application use the command.

```
java -jar target/TareaMVNGit-LOC-1.0-SNAPSHOT.jar phy "src/main/java/edu/escuelaing/arsw/ASE/app/"
```

### Previous requirements

It is necessary to have Maven and Java installed, preferably in their latest versions.

```
Download Maven at http://maven.apache.org/download.html 

Follow the instructions at http://maven.apache.org/download.html#Installation
```
```
Download Java at https://www.java.com/es/download/ie_manual.jsp
```


### Installing

Once you have the cloned project in your repository. Follow the steps below to launch the program successfully

1. Open a terminal and enter the folder where I clone the repository.

2. Use the following command to compile and clean the target directory.
```
mvn clean compile
```
3. Now use the following command to package the project as a JAR file.

```
mvn package
```

4.Now you can run the project using the following command.

```
#To get the physical lines of files in a directory.

java -jar target/TareaMVNGit-LOC-1.0-SNAPSHOT.jar phy "src/main/java/edu/escuelaing/arsw/ASE/app/"
```

```
#To get the logical lines of files in a directory.

java -jar target/TareaMVNGit-LOC-1.0-SNAPSHOT.jar loc "src/main/java/edu/escuelaing/arsw/ASE/app/"
```

```
#To obtain the logical lines of a specific file.

java -jar target/TareaMVNGit-LOC-1.0-SNAPSHOT.jar loc "src/main/java/edu/escuelaing/arsw/ASE/app/Example.java"
```
![imgExe.png](images%2FimgExe.png)

## Running the tests

To run the tests you can use the following Maven command

```
mvn test
```
![imgTest.png](images%2FimgTest.png)

### Example tests

4 unit tests were carried out, which test the operation of the code methods, additionally 2 tests were carried out for validity against the answers obtained through the console.

```
The style of the tests is as follows:

    private String testFilesPath = "src/main/java/edu/escuelaing/arsw/ASE/app";
    private String[] args = {"phy", testFilesPath + "/Example.java"};

    @Test
    public void testCountPhysicalLines() {
        LineCounter lineCounter = new LineCounter();
        List<File> files = lineCounter.getFiles(args[1]);
        try {
            int count = lineCounter.countPhysicalLines((files.get(0)));
            assertEquals(17, count);
        } catch (Exception e) {
            fail("Unexpected error counting physical lines: " + e.getMessage());
        }
    }

In these tests we compare that the physical lines of the *Example.java* file are correctly chosen 
```
## Design
For the design, the following class diagram was made. In which it was considered to have a class for all the methods that were going to be counted and the main class that uses these methods.
The methods considered are 3:

* main: It is the main entry point of a program. It is the function or method where the execution of the program begins.

* getFiles: to get the files from the path.

* countPhysicalLines: count physical lines.

* countLogicalLines: count logical lines.

![img_1.png](images%2Fimg_1.png)

### Phase architecture

to be able to run the application using "*java -jar*" including in the "pom.xml" file and specifying the main class.
```
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>3.1.0</version>
        <configuration>
          <archive>
            <manifest>
              <addClasspath>true</addClasspath>
              <mainClass>edu.escuelaing.arsw.ASE.app.CountLines</mainClass>
            </manifest>
          </archive>
        </configuration>
      </plugin>
    </plugins>
```
## Built with

* [Maven](https://maven.apache.org/) - Dependency management
* [java](https://www.java.com/es/) - Programming language

## Versioned

We use [Git](https://github.com/) for version control. For available versions, see the tags in this repository.

## Authors

* **Jose Ricardo Vasquez Vega** - [Richi025](https://github.com/Richi025)

## Date

Wednesday, June 12, 2024

## License

This project is licensed under the GNU license; See the [LICENSE.txt](LICENSE.txt) file for details.