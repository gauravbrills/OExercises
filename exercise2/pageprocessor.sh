#!/bin/bash
# Exercise 2 : Script replaces characters with ones from property file.

if [ $# -ne 3 ]; then
    echo "usage: pageprocessor.sh (html-file) (prop-file) (output-file)"
    exit 1
fi

htmlFile=$1
propFile=$2
outputFile=$3

# gets Property By key
function getProp() {    
   local KEY=$1
   local VAL=`cat $propFile | grep "$KEY" | cut -d'=' -f2`
   echo $VAL
}

if [ -f $htmlFile ]
then
 if [ -f $propFile ]
 then
  
  mkdir -p $(dirname $outputFile)
  cp $htmlFile $outputFile
  while IFS= read -r line
  do
     param="$(grep -o -P "\[\[(.*?)\]\]" $outputFile| head -1)"
   if [ -n "$param" ]; then
     key=${param:2:-2}
     subst=$(getProp ${key})
     printf " $key $subst\n"
     pat="\[\[${key}\]\]"
     sed -i "s,${pat},${subst},g" "$outputFile"
    else
    break;
    fi 
  done < $htmlFile
 else
  echo "$propFIle not found."
 fi
else
 echo "$htmlFile not found."
fi