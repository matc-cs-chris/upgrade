README - UpGrade Application

-Goal-

Using 1 one-time setup config.xml and 1 export from Zybooks per book, you can produce a .csv file with all the info you need to paste into BrightSpace for grading 

-Instructions-

1) Ensure your assignments in ZyBooks are split by PAs, CAs, Labs-only (so 3 assignments a week)
2) Download a .csv report of all ZyBooks assignments to get zybooks student ids
3) Download a .csv report of the brightspace grades of all your sections to get brightspace usernames for students
4) Setup /src/main/resources/com/upgrade/config.xml to match your classes/sections
5) In config.xml, you'll match bs_usernames to zy_ids per student, and specify the order of students in your brightspace sections
6) After correct setup, run /src/main/java/Main (you could fork into a new IntelliJ project)
7) Console input choices: process to run, percentages/points, column letters, course to report on
8) Then 2 GUI filechoosers will show up. The first is to select the export from Zybooks. The second is what directory you want to output to.
9) Watch the console, it may warn that some student setup should be verified
10) Once done, the program will say it's complete and provide a filepath to the .csv you've created
11) This output should have everything you need to easily copy into imports for brightspace! :)
