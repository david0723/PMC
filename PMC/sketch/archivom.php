<?php
//poner en htdocs de XAMPP, nombrado archivo.php
$mysql_servidor = "169.254.96.47";
$mysql_base = "crowdcontrol";
$mysql_usuario = "pmc";
$mysql_clave = "crowdcontrol";
 
date_default_timezone_set("America/Bogota");
$hora = time();
$fechaRegistro="".date("Y-m-d H:i:s", $hora);
$valor = htmlspecialchars($_GET["v"],ENT_QUOTES);
echo "".$fechaRegistro."---".$valor;
// Valida que esten presente todos los parametros
if (($fechaRegistro!="") and ($valor!="")) {
        mysql_connect($mysql_servidor,$mysql_usuario,$mysql_clave) or die("Imposible conectarse al servidor.");
		echo "Conecta";
        mysql_select_db($mysql_base) or die("Imposible abrir Base de datos");
		echo "Entra";
$sql = "insert into `movimiento` (tiempoRegistro, valor) values ('$fechaRegistro','$valor')";
echo "Inserta";
echo $sql;
        mysql_query($sql);
}else {
        echo "paso por aqui";
}
?>
