package ws;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static int line;
    private static List<Integer> memJmp = new ArrayList<Integer>();
    private static String content;
    private static int linei;

    private static int cmd;
    private static int rfc;

    public static void main(String[] args) {
        File fileR = new File("Fake_Whitespace.fws");
        content = "";
        try{
            content = String.join("\n", Files.readAllLines(fileR.toPath()));
            content.toCharArray();
            for (int i = 0; i < content.length(); i++){
                System.out.print(content.charAt(i));
                System.out.print("|");
            }
            System.out.print("\n");
        }
        catch (IOException e){
            System.out.println(e);
        }

        HashMap<Character, Runnable> hmap = new HashMap<>();

        Commands.init();
        cmd = 0;
        rfc = 0;
        line = 0;
        hmap.put(' ', () -> cmd++);
        hmap.put('\t', () -> rfc++);
        hmap.put('\n', () ->
        {
            line++;
            String function = "cmd";
            function += String.valueOf(cmd);

            switch(function.toLowerCase()) {
                case "cmd0":{               //def a var
                    Commands.cmd0(rfc);
                    break;
                }
                case "cmd1":{               //change pos
                    Commands.cmd1();
                    break;
                }
                case "cmd2":{               //change pos of var to ultimate-pos
                    Commands.cmd2(rfc);
                    break;
                }
                case "cmd3":{               //simple add
                    Commands.cmd3(rfc);
                    break;
                }
                case "cmd4":{               //simple sub
                    Commands.cmd4(rfc);
                    break;
                }
                case "cmd5":{               //simple mul
                    Commands.cmd5(rfc);
                    break;
                }
                case "cmd6":{               //simple div
                    Commands.cmd6(rfc);
                    break;
                }
                case "cmd7":{               //extended add
                    Commands.cmd7();
                    break;
                }
                case "cmd8":{               //extended sub
                    Commands.cmd8();
                    break;
                }
                case "cmd9":{               //extended mul
                    Commands.cmd9();
                    break;
                }
                case "cmd10":{              //extended div
                    Commands.cmd10();
                    break;
                }
                case "cmd11":{              //create jump-point
                    memJmp.add(line);
                    break;
                }
                case "cmd12":{              //jump to a jump-point
                    line = memJmp.get(rfc);
                    char[] con = content.toCharArray();
                    for (int i = line+1; i < con.length; i++){
                        Runnable r = hmap.get(con[i]);
                        if (r != null){
                            r.run();
                        }
                    }
                    break;
                }
                case "cmd13":{              //if(true)
                    if (!Commands.cmd13(rfc)){
                        line++;
                        linei++;
                    }
                    break;
                }
                case "cmd14":{              //if(false)
                    if (Commands.cmd13(rfc)){
                        line++;
                        linei++;
                    }
                    break;
                }
                case "cmd15":{              //print function
                    Commands.cmd15();
                    break;
                }
                case "cmd16":{              //save vars
                    Commands.cmd16(rfc);
                    break;
                }
                case "cmd17":{
                    Commands.cmd17(rfc);
                    break;
                }
            }
            cmd = 0;
            rfc = 0;
        });

        char[] con = content.toCharArray();
        for (linei = 0; linei < con.length; linei++){
            Runnable r = hmap.get(con[linei]);
            if (r != null){
                r.run();
            }
        }
    }
}
