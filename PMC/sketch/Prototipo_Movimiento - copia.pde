#include <SPI.h>
#include <Ethernet.h>


//Sensor de movimiento
int move;

//LEDs conectado a los pines digitales 8 y 9
int green = 8;
int red = 9;
int yellow = 10;

int contadorPersonas;
int contadorSegs;
int mod;


 
byte mac[] = {
  0x90, 0xA2, 0xDA, 0x0D, 0x96, 0xB4 };
byte ip[] = { 186.80.5.126 }; // Direccion ip local
char server[]={"192.168.0.4"};
 
EthernetClient client;

 
void setup()
{
  Serial.begin(9600);  // Inicia el serial en 9600 bps
  pinMode(green, OUTPUT); //Inicializa el pin 8 como output
  pinMode(red, OUTPUT); //Inicializa el pin 9 como output
  pinMode(yellow, OUTPUT);
  contadorPersonas = 0;
  contadorSegs = 0;
  mod = 1;
  
   Ethernet.begin(mac, ip); // inicializa ethernet shield
  delay(1000); // espera 1 segundo despues de inicializar
  Serial.print("My IP address: ");
  Serial.println(Ethernet.localIP());
  
}

void loop()
{
  // Lee el sensor de movimiento en valores analogos
  move = analogRead(0)/4;
  
  //Si lee valores mayores a X, Y y Z en los tres sensores, hay congestion
   
  
  Serial.println("Paso:");
  Serial.println(move, DEC);
  
  

Serial.println("Conectando..");
 
  if (client.connect(server, 80)) {  // Se conecta al servidor
    client.print("GET http://192.168.0.4/archivom.php?move="); // Envia los datos utilizando GET
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

  // Esperar 100ms para la siguiente lectura
  delay(100);
}
