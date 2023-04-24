package com.cebem.rickandmorty.controllers;

import com.cebem.rickandmorty.models.CharacterModel;
import com.cebem.rickandmorty.models.CharactersModel;
import com.cebem.rickandmorty.services.RickAndMortyService2;
import com.cebem.rickandmorty.utils.Utils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class rickController {

  @Autowired
  RickAndMortyService2 rickAndMortyService2;

  // http://127.0.0.1/
  // http://localhost/
  // http://angel.com/
  @GetMapping("/")
  public String saluda() {
    return "Bienvenid@ a mi api rest de rickAndMorty";
  }

  @GetMapping("/random")
  public String random() {
    return Math.round(Math.random() * 10) + "";
  }

  //        ./mvnw spring-boot:run
  // http://localhost:8080/palindrome/ana
  @GetMapping("/palindrome/{word}")
  public String palindrome(@PathVariable String word) {
    return Utils.isPalindrome(word)
      ? "Si es un palíndromo"
      : "No es un palíndromo";
  }

  //http://localhost:8080/add?n1=2&n2=4
  @GetMapping("/add")
  public String add(@RequestParam String n1, @RequestParam String n2) {
    float resultado = Float.parseFloat(n1) + Float.parseFloat(n2);
    Object params[] = { n1, n2, resultado };
    return MessageFormat.format(
      "La suma de {0} mas {1} es igual a {2}",
      params
    );
  }

  @PostMapping("/saveOnDisk")
  public String saveOnDisk(@RequestParam Map<String, String> body) {
    String name = body.get("name");
    String price = body.get("price");

    String info = name + " " + price + "\n";

    try {
      Utils.writeOnDisk("datos.txt", info);
    } catch (IOException e) {
      return "Error al intentar escribir en el fichero";
    }

    return "Gracias por enviar el form, los datos se han guardado en el servidor";
  }

  @DeleteMapping("/removeFromDisk")
  public String removeFromDisk() {
    boolean resultado = Utils.deleteFromDisk("datos.txt");
    return resultado ? "Borrado correcto" : "No he podido borrar";
  }

  /*
- Se pide que crees un endpoint en tu
servidor que, pasandole un número cualquiera
te devuelva ese número elevado al cuadrado
 */

  // GET   http://localhost:8080/cuadrado/5
  // GET   http://localhost:8080/cuadrado?numerito=5
  @GetMapping("/cuadrado")
  public String cuadradito(@RequestParam String numerito) {
    try {
      float value = Float.parseFloat(numerito);
      return value * value + "";
    } catch (NumberFormatException ex) {
      return "El numero no es válido";
    }
  }

  /*
   - Se pide que crees un endpoint que al 
llamarlo: vacie (no borre) el fichero datos.txt
Prueba el funcionamiento de este endpoint 
con la extensión ThunderClient de vsc
   */
  @DeleteMapping("/clear")
  public String clear() {
    try {
      Utils.clearFile("datos.txt");
      return "Fichero limpiado correctamente";
    } catch (IOException ex) {
      return "Error al limpiar el fichero." + ex.getMessage();
    }
  }

  /*
  Crea un endpoint que te devuelva toda la 
información guardada en el fichero datos.txt

GET   http://localhost:8080/products

  */
  @GetMapping("/products")
  public static String getProducts() {
    try {
      return Utils.readFromDisk("datos.txt");
    } catch (FileNotFoundException ex) {
      return "No se encontro el fichero datos.txt";
    } catch (IOException ex) {
      return "Fallo al acceder al fichero:" + ex.getMessage();
    }
  }

  /*


  /**
   Crea un endpoint al que le pases 3 números y devuelva el mayor de ellos.
    Si alguno de los elementos pasados no es un número devolver la frase "ERROR"

    GET http://localhost:8080/max?n1=4&n2=3&n3=8

   */
  @GetMapping("/max")
  public static String max(
    @RequestParam String n1,
    @RequestParam String n2,
    @RequestParam String n3
  ) {
    float f1 = Float.parseFloat(n1);
    float f2 = Float.parseFloat(n2);
    float f3 = Float.parseFloat(n3);
    return Utils.maxOfElements(f1, f2, f3) + "";
  }

  /*
 Crea un endpoint al que le pases un texto (frase).
Este devolverá el mismo texto, pero con la primera letra
de cada palabra en mayúsucula (el resto en minúscula)


 GET http://localhost:8080/capitalize/xxxx
 */
  @GetMapping("/capitalize/{text}")
  public static String capitalize(@PathVariable String text) {
    return Utils.capitalizeText(text);
  }
  /*

- Crea un endpoint que devuelva 3 colores random sin repetir
Parte de un array con los colores básicos
[negro, azul, marrón, gris, verde, naranja, rosa, púrpura, 
rojo, blanco y amarillo]
*/
  @GetMapping("/randomColors")
  public static String randomColors(){
    final int COLOR_COUNT = 3;
    final String[] COLORS = new String[] {"negro", "azul", "marrón", "gris", "verde", "naranja", "rosa", "púrpura", "rojo", "blanco", "amarillo"};
    if(COLOR_COUNT > COLORS.length) throw new RuntimeException("Limite de colores superado");
    
    ArrayList<String> colores = new ArrayList<String>(Arrays.asList(COLORS));
    String finalColors = "";
    for (int i=0;i<COLOR_COUNT;i++){
      int random = Utils.getRandomValue(colores.size());
      finalColors += colores.remove( random ) + " ";
    }
    return finalColors;
  }

  /**
   Crea un endpoint que te devuelva un persona random 
   de la serie rick and morty

   // GET https://rickandmortyapi.com/api/character/???
   
   */



   @GetMapping("/rickandmorty/random")
   public String randomCharacter(){
      //RickAndMortyService rickAndMortyService = new RickAndMortyService();
      CharacterModel characterModel = rickAndMortyService2.getCharacterRandom();

      return characterModel.name +"<br/>"+ "<img width='200' src='"+characterModel.image+"'/>";
   }

   @GetMapping("/rickandmorty/all")
   public String characters(){
    CharactersModel charactersModel = rickAndMortyService2.getAllCharacters();
    String html = "<html>";
    html+="<head>";
    html+="</head>";
    html +="<body>";
    for(CharacterModel characterModel : charactersModel.results){
      html += characterModel.name;
      html += "<br/>";
      html += "<img width='100px' src='"+characterModel.image+"'>";
      html += "<hr/>";
    }
    html+="</body>";
    html+="</html>";
    return html;
   }

   


}
