<?php
//poner en htdocs de XAMPP, nombrado archivo.php
$mysql_servidor = "192.168.0.4";
$mysql_base = "crowdcontrol";
$mysql_usuario = "pmc";
$mysql_clave = "crowdcontrol";
 
date_default_timezone_set("America/Bogota");
$hora = time();
$fechaRegistro="".date("d-m-Y H:i:s", $hora);
$valor = htmlspecialchars($_GET["move"],ENT_QUOTES);
echo "".$fechaRegistro."---".$valor;
// Valida que esten presente todos los parametros
if (($fechaRegistro!="") and ($valor!="")) {
        mysql_connect($mysql_servidor,$mysql_usuario,$mysql_clave) or die("Imposible conectarse al servidor.");
        mysql_select_db($mysql_base) or die("Imposible abrir Base de datos");
        $sql = "insert into movimiento (tiempoRegistro, valor) values ('$fechaRegistro','$valor')";
        mysql_query($sql);
}else {
        echo "paso por aqui";
}
?>
