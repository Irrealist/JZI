package jzi.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


/**
 *
 * @author Tobias Groth
 *
 */
public enum Language {

    /**
     * English language.
     */
    english(0),
    /**
     * German language.
     */
    german(1);

    /**
     * index of the language.
     */
    private int index;

    /**
     * current language.
     */
    private static int currentLanguage = 0;

    /**
     * Constructor for language.
     * @param index
     */
    private Language(int index) {

        this.index = index;

    }

    /**
     * return Index of a language.
     * @return  index
     **/
    public int getIndex() {
        return index;
    }

    /**
     * set the current Language.
     * @param language
     */
    public static void set(Language language) {
        currentLanguage = language.getIndex();
    }

    /**
     * return the current language.
     * @return  language
     **/
    private static Language get() {

        for (int i = 0; i < Language.values().length; i++) {
            if (i == currentLanguage) {
                return Language.values()[i];
            }
        }

        return null;
    }

    /**
     * return the words in a part of the current language file.
     * @return  words
     **/
    public static LinkedList<String> getCurrentLanguageWords(int part) {

        Data d = new Data("./resource/" + get().name() + ".txt");
        LinkedList<String> wordsList = d.readRow(part);
        return wordsList;
    }

    /**
     * return the name of the current language.
     * @return language
     */
    public static String getCurrentLanguage() {

        return get().name();
    }
}

/**
* read Strings.
* @author Tobias Groth
*
*/
class Data extends File {

    /**
     * UID serilization.
     */
    private static final long serialVersionUID = 4553390203281010967L;

    /**
     * create File.
     * @param path
     **/
    public Data(String path) {
        super(path);

    }

    /**
     * read the rows in a part.
     * @param part
     * @return words
     **/
    public LinkedList<String> readRow(int part) {

        LinkedList<String> read = new LinkedList<String>();

        try {
            String row = "";
            int currentpart = 0;

            BufferedReader reader = new BufferedReader(
                    new FileReader(getPath()));

            while ((row = reader.readLine()) != null) {
                if (!row.contains("*/")) {
                    row = row.replaceAll("\n", "");
                    if (row.contains("----------")) {
                        currentpart++;

                    }
                    if (part == currentpart) {
                        read.add(row);
                    }
                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();

        }

        if (part > 0) {
            read.removeFirst();
        }
        return read;
    }

}