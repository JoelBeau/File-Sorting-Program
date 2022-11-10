# File-Sorting-Program

Author: Joel Beauregard

Hello!

This repository contains my src code files for my file sorting program, which sorts the files in the directory according to the user, by last modified time, according to which way the user directs (Month, Year, or Both).

In short after getting the information from the user, it creates the year folders (if needed), creates the (month folders if need), and if the user requested that it be 
sorted by both, then it creates the year folders and then the month folders within each year. Next it gets all of the files in the specified directory, gets the last modified time of the file and moves it according that and how the user specified them to be sorted.

This program was originally created to sort pictures, but it can be used for other files as well.

Picture.java is a object file I used to represent the current file that is being looked at.

Sort.java is an interface used to define what folders to create, how to collect the files and how to move the files.

SortByMonth.java is used when the user specifies to sort the files by month.

SortByYear.java is used when the user specifies to sort the files by year.

SortByBoth.java is used when the user specifies to sort the files by both month and year.

Main.java is used to run the program.

More documentation can be found within each file. 

Thanks,

Joel
