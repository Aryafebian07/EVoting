<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Kandidat extends Model
{
    use HasFactory;

    public $table = 'kandidat';

    public $fillable = [
        'nama',
        'visi',
        'misi',
        'nomor',
        'jumlah_suara',
        'pemilu_id',
        'path_image'
    ];

    protected $casts = [
        'id' => 'integer',
        'nama'=> 'string',
        'visi' => 'string',
        'misi' => 'string',
        'nomor' => 'integer',
        'jumlah_suara' => 'integer',
        'pemilu_id' => 'integer',
        'path_image' => 'string'
    ];
}
