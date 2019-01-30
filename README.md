# MIDOS
Emulación del MS DoS usando java, parte tarea 1 UNED

MS DOS basic "emulation" which implements variety of commands as :
1. cd
2. cls
3. copy
4. date
5. del
6. dir
7. exit
8. md
9. prompt
10. rd
11. ren
12. time
13. tree
14. type
15. ver

It does not implement really directory create functionality, what the application does is create a txt file which handles folder and 
file tree information. It uses two txt files, first one to handle memory info usage, with a maximum og 256 kb, each file uses 4 kb, each folder
uses 8 kb. The other file is for handle directory tree.

Both files are stored in C root, with names of : "MIDOSFRE.txt and MIDOSTRE.txt

Execute the application
 Go to project directory or folder and then click on "MIDOS - Ejecutar Aqui.lnk"



