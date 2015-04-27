#include <SPI.h>
#include <Ethernet.h>
 
 //Pines para los LEDs
int ledRojo = 9;
int ledVerde = 10;
int sensor;

 
 
byte mac[] = {
  0x90, 0xA2, 0xDA, 0x0D, 0x96, 0xB4 };
byte ip[] = { 186.80.5.126 }; // Direccion ip local
char server[]={"192.168.0.4"};
 
EthernetClient client;

 
void setup()
{
  Serial.begin(9600);
  
    //Define los pines 9 y 10 como output
  pinMode(ledRojo, OUTPUT);
  pinMode(ledVerde, OUTPUT);

  Ethernet.begin(mac, ip); // inicializa ethernet shield
  delay(1000); // espera 1 segundo despues de inicializar
  Serial.print("My IP address: ");
  Serial.println(Ethernet.localIP());
}

//Metodo que se ejecuta en un loop infinito
void loop()
{
  
  //Lee el pin 0 analogo
  sensor = analogRead(0);
  
  //Imprime el valor del pin 0 en el serial
  Serial.println(sensor, DEC);
  
    
  Serial.println("Conectando..");
 
  if (client.connect(server, 80)) {  // Se conecta al servidor
    client.print("GET http://192.168.0.4/archivos.php?sensor="); // Envia los datos utilizando GET
        client.println(" HTTP/1.0");
    client.println("User-Agent: Arduino 1.0");
    client.println();
    Serial.println("Conexion exitosa");
  }
  else
  {
    Serial.println("Falla en la conexion");
  }
  if (client.connected()) {}
  else {
    Serial.println("Desconectado");
  }
  client.stop();
  client.flush();

  //Hacer una lectura cada 10 milisegundos
  delay(100);
}
