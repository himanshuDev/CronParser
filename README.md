# CronParser Command Line Tool
This application is a command line tool for parsing the cron expression.
The detail application design can be found in the resource folder.
following is the output of the command when executed 

$ java -jar CronParser-1.0.jar "*/15 0 1,15 * 1-5 /usr/bin/find"<br>
minute&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0 15 30 45<br>
hour &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0<br>
day of month&nbsp;&nbsp;&nbsp; 1 15<Br>
month&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1 2 3 4 5 6 7 8 9 10 11 12<br>
day of week&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1 2 3 4 5<br>
command&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/usr/bin/find


## Getting Started
Perform the following steps to run this application on local machine.

### Dependencies
Following dependencies must be installed.
1. Maven
2. Java 17

### Installing
To run this repo on local

1. clone this repo 
  <br>**$ git clone https://github.com/himanshuDev/CronParser.git**

2. Once cloned, Go inside the CronParser directory and execute
  <br>**$ mvn clean install**
  <br>This will build the project

3. to execute the project, provide the following command.<br>
   **$ CronParser-1.0.jar "*/15 0 1,15 * 1-5 /usr/bin/find"**

## Authors

Contributor names and contact info
Himanshu Upadhyay [himanshu.udhyay@gmail.com]

## Version History

* 1.0
    * Initial Release

## License

This project is licensed under the MIT License - see the LICENSE.md file for details
