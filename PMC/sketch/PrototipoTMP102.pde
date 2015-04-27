//Incluir la libreria para la comunicacion con el sensor
#include <Wire.h>
//Definir la direccion del sensor
int direccionTMP102 = 0x48;

#include <SPI.h>
#include <Ethernet.h>
 
byte mac[] = {
  0x90, 0xA2, 0xDA, 0x0D, 0x96, 0xB4 };
byte ip[] = { 186.80.5.126 }; // Direccion ip local
char server[]={"192.168.0.4"};
 
EthernetClient client;

 
void setup()
{
  Serial.begin(9600);
  //Iniciar la libreria
  Wire.begin();
  Ethernet.begin(mac, ip); // inicializa ethernet shield
  delay(1000); // espera 1 segundo despues de inicializar
  Serial.print("My IP address: ");
  Serial.println(Ethernet.localIP());
}



//Metodo que se ejecuta en un loop infinito
void loop()
{
  //Llama al metodo getTemperatura
  float temp = getTemperatura();
  
  //Imprime los datos en el serial
  Serial.print("Temperatura: ");
  Serial.print(temp);
  Serial.println(" C");

  
  
  Serial.println("Conectando..");
 
  if (client.connect(server, 80)) {  // Se conecta al servidor
    client.print("GET http://192.168.0.4/archivo.php?temp="); // Envia los datos utilizando GET
    client.print(celsius);
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
  
  
  
  
  
  //Hace una lueva lectura cada segundo
  delay(1000);
}

//Metodo que extrae los datos del sensor y retorna un float
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

