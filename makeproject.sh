#!/bin/bash

if [ ! -d $1 ]
then
        mkdir "$1"
        mkdir "$1"/docs
        mkdir "$1"/source
        mkdir "$1"/backup
        mkdir "$1"/archive
        cd "$1"/source
        count=2
fi

if [ -d $1 ]
then
        mkdir "$1"/"$2"
        mkdir "$1"/"$2"/docs
        mkdir "$1"/"$2"/source
        mkdir "$1"/"$2"/backup
        mkdir "$1"/"$2"/archive
        cd "$1"/"$2"/source
        count=3
fi

touch makefile
chmod +x makefile
echo -n "#!/bin/sh
a.out: " >> makefile

i=$count
while [ "$i" -le "$#" ]
do
        file_name="${!i}"
        echo -n "${file_name%.*}.o " >> makefile
        i=$((i+1))
done

echo -n "
        gcc " >> makefile

i=$count
while [ "$i" -le "$#" ]
do
	file_name="${!i}"
        echo -n "${file_name%.*}.o " >> makefile
        i=$((i+1))
done

i=$count
while [ "$i" -le "$#" ]
do
        file_name="${!i}"
        echo -n "
${file_name%.*}.o : ${file_name%.*}.c ${file_name%.*}.h
        gcc -c ${file_name%.*}.c" >> makefile
        i=$((i+1))
done
