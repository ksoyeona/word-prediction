/**Soyeon Kim, cs8bwaha 
 *3/1/18
 *
 *File CharacterModel.java contains a ChacterModel class which
 *is a subclass of WordModel. It contains overwritten method 
 *to read character by character and generate character prediction.
 */

import java.util.HashMap;
import java.util.ArrayList;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**Class CharacterModel contains a CharacterModel constructor and methods 
 * with prefix and predictions in the unit of characters.
 */
public class CharacterModel extends WordModel {

    /**General constructor for CharacterModel object.
     *
     *@param degree the number of characters used to predict nextx character
     */
    public CharacterModel (int degree) {
        super(degree);
    }

    /**This method reads the training text character by character and increment
     *frequency of prefix and predicton pairs in hashmap.
     *
     *@param content string containing the training text
     *
     *@return number of characters read from the training string
     */
    @Override
        public int trainFromText(String content) {

            ArrayList<String> list = new ArrayList<>();
            //add every character into an ArrayList
            for(int i=0; i<content.length(); i++){
                String str = Character.toString(content.charAt(i));
                list.add(i, str.toLowerCase());
            }

            //create a sublist for prefix
            for(int i= getDegree(); i < list.size(); i++){

                ArrayList<String> prefix = 
                    new ArrayList<String>(list.subList(i-getDegree(), i));

                incrementPrediction(prefix, list.get(i));
            }

            return list.size();
        }


    /**This method randomly selects a prefix from all prefixes and use
     *the most recent "degree" characters to produce a prediction and add
     *predictioin word to the prefix, and repeats this process until the string
     *contains count number of characters
     *
     *@param count number of characters in String returning
     *
     *@return a String of "count" characters generated
     */
    @Override
        public String generate(int count) {
            // get all the keys in predictionMap into an ArrayList
            ArrayList<ArrayList<String>> keys = 
                new ArrayList<ArrayList<String>>(getPredictionMap().keySet());

            StringBuilder string = new StringBuilder();
            //generate a random key
            int index= getRandom().nextInt(keys.size());

            ArrayList<String> strArrList = 
                new ArrayList<String>(keys.get(index));

            for(int j=degree; j<count; j++){

                ArrayList<String> prefix = 
                    new ArrayList<String>(strArrList.subList(j-getDegree(),j));
                //generate prediction based on selected number of previous 
                //characters
                String word = generateNext(prefix);

                strArrList.add(word);
            }
            //convert arraylist into a single stringbuiler
            for(String str: strArrList){

                string.append(str);
            }

            String str= new String(string.toString());
            return str;
        }

}
