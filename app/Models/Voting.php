<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Voting extends Model
{
    use HasFactory;
    public $table = 'voting';

    public $fillable = [
        'user_id',
        'pemilu_id',
        'kandidat_id'
    ];

    protected $casts = [
        'id' => 'integer',
        'user_id'=> 'integer',
        'pemilu_id'=> 'integer',
        'kandidat_id' => 'integer'
    ];
}
