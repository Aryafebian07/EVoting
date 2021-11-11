<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class Pemilu extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('pemilu', function (Blueprint $table) {
            $table->id();
            $table->string('nama')->nullable();
            $table->string('periode')->nullable();
            $table->string('status')->nullable();
            $table->bigInteger('jurusan_id')->nullable();
            $table->string('path_image')->nullable();
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
        Schema::dropIfExists('pemilu');
    }
}
