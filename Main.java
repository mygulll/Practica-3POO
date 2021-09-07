public class Main {
    public static void main(String[] args) {

        System.out.println("Persona mexicana 1)");
        Mexicano obj1 = new Mexicano();
        System.out.println("CURP obj1: "+obj1.CalculaCURP(obj1));

        System.out.println("Persona mexicana 2)");
        Mexicano obj2 = new Mexicano();
        System.out.println("RFC obj2: "+obj2.CalculaRFC(obj2));

        System.out.println("Persona mexicana 3)");
        Mexicano obj3 = new Mexicano();
        System.out.printf("CURP obj3: %s\nRFC obj3: %s",obj3.CalculaCURP(obj3), obj3.CalculaRFC(obj3));


    }
}
