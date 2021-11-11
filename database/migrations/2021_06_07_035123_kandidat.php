<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class Kandidat extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('kandidat', function (Blueprint $table) {
            $table->id();
            $table->string('nama')->nullable();
            $table->string('visi')->nullable();
            $table->string('misi')->nullable();
            $table->integer('nomor')->nullable();
            $table->integer('jumlah_suara')->nullable();
            $table->string('path_image')->nullable();
            $table->bigInteger('pemilu_id')->nullable();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('kandidat');
    }
}
