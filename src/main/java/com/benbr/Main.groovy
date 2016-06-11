package main.java.com.benbr

import main.java.com.benbr.converter.ConversionPolicy
import main.java.com.benbr.converter.Converter
import main.java.com.benbr.converter.MessageConverter
import main.java.com.benbr.format.HTMLFITFormatter
import main.java.com.benbr.format.JsonFITFormatter
import main.java.com.benbr.format.TextFITFormatter
import main.java.com.benbr.parser.DataMessage
import main.java.com.benbr.parser.FitParser

class Main {
    private static void generateProfileFiles() {
        String code = new ProfileCodeGenerator().generateCode(new CSVProfileParser(new FileReader("profile.csv")).getFields())
        new File("src\\main\\java\\com\\benbr\\Profile.groovy").write(code)
        code = new TypeCodeGenerator().generateCode(new CSVTypeParser(new FileReader("types.csv")).parse())
        new File("src\\main\\java\\com\\benbr\\Type.groovy").write(code)
    }

    public static void main(String[] args) {

        HashMap<String, String> unitPolicy = [
                semicircle : "degree",
                metre : "mile"
        ]

        HashMap<String, String> fieldPolicy = [
                altitude : "feet"
        ]

        def converter = new MessageConverter(new ConversionPolicy(fieldPolicy, unitPolicy))


        new FitParser().parse(new File("fit/fit.fit"))each {message->
            println new TextFITFormatter().formatDataMessage(converter.convertMessage(message))
        }
    }

}
