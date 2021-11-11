<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\User;

class UserController extends Controller
{
    public function login(Request $request){
        $user = User::where('email',$request->email)->first();

        if($user){
            if(password_verify($request->password, $user->password)){
                $response["success"] = 1;
                $response["massage"] = 'Selamat datang '.$user->name;
                $response["user"][] =array(
                    "id" =>$user->id,
                    "nim" =>$user->nim,
                    "name" =>$user->name,
                    "alamat" =>$user->alamat,
                    "jenis_kelamin" =>$user->jenis_kelamin,
                    "jurusan_id" =>$user->jurusan_id,
                    "path_image" =>$user->path_image,
    
                );
                echo json_encode($response);
            }else {
                $this->error("Password Salah");
            }
            
        }else {
            $this->error("Email tidak terdaftar");
        }
        
    }

    public function error($message){
        $response["success"] = 0;
        $response["massage"] = $message;
        echo json_encode($response);
    }
}
