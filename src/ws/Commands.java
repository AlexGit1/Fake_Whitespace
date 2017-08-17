package ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Commands {

    private static String content;
    private static char[] Alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private static List<Integer> longID = new ArrayList<Integer>();
    private static List<Integer> memID_1 = new ArrayList<Integer>();
    private static List<Integer> memID_2 = new ArrayList<Integer>();
    private static int pos;

    public static void init(){
        for (int i = 0; i < 538; i++){
            memID_1.add(-1);
            longID.add(-1);
        }
        pos = 0;
    }

    public static void cmd0(int id){
        longID.set(pos, id);
        pos++;
    }
    public static void cmd1(){
        pos++;
    }
    public static void cmd2(int postoC){                            //change var to uPos
        int oVar = longID.get(0);
        longID.set(0, longID.get(postoC));
        longID.set(postoC, oVar);
    }

    public static void cmd3(int varToAdd){                          //add a var
        longID.set(0, longID.get(0)+varToAdd);
    }
    public static void cmd4(int varToSub){                          //sub a var
        longID.set(0, longID.get(0)-varToSub);
    }
    public static void cmd5(int varToMul){                          //mul a var
        longID.set(0, longID.get(0)*varToMul);
    }
    public static void cmd6(int varToDiv){                          //div a var
        longID.set(0, longID.get(0)/varToDiv);
    }

    public static void cmd7(){                                      //add a var     extended
        int solution = 0;
        for (int i = 0; i < longID.size(); i++){
            if (longID.get(i) >= 0){
                solution += longID.get(i);
            }
        }
        memID_1.set(0, solution);
        longID = memID_1;
    }
    public static void cmd8(){                                      //sub a var     extended
        int solution = longID.get(0);
        for (int i = 1; i < longID.size(); i++){
            solution -= longID.get(i);
        }
        memID_1.set(0, solution);
        longID = memID_1;
    }
    public static void cmd9(){                                      //mul a var     extended
        int solution = 0;
        for (int i = 0; i < longID.size(); i++){
            solution = solution * longID.get(i);
        }
        memID_1.set(0, solution);
        longID = memID_1;
    }
    public static void cmd10(){                                      //div a var     extended
        int solution = longID.get(0);
        for(int i = 1; i < longID.size(); i++){
            solution = solution / longID.get(i);
        }
        memID_1.set(0, solution);
        longID = memID_1;
    }

    public static boolean cmd13(int checkNum){
        if (longID.get(0).equals(checkNum)){
            return true;
        }
        else {
            return false;
        }
    }

    public static void cmd15(){                                      //print to console

        HashMap<Integer, Runnable> hmap = new HashMap<>();
        content = "";

        for (int in = 0; in < Alphabet.length; in++){
            final int fin = in;
            hmap.put(fin, () -> content += Alphabet[fin]);
        }
        for (int i = 0; i < 512; i++){
            final int fi = i+26;
            hmap.put(fi, () -> content += String.valueOf(fi-26));
        }

        for (int i = 0; i < longID.size(); i++){
            Runnable r = hmap.get(longID.get(i));
            if (r != null){
                r.run();
            }
        }

        System.out.println(content);
    }

    public static void cmd16(int action){
        if (action == 0){
            memID_2 = memID_1;
        }
        else {
            memID_1 = memID_2;
        }
    }

    public static void cmd17(int action){
        if (action == 0){
            memID_1 = longID;
            for (int i = 0; i < longID.size(); i++){
                longID.set(i, -1);
            }
        }
        else {
            for (int i = 0; i < longID.size(); i++){
                longID.set(i, -1);
            }
        }
    }
}
