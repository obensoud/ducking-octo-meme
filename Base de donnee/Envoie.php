<?php 
if(isset($_POST) && !empty($_POST))
{
  extract($_POST);
  $db = mysql_connect('localhost','root','root');
  mysql_select_db('test',$db);
  $sql = " INSERT INTO `test`.`utilisateur` (`message`) VALUES ('$message')";
  mysql_query($sql);
   mysql_close();
}
?>