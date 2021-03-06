Arimaa
======

CS 229 Project

How to run the starter code:
- Clone / pull latest code
- Open Eclipse and select File -> New -> Java Project
- Uncheck "Use Default Location" and browse for the "ArimaaBot" directory in this git repo
- Click finish.
- Right click on the directory in the project browser -> Run as... -> Run Configurations (you may need to run ArimaaEngine.java to get Eclipse to recognize this as an application for which you can change the VM arguments as below)
- Click the "Arguments" tab and enter these VM arguments to expand the program's usable heap space: -Xmx500000000
- Set up JDBC as per the instructions below
- Run ArimaaEngine.java 

Setting up a database connection from Java:
- Download the JDBC driver from http://www.mysql.com/products/connector/ . Version 5.1.26 is fine. Store it anywhere you like (except in the git repo).
- Right click on the ArimaaBot project in Eclipse's project browser and select Build Path -> Configure Build Path
- In the Libraries tab, select "Add External Jar" and browse for your newly downloaded .jar file. 
- For reference on how to use JDBC / working with ResultSet, see http://www.stanford.edu/class/cs108/handouts131/22JDBC.pdf starting at page 5. 

Setting up liblinear
- Download the liblinear jar file from http://www.bwaldvogel.de/liblinear-java/liblinear-1.94.jar
- Right click on the ArimaaBot project in Eclipse's project browser and select Build Path -> Configure Build Path
- In the Libraries tab, select "Add External Jar" and browse for your newly downloaded .jar file. 

Setting up libsvm
- Download the libsvm jar file https://github.com/cjlin1/libsvm/tree/master/java
- Flow the steps for liblinear above 

Migrating Data:
- Get zip from Google Drive
- Unzip
- Copy the following lines of code with ConvertData as the pwd:

<pre><code>
mysqladmin create Arimaa           # create database; may need to use 'sudo'
cat FastSQLLoad/*.sql | mysql -u root Arimaa   # create tables in database
mysqlimport --local -u root Arimaa FastSQLLoad/*.txt   # load data into tables; may need to specify full path
</code></pre>

Running long processes on corn:
- If we want to write to files and leave a process running on corn, follow the instructions at the bottom of the document
- https://www.stanford.edu/group/farmshare/cgi-bin/wiki/index.php/AFS

Other items: 
- Use MySQL5.5
- For reference on Arimaa move notation, see: http://arimaa.com/arimaa/learn/notation.html


Style Conventions: consistent with Jeff Bacher's starter code
- Method names: camelCase
- Variables: lower case separated by _

Vocabulary:
- Piece id's are Jeff Bacher's piece_types (0-11)
- Piece_types are David Wu's piece_types (0-7)
- A turn/move is made up of at most 4 steps


Feature Vector Mapping:
It shows which features (from David Wu's paper) correspond to which bits of our feature vector (i.e. BitSet).

| Feature | Range of Bits |
| ------ |:---:|
| Position + Movement | 0 - 1039 |
| Trap Status | 1040 - 1551 |
| Freezing | 1552 - 1711 |
| Stepping on Traps | 1712 - 1775 |
| Capture Threats | 1776 - 2479 |
| Previous Moves | 2480 - 2607 |

Get jar file for kmeans: http://www.java2s.com/Code/Jar/j/Downloadjavaml016jar.htm
Get jar file for probability distributions: http://mvnrepository.com/artifact/org.apache.commons/commons-math3/3.0
