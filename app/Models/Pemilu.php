<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Pemilu extends Model
{
    use HasFactory;

    public $table = 'pemilu';

    public $fillable = [
        'nama',
        'periode',
        'status',
        'jurusan_id',
        'path_image'
    ];

    protected $casts = [
        'id' => 'integer',
        'nama' => 'string',
        'periode' => 'string',
        'status' => 'string',
        'jurusan_id' =>'integer',
        'path_image' => 'string'
    ];
}
