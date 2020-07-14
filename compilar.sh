#!/bin/bash

clear

classes="br/com/slira/cliunixtoolkit/cliconversiontool/*.class"

cd lang
native2ascii -encoding UTF-8 ja.properties strings_ja_JP.properties
native2ascii -encoding UTF-8 pt.properties strings_pt_BR.properties

cd ..
rm br/com/slira/cliunixtoolkit/cliconversiontool/*.class
javac br/com/slira/cliunixtoolkit/cliconversiontool/*.java

rm "Lira's CLI Toolkit.jar"
jar cfm "Lira's CLI Toolkit.jar"  manifest.mf $classes img css lang


if [ "$1" == "e" ]; then
  echo "Executando..."
  java -jar "Lira's CLI Toolkit.jar"
  echo ""
  echo "Finalizado"
fi
