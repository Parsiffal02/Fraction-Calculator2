import java.util.*;
class drob {
    int chisl, znam;

    public drob(int chisl, int znam) {
        if (chisl == 0) {
            System.out.println("Ошибка. Нулевой числитель. Пожалуйста, перезапустите программу.");
            this.chisl = 1;
        } else {
            this.chisl = chisl;
        }
        if (znam == 0) {
            System.out.println("Ошибка. Нулевой знаменатель. Пожалуйста, перезапустите программу.");
            this.znam = 1;
        } else {
            this.znam = znam;
        }
    }

    public String toString() {
        return ("числитель = " + chisl + ", знаменатель = " + znam);
    }

    public String Sum(drob drob2) {
        if (drob2.chisl == 0 || drob2.znam == 0) {
            System.out.println("Введено неверное значение");
            return (chisl + "/" + znam);
        } else {
            return ((chisl* drob2.znam+drob2.chisl * znam) + "/" + (znam * drob2.znam));
        }
    }

    public String Vichit(drob drob2) {
        if (drob2.chisl == 0 || drob2.znam == 0) {
            System.out.println("Введено неверное значение");
            return (chisl + "/" + znam);
        } else {
            return ((chisl * drob2.znam - drob2.chisl * znam) + "/" + (znam * drob2.znam));
        }
    }

    public String Umnozh(drob drob2) {
        if (drob2.chisl == 0 || drob2.znam == 0) {
            System.out.println("Введено неверное значение");
            return (chisl + "/" + znam);
        } else {
            return ((chisl * drob2.chisl) + "/" + (znam * drob2.znam));
        }
    }

    public String Del(drob drob2) {
        if (drob2.chisl == 0 || drob2.znam == 0) {
            System.out.println("Введено неверное значение");
            return (chisl + "/" + znam);
        } else {
            return ((chisl * drob2.znam) + "/" + (znam * drob2.chisl));
        }
    }
    public String Sokr(){
        for(int i = 2;i<= Math.min(Math.abs(znam),Math.abs(chisl)); i++){
            while (znam % i == 0 && chisl % i == 0){
                znam = znam / i;
                chisl = chisl / i;
            }
        }
        if (znam < 0 && chisl < 0){
            znam = znam / (-1);
            chisl = chisl / (-1);
        }
        return(chisl+"/"+znam);
    }
}
public class Main {
    static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) {
        String virazh;
        Scanner in = new Scanner(System.in);
        System.out.println("Введите выражение");
        virazh = in.nextLine();
        System.out.println(calculate(virazh));
        in.close();
    }
    public static String calculate(String expression) {
        for (String element : expression.trim().split(" ")) {
            list.add(element);
        }
        for(int i = 0; i < list.size(); i++){
            String zxc = (String)list.get(i);
            if (zxc.indexOf("**") != -1 || zxc.indexOf("*+") != -1 || zxc.indexOf("*-") != -1 || zxc.indexOf("*:") != -1 ||zxc.indexOf("+*") != -1 ||zxc.indexOf(":*") != -1 ||zxc.indexOf("-*") != -1||zxc.indexOf(":+") != -1|| zxc.indexOf(":-") != -1||zxc.indexOf("::") != -1 ||zxc.indexOf("-:") != -1 ||zxc.indexOf("+:") != -1 ||zxc.indexOf("+-") != -1 ||zxc.indexOf("++") != -1 ||zxc.indexOf("-+") != -1 ||zxc.indexOf("--") != -1){
                return("Ошибка. Перезапустите программу и введите корректное выражение");
            }
        }
        System.out.println(list);
        while(list.indexOf("(") != -1){
            String Key= expression.substring(expression.lastIndexOf("(")+1,expression.indexOf(")")-1);
            String result = calculate(Key);
            Key = expression.substring(expression.lastIndexOf("("),expression.indexOf(")"));
            expression.replace(Key,result);
        }
        while (list.size() != 0) {
                while (list.indexOf(":") != -1) {
                    int index = list.indexOf(":");
                    String pervaya = (String) list.get(index - 1);
                    String vtoraya = (String) list.get(index + 1);
                    if ((pervaya.indexOf("/") == -1) || (vtoraya.indexOf("/") == -1)) {
                        System.out.println("Введен неверный пример");
                        break;
                    }
                    int indexdrobi1 = pervaya.indexOf("/");
                    drob drob1 = new drob(Integer.valueOf(pervaya.substring(0, indexdrobi1)), Integer.valueOf(pervaya.substring(indexdrobi1 + 1, pervaya.length())));
                    int indexdrobi2 = vtoraya.indexOf("/");
                    drob drob2 = new drob(Integer.valueOf(vtoraya.substring(0, indexdrobi2)), Integer.valueOf(vtoraya.substring(indexdrobi2 + 1, vtoraya.length())));
                    list.add(index - 1, drob1.Del(drob2));
                    list.remove(index + 2);
                    list.remove(index + 1);
                    list.remove(index);
                }
                while (list.indexOf("*") != -1) {
                    int index = list.indexOf("*");
                    String pervaya = (String) list.get(index - 1);
                    String vtoraya = (String) list.get(index + 1);
                    if ((pervaya.indexOf("/") == -1) || (vtoraya.indexOf("/") == -1)) {
                        System.out.println("Введен неверный пример");
                        break;
                    }
                    int indexdrobi1 = pervaya.indexOf("/");
                    drob drob1 = new drob(Integer.valueOf(pervaya.substring(0, indexdrobi1)), Integer.valueOf(pervaya.substring(indexdrobi1 + 1, pervaya.length())));
                    int indexdrobi2 = vtoraya.indexOf("/");
                    drob drob2 = new drob(Integer.valueOf(vtoraya.substring(0, indexdrobi2)), Integer.valueOf(vtoraya.substring(indexdrobi2 + 1, vtoraya.length())));
                    list.add(index - 1, drob1.Umnozh(drob2));
                    list.remove(index + 2);
                    list.remove(index + 1);
                    list.remove(index);
                }
                while (list.indexOf("-") != -1) {
                    if (list.get(list.indexOf("-")) != "-") {
                        list.indexOf("-");
                    }
                    int index = list.indexOf("-");
                    String pervaya = (String) list.get(index - 1);
                    String vtoraya = (String) list.get(index + 1);
                    if ((pervaya.indexOf("/") == -1) || (vtoraya.indexOf("/") == -1)) {
                        System.out.println("Введен неверный пример");
                        break;
                    }
                    int indexdrobi1 = pervaya.indexOf("/");
                    drob drob1 = new drob(Integer.valueOf(pervaya.substring(0, indexdrobi1)), Integer.valueOf(pervaya.substring(indexdrobi1 + 1, pervaya.length())));
                    int indexdrobi2 = vtoraya.indexOf("/");
                    drob drob2 = new drob(Integer.valueOf(vtoraya.substring(0, indexdrobi2)), Integer.valueOf(vtoraya.substring(indexdrobi2 + 1, vtoraya.length())));
                    list.add(index - 1, drob1.Vichit(drob2));
                    list.remove(index + 2);
                    list.remove(index + 1);
                    list.remove(index);
                }
                while (list.indexOf("+") != -1) {
                    int index = list.indexOf("+");
                    String pervaya = (String) list.get(index - 1);
                    String vtoraya = (String) list.get(index + 1);
                    if ((pervaya.indexOf("/") == -1) || (vtoraya.indexOf("/") == -1)) {
                        System.out.println("Введен неверный пример");
                        break;
                    }
                    int indexdrobi1 = pervaya.indexOf("/");
                    drob drob1 = new drob(Integer.valueOf(pervaya.substring(0, indexdrobi1)), Integer.valueOf(pervaya.substring(indexdrobi1 + 1, pervaya.length())));
                    int indexdrobi2 = vtoraya.indexOf("/");
                    drob drob2 = new drob(Integer.valueOf(vtoraya.substring(0, indexdrobi2)), Integer.valueOf(vtoraya.substring(indexdrobi2 + 1, vtoraya.length())));
                    list.add(index - 1, drob1.Sum(drob2));
                    list.remove(index + 2);
                    list.remove(index + 1);
                    list.remove(index);
                }
                if (list.indexOf(":") == -1 && list.indexOf("*") == -1 && list.indexOf("-") == -1 && list.indexOf("+") == -1) {
                    String result = (String) list.get(0);
                    list.remove(0);
                    int index = result.indexOf("/");
                    drob itog = new drob(Integer.valueOf(result.substring(0, index)), Integer.valueOf(result.substring(index + 1, result.length())));
                    return itog.Sokr();
                } else {
                    return ("Mistake");
                }
            }

    return("Готово");
    }
}
//* ( ( -1/3 * 3/4 - 1/2 ) * 2/1 )
