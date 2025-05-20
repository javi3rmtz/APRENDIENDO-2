import java.util.Scanner;


// Clase base: Personaje
class Personaje {
    String nombre;
    int fuerza;
    int velocidad;
    int estamina;
    float vida;

    // Constructor para inicializar el personaje
    public Personaje(String nombre, int fuerza, int velocidad, int estamina) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
        this.estamina = estamina;
        this.vida = 1000; 
    }

    
    // Constructor para inicializar el personaje
    public Personaje(String nombre, int fuerza, int velocidad, int estamina,float vida) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
        this.estamina = estamina;
        this.vida = vida;
    }

    // Método para atacar a otro personaje
    public void ataqueBasico(Personaje oponente, String nombre) {
        float critico = Math.abs(((vida/fuerza)+ estamina)/10);
        oponente.vida -= critico;
        System.out.println(this.nombre + " ataca a " + oponente.nombre + " con un ataque basico "+nombre+" con un critico de " + critico + " puntos.");
        estamina -= 20;
        if (oponente.vida < 0) oponente.vida = 0;
        System.out.println(oponente.nombre + " ahora tiene " + oponente.vida + " puntos de vida.");
    }

    public void ataqueActivo(Personaje oponente, String nombre) {
        float critico = Math.abs(((vida/fuerza)+ estamina)/4);
        oponente.vida -= critico;
        System.out.println(this.nombre + " ataca a " + oponente.nombre + "con un ataque activo "+nombre+" con un critico de " + critico + " puntos.");
        estamina -= 40;
        if (oponente.vida < 0) oponente.vida = 0;
        System.out.println(oponente.nombre + " ahora tiene " + oponente.vida + " puntos de vida.");
    }
    // Método para mostrar estadísticas del personaje
    public void mostrarEstadisticas() {
        System.out.println("--- Estadísticas de " + nombre + " ---");
        System.out.println("Fuerza: " + fuerza);
        System.out.println("Vida:  " + vida);
        System.out.println("Velocidad: " + velocidad);
        System.out.println("Resistencia: " + estamina);
        System.out.println("-----------------------------------");
    }

    // Método para recuperarse y aumentar la resistencia
    public void recuperarse() {
        if (estamina <= 200) {
            if (estamina + 10 >= 200) {
                estamina = 200; // Resistencia máxima
            }
            else {
                estamina += 10;
            }
            System.out.println(nombre + " se ha recuperado y ahora tiene " + estamina + " puntos de resistencia.");
        }
        else {
            System.out.println(nombre + " ya tiene la resistencia máxima.");
        }
    }
}

// Clase derivada: SuperHeroe
class SuperHeroe extends Personaje {
    // Constructor
    public SuperHeroe(String nombre, int fuerza, int velocidad, int resistencia) {
        super(nombre, fuerza, velocidad, resistencia);
    }

    // Constructor
    public SuperHeroe(String nombre, int fuerza, int velocidad, int resistencia ,float vida) {
        super(nombre, fuerza, velocidad, resistencia,vida);
    }

    // Método para un ataque especial
    public void ataqueEspecial(Personaje oponente, String nombre) {
        if (estamina <= 100) {
            System.out.println(this.nombre + " no tiene suficiente resistencia para realizar un ataque especial.");
        }
        else{
         float critico=Math.abs(((fuerza/velocidad)*estamina)+90);
         estamina -= 100; // Reduce la resistencia al usar el ataque especial
         System.out.println(this.nombre + " realiza un ataque especial "+nombre+" a " + oponente.nombre + " con " + critico + " puntos de daño.");
            oponente.vida -= critico;
            if (oponente.vida < 0) oponente.vida = 0;
            System.out.println(oponente.nombre + " ahora tiene " + oponente.vida + " puntos de vida.");
        }
    
        recuperarse();
        oponente.recuperarse();
        System.out.println(this.nombre + " ahora tiene " + estamina + " puntos de resistencia.");
       
    }

      // Método sobrecargado para un ataque especial
    
}

// Clase derivada: Villano
class Villano extends Personaje {
    // Constructor
    public Villano(String nombre, int fuerza, int velocidad, int resistencia) {
        super(nombre, fuerza, velocidad, resistencia);
    }

     // Constructor
     public Villano(String nombre, int fuerza, int velocidad, int resistencia,float vida) {
        super(nombre, fuerza, velocidad, resistencia,vida);
    }

    // Método para hacer trampa 
    public void hacerTrampa(Personaje oponente, String nombre) {
        if (estamina <= 150) {
            System.out.println(this.nombre + " no tiene suficiente resistencia para realizar un ataque especial.");
        }
        else{
         double critico = Math.abs(Math.sqrt((estamina/fuerza)*vida));
         estamina -= 150; // Reduce la resistencia al usar el ataque especial
         System.out.println(this.nombre + " realiza un ataque especial "+nombre+" a " + oponente.nombre + " con " + critico + " puntos de daño.");
            oponente.vida -= critico;
            if (oponente.vida < 0) oponente.vida = 0;
            System.out.println(oponente.nombre + " ahora tiene " + oponente.vida + " puntos de vida.");
        }
    
        recuperarse();
        oponente.recuperarse();
        System.out.println(this.nombre + " ahora tiene " + estamina + " puntos de resistencia.");
    }
}

// Clase principal: Simulación de Batalla
public class SimulacionBatalla {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear superhéroe y villano
        SuperHeroe heroe = new SuperHeroe("Goku", 4000, 2000, 200,10000);
        Villano villano = new Villano("saitama", 10000, 1500, 200,10000);

        // Mostrar estadísticas iniciales
        heroe.mostrarEstadisticas();
        villano.mostrarEstadisticas();

        // Interacción del usuario
        int opcion;
        do {
            System.out.println("Elige una acción:");
            System.out.println("1. El héroe usa ataqueBasico al villano");
            System.out.println("2. El héroe usa ataqueActivo al villano");
            System.out.println("3. El héroe usa su ataque especial");
            System.out.println("4. El villano usa ataqueBasico al héroe");
            System.out.println("5. El villano usa ataqueActivo al héroe");
            System.out.println("6. El villano hace trampa al héroe");
            System.out.println("7. El héroe se recupera");
            System.out.println("8. El villano se recupera");
            System.out.println("9. Mostrar estadísticas");
            System.out.println("0. Terminar batalla");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    heroe.ataqueBasico(villano, "Golpe de artes marciales");
                    break;
                case 2:
                    heroe.ataqueActivo(villano, "Kamehameha");
                    break;
                case 3:
                    heroe.ataqueEspecial(villano, "Genkidama");
                    break;
                case 4:
                    villano.ataqueBasico(heroe, "Puño de fuerza bruta");
                    break;
                case 5:
                    villano.ataqueActivo(heroe, "Golpe de fuerza bruta");
                    break;
                case 6:
                    villano.hacerTrampa(heroe, "Serie de multiples golpes");
                    break;
                case 7:
                    heroe.recuperarse();
                    break;
                case 8:
                    villano.recuperarse();
                    break;
                case 9:
                    heroe.mostrarEstadisticas();
                    villano.mostrarEstadisticas();
                    break;
                case 0:
                    System.out.println("La batalla ha terminado.");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (opcion != 0);

        // Si la resistencia de alguno llega a 0, la batalla termina
        if (heroe.vida == 0) {
            System.out.println(heroe.nombre + " ha sido derrotado. ¡El villano gana!");
        } else if (villano.vida == 0) {
            System.out.println(villano.nombre + " ha sido derrotado. ¡El héroe gana!");
        }else if (heroe.vida != 0 && villano.vida != 0){
            System.out.println("El villano y el heroe se han retirado para tener un proximo encuentro.");
        }

        scanner.close();
    
    }

}