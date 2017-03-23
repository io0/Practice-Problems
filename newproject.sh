#!/bin/sh

if [ "$#" = "0" ]
then
	echo "Project name is missing. Type in: NewProject path project_name"
	exit
fi

if [ "$#" = "2" ] && [ ! -d $1 ]
then
	echo "Your path name is malformed. Type in: NewProject path project_name"
	exit
fi

if [ "$#" = "1" ]
then
	mkdir "$1"
        mkdir "$1"/docs
        mkdir "$1"/source
        mkdir "$1"/backup
        mkdir "$1"/archive

echo "
#!/bin/sh

if [ \"\$#\" = \"0\" ]
then
        echo \"You are missing file names. Type in: compile -o executable_name file_names\"
        exit
fi

if [[ \"\$#\" < \"3\" ]] && [ \"\$1\" = \"-o\" ]
then
        echo \"You are missing file names. Type in: compile -o executable_name file_names\"
        exit
fi

if [[ \"\$#\" > \"2\" ]] && [ \"\$1\" = \"-o\" ]
then
        filename = \"\$2\"
        shift
        shift
        cp \$@ ../backup
        gcc -o \$filename \$@ > errors.txt | more
else
        cp \$@ ../backup
        gcc \$@ > errors.txt | more
fi

" >> "$1"/source/compile
chmod +x "$1"/source/compile
fi


if [ "$#" = "2" ]
then
	mkdir "$1"/"$2"
	mkdir "$1"/"$2"/docs
	mkdir "$1"/"$2"/source
	mkdir "$1"/"$2"/backup
	mkdir "$1"/"$2"/archive

echo "
#!/bin/sh

if [ \"\$#\" = \"0\" ]
then
	echo \"You are missing file names. Type in: compile -o executable_name file_names\"
	exit
fi

if [[ \"\$#\" < \"3\" ]] && [ \"\$1\" = \"-o\" ]
then
	echo \"You are missing file names. Type in: compile -o executable_name file_names\"
	exit
fi

if [[ \"\$#\" > \"2\" ]] && [ \"\$1\" = \"-o\" ]
then
	filename = \"\$2\"
	shift
	shift
	cp \$@ ../backup
	gcc -o \$filename \$@ > errors.txt | more
else 
	cp \$@ ../backup
	gcc \$@ > errors.txt | more
fi

" >> "$1"/"$2"/source/compile
chmod +x "$1"/"$2"/source/compile
fi
 




