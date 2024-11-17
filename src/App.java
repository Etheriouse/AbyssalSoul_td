import Interface.Window;

public class App {

    public static void main(String[] args) throws Exception {
        String s = ".*#@_ù%£$^+";
        /* 46 42 35 64 95 249 37 163 36 94  */
        /*  .  * #  @  _  ù   %  £   $  ^  + */
        for(int i = 0; i<s.length(); i++) {
            //System.out.println((int)(s.charAt(i)));
        }
        Window.GetNewInstance().run();
    }
}
