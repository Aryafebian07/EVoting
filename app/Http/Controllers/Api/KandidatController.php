<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Models\Kandidat;
use App\Models\Voting;

class KandidatController extends Controller
{
    public function semuakandidat(Request $request){
        $kandidats = Kandidat::where('pemilu_id','=',$request->pemilu_id)->get();

        if(Count($kandidats)>0){
            $response["success"] = 1;
            $response["massage"] = 'Data kandidat Ada';
            foreach($kandidats as $kandidat){
                $response["semuakandidat"][] =array(
                    "id" =>$kandidat->id,
                    "nama" =>$kandidat->nama,
                    "visi" =>$kandidat->visi,
                    "misi" =>$kandidat->misi,
                    "nomor" =>$kandidat->nomor,
                    "jumlah_suara" =>$kandidat->jumlah_suara,
                    "path_image" =>$kandidat->path_image,
                    "pemilu_id" =>$kandidat->pemilu_id
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

    public function votekandidat(Request $request){
        $checkvote = Voting::where([['user_id','=',$request->user_id],['pemilu_id','=',$request->pemilu_id]])
            ->first();

        if(!$checkvote){

            $savevoting = DB::table('voting')->insert([
                'user_id' => $request->user_id,
                'pemilu_id'  => $request->pemilu_id,
                'kandidat_id'  => $request->kandidat_id,
                'created_at' => now()
            ]);
            $savesuara = DB::table('kandidat') ->where('id','=',$request->kandidat_id)->increment('jumlah_suara');

            $response["success"] = 1;
            $response["massage"] = 'Selamat Berhasil Vote ';
            echo json_encode($response);
        }else{
            $this->error("Sudah Vote dipemilu ini, Tidak bisa vote lagi");
        }
    }
}
