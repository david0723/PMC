#include <Ethernet.h>
#include <EthernetClient.h>
#include <EthernetServer.h>
#include <EthernetUdp.h>
#include <SPI.h>
#include <sha256.h>
#include <mysql.h>
#include <Wire.h>

//Definir la direccion del sensor
int direccionTMP102 = 0x48;

//Definir los pines para los leds
int ledRGB = 8;
int ledRojo = 9;
int ledVerde = 10;

//Definir variables para valores de sonido y proximidad
int sonido;
int movimiento;

//Definir variables para el movimiento
int contadorPersonas;
int contadorSegs;
int mod;
boolean muchoMov;

 
byte mac[] = {
  0x98, 0x4F, 0xEE, 0x00, 0x54, 0x72 };
byte ip[] = { 169,254,6,182 }; // Direccion ip local
char server[]={"169.254.96.47"};
 
EthernetClient client;

 
void setup()
{
  Serial.begin(9600);  // Inicia el serial en 9600 bps
  Wire.begin();
  
  //Inicializar los pines de salida
  pinMode( ledRGB, OUTPUT );
  pinMode( ledRojo, OUTPUT );
  pinMode( ledVerde, OUTPUT );
  contadorPersonas = 0;
  contadorSegs = 0;
  mod = 1;
  muchoMov = false;
  
   Ethernet.begin(mac, ip); // inicializa ethernet shield

  delay(1000); // espera 1 segundo despues de inicializar
  Serial.print("My IP address: ");
  Serial.println(Ethernet.localIP());
  
}

void loop()
{
  
  //Lee los sensores
  movimiento = analogRead(0)/4;
  sonido = analogRead(1);
  
  //Llama al metodo getTemperatura
  float temp = getTemperatura();
  
  //Imprime los datos de los sensores en el serial
  Serial.print("Temperatura: ");
  Serial.print(temp);
  Serial.print(" C / ");
  Serial.print("Sonido: ");
  Serial.print(sonido, DEC);
  Serial.print(" / ");
  Serial.print("Movimiento: ");
  Serial.println(movimiento, DEC);
  
  //Empieza a contar personas
  if( movimiento > 50 )
  {
    contadorPersonas ++;    
  }
  
  //Si es la primera vez que cuento personas, no hay congestion
  if (mod == 1)
  {
    digitalWrite(ledRojo, LOW);
    digitalWrite(ledVerde, HIGH);
  }
  contadorSegs ++;
  
  //Si han pasado 8 segundos, comparo
  if( contadorSegs/(10) == 8 )
  {
    mod = 2; 
    contadorSegs = 0;
    
    //Si han pasado 5 personas aprox, hay mucho movimiento
    if( contadorPersonas/2 >= 5 )
    {
      digitalWrite(ledRojo, HIGH);
      digitalWrite(ledVerde, LOW);
      muchoMov = true;
    }
    //Si han pasado menos de 5 personas, no hay mucho movimiento
    else
    {
      digitalWrite(ledRojo, LOW);
      digitalWrite(ledVerde, HIGH);
      muchoMov = false;
    }
    
    //Si hay mucho movimiento, hay picos en el sonido y la temperatura es mayor a 22C, hay congestion
    if( muchoMov && (sonido > 343 || sonido < 340) && temp > 22 )
    {
      digitalWrite(ledRGB, HIGH);
    }
    //Si no hay picos en las tres variables al mismo tiempo, no hay congestion
    else
    {
      digitalWrite(ledRGB, LOW);
    }
    contadorPersonas = 0;
  }

  
Serial.println("Conectando..");
 
  if (client.connect(server, 80)) {  // Se conecta al servidor
    client.println("GET http://169.254.96.47/archivom.php?v="+int(movimiento)); // Envia los datos utilizando GET
    Serial.println("GET http://169.254.96.47/archivom.php?v="+int(movimiento)); 
    client.println("GET http://169.254.96.47/archivo.php?t="+int(temp)); // Envia los datos utilizando GET
    client.println("GET http://169.254.96.47/archivos.php?s="+int(sonido)); // Envia los datos utilizando GET
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
float getTemperatura()
{
  //Iniciar la lectura de datos
  Wire.requestFrom(direccionTMP102,2);
  byte MSB = Wire.read();
  byte LSB = Wire.read();

  //Calcula la temperatura haciendo corrimiento del output binario
  int sumaTemp = ((MSB << 8) | LSB) >> 4; 

  //Multiplica el valor por el factor de conversion especificado en el datasheet del TMP102
  float temp = sumaTemp*0.0625;
  
  //El sensor tiene un error de +5C en la lectura, aqui se corrige el error
  float tempRecalculada = temp - 5;
  return tempRecalculada;
}
