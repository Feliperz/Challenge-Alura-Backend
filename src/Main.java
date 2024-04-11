

import com.alura.conversor.felipe.componentes.Divisas;
import com.alura.conversor.felipe.componentes.DivisasApi;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello world!");

        Scanner scanner = new Scanner(System.in);
        try{
            while(true){

                String opcion = opcionMenu();
                if(opcion == "exit") break;

                System.out.println("Cuanto desea convertir?");
                double amount = scanner.nextDouble();

                String divisaPrimaria = opcion.split(" ")[0];
                String divisaConvertir = opcion.split(" ")[1];
                String url = "https://v6.exchangerate-api.com/v6/2ce672e92e42b9aacaa0cbac/pair/" +
                        divisaPrimaria +
                        "/"+ divisaConvertir +
                        "/" +
                        amount;


                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                Gson gson = new Gson();
                DivisasApi divisasApi = gson.fromJson(json, DivisasApi.class);
                Divisas divisaConvertida = new Divisas(divisasApi, amount);
                System.out.println(divisaConvertida);

            }
        }catch (NumberFormatException e){
            System.out.println("Error al optener problema " + e.getMessage());
        }catch (Error e){
            System.out.println("error inesperado ocurrido");
        }


    }
    public static String opcionMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("=====Systema Conversion======");
        System.out.println("""
                1) DOLAR ===> Peso Mexicano
                2) Peso Mexicano ===> DOLAR
                3) DOLAR ===> Euro
                4) Euro ===> DOLAR
                5) DOLAR ===> Peso Argentino
                6) Peso Argentino ====> Dolar
                7) salir
                """);
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                return "USD MXN";
            case 2:
                return "MXN USD";
            case 3:
                return "USD EUR";
            case 4:
                return "EUR USD";
            case 5:
                return "USD ARS";
            case 6:
                return "ARS USD";
            default:
                return "exit";

        }

    }
}