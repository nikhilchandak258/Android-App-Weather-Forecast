<?php
			$st_add=$_GET["text1"];
			$p_city=$_GET['text2'];
			$p_list=$_GET['text3'];
			$p_degree=$_GET['text4'];

			$xml=simplexml_load_file("https://maps.google.com/maps/api/geocode/xml?address=$st_add,$p_city,$p_list&key=AIzaSyBFuzBVvXwyMKk1sEndCbAPWa1sqRxbdDU") or die("Error: Cannot create object");
				//print_r($xml);
				$p_status=$xml->status;
				if($p_status=="OK")
				{	
					$p_lat=$xml->result->geometry->location->lat;
					$p_lng=$xml->result->geometry->location->lng;
					$p_string = file_get_contents("https://api.forecast.io/forecast/6ac51ed97fdf14ad66a47641584cfb9e/$p_lat,$p_lng?units=$p_degree&exclude=flags");	
						echo $p_string;
				}	
?>