<?php
        header('Content-Type: application/excel');
        header ('Content-Disposition: attachment; filename="newfile.csv"');
        $inputData = array('data1', 'data2', 'data3', 'data4');
        $fp = fopen('php://output', 'w');
        foreach($inputData as $line){
                $val = explode(",", $line);
                fputcsv($fp, $val);
        }
        fclose($fp);
?>
