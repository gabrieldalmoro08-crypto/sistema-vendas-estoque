package view;
import java.util.Scanner;

public class View {

    private Scanner entrada = new Scanner(System.in);

    public int menuUsuario(){
        String menu = "------- MENU DE OPÇÕES! -------\n"
                    + "\n1 Realizar cadastro: "
                    + "\n2 Login: "
                    + "\n3 Escolha uma opção ";

        System.out.println(menu);
    return entrada.nextInt();
    }

}
