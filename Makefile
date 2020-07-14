

CLASSES = "./br/com/slira/cliunixtoolkit/cliconversiontool/$(wildcard *.class)"
LANG = "lang"
CLICONVERSIONTOOL = "br/com/slira/cliunixtoolkit/cliconversiontool"


all:
	clear
	echo "Compilando..."

	#native2ascii -encoding UTF-8 $(LANG)/ja.properties strings_ja_JP.properties
	#native2ascii -encoding UTF-8 $(LANG)/pt.properties strings_pt_BR.properties

	javac -encoding utf8 br/com/slira/cliunixtoolkit/cliconversiontool/*.java
	
	jar cfm "Lira's CLI Toolkit.jar"  manifest.mf $(CLASSES) img css lang

run:
	echo "Executando..."
	java -jar "Lira's CLI Toolkit.jar"
	echo ""
	echo "Finalizado"


clean:
	rm br/com/slira/cliunixtoolkit/cliconversiontool/*.class
	rm "Lira's CLI Toolkit.jar"