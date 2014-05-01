<?php 
$conn = mysql_connect("localhost", "root", "root");

if (!$conn) {
echo "Impossible de se connecter à la base de données : " . mysql_error();
   exit;
}

if (!mysql_select_db('test',$conn)) {
   echo "Impossible de sélectionner la base mydbname : " . mysql_error();
   exit;
}

$sql = "SELECT `message` FROM `utilisator`";

$result = mysql_query($sql);

if (!$result) {
   echo "Impossible d'exécuter la requête ($sql) dans la base : " . mysql_error();
   exit;
}

if (mysql_num_rows($result) == 0) {
   echo "Aucune ligne trouvée, rien à afficher.";
   exit;
}

// Tant qu'une ligne existe, place cette ligne dans la variable $row
// sous la forme d'un tableau associatif.
// Note : Si vous n'attendez qu'une seule ligne, oubliez la boucle
// Note : Si vous utilisez extract($row); dans la boucle suivante
//       vous créerez $userid, $fullname et $userstatus
while ($row = mysql_fetch_assoc($result)) {
   $sortie[]=$row;
}
$jsob=json_encode($sortie);
print($jsob);
mysql_free_result($result);
mysql_close();

?>