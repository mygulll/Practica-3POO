import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Mexicano {
    String nombre;
    String apellidoMaterno;
    String apellidoPaterno;
    String CURP;
    String RFC;
    String genero; //[Hombre o Mujer]
    String entidadDeNacimiento;
    LocalDate fechaNacimiento;

    //Constructor que al crear una instancia mexicano te capture sus valores
    public Mexicano() {
        setNombre(CapturaEntrada.capturarCadena("Ingrese su nombre "));
        setApellidoPaterno(CapturaEntrada.capturarCadena("Ingrese su Apellido paterno "));
        setApellidoMaterno(CapturaEntrada.capturarCadena("Ingrese su Apellido materno "));
        setGenero(CapturaEntrada.capturarCadena("Ingrese su Genero "));
        System.out.println("Ingrese su entidad de nacimiento abreviada, si usted no conoce su entiedad de nacimiento presione 1\n" +
                "Si la conoce, presione 0");
        if (CapturaEntrada.capturarEntero("") == 1){
            System.out.printf(
                    "|AGUASCALIENTES AS\t| BAJA CALIFORNIA BC|\n" +
                    "|BAJA CALIFORNIA SUR BS\t| CAMPECHE CC|\n" +
                    "|COAHUILA CL\t| COLIMA |\n" +
                    "|CHIAPAS CS\t| CHIHUAHUA CH|\n" +
                    "|DISTRITO FEDERAL DF\t| DURANGO DG|\n" +
                    "|GUANAJUATO GT\t| GUERRERO GR|\n" +
                    "|HIDALGO HG\t| JALISCO JC|\n" +
                    "|MEXICO MC\t| MICHOACAN MN|\n" +
                    "|MORELOS MS\t| NAYARIT NT|\n" +
                    "|NUEVO LEON NL\t| OAXACA OC|\n" +
                    "|PUEBLA PL\t| QUERETARO QT|\n" +
                    "|QUINTANA ROO QR\t| SAN LUIS POTOSI SP|\n" +
                    "|SINALOA SL\t| SONORA SR|\n" +
                    "|TABASCO TC\t| TAMAULIPAS TS|\n" +
                    "|TLAXCALA TL\t| VERAZCRUZ VZ|\n" +
                    "|YUCATAN YN\t| ZACATECAS ZS|\n" +
                    "|NACIDO EN EL EXTANJERO NE\n");
        }
        setEntidadDeNacimiento(CapturaEntrada.capturarCadena("Entidad abreviada "));
        setFechaNacimiento(CapturaEntrada.capturarCadena("Ingrese su fecha de nacimiento en el formato D/MM/YYYY\n"));
    }

    //Setters y getters
    public String getNombre() {
        return nombre;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getCURP() {
        if(CURP == null){
            System.out.println("Calcule el CURP primero");
            return null;
        }
        return CURP;
    }

    public String getRFC() {
        if(RFC == null){
            System.out.println("Calcule el RFC primero");
            return null;
        }
        return RFC;
    }

    public String getGenero() {
        return genero;
    }

    public String getEntidadDeNacimiento() {
        return entidadDeNacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setEntidadDeNacimiento(String entidadDeNacimiento) {
        this.entidadDeNacimiento = entidadDeNacimiento;
    }

    public void setFechaNacimiento(String fecha){
        this.fechaNacimiento = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("d/M/yyyy") );
    }
    //LocalDate seisNov = LocalDate.parse("6/11/2020", DateTimeFormatter.ofPattern("d/M/yyyy") );


    public String CalculaRFC(Mexicano mx)
    {
        //Convertiremos todos los nombres y apellidos a mayusculas.
        this.apellidoPaterno = mx.apellidoPaterno.toUpperCase();
        this.apellidoMaterno = mx.apellidoMaterno.toUpperCase();
        mx.nombre = mx.nombre.toUpperCase();
        this.genero = mx.genero.toUpperCase();


        //Primera Letra del primer apellido
        this.RFC = mx.apellidoPaterno.charAt(0)+"";
        //Primera vocal del primer apellido
        boolean primerVocalFoundIt= true;
        int i = 0;
        do {
            switch (mx.apellidoPaterno.charAt(i)){
                case 'A':
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                    this.RFC += mx.apellidoPaterno.charAt(i);
                    primerVocalFoundIt = false;
                    break;
            }
            i++;
        }while(primerVocalFoundIt);
        //Primera letra del segundo apellido:
        this.RFC += mx.apellidoMaterno.charAt(0);
        //Primera letra del primer nombre
        this.RFC += mx.nombre.charAt(0);

        //Fecha de nacimiento comenzando en año mes y dia.
        if (mx.fechaNacimiento.getYear()%100 == 0)
        {//Esta condicion es para segurarnos de los que nacieron en el año 2000
            this.RFC += "00";
        }
        else{
            this.RFC += mx.fechaNacimiento.getYear()%100;
        }
        //El mes
        if(mx.fechaNacimiento.getMonthValue() < 10){
            this.RFC += "0";
        }
        this.RFC += mx.fechaNacimiento.getMonthValue();

        //El dia
        if(mx.fechaNacimiento.getDayOfMonth() < 10){
            this.RFC += "0";
        }
        this.RFC += mx.fechaNacimiento.getDayOfMonth();

        //Homoclave
        Random random = new Random();
        //Valores que puede tomar la homoclave
        String caracteresRandom = "0123456789ABCDEFGHIJKMNOLPQRSTUVWXYZ";
        for (int j = 0; j < 3 ; j++){
            this.RFC += caracteresRandom.charAt(random.nextInt(caracteresRandom.length()));
        }

        return this.RFC;
    }

    public String CalculaCURP(Mexicano mx){
        //Convertiremos todos los nombres y apellidos a mayusculas.
        this.apellidoPaterno = mx.apellidoPaterno.toUpperCase();
        this.apellidoMaterno = mx.apellidoMaterno.toUpperCase();
        mx.nombre = mx.nombre.toUpperCase();
        this.genero = mx.genero.toUpperCase();
        mx.entidadDeNacimiento = mx.entidadDeNacimiento.toUpperCase();


        //Primera Letra del primer apellido
        this.CURP = mx.apellidoPaterno.charAt(0)+"";
        //Primera vocal del primer apellido
        boolean primerVocalFoundIt= true;
        int i = 0;
        do {
            switch (mx.apellidoPaterno.charAt(i)){
                case 'A':
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                    this.CURP += mx.apellidoPaterno.charAt(i);
                    primerVocalFoundIt = false;
                    break;
            }
            i++;
        }while(primerVocalFoundIt);
        //Primera letra del segundo apellido:
        this.CURP += mx.apellidoMaterno.charAt(0);
        //Primera letra del primer nombre
        this.CURP += mx.nombre.charAt(0);

        //Fecha de nacimiento comenzando en año mes y dia.
        if (mx.fechaNacimiento.getYear()%100 == 0)
        {//Esta condicion es para segurarnos de los que nacieron en el año 2000
            this.CURP += "00";
        }
        else{
            this.CURP += mx.fechaNacimiento.getYear()%100;
        }
        //El mes
        if(mx.fechaNacimiento.getMonthValue() < 10){
            this.CURP += "0";
        }
        this.CURP += mx.fechaNacimiento.getMonthValue();

        //El dia
        if(mx.fechaNacimiento.getDayOfMonth() < 10){
            this.CURP += "0";
        }
        this.CURP += mx.fechaNacimiento.getDayOfMonth();

        //Genero
        this.CURP += mx.genero.charAt(0);

        //Entidad
        this.CURP += mx.entidadDeNacimiento;

        //Siguiente consonante del primer apellido Aranguren
        boolean consonanteFound= true;
        i = 0;
        do {
            switch (mx.apellidoPaterno.charAt(i)){
                case 'A':
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                    break;
                default: //Si no es una vocal, es una consonante
                    this.CURP += mx.apellidoPaterno.charAt(i);
                    consonanteFound = false;
                    break;
            }
            i++;
        }while(consonanteFound);

        //Siguiente consonante del segundo apellido
        consonanteFound= true;
        i = 1;
        do {
            switch (mx.apellidoMaterno.charAt(i)){
                case 'A':
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                    break;
                default: //Si no es una vocal, es una consonante
                    this.CURP += mx.apellidoMaterno.charAt(i);
                    consonanteFound = false;
                    break;
            }
            i++;
        }while(consonanteFound);
        //Siguiente consonante del primer nombre
        consonanteFound= true;
        i = 1;
        do {
            switch (mx.nombre.charAt(i)){
                case 'A':
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                    break;
                default: //Si no es una vocal, es una consonante
                    this.CURP += mx.nombre.charAt(i);
                    consonanteFound = false;
                    break;
            }
            i++;
        }while(consonanteFound);

        //Homoclave
        Random random = new Random();
        //Valores que puede tomar la homoclave
        String caracteresRandom = "0123456789ABCDEFGHIJKMNOLPQRSTUVWXYZ";
        for (int j = 0; j < 2 ; j++){
            this.CURP += caracteresRandom.charAt(random.nextInt(caracteresRandom.length()));
        }

        return this.CURP;
    }
}
