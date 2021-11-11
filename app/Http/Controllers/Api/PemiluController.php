<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\Pemilu;

class PemiluController extends Controller
{
    public function semuapemilu(Request $request){
        $pemilus = Pemilu::where('jurusan_id','=',$request->jurusan_id)
        ->orwhere('jurusan_id','=',0)->get();

        if($pemilus){
            $response["success"] = 1;
            $response["massage"] = 'Data Pemilu Ada';
            foreach($pemilus as $pemilu){
                $response["semuapemilu"][] =array(
                    "id" =>$pemilu->id,
                    "nama" =>$pemilu->nama,
                    "periode" =>$pemilu->periode,
                    "status" =>$pemilu->status,
                    "jurusan_id" =>$pemilu->jurusan_id,
                    "path_image" =>$pemilu->path_image,
    
                );
            }
            echo json_encode($response);
        }else {
            $this->error("Data Pemilu tidak ada");
        }
    }

    public function error($message){
        $response["success"] = 0;
        $response["massage"] = $message;
        echo json_encode($response);
    }
}
